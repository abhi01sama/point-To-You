package com.org.PointTOYou.TestCase;

import org.testng.annotations.Test;

import com.org.PointTOYou.BaseConfig.BaseTest;
import com.org.WebutilLayer.WebUtil;

import Basetest.Drop;

public class DorpDown extends BaseTest {
	@Test

	public void loginvarificaton() throws InterruptedException {
		WebUtil util = new WebUtil();
		util = new WebUtil();
		util.extentReport("poinOfYou");
		util.openBroswer("chrome");
		util.openURl("https://p2u.kr/item-gallery/102001?limit=24&page=1&sort=mx.created_on+desc&p=");
		util.maximize();
		Drop d = new Drop(util);
		// util.implicitWait(10);

		d.Selectdropdown1();

		Thread.sleep(2000);

		d.Selectdropdown2();
		Thread.sleep(2000);

		d.Selectdropdown3();
		Thread.sleep(2000);

		d.Selectdropdown4();

	}

}
