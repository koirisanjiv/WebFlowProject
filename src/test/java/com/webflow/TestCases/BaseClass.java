package com.webflow.TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.webflow.Utilities.Read_config_File;

public class BaseClass {
	
	Read_config_File readconfig = new Read_config_File();
	public String baseurl = readconfig.getApplicationBaseurl();
	public String email = readconfig.getEmail();
	public String password = readconfig.getPassword();
	
//	public String baseurl = "https://webflow.com/dashboard/login";
//	public String email = "jackmalick1210@yopmail.com";
//	public String password = "Qwer4321!0";
	
	public static WebDriver driver;
	
	public static Logger logger;
	
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chromedriver",readconfig.getChromepath());
			driver = new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.geckodriver",readconfig.getFirefoxpath());
			driver = new FirefoxDriver();
		}
		else if(br.equals("edge"))
		{
			System.setProperty("webdriver.chromedriver",readconfig.getEdgepath());
			driver = new EdgeDriver();
		}
		
		driver.get(baseurl);
		logger = Logger.getLogger("WebflowPorject");
		PropertyConfigurator.configure("log4j.properties");
		
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
}
