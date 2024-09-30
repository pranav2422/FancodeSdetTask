package com.fancode.methodimplementation;

import com.fancode.constants.Constants;

public class ValidationMethods {
	
	// Mrthod to check is city isFancode or not bas on lat and lng
	
	public static boolean IsFancodeCity(double lat, double lng) 
	{
		if (lat >= Constants.LAT_STARTPOINT && lat <= Constants.LAT_ENDPOINT && lng >= Constants.LONG_STARTPOINT
				&& lng <= Constants.LONG_ENDPOINT) {
		
			return true;
		}
		return false;
	}

	
	//method to calculate percentage of task completion
	
	public static double CalculatePercentage (double completedTask, double totalTask) {
		return (completedTask/totalTask) * 100;
	}
	
	
}
