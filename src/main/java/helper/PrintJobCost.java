package helper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Cost;
import model.PaperCost;
import model.PaperType;
import model.PrintJob;
import model.PrintType;

public class PrintJobCost {
	
	public static FeedStatus status = FeedStatus.WAITING;
	
	public static Map<PaperType, PaperCost> paperTypeToPaperCost = new HashMap<>();
	
	public static void initializeCostMap(List<PaperCost> paperCostList){
		paperTypeToPaperCost = paperCostList.stream().collect(Collectors.toMap(paper -> paper.getPaperType(), paper -> paper));
		status = FeedStatus.DONE;
	}

	public static double getCost(PrintJob job) {
		
		if(status != FeedStatus.DONE){
			PaperCostFeed.initializeFeed(); 
		}
		
		double jobCost = 0;
		
		try{
			PaperCost paper = paperTypeToPaperCost.get(job.getPaperType());
			
			Cost paperCost = paper.getSingleSide();
			
			if(job.getPrintType().equals(PrintType.DOUBLE)){
				paperCost = paper.getDoubleSide();
			} 
			
			jobCost = paperCost.getBlack() * (job.getNoOfTotalPages() - job.getNoOfColorPages());
			jobCost += paperCost.getColor() * job.getNoOfColorPages();
		} catch (Exception ex){
			jobCost = 0;
		}
		
		return jobCost;
	}
	
	public enum FeedStatus {
		WAITING, DONE
	}
}
