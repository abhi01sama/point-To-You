package com.org.PointTOYou.BeatyPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.WebutilLayer.WebUtil;

public class BeautyPage {

	@FindBy(xpath = "//ul[@class='navbar-list'][1]")
	private WebElement beuaty_ln;


	// #limit-select css

	@FindBy(xpath = "//select[@name='limit']")
	private WebElement BeautyDrop_down;

	
	@FindBy(css = "#sort-select")
	private WebElement shortby_ln;

	private WebUtil util;

	public BeautyPage(WebUtil util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);

	}

	public void beautyVarification() {
		util.click(beuaty_ln, "dropDown");

	}

	public void Beauty_dropDown() {
		util.SelectByValue(BeautyDrop_down, "beautyD", "10000");

	}

	public void Shortby() {
		util.SelectByValue(shortby_ln, "short", "mx.created_on asc");
	}

}
