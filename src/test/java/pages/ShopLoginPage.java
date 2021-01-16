package pages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.*;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;

/**
 * PageFactory For ShopPage
 */
public class ShopLoginPage extends ShopMasterPageWeb {
    protected static final Logger LOG = LoggerFactory.getLogger(ShopLoginPage.class);
    /**
     * All WebElements are identified by @FindBy annotation
     */

    @FindBy(id = "menu")
    WebElement btnMenu;

    @FindBy(xpath = "//span[contains(text(),'Login')]")
    WebElement btnLoginMenu;

    @FindBy(id = "email")
    WebElement txtEmailID;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(id = "confirmpassword")
    WebElement txtConfirmPassword;

    @FindBy(id = "firstname")
    WebElement txtFirstName;

    @FindBy(id = "lastname")
    WebElement txtLastName;

    @FindBy(id = "registeremail")
    WebElement txtRegisteredEmail;

    @FindBy(xpath = "//span[contains(text(),'Log In')]")
    WebElement btnLogin;

    @FindBy(xpath = "//span[contains(text(),'Create Your E-Shop Account')]")
    WebElement btnCreateAccount;

    @FindBy(xpath = "//span[contains(text(),'is already Registered')]")
    WebElement lblRegistered;

    @FindBy(xpath = "//input[@id='productsearch']")
    WebElement txtProductSearch;

    @FindBy(xpath = "//span[contains(text(),'Customer not found with email')]")
    WebElement lblCustomerNotFound;

    static int iTime = (int) new Date().getTime();

    public ShopLoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Click on login button
    public void clickMenu() {
        WebDriverWait localWait = new WebDriverWait(driver, 20);
        localWait.until(ExpectedConditions.visibilityOf(btnMenu));
        try {
            btnMenu.click();
        } catch (ElementClickInterceptedException e) {
        //LOG.error(e.getMessage());
        }
    }

    public void clickLoginMenu() {
        WebDriverWait localWait = new WebDriverWait(driver, 60);
        localWait.until(ExpectedConditions.visibilityOf(btnLoginMenu));
        btnLoginMenu.click();
    }

    //Set user Email in textbox
    public void setEmailID(String strEmailID) {
        strEmailID = strEmailID.replace("@", iTime + "@");
        txtEmailID.sendKeys(strEmailID);
      //  ExtentCucumberAdapter.addTestStepLog("Note : UserName/Email Id entered as : " + strEmailID);
    }

    //Set user Email in textbox
    public void setRegisteredEmailID(String strRegisteredEmailID) {
        strRegisteredEmailID = strRegisteredEmailID.replace("@", iTime + "@");
        txtRegisteredEmail.sendKeys(strRegisteredEmailID);
      //  ExtentCucumberAdapter.addTestStepLog("Note : UserName/Email Id entered as : " + strRegisteredEmailID);
    }

    //Set First name in textbox
    public void setFirstName(String strFirstName) {
        txtFirstName.sendKeys(strFirstName);
    }

    //Set Last name in textbox
    public void setLastNam(String strLastName) {
        txtLastName.sendKeys(strLastName);
    }

    //Set password in password textbox
    public void setPassword(String strPassword) {
        txtPassword.sendKeys(strPassword);
    }

    //Set password in password textbox
    public void setConfirmPassword(String strConfirmPassword) {
        txtConfirmPassword.sendKeys(strConfirmPassword);
    }


    //Click on login button
    public void clickLogin() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Log In')]")));
        btnLogin.click();

    }

    //Click on Create Account button
    public void clickCreateAccount() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Create Your E-Shop Account')]")));
        btnCreateAccount.click();

    }

    /**
     * This POM method will be exposed in test case to login in the application
     *
     * @param strEmailID
     * @param strPasword
     * @return
     */

    public void loginToShop(String strEmailID, String strPasword) {
        //Fill Email ID
        this.setEmailID(strEmailID);
        //Fill password
        this.setPassword(strPasword);
    }

    /**
     * This POM method will be exposed in test case to create account in the application
     *
     * @param strFirstName
     * @param strLastName
     * @param strEmailID
     * @param strPassword
     * @return
     */
    public void CreatAccount(String strFirstName, String strLastName, String strEmailID, String strPassword) {
        //Fill strFirstName
        this.setFirstName(strFirstName);
        //Fill strLastName
        this.setLastNam(strLastName);
        //Fill Email ID
        this.setRegisteredEmailID(strEmailID);
        //Fill password
        this.setPassword(strPassword);
        //Fill Confirm password
        this.setConfirmPassword(strPassword);
    }

    /**
     * This POM method will be exposed in test case to verify Account Creation
     */
    public void verifyAccountCreation() {
        WebDriverWait waitLocal = new WebDriverWait(driver, 20);
        waitLocal.until(ExpectedConditions.visibilityOf(txtProductSearch));
        if (txtProductSearch.isDisplayed()) {
           // ExtentCucumberAdapter.addTestStepLog("Account created successfully");
        }
    }

    /**
     * This POM method will be exposed in test case to verify Customer Not Found
     */
    public Boolean visibilityOfCustomerNotFound() {
        WebDriverWait waitLocal = new WebDriverWait(driver, 20);
        waitLocal.until(ExpectedConditions.visibilityOf(lblCustomerNotFound));
        return lblCustomerNotFound.isDisplayed();
    }

}
