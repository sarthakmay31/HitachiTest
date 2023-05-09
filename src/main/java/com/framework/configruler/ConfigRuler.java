package com.framework.configruler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.framework.configruler.exceptions.PropertyFileNotFoundException;

public final class ConfigRuler {

	private static Properties props;
	private static Map<String, Properties> configMap= new HashMap<String, Properties>();
	private static final String PROPERTIES_EXT= ".properties";
	private static final String PROPERTIES_FOLDER= "./properties";
	private static Logger logger=Logger.getLogger(ConfigRuler.class.getName());


	private ConfigRuler() {


	}


	private static Properties getInstance(String propertyFileName)
	{
		Properties props=null;
		if(configMap.size()==0)
		{
			props=propertiesloader(propertyFileName);
			configMap.put(propertyFileName, props);
			return props;
		}
		for(Map.Entry<String, Properties> entry:configMap.entrySet())
		{
			if(entry.getKey().equals(propertyFileName))
			{
				return entry.getValue();
			}
		}


		props=propertiesloader(propertyFileName);
		configMap.put(propertyFileName, props);
		return props;

	}


	private static Properties getInstance() {
		if(props==null)
		{
			props=propertiesloader();
			return props;
		}
		else
		{
			return props;

		}
	}

	private static Properties propertiesloader() {

		Properties props= new Properties();
		File propertiesFiles=null;

		ClassLoader loader=Thread.currentThread().getContextClassLoader();

		try {
			propertiesFiles=new File(loader.getResource(PROPERTIES_FOLDER).getFile());

		}
		catch(NullPointerException e)
		{
			throw new PropertyFileNotFoundException("No properties file inside properties folder");
		}
		try
		{
			File[] listOfFiles=propertiesFiles.listFiles();
			for(File file:listOfFiles)
			{
				if(file.isFile()&& file.getName().endsWith(PROPERTIES_EXT))
				{
					props.load(new FileInputStream(propertiesFiles+File.separator+file.getName()));
				}
			}
		}
		catch(IOException e)
		{
			logger.warning("Not able to load property");
		}
		return props;


	}

	private static Properties propertiesloader(String propertyFile)
	{
		Properties props=new Properties();
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		InputStream is=loader.getResourceAsStream(PROPERTIES_FOLDER+File.separator+propertyFile+PROPERTIES_EXT);
		try {
			props.load(is);
		}
		catch(NullPointerException e)
		{
			throw new PropertyFileNotFoundException("No properties file inside properties folder");
		}
		catch(IOException e)
		{
			logger.warning("Not able to load property");
		}
		return props;
	}


	public static String fetchAsString(String key)
	{
		return getInstance().getProperty(key);
	}

	public static int fetchAsInt(String key)
	{
		return Integer.parseInt(getInstance().getProperty(key));
	}
	public static String fetchAsString(String fileName,String key)
	{
		return getInstance(fileName).getProperty(key);
	}

	public static int fetchAsInt(String fileName,String key)
	{
		return Integer.parseInt(getInstance(fileName).getProperty(key));
	}
	public static String fetchAsString(String environment,String propertyFile,String key)
	{
		Properties props=new Properties();
		ClassLoader loader=Thread.currentThread().getContextClassLoader();
		InputStream inputStream=loader.getResourceAsStream(PROPERTIES_FOLDER+File.separator+environment+File.separator+PROPERTIES_EXT);
		String value=null;
		try
		{
			props.load(inputStream);
			value=props.getProperty(key);
			props.clear();
			inputStream.close();

		}
		catch(IOException e)
		{
			logger.log(Level.SEVERE,"an exception was thrown", e);
		}
		return value;
	}



}
