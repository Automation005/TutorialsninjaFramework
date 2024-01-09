package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.AccountSuccessPage;
import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.RegisterPage;
import com.tutorialsninja.qa.TestBase.TestBase;
import com.tutorialsninja.qa.Utilities.Util;

public class RegisterTest extends TestBase{
	
	public RegisterTest() throws Exception {
		super();	
	}

	public WebDriver driver;
	public HomePage homepage;
	public RegisterPage registerpage;
	public AccountSuccessPage accountsuccesspage ;
	
	

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homepage = new HomePage(driver);
		homepage.ckickOnMyAccountDropDownMenu();
		homepage.selectRegisterOption();
		
	}
	@Test (priority =1)
	public void verifyRegisterWithMandatoryFields () {
		homepage = new HomePage(driver);
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		accountsuccesspage = new AccountSuccessPage(driver);
		String actualAccountCreatedMessage = accountsuccesspage.retieveAccountSuccessMessage();
		String expectedAccountCreatedMessage = dataProp.getProperty("accountSuccessMessage");
		Assert.assertTrue(actualAccountCreatedMessage.contains(expectedAccountCreatedMessage));		
	}
	@Test (priority = 2)
	public void verifyRegisterWithAllFields() {
		homepage = new HomePage(driver);
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();
		accountsuccesspage = new AccountSuccessPage(driver);
		String actualAccountCreatedMessage = accountsuccesspage.retieveAccountSuccessMessage();
		String expectedAccountCreatedMessage = dataProp.getProperty("accountSuccessMessage");
		Assert.assertTrue(actualAccountCreatedMessage.contains(expectedAccountCreatedMessage));
		
		
	}
	@Test (priority = 3)
	public void verifyRegisterWithExistingEmail() {
		homepage = new HomePage(driver);
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(prop.getProperty("validEmail"));
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();	
		String actualDuplicateWarningMessage = registerpage.retieveDuplicateEmailWarning();
		String expectedDuplicateWarningMessage = dataProp.getProperty("DuplicateEmailWarningMessage");
		Assert.assertTrue(actualDuplicateWarningMessage.contains(expectedDuplicateWarningMessage));
		
		
	}
	@Test (priority = 4)
	public void verifyRegisterWithMismatchPassword() {
		homepage = new HomePage(driver);
		registerpage = new RegisterPage(driver);
		registerpage.enterFirstName(dataProp.getProperty("firstName"));
		registerpage.enterLastName(dataProp.getProperty("lastName"));
		registerpage.enterEmail(Util.emailWithDateTimeStamp());
		registerpage.enterTelephone(dataProp.getProperty("mobile"));
		registerpage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerpage.enterConfirmPassword(dataProp.getProperty("invalidPassword"));
		registerpage.clickOnNewsLetterYesOptionRadioButton();
		registerpage.clickOnPrivacyPolicyCheckBox();
		registerpage.clickOnContinueButton();		
		String actualPasswordMismatchWarningMessage = registerpage.retievePasswordMismatchWarning();
		String expectedPasswordMismatchWarningMessage = dataProp.getProperty("passwordMismatchWarningMessage");
		Assert.assertTrue(actualPasswordMismatchWarningMessage.contains(expectedPasswordMismatchWarningMessage));
		
		
	}
	@Test (priority = 5)
	public void verifyRegisterWithNofields() {
		registerpage = new RegisterPage(driver);
		registerpage.clickOnContinueButton();	
		
		String actualPrivacyPolicyWarningMessage = registerpage.retievePrivacyPolicyWarning();
		String expectedPrivacyPolicyWarningMessage = dataProp.getProperty("PrivacyPolicyWarningMessage");
		Assert.assertTrue(actualPrivacyPolicyWarningMessage.contains(expectedPrivacyPolicyWarningMessage));
		
		String actualFirstNameWarningMessage = registerpage.retieveFirstNameWarning();
		String expectedFirstNameWarningMessage = dataProp.getProperty("firstNameWarningMessage");
		Assert.assertTrue(actualFirstNameWarningMessage.contains(expectedFirstNameWarningMessage));
	
		String actualEmailWarningMessage = registerpage.retieveEmailWarning();
		String expectedEmailWarningMessage = dataProp.getProperty("emailWarningMessage");
		Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));
		
		String actualTelephoneWarningMessage = registerpage.retieveTelephoneWarning();
		String expectedTelephoneWarningMessage = dataProp.getProperty("telephoneWarningMessage");
		Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));
		
		String actualPasswordWarningMessage = registerpage.retievePsswordWarning();
		String expectedPasswordWarningMessage = dataProp.getProperty("passwordWarningMessage");
		Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
	}
		
	@AfterMethod
	public void tearDown() {
	 driver.quit();
	
	}
}
