package stepdefinitions;



import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.APIReusuableLibrary;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.*;


public class APIEShopStepDefs extends MasterStepDefs {
    ValidatableResponse response;
    String strProductEndpointUri;
    String strCreditCardTransactionsEndpointUri;
    String strRegistrationEndpointUri;
    String strRegistrationPayload;
    String strPostBodyContent;
    String strApiHost;


    public APIEShopStepDefs() {
        String strBackendIP = System.getenv("backendPrivateIP");
        if (strBackendIP == null || strBackendIP.isEmpty()) {
            strApiHost = System.getProperty("apiHost");
            LOG.info("API URL Fetched from POM Settings");
        } else {
            strApiHost = strBackendIP;
            LOG.info("API URL Fetched from AWS ENVIRONMENT");
        }
        String strApiHostURL = "http://" + strApiHost + ":8180";
        LOG.info("API URL : " + strApiHostURL);
        strProductEndpointUri = strApiHostURL + readData().getProperty("ProductEndpoint");
        strCreditCardTransactionsEndpointUri = strApiHostURL + readData().getProperty("CreditCardTransactionsEndpoint");
        strRegistrationEndpointUri = strApiHostURL + readData().getProperty("RegistrationEndpoint");
    }

    @Given("^An API endpoint for Products$")
    public void userSetGETPostsAPIEndpoint() {
        strProductEndpointUri = strProductEndpointUri + "all";
    }

    @When("^User send GET HTTP request$")
    public void sendGETHTTPRequest() {
        LOG.info("Executed this step");
    }

    @Then("^User receive valid HTTP response code \"([^\"]*)\"$")
    public void UserReceiveValidHTTPResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strProductEndpointUri, APIReusuableLibrary.SERVICEMETHOD.GET, null, Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Given("^An API endpoint for Products with \"([^\"]*)\"$")
    public void userSetGETPostsAPIEndpointFor(String strName) {
        strProductEndpointUri = strProductEndpointUri + "search?productName=" + strName;
    }

    @And("^User set request body$")
    public void setRequestBody() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("PositivePaymentInputTemplate"));
    }

    @And("^User set invalid request body$")
    public void setInvalidRequestBody() {
        strPostBodyContent = apiDriver.readInput(readData().getProperty("NegativePaymentInputTemplate"));
    }

    @And("^User send POST HTTP request$")
    public void sendPOSTHTTPRequest() {
        LOG.info("Executed this step");
    }

    @Then("^User receive HTTP response code \"([^\"]*)\"$")
    public void UserReceiveHTTPResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strCreditCardTransactionsEndpointUri, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strPostBodyContent, null,
                    Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("^Response body contains all the expected fields$")
    public void responseBodyContainsAllTheExpectedFields() {
        response.body("status", equalTo("OK")).body("id", notNullValue()).body("additionalProperties.result", equalTo("Transaction Success")).body("additionalProperties.isTestMode", equalTo("true"));
    }

    @And("^Response body contains all the valid fields for all the products$")
    public void responseBodyContainsAllTheValidFieldsForAllTheProducts() {
        response.body("id", notNullValue()).body("productCode", notNullValue()).body("productName", notNullValue()).body("productImage", notNullValue()).body("productDescription", notNullValue()).body("productPrice", notNullValue()).body("productQuantity", notNullValue()).body("inStock", notNullValue());
    }

    @Given("^An API endpoint for Payment$")
    public void anAPIEndpointForPayment() {
        LOG.info("Executed this step");
    }

    @Given("An API endpoint for Registration")
    public void anAPIEndpointForRegistration() {
        LOG.info("Executed this step");
    }

    @And("User set request body with {string},{string},{string} and {string}")
    public void userSetRequestBodyWithAnd(String strFirstName, String strLastName, String strEmail, String strPassword) {
        strRegistrationPayload = apiDriver.registrationPayload(strFirstName, strLastName, strEmail, strPassword);
    }

    @Then("User receive response code {string}")
    public void userReceiveResponseCode(String strStatusCode) {
        try {
            response = apiDriver.sendNReceive(strRegistrationEndpointUri, APIReusuableLibrary.SERVICEMETHOD.POST, APIReusuableLibrary.SERVICEFORMAT.JSON, strRegistrationPayload, null,
                    Integer.parseInt(strStatusCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
