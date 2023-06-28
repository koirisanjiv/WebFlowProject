package com.webflow.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;
	
	// Creaetion of LoginPage constructor
	public LoginPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	
	// find elements from the webpage
	@FindBy(xpath = "//input[@placeholder='Email or Username']")
	@CacheLookup
	WebElement txtemail;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	@CacheLookup
	WebElement txtpassword;
	
	@FindBy(xpath = "//button[@class='button button--continue']")
	@CacheLookup
	WebElement btnlogin;
	
	
	//Setting action method for all the web elements
	public void setEmailAddress(String email)
	{
		txtemail.clear();
		txtemail.sendKeys(email);
	}
	
	public void setPassword(String pass)
	{
		txtpassword.clear();
		txtpassword.sendKeys(pass);
	}
	
	public void clickLogin()
	{
		btnlogin.click();
	}


}
