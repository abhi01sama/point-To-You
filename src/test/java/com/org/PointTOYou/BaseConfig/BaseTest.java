package com.org.PointTOYou.BaseConfig;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.org.PointTOYou.loginPage.LoginPage;

public class BaseTest {
	protected com.org.WebutilLayer.WebUtil util;
	protected List<Map<String, String>> listdata;
	protected Map<String, String> mapData;

	@BeforeClass

	public void initDriver() {
		util = new com.org.WebutilLayer.WebUtil();
		util.extentReport("pointOfYou");
		util.openBroswer("chrome");
		util.openURl("https://p2u.kr/");
		util.maximize();
	}

	@BeforeMethod

	public void login(Method m) throws InterruptedException {
		String testName = m.getName();
		util.createTestCase(testName);
		LoginPage  log = new LoginPage(util);
		log.loginVarification();
		log.Email();
		log.Password();
		log.Check_Box();
	  log.ScrollPage();



}
}
