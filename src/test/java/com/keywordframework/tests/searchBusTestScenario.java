package com.keywordframework.tests;

import org.testng.annotations.Test;

import com.keywordframework.keywords.Keyword;
import com.keywordframework.utilities.Utils;

public class searchBusTestScenario {
	@Test(description = "verifySearchBusesScenario")
	public void verifysearchBusScenario(){
		
		//Open Browser
		Keyword.openBrowser(Utils.getProperty("browserName"));
		
		//Open URL
		Keyword.openURL(Utils.getProperty("baseURL"));
				
		//From Source
		Keyword.enterText("CSS", "input#src", "nagpur");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Keyword.autoSelectOptioWithText("Chatrapathi, Nagpur");
		
		//To Destination
		Keyword.enterText("CSS", "input#dest", "pune");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Keyword.autoSelectOptioWithText("Swargate, Pune");
		
		//pick onward date of Journey
		Keyword.clickOnElement("XPATH", "//*[@class='fl search-box date-box gtm-onwardCalendar']");
		Keyword.pickdatefromCalender("3","Apr 2020");
		
		//click on search buses
		Keyword.clickOnElement("CSS", "button#search_btn");
	}

	@Test(description = "VerifyCloseBrowserAction")
	public void verifyCloseBrowser() {
		Keyword.closeBrowser();

	}


}
