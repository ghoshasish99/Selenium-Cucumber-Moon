package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * PageFactory For ShopPage
 */
public class ShopPaymentsPage extends ShopMasterPageWeb {

    /**
     * All WebElements are identified by @FindBy annotation
     */

    @FindBy(id = "cardnumber")
    WebElement txtCardNumber;

    @FindBy(id = "nameoncard")
    WebElement txtNameOnCard;

    @FindBy(id = "expirymonth")
    WebElement txtExpiryMonth;

    @FindBy(id = "expiryyear")
    WebElement txtExpiryYear;

    @FindBy(id = "securitycode")
    WebElement txtSecurityCode;

    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    WebElement btnConfirm;

    @FindBy(xpath = "//h4[contains(text(),'Thanks for your order')]")
    WebElement lblOrderConfirmation;

    public ShopPaymentsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    //Set card number in Card Number textbox
    public void setCardNumber(String strCardNumber) {
        txtCardNumber.sendKeys(strCardNumber);
    }

    //Set name in NameOnCard textbox
    public void setNameOnCard(String strNameOnCard) {
        txtNameOnCard.sendKeys(strNameOnCard);
    }

    //Set ExpiryMonth in ExpiryMonth textbox
    public void setExpiryMonth(String strExpiryMonth) {
        txtExpiryMonth.sendKeys(strExpiryMonth);
    }

    //Set ExpiryYear in ExpiryYear textbox
    public void setExpiryYear(String strExpiryYear) {
        txtExpiryYear.sendKeys(strExpiryYear);
    }

    //Set SecurityCode in SecurityCode textbox
    public void setSecurityCode(String strSecurityCode) {
        txtSecurityCode.sendKeys(strSecurityCode);
    }

    //Click on Next button
    public void clickConfirm() {
        btnConfirm.click();
    }


    /**
     * This POM method will be exposed in test case to enter Payment details in the application
     *
     * @param strCardNumber
     * @param strNameOnCard
     * @param strExpiryYear
     * @param strExpiryMonth
     * @param strSecurityCode
     * @return
     */

    public void fillPaymentDetails(String strCardNumber, String strNameOnCard,String strExpiryYear, String strExpiryMonth, String strSecurityCode ) {
        //Fill Card Number
        this.setCardNumber(strCardNumber);
        //Fill Name On Card
        this.setNameOnCard(strNameOnCard);
        //Fill Name On Card
        this.setExpiryYear(strExpiryYear);
        //Fill Name On Card
        this.setExpiryMonth(strExpiryMonth);
        //Fill Name On Card
        this.setSecurityCode(strSecurityCode);
        //Click Login button
        this.clickConfirm();
    }

    public Boolean visibilityOfOrderConfirmation() {
        WebDriverWait waitLocal = new WebDriverWait(driver, 20);
        waitLocal.until(ExpectedConditions.visibilityOf(lblOrderConfirmation));
        return lblOrderConfirmation.isDisplayed();
    }
}
