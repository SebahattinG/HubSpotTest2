package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.ContactsPage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.ExcellUtil;

public class ContactsPageTest {
	
	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	Credentials userCred;
	
	@BeforeMethod
	
	public void SetUp(){
		
		basePage = new BasePage();
		prop=basePage.init_properties();
		String browserName=prop.getProperty("browser");
		driver=basePage.init_driver(browserName);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		homePage= loginPage.doLoginMethod(userCred);
		contactsPage=homePage.goToContactsPage();
		}
	
	@Test
	public void verifyContactsPageTitle(){
		String title= contactsPage.getContactsPageTitle();
		System.out.println(title);
		Assert.assertEquals(title, "Contacts");
		
	}
	@DataProvider
	public Object [][] getContactsTestData(){
		Object [][] data= ExcellUtil.getTestData("contacts");
		return data;
		
		
	}
	@Test(dataProvider="getContactsTestData")
	public void createContactsTest(String email, String firstname, String lastname, String jobtitle){
		contactsPage.createNewContact(email, firstname, lastname, jobtitle);
	}
	
@AfterTest
public void tearDown(){
	driver.quit();
}
}
