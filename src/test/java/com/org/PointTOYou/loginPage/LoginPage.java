package com.org.PointTOYou.loginPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.WebutilLayer.WebUtil;

public class LoginPage {

	@FindBy(css = "span[class='icn'] i[class='fas fa-sign-in-alt']")
	private WebElement Login_BT;

	@FindBy(xpath = "//input[@id='email-txt']")
	private WebElement Email_BO;

	@FindBy(xpath = "//input[@id='password-txt']")
	private WebElement Pass_Box;
	
	@FindBy(xpath = "//input[@ id='check']")
	private WebElement check_box;
	
	@FindBy(xpath = "//button[@id='member-signup-btn']")
	private WebElement SignIN_BT;

	private WebUtil util;

	public LoginPage(WebUtil util) {
		this.util = util;
		PageFactory.initElements(util.getDriver(), this);

	}

	public void loginVarification() {
		util.click(Login_BT, "login");
	}

	public void Email() {
		util.sendkeys(Email_BO, "email", "wngur6076@naver.com");
	}

	public void Password() {
		util.sendkeys(Pass_Box, "password_bo", "alsckd12@");
	}
	 public void Check_Box() {
		util.click(check_box, "check");
	}
	 
	 public void ScrollPage() {
		util.scrollToElement(SignIN_BT, "sign");
		util.click(SignIN_BT, "sign");
	}
//	 public void SignIN() {
//		util.actionClick(SignIN_BT, "sign");
//	}
}
