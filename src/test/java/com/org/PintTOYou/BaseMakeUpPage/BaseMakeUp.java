package com.org.PintTOYou.BaseMakeUpPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.WebutilLayer.WebUtil;

public class BaseMakeUp {

	@FindBy(css = "#selectOpt1#selectOpt1")
	private WebElement color_ln;

	@FindBy(css = "#selectOpt2#selectOpt2")
	private WebElement size_ln;

	private WebUtil util;

	public BaseMakeUp(WebUtil util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);

	}

	public void Color() {
		util.scrollToElement(color_ln, "color");
		util.SelectByValue(color_ln, "color", "[1305,258]");
	}

	public void Size() {
	//	util.scrollToElement(color_ln, "color");
		util.SelectByValue(size_ln, "size", "1306");
	}

}
