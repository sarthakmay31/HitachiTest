package com.framework.seleniumadapters;

public class DriverManagerFactory {


	public static DriverManager getManager(String broswerName)
	{
		DriverManager driverManager;


		if(broswerName.equalsIgnoreCase("chrome"))
		{
			driverManager= new ChromeDriverManager();
		}

		else
		{
			System.out.println("No particular browser expected....launching chrome");
			driverManager= new ChromeDriverManager();
		}


		return driverManager;

	}



}
