package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	Credentials userCred;
	
	
	@BeforeTest
	public void setUp() throws InterruptedException{
		
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browserName = prop.getProperty("browser");
		driver= basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred= new Credentials(prop.getProperty("username"),prop.getProperty("password"));
		homePage = loginPage.doLoginMethod(userCred);
		Thread.sleep(5000);
		
	}
	
	@Test(priority= 1, description = "Home page title_ Contacts")
	public void verifyHomePageTitle(){
		
		String title= homePage.getHomePageTitle();
		System.out.println("Home page title is:  "+ title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	
	}
	
	@Test(priority=2, description= "Home header- Marketing Dashboard")
	public void verifyHeader(){
	String header=	homePage.getHeader();
		System.out.println("Header is "+ header);
		Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
		
	}
	
	@Test(priority=3 , description= "account name is The Fix", enabled= true)
	
	public void verifyLoggedUserTest(){

		
	String accountName= homePage.getLoggedAccountName();
	System.out.println(accountName);
	Assert.assertEquals(accountName, "The Fix ");
		
	}
	
	
	@AfterTest
	public void tearDown(){
		
		driver.quit();
	}
	
	
	
	
}
