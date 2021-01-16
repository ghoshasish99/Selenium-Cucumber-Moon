package framework;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import runner.TestPlan;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// import software.amazon.awssdk.regions.Region;
// import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
// import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
// import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * DriverFactory which will create respective driver Object
 *
 * @author Cognizant
 */
public class WebDriverFactory {
    /**
     * Function to return the object for WebDriver {@link WebDriver} object
     *
     * @return Instance of the {@link WebDriver} object
     */
	protected static final Logger LOG = LoggerFactory.getLogger(WebDriverFactory.class);
    public static WebDriver createWebDriverInstance(String strDevice) {
        WebDriver driver = null;

        try {

            ChromeOptions chromeOptions;
            FirefoxOptions firefoxOptions;
            URL testGridUrl = null;
            String strExecutionPlatform = System.getProperty("executionPlatform").trim().toUpperCase();
            //LOCAL_CHROME, LOCAL_FIREFOX, AWS_CHROME, AWS_FIREFOX, AWS_DEVICEFARM_CHROME, AWS_DEVICEFARM_FIREFOX
            Map<String, String> mobileEmulation = new HashMap<>();
            //Nexus 7, Galaxy S5, iPad, Pixel 2
            if (!strDevice.isEmpty() && !strDevice.equalsIgnoreCase("Web"))
            {
                mobileEmulation.put("deviceName", strDevice);
            }

            switch (strExecutionPlatform) {
                case "LOCAL_CHROME":
                    chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                    System.setProperty("webdriver.chrome.driver", readData().getProperty("LOCAL_CHROME_DRIVER_PATH").trim());
                    System.out.println("Driver :"+readData().getProperty("LOCAL_CHROME_DRIVER_PATH").trim());
                    // WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "LOCAL_FIREFOX":
                    firefoxOptions = new FirefoxOptions();
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "GRID_CHROME":
                    //chromeOptions = new ChromeOptions();
                    
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setJavascriptEnabled(true);
                    capabilities.setBrowserName(BrowserType.CHROME);
                    capabilities.setPlatform(Platform.ANY);                   
                    try {
                       // driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
                    	driver = new RemoteWebDriver(new URL(System.getProperty("hub")), capabilities);
                       // System.out.println("Hub URL :"+ System.getProperty("hub"));
                    } catch (MalformedURLException e) {
                        LOG.error(e.getMessage());
                    }
                        
                    //System.setProperty("webdriver.chrome.driver", readData().getProperty("LOCAL_CHROME_DRIVER_PATH").trim());
                    
                    //driver = new ChromeDriver(chromeOptions);
                    driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
                    break;
                default:
                     LOG.info("ExecutionPlatform Platform must be set in settings file.");
            }
            LOG.info(strExecutionPlatform + " Driver Creation Completed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return driver;
    }

    public static Properties readData() {
        Properties objProp = new Properties();
        try {
            String env = System.getProperty("env");
            File file = new File("TestSettings.properties");
            FileInputStream fileInput = null;
            fileInput = new FileInputStream(file);
            objProp.load(fileInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objProp;
    }
}


