package helper;

import java.text.DecimalFormat;

public class CurrencyHelper {
	
	static DecimalFormat df = new DecimalFormat("#.##"); 
	
	public static double getRoundedValue(double value){
		return Double.parseDouble(df.format(value));
	}
	
}
