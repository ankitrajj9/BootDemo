package com.ankit.demo;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class CommonMethod {
	private static String os = null;
	public void errorMethod(ModelMap modelMap) {
		
	}
	public static String getOsName() {
		if(os == null) {
			os = System.getProperty("os.name");
		}
		return os;
	}
	public static boolean isWindows() {
		return getOsName().startsWith("Windows");
	}
	public static int calculateAge(LocalDate dob)   
	{  
	//creating an instance of the LocalDate class and invoking the now() method      
	//now() method obtains the current date from the system clock in the default time zone      
	LocalDate curDate = LocalDate.now();  
	//calculates the amount of time between two dates and returns the years  
	if ((dob != null) && (curDate != null))   
	{  
	return Period.between(dob, curDate).getYears();  
	}  
	else  
	{  
	return 0;  
	}
	}

}
