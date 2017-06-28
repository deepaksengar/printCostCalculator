package helper;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

import model.Cost;
import model.PaperCost;
import model.PaperType;

public class PaperCostFeed {
	
	public static void initializeFeed()
    {
    	List<PaperCost> paperCostList = new ArrayList<>();
		String propFileName = "print-cost-config.properties";
		InputStream inputStream = PaperCostFeed.class.getClassLoader().getResourceAsStream(propFileName);
        
        JsonObject jsonObject = Json.createReader(inputStream).readObject();
        JsonArray jsonArray =  jsonObject.getJsonArray("priceList");
        
        populatePaperCostList(paperCostList, jsonArray);
        PrintJobCost.initializeCostMap(paperCostList);
    }

	private static void populatePaperCostList(List<PaperCost> paperCostList, JsonArray jsonArray) {
		for(int i = 0; i < jsonArray.size(); i++){
        	JsonObject object = jsonArray.getJsonObject(i);
        	//System.out.println("Json Obj: " + object);
        	String paper = object.getString("paper");
        	PaperType paperType = PaperType.valueOf(paper.toUpperCase());
        	//System.out.println("paper: " + paper);
        	
        	JsonObject costObj = object.getJsonObject("cost");
        	
        	Cost singleSideCost = createCostObj(costObj.getJsonObject("singleSide"));
        	Cost doubleSideCost = createCostObj(costObj.getJsonObject("doubleSide"));
        	
        	PaperCost paperCostObj = new PaperCost();
        	paperCostObj.setPaperType(paperType);
        	paperCostObj.setSingleSide(singleSideCost);
        	paperCostObj.setDoubleSide(doubleSideCost);
        	
        	paperCostList.add(paperCostObj);
        }
	}

	private static Cost createCostObj(JsonObject costObj) {
		double blackCost = costObj.getJsonNumber("black").doubleValue();
		double colorCost = costObj.getJsonNumber("color").doubleValue();
		Cost cost = new Cost(blackCost, colorCost);
		return cost;
	}
}
