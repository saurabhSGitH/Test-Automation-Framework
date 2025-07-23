package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	//read properties file
	public static String readProperty(Env env, String propertyName)  {
		System.out.println(System.getProperty("user.dir"));
		File propfile = new File(System.getProperty("user.dir") +"\\config\\"+env+".properties" );
		FileReader fileReader = null;
		Properties properties = new Properties();
		try {
			fileReader = new FileReader(propfile);
			properties.load(fileReader);
		}catch ( FileNotFoundException e) {
			System.out.println("File not found at:" + propfile.getAbsolutePath());
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		String value = properties.getProperty(propertyName.toUpperCase());
        return value;
	}
}
