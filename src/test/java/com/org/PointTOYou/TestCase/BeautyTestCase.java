package com.org.PointTOYou.TestCase;

import org.testng.annotations.Test;

import com.org.PointTOYou.BaseConfig.BaseTest;
import com.org.PointTOYou.BeatyPage.BeautyPage;

public class BeautyTestCase extends BaseTest {

	@Test

	public void BeautypageVarification() throws InterruptedException {
		BeautyPage beauty = new BeautyPage(util);
		beauty.beautyVarification();
		Thread.sleep(2000);
		beauty.beautyVarification();
		util.implicitWait(20);
		Thread.sleep(2000);
		beauty.Shortby();

	}

}
