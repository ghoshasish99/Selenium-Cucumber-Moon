package stepdefinitions;


import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import org.junit.Assert;
import pages.ShopAddressPage;
import pages.ShopLoginPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import pages.ShopPaymentsPage;
import pages.ShopProductsPage;

import java.io.IOException;

public class WebEShopStepDefs extends MasterStepDefs {

    ShopLoginPage shopLoginPage;
    ShopAddressPage shopAddressPage;
    ShopPaymentsPage objPaymentPage;
    ShopProductsPage shopProductsPage;

    @Given("^User launched eshop login page in \"([^\"]*)\"$")
    public void user_launched_eshop_login_page_in(String strDevice) {

        initializeDriver(strDevice);
        shopLoginPage = new ShopLoginPage(driver);
        shopAddressPage = new ShopAddressPage(driver);
        objPaymentPage = new ShopPaymentsPage(driver);
        shopProductsPage = new ShopProductsPage(driver);
        shopLoginPage.waitForLoad(driver);
        shopLoginPage.clickMenu();
        /* ExtentCucumberAdapter.addTestStepLog("Application launched successfully");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @When("^User create account with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\" and \"([^\"]*)\"$")
    public void user_create_account_with_and(String strFirstName, String strLastName, String strEmailID, String strPassword) throws InterruptedException {
        shopLoginPage.waitForLoad(driver);
        shopLoginPage.clickMenu();
       // ExtentCucumberAdapter.addTestStepLog("Application launched successfully");
        //shopLoginPage.clickMenu();
        shopLoginPage.clickLoginMenu();
        shopLoginPage.clickCreateAccount();
        Thread.sleep(5000);
        shopLoginPage.CreatAccount(strFirstName, strLastName, strEmailID, strPassword);
        shopLoginPage.clickCreateAccount();
       /* ExtentCucumberAdapter.addTestStepLog("Account Created");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Then("^User account should get created$")
    public void user_account_should_get_created() {
        shopLoginPage.verifyAccountCreation();
    }

    @When("^User logged in eshop using the valid emailid \"([^\"]*)\" and the valid password \"([^\"]*)\"$")
    public void user_is_logged_in_using_the_valid_username_and_the_valid_password(String strEmailID, String strPassword) {
        shopLoginPage.waitForLoad(driver);
        shopLoginPage.clickMenu();
      //  ExtentCucumberAdapter.addTestStepLog("Application launched successfully");
        shopLoginPage.clickMenu();
        shopLoginPage.clickLoginMenu();
        shopLoginPage.loginToShop(strEmailID, strPassword);
        shopLoginPage.clickLogin();
      /*  try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @When("^User logged in eshop using the invalid emailid \"([^\"]*)\" and the invalid password \"([^\"]*)\"$")
    public void user_is_logged_in_using_the_invalid_username_and_the_valid_password(String strEmailID, String strPassword) {
        shopLoginPage.waitForLoad(driver);
        shopLoginPage.clickMenu();
     //   ExtentCucumberAdapter.addTestStepLog("Application launched successfully");
        shopLoginPage.clickMenu();
        shopLoginPage.clickLoginMenu();
        shopLoginPage.loginToShop(strEmailID, strPassword);
        shopLoginPage.clickLogin();
      /*  try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Then("user should see a shop home page$")
    public void i_should_see_a_shop_home_page() {
        Assert.assertTrue("Product Search Element is not Displayed", shopProductsPage.visibilityOfTxtProductSearch());
      /*  ExtentCucumberAdapter.addTestStepLog("Application logged in successfully");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Then("User should not get logged in$")
    public void User_should_get_logged_in() {
        Assert.assertTrue("Customer not found message displayed", shopLoginPage.visibilityOfCustomerNotFound());
      /*  ExtentCucumberAdapter.addTestStepLog("Customer not found message displayed successfully");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @When("^User searches the \"([^\"]*)\"  in the Search box$")
    public void user_searches_the_in_the_Search_box(String strProduct) {
        shopProductsPage.setProduct(strProduct);
        shopProductsPage.clickSerch();
     /*   ExtentCucumberAdapter.addTestStepLog(strProduct + "item  searched");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Then("^User should be able to see the product \"([^\"]*)\" listed$")
    public void user_should_be_able_to_see_the_products_based_upon_his_search(String strProduct) {
        shopProductsPage.verifyProductDetailsPage(strProduct);
     /*   ExtentCucumberAdapter.addTestStepLog("Product displayed");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @When("^User searches for the \"([^\"]*)\"$")
    public void user_searches_the(String strProduct) {
        shopProductsPage.setProduct(strProduct);
        shopProductsPage.clickSerch();
     /*   ExtentCucumberAdapter.addTestStepLog("Product searched");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Then("^User should be able to view the listed product \"([^\"]*)\"$")
    public void user_should_be_able_to_view_the_listed_product(String strProduct) {
        shopProductsPage.verifyProductListed(strProduct);
      /*  ExtentCucumberAdapter.addTestStepLog("Product listed");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Then("^User should be able to add the  \"([^\"]*)\" to the cart$")
    public void user_should_be_able_to_add_the_to_the_cart(String strProduct) {
        shopProductsPage.verifyProductListed(strProduct);
        shopProductsPage.selectProduct(strProduct);
        shopProductsPage.verifyProductDetailsPage(strProduct);
        shopProductsPage.clickAddToYourBasket();
        shopProductsPage.clickBasket();
   /*     ExtentCucumberAdapter.addTestStepLog(strProduct + " Added into Cart");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @When("^User adds the  \"([^\"]*)\" to the cart$")
    public void user_adds_the_to_the_cart(String strProduct) {
        shopProductsPage.setProduct(strProduct);
        shopProductsPage.clickSerch();
        shopProductsPage.verifyProductListed(strProduct);
        shopProductsPage.selectProduct(strProduct);
        shopProductsPage.verifyProductDetailsPage(strProduct);
        shopProductsPage.clickAddToYourBasket();
        shopProductsPage.clickBasket();
        shopProductsPage.clickCheckOut();
      /*  ExtentCucumberAdapter.addTestStepLog(strProduct + " Checked Out");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    @When("^User Address details with \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\"$")
    public void user_Address_details_with(String strTitle, String strFirstName, String strLastName, String AddresslineOne, String AddresslineTwo, String strCity, String strState, String strZip) {
        shopAddressPage.fillAddressDetails(strTitle, strFirstName, strLastName, AddresslineOne, AddresslineTwo, strCity, strState, strZip);
     /*   ExtentCucumberAdapter.addTestStepLog("Address details entered");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        shopAddressPage.clickNext();
    }


    @When("^User Payment details with \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
    public void user_Payment_details_with(String strCardNumber, String strNameOnCard, String strExpiryYear, String strExpiryMonth, String strSecurityCode) {
        objPaymentPage.fillPaymentDetails(strCardNumber, strNameOnCard, strExpiryYear, strExpiryMonth, strSecurityCode);
     /*   ExtentCucumberAdapter.addTestStepLog("Payment details entered");
        try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        objPaymentPage.clickConfirm();
    }

    @Then("^User should get the Confirmation of Order$")
    public void user_should_get_the_Confirmation_of_Order() {

     /*   try {
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(getScreenhot(driver));
        } catch (IOException e) {
            e.printStackTrace();
        } */
    }

    @After
    public void tearDown(Scenario scenario) {
        //  quit
        if (driver != null) {
            driver.quit();
        }
    }
}
