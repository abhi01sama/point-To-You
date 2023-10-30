package com.org.PointTOYou.TestCase;

import org.testng.annotations.Test;

import com.org.PintTOYou.BaseMakeUpPage.BaseMakeUp;
import com.org.PointTOYou.BaseConfig.BaseTest;

public class BaseMakeUPTestCase extends BaseTest {

	@Test

	public void MakeUP_pageVarification() throws InterruptedException {
		util.openURl("https://p2u.kr/item-details/DARK_137");
		BaseMakeUp base = new BaseMakeUp(util);
		base.Color();
		Thread.sleep(2000);
		base.Size();
		
		
	}
	}





















