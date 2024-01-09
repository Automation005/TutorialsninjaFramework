package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.AccountPage;
import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.LoginPage;
import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.TestData.ExcelCode;
import com.tutorialsninja.qa.Utilities.Util;

public class LoginTest extends TestBase {
	public LoginTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public HomePage homepage;
	public LoginPage loginpage;

	@BeforeMethod
	public void setup() {	
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.ckickOnMyAccountDropDownMenu();
		homepage.selectLoginOption();

	}

	@Test(priority =1, dataProvider = "TNLogin", dataProviderClass = ExcelCode.class)
	public void verifyLoginWithValidCredintials(String username, String password) {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(username);
		loginpage.enterPassword(password);
		loginpage.clickOnLoginButton();
		AccountPage accountpage = new AccountPage(driver);
		Assert.assertTrue(accountpage.getDisplayedStatusOfChangePassword());
		
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidEmailValidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");

		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailInvalidPassword() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 4)
	public void verifyLoginWithInvalidCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterEmailAddress(Util.emailWithDateTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@Test(priority = 5)
	public void verifyLoginWithNoCredentials() {
		LoginPage loginpage = new LoginPage(driver);
		loginpage.clickOnLoginButton();
		String actualWarningMessage = loginpage.retrieveEmailPasswordNoMatchWarningText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
