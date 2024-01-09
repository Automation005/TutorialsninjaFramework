package com.tutorialsninja.qa.TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.SearchPage;
import com.tutorialsninja.qa.TestBase.TestBase;

public class SearchProductTest extends TestBase {
	public SearchProductTest() throws Exception {
		super();

	}

	public WebDriver driver;
	public HomePage homepage;
	public SearchPage searchpage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));

	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("validProduct2"));
		homepage.clickOnSearchIcone();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.verifyDisplayStatusOfValidProduct());
	

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("invalidProduct"));
		homepage.clickOnSearchIcone();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInvalidProduct());
	
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {
		homepage = new HomePage(driver);
		homepage.clickOnSearchIcone();
		searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.verifyDisplayStatusOfInvalidProduct());


	}

	@AfterMethod
	public void teraDown() {
		driver.quit();

	}

}
