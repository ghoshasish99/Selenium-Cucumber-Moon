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
        //plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
         plugin = {"junit:target/junitreport.xml","json:target/jsonreport.json"}
        
)
public class TestsRunner {

    private TestsRunner() {

    }
    
    protected static final Logger LOG = LoggerFactory.getLogger(TestsRunner.class);
    protected static Map<String, String> UITest = new HashMap<String, String>();
    protected static Map<String, String> APITest = new HashMap<String, String>();
    protected static Map<String, String> UIRWDTest = new HashMap<String, String>();
    
    @BeforeClass
    public static void beforeClass() {
        LOG.info("================ Inside Before Class ================");

    }
    @AfterClass
    public static void afterClass() throws IOException {
        LOG.info("================ Inside After Class ================");
        for (Entry<String, String> set : CukeHooks.features.entrySet()) {
             if(set.getKey().contains("UITest")) 
            	 UITest.put(set.getKey().split(":")[1], set.getValue()+":"+CukeHooks.status.get(set.getValue()));
             else if(set.getKey().contains("APITest"))
            	 APITest.put(set.getKey().split(":")[1], set.getValue()+":"+CukeHooks.status.get(set.getValue()));
             else
            	 UIRWDTest.put(set.getKey().split(":")[1], set.getValue()+":"+CukeHooks.status.get(set.getValue()));
		}
        if(!UITest.isEmpty()) {
        	TestPlan.updateTestResults("UITest", UITest);
        }
        if(!APITest.isEmpty()) {
        	TestPlan.updateTestResults("APITest", APITest);
        }
        if(!UIRWDTest.isEmpty()) {
        	TestPlan.updateTestResults("UIRWDTest", UIRWDTest);
        }
    }
    
    
}
