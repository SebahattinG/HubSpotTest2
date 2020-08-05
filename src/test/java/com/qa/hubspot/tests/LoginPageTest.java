
package com.qa.hubspot.tests;

import java.util.Properties;

import org.apache.http.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;



public class LoginPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	Credentials userCred;
	
	@BeforeTest
	public void setUp(){
		
		basePage = new BasePage();
		prop= basePage.init_properties();
		String browser= prop.getProperty("browser");
		System.out.println(browser);
		driver= basePage.init_driver(browser);
		
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred= new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		
		
	}
	@Test(priority=1 , description= "get page TitleBarHeightState aState Hubspot Login", enabled=true)
	
	public void verifyPageTitleTest() throws InterruptedException{
		//Thread.sleep(5000);
		String title = loginPage.getPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		}
	
	@Test(priority=2, description= "Sign up link is displayed or not", enabled=true)
	public void verifySignUPTest(){
		
		Assert.assertTrue(loginPage.CheckSignUpLink());
		
	}
	@Test(priority=3, description="invalid username and pasword for the login page", enabled=true)
	public void loginTest() {
		HomePage homePage = loginPage.doLoginMethod(userCred);
		String accountName= homePage.getLoggedAccountName();
		System.out.println("logged in account name : "+ accountName);
		Assert.assertEquals(accountName, prop.getProperty("accountname"));

	}
	@DataProvider
	
	public Object[][] getLoginInvalidData(){
		
		Object data[][] = {{"bill@gmail.com","test12345"},
	            {"jimy@gmail.com", " "},
	            {" ","test12345"},
	            {"yummy","yummy"},
	            {" "," "}};
		return data;
		
	}
	@Test(priority=4, dataProvider="getLoginInvalidData", enabled=true)
	public void login_invalidTestCase(String username, String pwd){
		userCred.setAppUserName(username);
		userCred.setAppPassword(pwd);
		loginPage.doLoginMethod(userCred);
		Assert.assertTrue(loginPage.checkLoginErrorMessage());
		
			
	}
	
	@AfterTest  //Browser 
	public void tearDownMethod(){
		
		
		driver.quit();
	}

}
