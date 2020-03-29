package com.keywordframework.keywords;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeOptionsTo {
	public static void addInChrome() {
		
	Constants.options = new ChromeOptions();
	//Constants.options.setPageLoadStrategy(PageLoadStrategy.EAGER);
	Constants.options.addArguments("--start-maximized");
	Constants.options.addArguments("--disable-notifications");
	Constants.options.addArguments("disable-infobars");
	
	}

}
