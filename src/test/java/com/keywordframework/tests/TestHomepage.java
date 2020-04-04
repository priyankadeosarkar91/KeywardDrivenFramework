package com.keywordframework.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.keywordframework.utilities.Custom_Listeners;
import com.keywordframework.utilities.Utils;
import com.testpractice.test2;
import com.keywordframework.keywords.Constants;
import com.keywordframework.keywords.Keyword;

@Listeners(Custom_Listeners.class)
public class TestHomepage {
	static final Logger log=Logger.getLogger(test2.class);
	@Test(description = "VerifyBrowser : Chrome")
	public void tc_001() {
		log.info("******************* Starting test cases execution **********************");
		Keyword.openBrowser(Utils.getProperty("browserName"));
		log.info(Utils.getProperty("browserName")+" browser is opened");
	}
	
	@Test(description = "VerifyURL :'https://www.redbus.in/'")
	public void tc_002() {
		log.info("******************* Launching chrome broswer **********************");
		Keyword.openURL(Utils.getProperty("baseURL"));
		Assert.assertEquals(Utils.getProperty("baseURL"),"https://www.redbus.in/","Launching of URL "+Utils.getProperty("baseURL")+"  is failed");
	}
	
	@Test(description = "VerifyTitleOfPage")
	public void tc_003() {
		log.info("******************* Verify Title Of Page **********************");
		String title=Keyword.getTitleofPage();
		Assert.assertEquals(title,Utils.getProperty("home.title"));
		log.assertLog(false, "Tittle Mismatch.");
		}
	
	@Test(description = "VerifyLogo")
	public void tc_004() {
		log.info("******************* Verify Logo **********************");
		boolean isLogoDisplayed=Keyword.getElementfrom("CSS","[class='redbus-logo home-redirect']").isDisplayed();
		Assert.assertEquals(isLogoDisplayed, true);
		log.assertLog(false,"Logo Invalid test case failed.");
	}
	
	@Test(description = "VerifyMenuItems1")
	public void tc_005() {
		log.info("******************* Verify Menu Items1 **********************");
		
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#page_main_header>nav>ul>li>a"));
		
		for(String items:Constants.expectedSubNAvItem1)
		{
			Assert.assertTrue(Keyword.verifyMenuItems("#page_main_header>nav>ul>li>a").contains(items),"Items in Menu are not Verified");
		}
		log.assertLog(false,"Menu items1 are not verified.");
	}
	
	@Test(description = "VerifyMenuItems2")
	public void tc_006() {
		log.info("******************* Verify Menu Items2 **********************");
		Keyword.getElementfrom("XPATH", "//div[@class='icon-down icon ich dib mbh']").click();
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#hmb>div>ul>li"));
		
		//Second Approach
		boolean isequal=Keyword.verifyMenuItems("#hmb>div>ul>li").containsAll(Arrays.asList(Constants.expectedSubNavItems2));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
		log.assertLog(false,"Menu items2 are not verified.");

	}
	
