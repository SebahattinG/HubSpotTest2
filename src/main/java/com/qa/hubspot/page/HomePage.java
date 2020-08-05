package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.ElementUtil;


public class HomePage extends BasePage {

	WebDriver driver;
	ElementUtil elementUtil;
	
	//Locators
	
	By header = By.xpath("//i18n-string[contains(text(),'Thanks for choosing HubSpot')]");
	By accountname = By.cssSelector("span.account-name");//Constructor
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		elementUtil= new ElementUtil(driver);
		
		
	}
	
	public String getHomePageTitle(){
		return elementUtil.doGetPageTitle();
	}
	public String getHeader(){
		return elementUtil.doGetText(header);
		
		
	}
	public String getLoggedAccountName() {
		
		return elementUtil.doGetText(accountname);
		
		
	}
	public void clickOnContacts(){
		elementUtil.waitForElementPresent(mainContactsLink);
		elementUtil.doClick(mainContactsLink);
		elementUtil.waitForElementPresent(childContactsLink);
		elementUtil.doClick(childContactsLink);
		
	}
	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}
	
	
	
}
