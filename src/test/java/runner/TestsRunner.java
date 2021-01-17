package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import stepdefinitions.CukeHooks;
import runner.TestPlan;


@RunWith(Cucumber.class)
@CucumberOptions(strict = true, monochrome = true,
        features = "src/test/resources/features/",
        tags = "@UITest and @Smoke",
        glue = {"stepdefinitions"},
        plugin = {"junit:target/junitreport.xml","json:target/jsonreport.json","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        
)
public class TestsRunner {

    private TestsRunner() {

    }
    
    protected static final Logger LOG = LoggerFactory.getLogger(TestsRunner.class);
    
    @BeforeClass
    public static void beforeClass() {
        LOG.info("================ Inside Before Class ================");

    }
    @AfterClass
    public static void afterClass() throws IOException {
        LOG.info("================ Inside After Class ================");
    }
    
    
}
