package com.keywordframework.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

public class Constants {
	public static WebDriver driver;
	public static String url;
	public static Actions action;
	public static ChromeOptions options;
	public static AShot ashot;
	public static Screenshot screenshot;
	public static String parentWindowHandle;
	public static String[] expectedSubNAvItem1= {"BUS TICKETS", "rPool New","BUS HIRE","PILGRIMAGES"};
	public static String[] expectedSubNavItems2= {"Bus Tickets", "Cancel", "Reschedule", "Show My Ticket", "Email/SMS", "Hotels Tickets", "Cancel/Refund"};
	public static String[] expectedSubNavItems3= {"Sign In/Sign Up"};
	
	

}
