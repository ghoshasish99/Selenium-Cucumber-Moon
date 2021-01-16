package stepdefinitions;


import framework.APIReusuableLibrary;
import framework.WebDriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;

import java.io.File;
import java.io.FileInputStream;


import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class MasterStepDefs {

    WebDriver driver;
    WebDriverWait wait;
    protected static final Logger LOG = LoggerFactory.getLogger(MasterStepDefs.class);
    protected APIReusuableLibrary apiDriver = new APIReusuableLibrary();
    private static final int TIMEOUT = 30;

    /**
     * Function to initialize Driver
     *
     * @param strDevice Device Name
     */

    public void initializeDriver(String strDevice) {
        String strAppHost;
        driver = WebDriverFactory.createWebDriverInstance(strDevice);
        wait = new WebDriverWait(driver, MasterStepDefs.TIMEOUT);
        String strFrontendIP = System.getenv("backendPrivateIP");
        if (strFrontendIP == null || strFrontendIP.isEmpty()) {
            strAppHost = System.getProperty("applicationHost");
            LOG.info("UI URL Fetched from POM Settings");
        } else {
            strAppHost = strFrontendIP;
            LOG.info("UI URL Fetched from AWS ENVIRONMENT");
        }

        String strAppHostURL = "http://" + strAppHost + ":3000";
        String strAppUrl = "ApplicationUrl : " + strAppHostURL;
        LOG.info(strAppUrl);
        driver.get(strAppHostURL.trim());
        driver.manage().timeouts().implicitlyWait(MasterStepDefs.TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    /**
     * Function to get Data
     *
     * @return Properties Object
     */

    public static Properties readData() {
        Properties objProp = new Properties();
        try {
            File file = new File("TestSettings.properties");
            FileInputStream fileInput = null;
            fileInput = new FileInputStream(file);
            objProp.load(fileInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objProp;
    }

    /**
     * Function to Take Screenshot
     *
     * @param driver Web driver
     */

    public static String getScreenhot(WebDriver driver) {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;
    }

}
