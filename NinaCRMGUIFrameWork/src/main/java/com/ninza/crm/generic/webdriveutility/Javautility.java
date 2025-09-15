package com.ninza.crm.generic.webdriveutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Javautility {

	public String getCurrentdate() {
		Date date=new Date();	// import from java.util package
		
		//Format Date
		 SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		String CurrentDate = sim.format(date);
		 Calendar cal = sim.getCalendar();
		 return CurrentDate;
		 
	}


public String togetRequiredDate(int exDate) {
	Date date=new Date();	// import from java.util package
	
	//Format Date
	 SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
	 sim.format(date);
	 Calendar cal = sim.getCalendar();
	 
	 
	cal.add(Calendar.DAY_OF_MONTH,exDate);
	String expectedDate = sim.format(cal.getTime());
	return expectedDate;
	}
	
 public int getRandomNumber() {
	 Random rd=new Random();
	int randomNumber = rd.nextInt(1000);
	return randomNumber;
 }
	 
}

