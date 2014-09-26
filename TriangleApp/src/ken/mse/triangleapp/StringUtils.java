package ken.mse.triangleapp;

import java.util.ArrayList;

public class StringUtils {
	private static final String sCommonDelimiters = " |,|;";
	
	public static float[] ParseStringToNumericValues(String inputString){
		if(inputString == null){
			return new float[0];
		}
		
		//Split string using common delimiters
		String[] splitStr = inputString.split(sCommonDelimiters);
		
		//Count how many are numeric
		ArrayList<String> lstValidNumericStr = new ArrayList<String>();
		for(String str: splitStr){
			if(CheckStringIsPositiveNumeric(str)){
				lstValidNumericStr.add(str);
			}
		}
		
		//Return Numeric Array
		float[] resultArr = new float[lstValidNumericStr.size()];
		for(int i=0; i < lstValidNumericStr.size(); i++){
			resultArr[i] = Float.parseFloat(lstValidNumericStr.get(i));
		}
		
		return resultArr;
	}
	
	public static int CountPositiveNumericValuesInString(String inputString){
		if(inputString == null){
			return 0;
		}
		
		//Split string using common delimiters
		String[] splitStr = inputString.split(sCommonDelimiters);
		
		//Count how many are numeric
		int countNumeric = 0;
		for(String str: splitStr){
			if(CheckStringIsPositiveNumeric(str)){
				countNumeric++;
			}
		}
		
		return countNumeric;
	}
	
	public static boolean CheckStringIsPositiveNumeric(String numericStr){
		if(numericStr == null){
			return false;
		}
		
		//Trim whitespaces from numericStr
		String testString = numericStr.trim();
		
		//Check if string is numeric
		return testString.matches("^[0-9]+(.[0-9]+)?");
	}
}
