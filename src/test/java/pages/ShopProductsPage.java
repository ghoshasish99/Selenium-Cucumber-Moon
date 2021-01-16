package pages;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * PageFactory For ShopPage
 */
public class ShopProductsPage extends ShopMasterPageWeb {

    private WebDriverWait wait;

    /**
     * All WebElements are identified by @FindBy annotation
     */



    @FindBy(id = "productsearch")
    WebElement txtProductSearch;

    @FindBy(id = "searchicon")
    WebElement btnSearchIcon;

    @FindBy(xpath = "//h4[@class='MuiTypography-root MuiTypography-h4']")
    WebElement txtProductHeader;

    @FindBy(xpath = "//span[contains(text(),'Add to your basket')]")
    WebElement btnAddToYourBasket;

    @FindBy(id = "basket")
    WebElement lnkBasket;

    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    WebElement btnCheckOut;

    public ShopProductsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public Boolean visibilityOfTxtProductSearch() {
        wait = new WebDriverWait(driver, 30);
        WebElement btnSearch = driver.findElement(By.xpath("//input[@id='productsearch']"));
        wait.until(ExpectedConditions.visibilityOf(btnSearch));
        return btnSearch.isDisplayed();
    }

    //Set product in textbox
    public void setProduct(String strProduct) {
        txtProductSearch.sendKeys(strProduct);
    }

    //Click on Search button
    public void clickSerch() {
        btnSearchIcon.click();
    }

    //Verify on Product listed
    @SuppressWarnings("deprecation")
	public void verifyProductListed(String strProduct) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[contains(text(),'" + strProduct.trim() + "')]"))));
        Assert.assertTrue(driver.findElement(By.xpath("//a[contains(text(),'" + strProduct.trim() + "')]")).isDisplayed());
    }

    //Verify on Product
    public void selectProduct(String strProduct) {
        driver.findElement(By.xpath("//a[contains(text(),'" + strProduct.trim() + "')]")).click();
        //ExtentCucumberAdapter.addTestStepLog("Product selected sucessfully");
    }

    //Verify on Product Details
    public void verifyProductDetailsPage(String strProduct) {
    	Assert.assertTrue(btnAddToYourBasket.isDisplayed());
		/*
		 * if (btnAddToYourBasket.isDisplayed()) {
		 * ExtentCucumberAdapter.addTestStepLog("Product details displayed successfully"
		 * ); }
		 */
    }

    //Click on AddToYourBasket button
    public void clickAddToYourBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(btnAddToYourBasket));
        if (btnAddToYourBasket.isDisplayed()) {
            btnAddToYourBasket.click();
           // ExtentCucumberAdapter.addTestStepLog("Add to your basket displayed successfully");
        }
    }

    //Click on Basket button
    public void clickBasket() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(lnkBasket));
        if (lnkBasket.isDisplayed()) {
            lnkBasket.click();
          //  ExtentCucumberAdapter.addTestStepLog("Basket displayed successfully");
        }
    }

    //Click on Check Out button
    public void clickCheckOut() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOf(btnCheckOut));
        if (btnCheckOut.isDisplayed()) {
            btnCheckOut.click();
          //  ExtentCucumberAdapter.addTestStepLog("CheckOut displayed successfully");
        }
    }

}
