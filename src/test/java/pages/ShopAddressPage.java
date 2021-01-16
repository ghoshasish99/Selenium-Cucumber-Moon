package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageFactory For ShopPage
 */
public class ShopAddressPage extends ShopMasterPageWeb {

    /**
     * All WebElements are identified by @FindBy annotation
     */

    @FindBy(id = "datitle")
    WebElement txtTitle;

    @FindBy(id = "dafirstname")
    WebElement txtFirstName;

    @FindBy(id = "dalastname")
    WebElement txtLastName;

    @FindBy(id = "daaddressline1")
    WebElement txtAddresslineOne;

    @FindBy(id = "daaddressline2")
    WebElement txtAddresslineTwo;


    @FindBy(id = "dacity")
    WebElement txtCity;

    @FindBy(id = "dastateprovinceregion")
    WebElement txtState;

    @FindBy(id = "dazippostcode")
    WebElement txtZipcode;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    WebElement btnNext;

    public ShopAddressPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //Set title in title textbox
    public void setTitle(String strTitle) {
        txtTitle.sendKeys(strTitle);
    }

    //Set FirstName in FirstName textbox
    public void setFirstName(String strFirstName) {
        txtFirstName.sendKeys(strFirstName);
    }

    //Set LastName in LastName textbox
    public void setLastName(String strLastName) {
        txtLastName.sendKeys(strLastName);
    }

    //Set Address line 1  in AddresslineOne textbox
    public void setAddresslineOne(String strAddLineOne) {
        txtAddresslineOne.sendKeys(strAddLineOne);
    }

    //Set Address line 2  in AddresslineTwo textbox
    public void setAddresslineTwo(String strAddLineTwo) {
        txtAddresslineTwo.sendKeys(strAddLineTwo);
    }

    //Set State in City textbox
    public void setCity(String strCity) {
        txtCity.sendKeys(strCity);
    }

    //Set State in State textbox
    public void setState(String strState) {
        txtState.sendKeys(strState);
    }

    //Set Zipcode in Zipcode textbox
    public void setZipcode(String strZip) {
        txtZipcode.sendKeys(strZip);
    }

    //Click on Next button
    public void clickNext() {
        btnNext.click();
    }

    /**
     * This POM method will be exposed in test case to enter Address details in the application
     *
     * @param strTitle
     * @param strFirstName
     * @param strLastName
     * @param AddresslineOne
     * @param AddresslineTwo
     * @param strCity
     * @param strState
     * @param strZip
     * @return
     */
    public void fillAddressDetails(String strTitle, String strFirstName, String strLastName, String AddresslineOne, String AddresslineTwo,String strCity,String strState, String strZip ) {
        //Fill Title
        this.setTitle(strTitle);
        //Fill FirstName
        this.setFirstName(strFirstName);
        //Fill LastName
        this.setLastName(strLastName);
        //Fill AddresslineOne
        this.setAddresslineOne(AddresslineOne);
        //Fill AddresslineTwo
        this.setAddresslineTwo(AddresslineTwo);
        //Fill City
        this.setCity(strCity);
        //Fill strState
        this.setState(strState);
        //Fill strZip
        this.setZipcode(strZip);
    }

}
