package Basetest;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.WebutilLayer.WebUtil;



public class Drop {
	
	 
	  // @FindBy(xpath = "//select[@name='limit']")
	   
	   @FindBy(css = "#limit-select")
	   private WebElement dropdown_ln;
	   
		private WebUtil util;

		public Drop(WebUtil util) {     
			this.util = util;
			PageFactory.initElements(util.getDriver(), this);

		} 

		public void Selectdropdown1() {
			util.SelectByValue(dropdown_ln, "dropDown", "10000");

		}
		public void Selectdropdown2() {
			util.SelectByValue(dropdown_ln, "dropDown", "36");

		}
		public void Selectdropdown3() {
			util.SelectByValue(dropdown_ln, "dropDown", "12");

		}
		public void Selectdropdown4() {
			util.SelectByValue(dropdown_ln, "dropDown", "24");

		}
}
