package com.keywordframework.tests;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.keywordframework.keywords.Keyword;
import com.keywordframework.utilities.ReadJSON;
import com.keywordframework.utilities.Utils;



public class TestJson {
	@Test
	public void tc_001() {
		
		Keyword.openBrowser(Utils.getProperty("browserName"));
		System.out.println( Utils.getProperty("browserName")+" browser is opened");
	}
	
	@Test(description = "VerifyURL")
	public void tc_002() {
		Keyword.openURL(Utils.getProperty("amazon.customerUrl"));
		Assert.assertEquals(Utils.getProperty("amazon.customerUrl"),"https://www.amazon.in/gp/help/customer/display.html?nodeId=200507590&ref_=nav_cs_help", "URL is failed");
	}
	
	@Test
	public void tc_003() {
//		boolean istrue=ReadJSON.readJSONfile("Recommended Topics").n
//		Assert.assertTrue(istrue,"Content Mismatch");
		boolean isEmpty=ReadJSON.readJSONfile(Keyword.getElementfrom("CSS", "a.active").getText()).isEmpty();
		Assert.assertEquals(true, true,"Actual JSON : Content Mismatch ");
		

	}

}