	@Test(description = "VerifyMenuItems3")
	public void tc_007() {
		log.info("******************* Verify Menu Items3 **********************");
		
		Keyword.getElementfrom("CSS", "#sign-in-icon-down").click();
		System.out.println("Actual Menu Items :  " + Keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li"));
		
		//Second Approach
		boolean isequal=Keyword.verifyMenuItems("#sign-in-icon-down>div>ul>li").containsAll(Arrays.asList(Constants.expectedSubNavItems3));
		Assert.assertTrue(isequal,"Items in Menu are not Verified");
		log.assertLog(false,"Menu items3 are not verified.");

	}
	
	@Test(description = "VerifyMouseHoverAction")
	public void tc_008(){
		log.info("******************* Verify Mouse Hover Action **********************");
		
		WebElement menuTab=Keyword.mouseHoverOnElement("CSS","a#redBus");
		System.out.println("The tooltip/infotip on mouse hover is verified.");
		System.out.println("MenuTab text is    :   "+menuTab.getText() + "  MenuTab atrritube is: "+ menuTab.getAttribute("title"));
		System.out.println("On mouse hover, the tooltip/infotip is displayed the information (**Info/Hover Box)about tab/element.");
	}
	@Test(description = "VerifyElementToBeClickable  : redbus")
	public void tc_009() {
		log.info("******************* Verify Element To Be Clickable : redbus**********************");
		
		Keyword.clickOnElement("CSS","a#redBus");
		
		System.out.println("Click event is successfully executed.");
		if(Constants.parentWindowHandle.equalsIgnoreCase(Constants.driver.getWindowHandle())){ //Check with current window handle
			Constants.driver.navigate().back();
		}
	}
	
	@Test(description = "VerifyElementToBeClickable  : rpool")
	public void tc_010() {
		log.info("******************* Verify Element To Be Clickable  : rpool**********************");
		
		Keyword.clickOnElement("CSS","a#cars");
		
		System.out.println("Click event is successfully executed.");
		if(Constants.parentWindowHandle.equalsIgnoreCase(Constants.driver.getWindowHandle())){ //Check with current window handle
			Constants.driver.navigate().back();
		}
	}
	
	@Test(description = "VerifyWindowHandle : perform action on new window >> close pop up")
	public void tc_011() {
			log.info("******************* Verify Window Handle **********************");
		Keyword.clickOnElementhandleSwitchToNewWindow("XPATH", "//a[@href='/info/redcare']");
		Keyword.getElementfrom("CSS", "div.modalCloseSmall>i").click(); //close popoup
		System.out.println("Child Window Title: " +Constants.driver.getTitle());
		Constants.driver.close();
		Constants.driver.switchTo().window(Constants.parentWindowHandle);
		System.out.println("Parent Window Title: " +Constants.driver.getTitle());
	}
	
	@Test(description = "VerifyTextBoxLabel : for src textbox")
	public void tc_012() {
		log.info("******************* VerifyTextBoxLabel : for src textbox **********************");
		String labelText=Keyword.getElementfrom("FROM").getText();
		Assert.assertEquals(labelText, "FROM", "For given textbox gettext is invalid.");
		log.assertLog(false, "InValid Textbox");
	}
	
	@Test(description = "VerifyFromSourceInputBox :By Entering Text")
	public void tc_013() {
		log.info("******************* VerifyFromSourceInputBox :By Entering Text **********************");
		Keyword.enterText("CSS", "input#src", "nagpur");
		log.assertLog(false, "InValid text box verified by sending keys.");
	}
	
	@Test(description = "VerifyFromSourceInputBox : By selecting Text from autopopup list")
	public void tc_014() {
		log.info("******************* VerifyFromSourceInputBox : By selecting Text from autopopup list **********************");
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
		log.info("******************* VerifyTextBoxLabel : for dest textbox **********************");
		String labelText=Keyword.getElementfrom("XPATH", "//label[contains(text(),'TO')]").getText();
		Assert.assertEquals(labelText, "TO", "For given textbox gettext is invalid.");
		log.assertLog(false, "InValid Textbox ready to get text");
		
	}
	
	@Test(description = "VerifyToDestinationInputBox :By Entering Text")
	public void tc_016() {
		log.info("******************* VerifyToDestinationInputBox :By Entering Text **********************");
		
		Keyword.enterText("CSS", "input#dest", "pune");
		log.assertLog(false, "Invalid text box verified by sending keys.");
		
	}
	
	@Test(description = "VerifyToDestinationInputBox : By selecting Text from autopopup list")
	public void tc_017() {
		log.info("******************* VerifyToDestinationInputBox : By selecting Text from autopopup list **********************");
		
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
		log.info("******************* Verify: PickDateFromCalender for Onward Journey **********************");
		Keyword.clickOnElement("XPATH", "//*[@class='fl search-box date-box gtm-onwardCalendar']");
		Keyword.pickdatefromCalender("3","Apr 2020");
	}

	
	@Test(description = "VerifyScreenshot : Redbus Logo Screen")
	public void tc_019() {
		log.info("******************* VerifyParticularPageScreenshot : Redbus Logo **********************");
		
		Keyword.getElementfrom("CSS", "a.redbus-logo");
		Keyword.takeAScreenshot("tc_019");
	}
	
	@Test(description = "VerifyFullPageScreenshot : Convenience on the go-SMS Link Screen")
	public void tc_020() {
		log.info(" *************************** VerifyFullPageScreenshot : Convenience on the go-SMS Link Screen  *************************");
		Keyword.getElementfrom("CSS", "div#platforms_div ");
		Keyword.fullpagescreenshot("tc_020");
		log.assertLog(false, "Failed to take screenshot of redbus Logo");

	}
	
	@Test(description = "VerifyScreenshot-forFailedTC")
	public void tc_021() {
		log.info("******************* VerifyScreenshot-forFailedTC ********************** ");
		
		Assert.assertEquals(false, true);
		log.assertLog(false, "Test case failed.");
	}
	
		
	@Test(description = "VerifyCloseBrowserAction")
	public void tc_022() {
		log.info("******************* Close Browser **********************");
		
		Keyword.closeBrowser();

	}
	
	
}
