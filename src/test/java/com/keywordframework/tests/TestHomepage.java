package com.keywordframework.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.keywordframework.utilities.Utils;
import com.keywordframework.keywords.Constants;
import com.keywordframework.keywords.Keyword;

public class TestHomepage {
	
	@Test(description = "VerifyBrowser : Chrome")
	public void tc_001() {
		
		Keyword.openBrowser(Utils.getProperty("browserName"));
		System.out.println( Utils.getProperty("browserName")+" browser is opened");
	}
	
	@Test(description = "VerifyURL :'https://www.redbus.in/'")
	public void tc_002() {
		Keyword.openURL(Utils.getProperty("baseURL"));
		Assert.assertEquals(Utils.getProperty("baseURL"),"https://www.redbus.in/","Launching of URL "+Utils.getProperty("baseURL")+"  is failed");
		//System.out.println("Base Url: "+ Utils.getProperty("baseURL")+" is successfully launched in chrome browser.");	
	}
	
	@Test(description = "VerifyTitleOfPage")
	public void tc_003() {
		String title=Keyword.getTitleofPage();
		Assert.assertEquals(title,Utils.getProperty("home.title"),"Title Mismatch");
		}
	
	@Test(description = "VerifyLogo")
	public void tc_004() {
		boolean isLogoDisplayed=Keyword.getElementfrom("CSS","[class='redbus-logo home-redirect']").isDisplayed();
		Assert.assertEquals(isLogoDisplayed, true, "Logo Invalid test case failed.");
		//System.out.println("redBus logo is displayed.");
	}
	
	@Test(description = "VerifyMenuItems1")
	public void tc_005() {
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#page_main_header>nav>ul>li>a"));
		
		for(String items:Constants.expectedSubNAvItem1)
		{
			Assert.assertTrue(Keyword.verifyMenuItems("#page_main_header>nav>ul>li>a").contains(items),"Items in Menu are not Verified");
		}
	}
	
	@Test(description = "VerifyMenuItems2")
	public void tc_006() {
		Keyword.getElementfrom("XPATH", "//div[@class='icon-down icon ich dib mbh']").click();
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#hmb>div>ul>li"));
		
		//Second Approach
		boolean isequal=Keyword.verifyMenuItems("#hmb>div>ul>li").containsAll(Arrays.asList(Constants.expectedSubNavItems2));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
	}
	
	@Test(description = "VerifyMenuItems3")
	public void tc_007() {
		Keyword.getElementfrom("CSS", "#sign-in-icon-down").click();
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li"));
		
		//Second Approach
		boolean isequal=Keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li").containsAll(Arrays.asList(Constants.expectedSubNavItems3));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
	}
	
	@Test(description = "VerifyMouseHoverAction")
	public void tc_008(){
		WebElement menuTab=Keyword.mouseHoverOnElement("CSS","a#redBus");
		System.out.println("The tooltip/infotip on mouse hover is verified.");
		System.out.println("MenuTab text is    :   "+menuTab.getText() + "  MenuTab atrritube is: "+ menuTab.getAttribute("title"));
		System.out.println("On mouse hover, the tooltip/infotip is displayed the information (**Info/Hover Box)about tab/element.");
	}
		
	@Test(description = "VerifyElementToBeClickable")
	public void tc_009() {
		Keyword.clickOnElement("CSS","a#redBus");
		System.out.println("Click event is successfully executed.");
	}
	
	@Test(description = "VerifyElementToBeClickableNavigateToParentWindow")
	public void tc_010() {
		Keyword.clickOnElementNavigateBackToParentWindow("CSS","a#cars");
		System.out.println("Click event is successfully executed.");
	}
	
		@Test(description = "VerifyWindowHandle")
	public void tc_011() {
		Keyword.handleSwitchToNewWindow("XPATH", "//a[@href='/info/redcare']");
		Keyword.clickOnElement("CSS", "div.modalCloseSmall>i");
		System.out.println("Child Window Title: " +Constants.driver.getTitle());
		Constants.driver.close();
		Constants.driver.switchTo().window(Constants.parentWindowHandle);
		System.out.println("Parent Window Title: " +Constants.driver.getTitle());
	}
	
	@Test(description = "VerifyTextBoxLabel : for src textbox")
	public void tc_012() {
		String labelText=Keyword.getElementfrom("FROM").getText();
		Assert.assertEquals(labelText, "FROM", "For given textbox gettext is invalid.");
	}
	
	@Test(description = "VerifyFromSourceInputBox :By Entering Text")
	public void tc_013() {
		Keyword.enterText("CSS", "input#src", "nagpur");
	}
	
	@Test(description = "VerifyFromSourceInputBox : By selecting Text from autopopup list")
	public void tc_014() {
		Keyword.enterText("CSS", "input#src", "nagpur");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Keyword.autoSelectOptioWithText("Chatrapathi, Nagpur");
	}
	
	@Test(description = "VerifyTextBoxLabel : for dest textbox")
	public void tc_015() {
		String labelText=Keyword.getElementfrom("XPATH", "//label[contains(text(),'TO')]").getText();
		Assert.assertEquals(labelText, "TO", "For given textbox gettext is invalid.");
	}
	
	@Test(description = "VerifyToDestinationInputBox :By Entering Text")
	public void tc_016() {
		Keyword.enterText("CSS", "input#dest", "pune");
	}
	
	@Test(description = "VerifyToDestinationInputBox : By selecting Text from autopopup list", dependsOnMethods = "tc_013")
	public void tc_017() {
		Keyword.enterText("CSS", "input#dest", "pune");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Keyword.autoSelectOptioWithText("Swargate, Pune");
	}
	
	@Test(description = "Verify: PickDateFromCalender for Onward Journey")
	public void tc_018() {
		Keyword.clickOnElement("XPATH", "//*[@class='fl search-box date-box gtm-onwardCalendar']");
		Keyword.pickdatefromCalender("3","Apr 2020");
	}

	@Test(description = "VerifyScreenshot : Redbus Logo Screen")
	public void tc_019() {
		Keyword.getElementfrom("CSS", "a.redbus-logo");
		Keyword.takeAScreenshot();

	}
		
	@Test(description = "VerifyCloseBrowserAction")
	public void tc_020() {
		Keyword.closeBrowser();

	}
	
	
}
