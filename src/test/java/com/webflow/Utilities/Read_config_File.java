package com.webflow.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Read_config_File {

	Properties pro;
	
	// this constructor will load the complete file 
	public Read_config_File()
	{
		File soucefile = new File("./"+"//Configuratios//config.properties");
		
		try {
			
			FileInputStream fis = new FileInputStream(soucefile);
			pro = new Properties();
			pro.load(fis);
		}
		catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
		}
	}
	
	
		public String getApplicationBaseurl() {
			String  baseurl = pro.getProperty("baseurl");
			return baseurl;
		}
	
		public String getEmail() {
			String  email = pro.getProperty("email");
			return email;
		}
	
		public String getPassword() {
			String  password = pro.getProperty("password");
			return password;
		}
	
		public String getChromepath() {
			String  chromepath = pro.getProperty("chromepath");
			return chromepath;
		}
		
		public String getFirefoxpath() {
			String  firefoxpath = pro.getProperty("firefoxpath");
			return firefoxpath;
		}
	
		public String getEdgepath() {
			String  edgepath = pro.getProperty("edgepath");
			return edgepath;
		}
		


}
