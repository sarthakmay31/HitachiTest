package com.framework.seleniumadapters;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.framework.configruler.ConfigRuler;

public class ChromeDriverManager extends DriverManager {

	private ChromeDriverService cdService;
	private static Logger logger=Logger.getLogger(ChromeDriverManager.class.getName());
	private static String chromeVersion=System.getProperty("chrome.version",ConfigRuler.fetchAsString("chrome.version"));
	private static String headlessFlag=System.getProperty("headless",ConfigRuler.fetchAsString("headless"));
	private static String chromecaps=System.getProperty("chrome.caps.list.of.strings",ConfigRuler.fetchAsString("chrome.caps.list.of.strings"));
	@Override
	protected void startService() {

		String driverExePath=DriverExecutables.fetchChromeDriverExe();

		if(!isServiceInitialized())
		{
			ClassLoader loader =Thread.currentThread().getContextClassLoader();
			try {
				File file=new File(loader.getResource(driverExePath).getFile());
				cdService=new ChromeDriverService.Builder().usingDriverExecutable(file).usingAnyFreePort().build();

			}
			catch(NullPointerException e)
			{
				logger.warning("Chrome Driver exe not found, Using default exe files");
				cdService=ChromeDriverService.createDefaultService();
			}
			try
			{
				cdService.start();
			}
			catch(IOException e)
			{
				logger.warning("Chrome service coulnt start!!!");
			}
		}

	}

	private boolean isServiceInitialized() {
		return null!=cdService;
	}



	@Override
	public void stopService() {
		if(isServiceInitialized() && cdService.isRunning())
		{
			cdService.stop();
		}
		else
		{
			driver.quit();
		}
	}

	@Override
	protected void createDriver() {
		ChromeOptions options = new ChromeOptions();

		if(!chromecaps.isEmpty() && chromecaps.contains("||")) {
			String[] arr = chromecaps.split(Pattern.quote("||"));
			for(int i=0;i<arr.length;i++) {
				String[] caps = arr[i].split(",");
				if(caps[1].equalsIgnoreCase("true")|| caps[1].equalsIgnoreCase("false")) {
					boolean flag = Boolean.valueOf(caps[1]);
					options.setCapability(caps[0], flag);
				}else {
					options.setCapability(caps[0], caps[1]);
				}
			}
		}else if(!chromecaps.isEmpty()) {
			String[] caps=chromecaps.split(",");
			if(caps[1].equalsIgnoreCase("true") || caps[1].equalsIgnoreCase("false")) {
				boolean flag = Boolean.valueOf(caps[1]);
				options.setCapability(caps[0], flag);
			}else {
				options.setCapability(caps[0], caps[1]);
			}
		}
		if(headlessFlag.equalsIgnoreCase("true")) {

			String[] values = ConfigRuler.fetchAsString("headless.options.list.of.strings").split(",");
			options.addArguments(values);
			HashMap<String, Object> chromePrefs = new HashMap<String,Object>();
			chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\Downloads\\");
			options.setExperimentalOption("prefs", chromePrefs);
			options.setCapability(CapabilityType.PLATFORM_NAME, fetchPlatform());
			options.setCapability(CapabilityType.VERSION,chromeVersion);
		}


		driver= new ChromeDriver(options);

	}


}
