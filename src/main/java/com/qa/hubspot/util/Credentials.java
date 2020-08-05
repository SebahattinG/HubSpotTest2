package com.qa.hubspot.util;

public class Credentials {
	
	String appUserName;
	String appPassword;
	
	
	public Credentials(String appUsername, String appPassword) {
	this.appPassword=appPassword;
	this.appUserName=appUsername;
	}


	public String getAppUserName() {
		return appUserName;
	}


	public void setAppUserName(String appUserName) {
		this.appUserName = appUserName;
	}


	public String getAppPassword() {
		return appPassword;
	}


	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}
	
	
	

}
