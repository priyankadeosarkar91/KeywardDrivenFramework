package com.keywordframework.keywords;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.sound.midi.SysexMessage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.util.concurrent.FluentFuture;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keyword{

/** 
 * This method is used to open browser in either of following "CHROME", "Firefox", Internet browser "IE", 
 * "Opera","HTMLUnit", "Safari".
 * @param browserName {@code String}
 * @author Priyanka
 *
 */
	public static void openBrowser(String browserName) {
		ChromeOptionsTo.addInChrome();
		switch (browserName) {

		case "Chrome":
			WebDriverManager.chromedriver().setup();
			Constants.driver = new ChromeDriver(Constants.options);
			break;

		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			Constants.driver = new FirefoxDriver();
			break;

		case "IE":
			WebDriverManager.iedriver().setup();
			Constants.driver = new InternetExplorerDriver();
			break;

		case "Opera":
			WebDriverManager.operadriver().setup();
			Constants.driver = new OperaDriver();
			break;

		case "HTMLUnit":
			Constants.driver = new HtmlUnitDriver();
			break;

		case "Safari":
			break;

		default:
			System.out.println("Invalid browser :  " + browserName);
			break;
		}
		// Manage Open browser with maximized mode
		//constants.driver.manage().window().maximize();
		
	}
	
	/**This Method is used to open Url as specified in String.
	 * @param url{@code String}
	 * @author Priyanka
	 */
	public static void openURL(String url) {
		Constants.driver.get(url);
	}
	
	public static String getCurrentURL() {
		return Constants.driver.getCurrentUrl();
	}
	
	
	/**This Method presented title of the webpage.
	 * @author Priyanka
	 * @return 
	 */
	public static String getTitleofPage() {
		String title=Constants.driver.getTitle();
		return title;
	}
	
	
	/** This method is used to get specific web element as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. This webelement to perform different action.
	 *
	 * @param locatorType {@code String}
	 * @param locatorval {@code String}
	 * @return {@link WebElement}
	 * @author Priyanka
	 */
	public static WebElement getElementfrom(String locatorType,String locatorval) {
		WebElement element = null;
		switch (locatorType) {
		case "CLASSNAME":
			element=Constants.driver.findElement(By.className(locatorval));
			break;
		case "CSS":
			element=Constants.driver.findElement(By.cssSelector(locatorval));
			break;
		case "ID":
			element=Constants.driver.findElement(By.id(locatorval));
			break;
		case "LINK_TEXT":
			element=Constants.driver.findElement(By.linkText(locatorval));
			break;
		case "NAME":
			element=Constants.driver.findElement(By.name(locatorval));
			break;
		case "PARTIAL_LINK_TEXT":
			element=Constants.driver.findElement(By.partialLinkText(locatorval));
			break;
		case "TAG_NAME":
			element=Constants.driver.findElement(By.tagName(locatorval));
			break;
		case "XPATH":
			element=Constants.driver.findElement(By.xpath(locatorval));
			break;
			
		default:
			System.err.println("Invalid Locator Type to be selected : " + locatorType);
			break;
		}
		return element;
	}
	

	/** This method is used to get specific web element as required with parameter elementName {@code String}, 
	 *  This webelement is used to search with contain text method perform different action.
	 * 
	 * @param elementName {@code String}
	 * @return {@link WebElement}
	 * @author Priyanka
	 */
	public static WebElement getElementfrom(String elementName) {
		WebElement elemnt=Constants.driver.findElement(By.xpath("//*[contains(text(),'"+elementName+"')]"));
		return elemnt;
	}
	
	/**
	 * This method is used to enter text if text-box is available.
	 * {@code SendKeys} for send text in text-box
	 * @param locatorType
	 * @param locatorval
	 * @param textval
	 */
	public static void enterText(String locatorType,String locatorval, String textval) {
		
		WebElement textbox=getElementfrom(locatorType, locatorval);
		textbox.clear();
		textbox.sendKeys(textval);
	}

	/** This Method is used to verify if menu(nav) items present. actual menu items stored in {@code List}.
	 * {@code ArrayList} used to get element of list in the form of array to compare with expected menu items.
	 * 
	 * @param subNavItems {@code List}
	 * @param actualSubNavItems {@code ArrayList}
	 * @param itr {@code Iterator}
	 * @return {@link ArrayList} actualSubNavItems
	 * @author Priyanka
	 */
	public static ArrayList<String> verifyMenuItems(String locatorval) {
		List<WebElement> subNavItems=Constants.driver.findElements(By.cssSelector(locatorval));
		ArrayList<String> actualSubNavItems=new ArrayList<String>();
		Iterator<WebElement> itr=subNavItems.iterator();
		while(itr.hasNext()) {
			actualSubNavItems.add(itr.next().getText());
		}
		return actualSubNavItems;
	}
	
	
	/**This method presents click on element action and perform action, back to parent window 
	 * as required with parameter locatorType {@code String}, locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 *
	 */
	public static void clickOnElement(String locatorType, String locatorval) {
		Constants.parentWindowHandle=Constants.driver.getWindowHandle();
		System.out.println("Parent Window handle : " + Constants.parentWindowHandle);
		
		WebElement element=getElementfrom(locatorType, locatorval);
		element.click();
		Set <String> childWindow=Constants.driver.getWindowHandles();
		System.out.println("child Window handles : " + childWindow);
		
		for(String handle:childWindow) {
			if(!Constants.parentWindowHandle.equalsIgnoreCase(handle)) {
				Constants.driver.switchTo().window(handle);
				System.out.println("Child Window Title: " +Constants.driver.getTitle());
				Constants.driver.close();
				Constants.driver.switchTo().window(Constants.parentWindowHandle);
				System.out.println("Parent Window Title: " +Constants.driver.getTitle());
			}
		}
	}
	
	/**This method presents multiple window handle when click on element action 
	 * as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 * @author Priyanka
	 */
	public static void clickOnElementhandleSwitchToNewWindow(String locatorType, String locatorval) {
		Constants.parentWindowHandle=Constants.driver.getWindowHandle();
		System.out.println("Parent Window handle : " + Constants.parentWindowHandle);
		WebElement element=getElementfrom(locatorType, locatorval);
		element.click();
		
		Set <String> childWindow=Constants.driver.getWindowHandles();
		System.out.println("child Window handles : " + childWindow);
		
		for(String handle:childWindow) {
			if(!Constants.parentWindowHandle.equalsIgnoreCase(handle)) {
				Constants.driver.switchTo().window(handle);
			}
		}
	}
		
	/**
	 * This method is used to get text from auto pop-up List while entering key text @param enterText.
	 * @param enterText
	 */
	public static void autoSelectOptioWithText(String enterText) {
		
		List<WebElement> optionToSelect=Constants.driver.findElements(By.cssSelector("ul.autoFill>li"));
//		ArrayList<String> al =new ArrayList<String>();
//		Iterator<WebElement> itr1=optionToSelect.iterator();
//		while(itr1.hasNext()) {
//			 al.add(itr1.next().getText());
//		}
//		System.out.println(al);
		 for(WebElement selectOptionFromList: optionToSelect) {
			if(selectOptionFromList.getText().equals(enterText)) {
				selectOptionFromList.click();
				break;
				}
		 }
	}

	/**This method perform action of mouse Hover On Element as required with parameter locatorType {@code String}, 
	 * locatorval {@code String}. 
	 * 
	 * @param locatorType
	 * @param locatorval
	 * {@link Actions}
	 * @return 
	 */
	public static WebElement mouseHoverOnElement(String locatorType, String locatorval) {
		Constants.action=new Actions(Constants.driver);
		WebElement tab=getElementfrom(locatorType, locatorval);
		Constants.action.moveToElement(tab);
		//action.perform();
		return tab;
	}
	
	/**
	 * This Method presents to pick date-month-year from the calendar.
	 * required parameters are:
	 * @param pickDate
	 * @param pickMonth
	 */
	public static void pickdatefromCalender(String pickDate, String pickMonth) {
		// Select desired month
		String currentMonth= Constants.driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='monthTitle']")).getText();
		if(!currentMonth.equalsIgnoreCase(pickMonth))
		{
			do{
				Constants.driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='next']")).click();
				}while(!Constants.driver.findElement(By.xpath("//div[@id='rb-calendar_onward_cal']//td[@class='monthTitle']")).getText().equalsIgnoreCase(pickMonth));
			}
		
		//This are the columns of the from date picker table
		List<WebElement> columns = Constants.driver.findElements(By.xpath("//div[@id='rb-calendar_onward_cal']//td"));
		
		//This is from date picker table
		for (WebElement cell: columns) {
			if (cell.getText().equals(pickDate)) {
				cell.click();
				break;
				}
			}
	}

	/**
	 * 
	 */
	public static void takeAScreenshot(String testMethodName) {
		File imgSrc = ((TakesScreenshot)Constants.driver).getScreenshotAs(OutputType.FILE);
	    String timespan =  new SimpleDateFormat("dd_MM_yy").format(new Date());
	    String timespan1 =  new SimpleDateFormat("ddMM_hhmm").format(new Date());
	    File imgDest = new File("D:\\java PDD\\Framework_Keyword_redbus\\Screenshots\\"+timespan+"\\SC_"+testMethodName+
	    		"_"+timespan1+".png");
	    try {
			FileUtils.copyFile(imgSrc, imgDest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void fullpagescreenshot(String testMethodName) {
		Constants.screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(Constants.driver);
		String timespan =  new SimpleDateFormat("dd_MM_yy").format(new Date());
	    String timespan1 =  new SimpleDateFormat("ddMM_hhmm").format(new Date());
	    try {
			ImageIO.write(Constants.screenshot.getImage(), "PNG", new File("D:\\java PDD\\Framework_Keyword_redbus\\FullPageSreenshot\\"+timespan+
					"\\FSC_"+testMethodName+"_"+timespan1+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeBrowser() {
		Constants.driver.close();
	}
}
