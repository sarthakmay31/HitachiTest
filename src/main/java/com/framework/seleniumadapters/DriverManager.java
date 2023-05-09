package com.framework.seleniumadapters;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import com.framework.configruler.ConfigRuler;


public abstract class DriverManager {

	protected WebDriver driver;
	protected abstract void startService();

	protected abstract void createDriver();

	public void stopService()
	{

	}

	public WebDriver fetchDriver() {
		if(null==driver)
		{

			DriverExecutables.setAllBrowserExe();
			startService();
			createDriver();
		}


		return driver;
	}


	Platform fetchPlatform() {
		String platformValue=System.getProperty("platform",ConfigRuler.fetchAsString("platform"));
		if(platformValue.equalsIgnoreCase("Windows7") || platformValue.equalsIgnoreCase("windows")|| platformValue.equalsIgnoreCase("6"))
		{
			return Platform.WINDOWS;
		}
		else if(platformValue.equalsIgnoreCase("Windows7") || platformValue.equalsIgnoreCase("windows"))
		{
			return Platform.WINDOWS;
		}
		else if(platformValue.equalsIgnoreCase("Windows8") || platformValue.equalsIgnoreCase("8"))
		{
			return Platform.WIN8;
		}
		else if(platformValue.equalsIgnoreCase("Windows7") || platformValue.equalsIgnoreCase("8.1"))
		{
			return Platform.WIN8_1;
		}
		else if(platformValue.equalsIgnoreCase("Windows10") || platformValue.equalsIgnoreCase("10"))
		{
			return Platform.WIN10;
		}
		else if(platformValue.equalsIgnoreCase("mac"))
		{
			return Platform.MAC;
		}
		else if(platformValue.equalsIgnoreCase("linux"))
		{
			return Platform.LINUX;
		}
		else if(platformValue.equalsIgnoreCase("unix"))
		{
			return Platform.UNIX;
		}
		else {
			return Platform.ANY;
		}
	}

}