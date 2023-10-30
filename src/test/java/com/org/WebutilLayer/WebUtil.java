package com.org.WebutilLayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

public class WebUtil {
	private ExtentTest test;
	private WebDriver driver;
	private ExtentReports report;

	public WebDriver getDriver() {

		return driver;

	}

	/**
	 * @return
	 */
	public String dateFormate() {
		DateFormat df = new SimpleDateFormat("dd_MMM+yyyy_HH_mm_ss_z");
		return df.format(new Date());
	}

	public ExtentTest getExtentTest() {
		return test;
	}

	/**
	 * @param fileNameOfExtendeport
	 * @param teatCaseName
	 */
	public void extentReport(String fileNameOfExtendeport) {
		String time = dateFormate();
		ExtentSparkReporter sparkObj = new ExtentSparkReporter("automation\\" + fileNameOfExtendeport + time + ".html");
		report = new ExtentReports();
		report.attachReporter(sparkObj);

	}

	///// -----------WebDriver--------interface////

	/**
	 * @param browser
	 * @param url
	 * @return
	 */
	public WebDriver openBroswer(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "driver//chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(option);

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		} else {

		}
		return driver;

	}

	public void openURl(String URL) {
		driver.get(URL);

	}

	/*
	 * We create the for the close method
	 */
	public void close() {
		driver.close();
		try {
			test.log(Status.INFO, "Browser closed  successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * for maximize method
	 */
	public void maximize() {
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * minimize method
	 */
	public void minimize() {
		try {
			driver.manage().window().minimize();
			test.log(Status.INFO, "Browser  minimize successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void moveBack() {
		try {
			driver.navigate().back();
			test.log(Status.INFO, "window moved back successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void quit(String browserPage) throws IOException {
		try {
			driver.quit();
			test.log(Status.INFO, "browser close successfully");
		} catch (Exception e) {
			getScreenShot(browserPage);
		}
	}

	/**
	 * @param url
	 */
	public void navigateURL(String url) {
		driver.get(url);
		test.log(Status.INFO, "URL Navigate" + url + "successfully");

	}

	///// -----------WebElement Interface-----////
	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param datavalue
	 * @param elementName
	 */
	public void inputTextValue(WebElement we, String datavalue, String elementName) {
		if (we.isDisplayed())
			if (we.isEnabled()) {
				we.clear();
				we.sendKeys(datavalue);
				test.log(Status.INFO, "intered  value in" + elementName + "box is syuccessfully");
			} else {
				test.log(Status.INFO, "not intered  value in" + elementName + "box is syuccessfully");
			}
	}

	public void flush() {
		report.flush();
	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 */
	public void click(WebElement we, String elementName) {
		if (we.isEnabled()) {
			we.click();


		} else {
			test.log(Status.FAIL, elementName + "not  click sucessfully");
		}
	}

	/**
	 * @param Value
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param i
	 * @param status
	 * @param we 
	@throws Exception
	 */
	/*
	 * getInnerText method return String
	 */
	public String getInnerText(WebElement we, String elementName) throws IOException {
		String innerText = "";
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				innerText = we.getText();

				test.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
			} else {
				test.log(Status.FAIL, elementName + " Not getText ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return innerText;
	}

	/**
	 * @method getInnerTextMultipleElements
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return List<String>
	 * @throws IOException
	 */
	public List<String> getInnerTextMultipleElements(String locatorValue, String locatorType, String elementName)
			throws IOException {
//				String innerText = "";
		List<String> arrList = null;
		try {
			arrList = new ArrayList<String>();
			List<WebElement> lstWe = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < lstWe.size(); i++) {
				WebElement we = lstWe.get(i);
				boolean st = checkElement(we, elementName);
				if (st == true) {
					String innerText = we.getText();
					arrList.add(innerText);
					test.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
				} else {
					test.log(Status.FAIL, elementName + " Not getText");
				}
			}

		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return arrList;
	}

	/**
	 * @param Value
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return
	 * @throws Exception
	 */
	public String getinnerText(WebElement we, String elementName) {
		String innerText = "";
		if (we.isDisplayed())
			if (we.isEnabled()) {
				we.getText();
				test.log(Status.INFO, "get text successfully=" + innerText);
			} else {
				test.log(Status.FAIL, elementName + "not  found getText");
			}
		return innerText;
	}

	/* get CssValue method */
	public String getCssValue(WebElement we, String elementName, String background_color_Ya_color, String colorHasValue)
			throws IOException {
		String colorText = "";
		try {

			boolean st = checkElement(we, elementName);
			if (st == true) {
				String colorProperty = we.getCssValue(background_color_Ya_color);
				colorText = Color.fromString(colorProperty).asHex();
				if (colorText.equalsIgnoreCase(colorHasValue)) {
					test.log(Status.INFO, elementName + " of color is Right");
				} else {
					test.log(Status.FAIL, elementName + " of color is not Right");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return colorText;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	/*
	 * we create the getScreenShot() method and add the screenShot with Report file
	 */
	public void getScreenShot(String elementName) {
		try {
			TakesScreenshot tc = (TakesScreenshot) driver;
			File from = tc.getScreenshotAs(OutputType.FILE);
			File to = new File(elementName + "//Screenshot.png");
			Files.copy(from, to);
			test.addScreenCaptureFromPath(to.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @throws Exception
	 */
	public void getAllSelectOptions(WebElement we, String elementName) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				List<WebElement> lst = sl.getAllSelectedOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOption = lst.get(i).getText();
					i++;
				}
				test.log(Status.INFO, "get one by one all selected options");
			} else {
				test.log(Status.FAIL, "getAllSelectedOptions Value in" + elementName + "Dropdown Successfully");
			}
	}

	public List<WebElement> getList(String locatorValue, String locatorType, String elementName) {
		List<WebElement> listWe = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			listWe = driver.findElements(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			listWe = driver.findElements(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			listWe = driver.findElements(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			listWe = driver.findElements(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			listWe = driver.findElements(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("css")) {
			listWe = driver.findElements(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("innerTest")) {
			listWe = driver.findElements(By.linkText(locatorValue));
		} else {
			test.log(Status.FAIL, elementName + "wrong xpath");
		}
		return listWe;
	}

	public WebElement get(String locatorValue, String locatorType) {
		WebElement We = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			We = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			We = driver.findElement(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			We = driver.findElement(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			We = driver.findElement(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			We = driver.findElement(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("css")) {
			We = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("innerTest")) {
			We = driver.findElement(By.linkText(locatorValue));
		} else {
			test.log(Status.FAIL, "element" + "wrong xpath");
		}
		return We;
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param attributeValue
	 * @return
	 */
	public void getAttributeValue(WebElement we, String elementName, String attributeValue) {

		if (we.isDisplayed()) {
			attributeValue = we.getAttribute(elementName);
			we.getText();
			test.log(Status.INFO, elementName + "get text successfully=" + attributeValue);
		} else {
			test.log(Status.FAIL, elementName + "not found attributeValue);");
		}
	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @param background_colour
	 * @param colourHasValue
	 * @return
	 * @throws Exception
	 */
	public void CssValue(WebElement we, String elementName, String background_colour, String colourHasValue) {

	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @param dropDownAttribute
	 * @throws Exception
	 */
	public void SelectByVisible(WebElement we, String elementName, String dropDownAttribute) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				sl.selectByVisibleText(dropDownAttribute);
				test.log(Status.INFO, elementName + "Select Value in Dropdown Successfully");
			} else {
				test.log(Status.FAIL, elementName + "Not Select Value in " + elementName + "Dropdown");
			}
	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @param dropDownValue
	 * @throws Exception
	 */
	public void SelectByValue(WebElement we, String elementName, String dropDownValue) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				sl.selectByValue(dropDownValue);
			//	test.log(Status.INFO, elementName + "Select Value in Dropdown Successfully");
			} else {
				test.log(Status.FAIL, elementName + "Not Select Value in " + elementName + "Dropdown");
			}
	}
	

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param dropDownValue
	 */
	public void SelectByindex(WebElement we, String elementName, int dropDownValue) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				sl.selectByIndex(dropDownValue);
				test.log(Status.INFO, elementName + "Select Value in Dropdown Successfully");
			} else {
				test.log(Status.FAIL, elementName + "Not Select Value in " + elementName + "Dropdown");
			}
	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @param dropDownValue
	 * @throws Exception
	 */
	public void selectDeselectByValue(WebElement we, String elementName, String dropDownValue) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				sl.deselectByValue(dropDownValue);
				test.log(Status.INFO, elementName + "Deselect Value in Dropdown Successfully=" + dropDownValue);
			} else {
				test.log(Status.FAIL, elementName + "Not Deselect Value in " + elementName + "Dropdown");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param dropDownValue
	 * @throws Exception
	 */
	public void selectDeselectByVisibleText(WebElement we, String elementName, String dropDownValue) {

		Select sl = new Select(we);
		if (we.isEnabled()) {
			sl.deselectByVisibleText(dropDownValue);
			test.log(Status.INFO, elementName + "Deselect Value in Dropdown Successfully=" + dropDownValue);
		} else {
			test.log(Status.FAIL, elementName + "Not Deselect Value in " + elementName + "Dropdown");
		}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param dropDownValue
	 * @throws Exception
	 */
	public void selectDeselectByindex(WebElement we, String elementName, int dropDownValue) {

		Select sl = new Select(we);
		if (we.isDisplayed()) {
			sl.deselectByIndex(dropDownValue);
			test.log(Status.INFO, elementName + "Deselect index in Dropdown Successfully=" + dropDownValue);
		} else {
			test.log(Status.FAIL, elementName + "Not Deselect index in " + elementName + "Dropdown");
		}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param dropDownValue
	 * @throws Exception
	 */
	public void DeselectAll(WebElement we, String elementName, int dropDownValue) {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				sl.deselectAll();
				test.log(Status.INFO, elementName + "Deselect all in Dropdown Successfully=");
			} else {
				test.log(Status.FAIL, elementName + "Not Deselect all in");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @throws Exception
	 */
	public void FirstSelect(WebElement we, String elementName) throws Exception {

		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				String getFSO = sl.getFirstSelectedOption().getText();
				test.log(Status.INFO,
						elementName + "getFirstSelectedOption Value in" + elementName + "Drodown is =" + getFSO);
			} else {
				test.log(Status.FAIL, elementName + "Not Deselect all in");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @throws Exception
	 */
	public void SelectGetOptions(WebElement we, String elementName) throws Exception {
		Select sl = new Select(we);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				List<WebElement> lst = sl.getOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOption = lst.get(i).getText();
					i++;
				}
				test.log(Status.INFO, "getAllOptions Value in" + elementName + "Dropdown Successfully");
			} else {
				test.log(Status.FAIL, "Not get First  Value in" + elementName + "Dropdown");
			}
	}
	////// -------Action Class------///////////

	//// /* Mouse Over *//////
	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 */
	public void mouseOver(WebElement we, String elementName) {

		Actions ac = new Actions(driver);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				ac.moveToElement(we).build().perform();
				test.log(Status.INFO, elementName + "MouseOver Successfully");
			} else {
				test.log(Status.FAIL, elementName + "not mouseOver");
			}
	}

	public void actionMoveToElement(WebElement we) {

		Actions act = new Actions(driver);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				act.moveToElement(we).perform();
			}
	}

	public void actionClick(WebElement we, String elementName) {
		try {
			new Actions(driver).click(we).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void actionSendkey(WebElement we, String elementName, String textBoxValue) {
		try {
			new Actions(driver).sendKeys(we).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 */
	public void actionClickAndHold(String locatorType, String locatorValue, String elementName) {

	}

	/**
	 * @param value
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param value
	 */
	public void actioninputValue(WebElement we, String value, String elementName) {

		Actions act = new Actions(driver);
		if (we.isDisplayed())
			if (we.isEnabled()) {
				act.moveToElement(we).sendKeys(value).build().perform();
				test.log(Status.INFO, "Send the value successfully");
			} else {
				test.log(Status.FAIL, " not Send the value successfully");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 */
	/**
	 * @param we
	 * @param elementName
	 */
	public void actionDoubleClick(WebElement we, String elementName) {

		Actions act = new Actions(driver);
		if (we.isDisplayed() == true)
			if (we.isEnabled() == true) {
				act.moveToElement(we).doubleClick().build().perform();
				test.log(Status.INFO, "by action double click is successfully");
			} else {
				test.log(Status.FAIL, "by action double click is not  successfully");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 */
	public void actionRightClick(WebElement we, String elementName) {

		Actions act = new Actions(driver);
		if (we.isDisplayed() == true)
			if (we.isEnabled() == true) {
				act.moveToElement(we).contextClick().build().perform();
			} else {
				test.log(Status.INFO, "by action right click is successfully");
			}
		else {
			test.log(Status.FAIL, "by action right click is not  successfully");

		}
	}

	public void dragAndDrop(WebElement elementForDrag, int xOff, int yOff, String elementName) {
		try {
			new Actions(driver).dragAndDropBy(elementForDrag, xOff, yOff).build().perform();
			test.log(Status.INFO, elementName + "has been drag sucessfully");
		} catch (Exception e) {
			test.log(Status.FAIL, elementName + "has not been drag sucessfully");
			e.printStackTrace();

		}

	}

	/**
	 * @param locatorType1
	 * @param locatorValue1
	 * @param locatorType2
	 * @param locatorValue2
	 * @param elementName
	 * @param we
	 */
	public void dragAnddrop(String elementName, WebElement we1, WebElement we2) {

		Actions act = new Actions(driver);
		if (we1.isDisplayed() == true)
			if (we1.isEnabled() == true) {
				act.dragAndDrop(we1, we2).contextClick().build().perform();
				test.log(Status.INFO, "by action drag and drop is successfully");
			} else {
				test.log(Status.FAIL, "by action drag and drop is  not successfully");
			}
	}

	/**
	 * @param locatorType
	 * @param locatorValue
	 * @param elementName
	 * @param textBoxValue
	 */
	public void sendkeys(WebElement we, String elementName, String textBoxValue) {

		if (we.isDisplayed()) {
			//test.log(Status.PASS, "element is Displayed");
			if (we.isEnabled() == true) {
				//test.log(Status.PASS, "send the value Successfully");
				we.sendKeys(textBoxValue);

			} else {
				test.log(Status.FAIL, "the vlaue  is not send successfully");
			}
		}
	}

	public void implicitWait(int time) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		} catch (Exception e) {

		}

	}

	/**
	 * @param time
	 */
	public void sleep(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (Exception e) {

		}

	}

	public String getAlertMsg() {
		String alertMSG = null;
		try {
			alertMSG = driver.switchTo().alert().getText();
			test.log(Status.INFO, "alert massage is taken sucessfully");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(Status.INFO, " alert massage is not taken successfully");
		}
		return alertMSG;
	}

	public void alertAccept() {
		driver.switchTo().alert().accept();
		test.log(Status.INFO, "Alert accepted successfully");
	}

	public void alertDismiss() {

		driver.switchTo().alert().dismiss();
		test.log(Status.INFO, "Alert dismiss successfully");
	}

	public void alertsendKeys(String value) {
		driver.switchTo().alert().sendKeys(value);
		test.log(Status.INFO, value + " enter in alert successfully");
	}

	public String alertGetStringValue() {
		String alertgetText = driver.switchTo().alert().getText();
		test.log(Status.INFO, alertgetText + " Has been taken successfully");
		return alertgetText;

	}

	public void javaScriptSendKeys(WebElement we, String inputValue) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		we.clear();
		jse.executeScript("arguments[0].value='" + inputValue + "", we);
		System.out.println("value is Inputed successfully");

	}

	public void javaScriptClick(WebElement we, String ElementName) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value= " + ElementName + "'", we);
		System.out.println("Click  successfully");

	}

	public void explicityWait(WebElement we, int timeInsecond) {
		if (we.isDisplayed() && we.isEnabled()) {
			WebDriverWait explicitwait = new WebDriverWait(driver, Duration.ofSeconds(timeInsecond));
			explicitwait.until(ExpectedConditions.visibilityOf(we));
			test.log(Status.INFO, "sendkey successfully");
		}
	}

	public void fluentWait(WebElement we, int totalTime, int timeInterval) {

	}

	public void refreshWindow() {
		driver.navigate().refresh();
		test.log(Status.INFO, "Window refresh  successfully");
	}

	public void quit() {
		driver.quit();
		test.log(Status.INFO, "window quit successfully");
	}

	public void navigateBack() {
		driver.navigate().back();
		test.log(Status.INFO, " page navigatBack successfully");
	}

	public void navigateForword() {
		driver.navigate().forward();
		test.log(Status.INFO, "page navigated forward successfully");
	}

	public void createTestCase(String teatCaseName) {
		test = report.createTest(teatCaseName);
	}

	public void actionScroll(WebElement element, String elementName) {
		try {
			new Actions(driver).scrollToElement(element).build().perform();
			test.log(Status.INFO, "page scroll to the" + elementName + " successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param element
	 */
	public void scrollToElemenetAct(WebElement element) {
		WebDriverWait wDwait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wDwait.until(ExpectedConditions.visibilityOf(element));

	}

	public void scrollToElement(WebElement element, String elementName) {
		try {
			new Actions(driver).scrollToElement(element).build().perform();
			test.log(Status.FAIL, "Window is sroll to the" + elementName);
		} catch (Exception e) {
			test.log(Status.INFO, "Window is not scoll to the" + elementName);
			e.printStackTrace();
		}
	}

	public void scrollToElementJS(WebElement element, String elementName) {
		try {
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("argument[0].scrollInView();", element);
			test.log(Status.FAIL, "Window is scroll to the" + elementName);
		} catch (Exception e) {
			test.log(Status.FAIL, "Window is not scroll to the " + elementName);
			e.printStackTrace();
		}
	}

	public void scrollByJs(int i, int j) {
		try {
			JavascriptExecutor Js = (JavascriptExecutor) driver;
			Js.executeScript("window.scrollBy(" + i, j + ")");
			test.log(Status.FAIL, "Window is scroll");
		} catch (Exception e) {
			test.log(Status.FAIL, "Window is not scroll");
			e.printStackTrace();
		}
	}
	public void GetWindowHandle() {
		
	}

	public Set<String> takeWindowHandles() {
		Set<String> windoeHandles = null;
		try {
			windoeHandles = driver.getWindowHandles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return windoeHandles;
	}

	/**
	 * @param titleOrUrl
	 * @param titleOrUrlValue
	 */
	public void handleMultipleWindow(String titleOrUrl, String titleOrUrlValue) {
		try {
			Set<String> windowHandles = takeWindowHandles();
			if (titleOrUrl.equalsIgnoreCase("title")) {
				for (String windowHandle : windowHandles) {
					driver.switchTo().window(windowHandle);
					String title = driver.getTitle();
					if (title.equalsIgnoreCase(titleOrUrlValue)) {
						break;
					}
				}
			} else {
				for (String windowHandle : windowHandles) {
					if (driver.getCurrentUrl().equalsIgnoreCase(titleOrUrlValue)) {
						driver.switchTo().window(windowHandle);
						break;
					}
				}
			}
		} catch (Exception e) {
		}
	}

	/**
	 * 
	 */
	public void scrollDownToPAge() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * @param KeyNmae
	 * @return
	 */
	public String getProperties(String KeyNmae) {
		File fobj = new File("config.properties");
		InputStream finputobj = null;
		Properties proobj = null;
		try {
			finputobj = new FileInputStream(fobj);
			proobj = new Properties();
			proobj.load(finputobj);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return proobj.getProperty(KeyNmae);
	}

	/**
	 * @param we
	 * @param elementName
	 * @return
	 */
	public boolean checkElement(WebElement we, String elementName) {

		boolean status = false;
		if (we.isDisplayed()) {
			test.log(Status.PASS, elementName + " element is Displayed");
			if (we.isEnabled()) {
				test.log(Status.PASS, elementName + " element is Enabled");
				status = true;
			} else {
				test.log(Status.FAIL, elementName + " element is not Enabled");
			}
		} else {
			test.log(Status.FAIL, elementName + " element is not Displayed");
		}
		return status;
	}

	/**
	 * @param element
	 * @return
	 */
	public Point getLocation(WebElement element) {
		Point locationPoint = null;
		try {
			locationPoint = element.getLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locationPoint;
	}

	/**
	 * 
	 */
	public void clickAtLocation() {
		try {
			new Actions(driver).moveByOffset(0, 0).click();
		} catch (Exception e) {
		}
	}



	public List<Map<String, String>> getAllData() {
		Workbook wbWorkbook=getWorkbook("");
		Sheet sh = wbWorkbook.getSheet("sheat name");
		List<Map<String,String>> listData=new ArrayList<Map<String,String>>();
		for (int i = 1; i < sh.getLastRowNum(); i++) {
			Row firstRow=sh.getRow(0);
			Row rwRow = sh.getRow(i);
			Map<String,String> mapDataMap=new HashMap<String, String>();
			for (int j = 0; j < rwRow.getLastCellNum(); j++) {
				String keyValue=getData(firstRow, j);
				String value=getData(rwRow, j);
				mapDataMap.put(keyValue, value);
			}
			listData.add(mapDataMap);
		}
		return listData;

	}
         
	
	
	
	
	
	
	public Workbook getWorkbook(String fileLOcation) {
		Workbook wbWorkbook = null;
		try {
			File fl = new File(fileLOcation);
			wbWorkbook = new XSSFWorkbook();
		} catch (Exception e) {

		}
		return wbWorkbook;
	}

	/**
	 * @param rwObj
	 * @param cellNum
	 * @return
	 */
	public String getData(Row rwObj, int cellNum) {
		Cell cl = rwObj.getCell(cellNum);
		CellType cType = cl.getCellType();
		String ss;
		if (cType == CellType.STRING) {
			ss = cl.getStringCellValue();
		} else if (cType == CellType.BOOLEAN) {
			Boolean booleanData = cl.getBooleanCellValue();
			ss = booleanData.toString();
		} else {
			Double value = cl.getNumericCellValue();
			Integer intValue = value.intValue();
			ss = intValue.toString();
		}
		return ss;

	}
	
	
	public void Tab() {
		String tab = Keys.chord(Keys.CONTROL,Keys.RETURN);
		driver.findElement(By.xpath("")).sendKeys(tab);
		
	}

	/**
	 * @param url
	 */
	public void handleMultipleWindow_Url(String url) {
		Set<String> handleValue = driver.getWindowHandles();
		for (String handle : handleValue) {
			driver.switchTo().window(handle);
			String currentUrl = driver.getCurrentUrl();
			if (currentUrl.equalsIgnoreCase(url)) {
				break;
			}
		}
	}

}



