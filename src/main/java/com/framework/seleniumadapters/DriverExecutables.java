package com.framework.seleniumadapters;

import com.framework.configruler.ConfigRuler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverExecutables {

	private static String exeChromeDriver="./drivers/chromedriver.exe";
	public static final String TEMP_DIR=System.getProperty("java.io.tmpdir");
	public static String proxyURL=ConfigRuler.fetchAsString("proxy.url")+";"+ConfigRuler.fetchAsString("proxy.port");
	public static String chromeVersion=System.getProperty("chrome.version")+";"+ConfigRuler.fetchAsString("chrome.version");

	protected static void putChromeDriverExe(int version)
	{
		exeChromeDriver=String.format(exeChromeDriver, version);
	}
	protected static String fetchChromeDriverExe()
	{
		return exeChromeDriver;
	}


	protected static void setAllBrowserExe() {

		String browserName= System.getProperty("browser",ConfigRuler.fetchAsString("browser"));
		if(browserName.equalsIgnoreCase("chrome")) {
			if(chromeVersion.isEmpty()) {
				WebDriverManager.chromedriver().version(chromeVersion).proxy(proxyURL).setup();
			}else {
				WebDriverManager.chromedriver().proxy(proxyURL).setup();
			}
		}


	}

}
