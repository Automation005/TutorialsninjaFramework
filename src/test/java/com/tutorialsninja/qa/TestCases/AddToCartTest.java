package com.tutorialsninja.qa.TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.ProductInfoPage;
import com.tutorialsninja.qa.Pages.SearchPage;
import com.tutorialsninja.qa.TestBase.TestBase;

public class AddToCartTest extends TestBase {

	public AddToCartTest() throws Exception {
		super();
	}

	public WebDriver driver;
	public ProductInfoPage productinfopage;
	public HomePage homepage;
	public SearchPage searchpage;

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));

	}

	@Test
	public void addValidProductToCart()  {
		homepage = new HomePage(driver);
		homepage.enterProductDetail(dataProp.getProperty("validProduct2"));
		SearchPage searchpage = homepage.clickOnSearchIcone();
		Assert.assertTrue(searchpage.verifyDisplayStatusOfValidProduct());
		productinfopage = searchpage.clickOnAddToCartButton();
		
		Assert.assertTrue(productinfopage.validateDisplayStatusProdInfo());
		productinfopage.clickOnAddToCartButtonInProdInfo();

		String actualMessage = productinfopage.retrieveProductAddToShoppingCartMessage();
		
		String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!";
		Assert.assertTrue(actualMessage.contains(expectedMessage));

	}

	@AfterMethod
	public void tearDown() {
		 driver.quit();
	}

}