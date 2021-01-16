package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.time.LocalDateTime ;
import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jayway.jsonpath.JsonPath;

import stepdefinitions.CukeHooks;

public class TestPlan {

	private TestPlan() {
	}

	protected static final Logger LOG = LoggerFactory.getLogger(TestPlan.class);
	protected static ArrayList<String> TestPoints=new ArrayList<String>();
	protected static ArrayList<String> TestresultStatus=new ArrayList<String>();
	protected static Map<String,String> Testresults=new HashMap<String,String>();

	public static void updateTestResults(String planName, Map<String, String> details) {
		LocalDateTime  timenow = LocalDateTime.now();
		String testplanID = getTestPlanID(planName);
		for (Entry<String, String> detail : details.entrySet()) {
    	   String suiteID = getSuiteID(testplanID, detail.getKey());
    	   String testcaseID = getTestCaseID(testplanID, suiteID, detail.getValue().split(":")[0]);
    	   String testpointID = getTestPointID(testplanID, suiteID, testcaseID);
    	   TestPoints.add(testpointID); 
    	   TestresultStatus.add(detail.getValue().split(":")[1]);
    	}
		String runId = createTestRun(planName+" "+timenow, testplanID, TestPoints);
		String resultIDbody = getTestResultIDBody(runId);
		String resultid = "";
		for (int i=0;i<TestPoints.size();i++)
		{
			resultid = JsonPath.read(resultIDbody, "$.value.[?(@.testPoint.id == '"+TestPoints.get(i)+"')].id").toString();
			resultid = resultid.replace("]", "").replace("[", "").trim();
			Testresults.put(resultid, TestresultStatus.get(i));
		}
		updateTestStatus(runId, Testresults);
		TestPoints.clear();
		TestresultStatus.clear();
		Testresults.clear();
	}

	public static String APIRequest(String URL, String method, String payload) throws IOException {
		String basicAuthorization = ":" + System.getProperty("pat");// ":lupunhvemn7sl3n7d76zcwdzz2wvxfat2ggudj4ojf5exqmnboya";
		String responsebody = "";
		URL url = new URL(URL);
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Authorization",
					"Basic " + new String(Base64.encodeBase64(basicAuthorization.getBytes())));
			switch (method) {
			case "POST": {
				connection.setDoOutput(true);
				connection.setRequestMethod(method);
				connection.setRequestProperty("Content-Type", "application/json");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(payload);
				out.flush();
				out.close();
				break;
			}
			case "PATCH": {
				allowMethods("PATCH");
				connection.setDoOutput(true);
				connection.setRequestMethod(method);
				connection.setRequestProperty("Content-Type", "application/json");
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(payload);
				out.flush();
				out.close();
				break;
			}
			case "GET": {
				connection.setRequestMethod(method);
				break;
			}
			}
			InputStreamReader reader = new InputStreamReader(connection.getInputStream());
			StringBuilder buf = new StringBuilder();
			char[] cbuf = new char[2048];
			int num;
			while (-1 != (num = reader.read(cbuf))) {
				buf.append(cbuf, 0, num);
			}
			responsebody = buf.toString();

		} catch (IOException ex) {
			LOG.error(ex.getMessage());

		} finally {
			connection.disconnect();
		}
		return responsebody;
	}

	public static String payload(String title) throws IOException {

		String payload = "[\r\n" + "  {\r\n" + "    \"op\": \"add\",\r\n"
				+ "    \"path\": \"/fields/System.Title\",\r\n" + "    \"from\": null,\r\n" + "    \"value\": \""
				+ title + "\"\r\n" + "  },\r\n" + "  {\r\n" + " \"op\": \"add\",\r\n"
				+ " \"path\": \"/fields/System.AssignedTo\",\r\n" + " \"from\": null,\r\n"
				+ " \"value\": \"401532@cognizant.com\"\r\n" + "}\r\n" + "]";

		return payload;
	}

	public static String getTestPlanID(String planName) {
		String planID = null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/plans?api-version=5.0";
			String response = APIRequest(URL, "GET", "");
			String jsonpath = "$.value.[?(@.name == '" + planName + "')].id";
			planID = JsonPath.read(response, jsonpath).toString();
			planID = planID.replace("]", "").replace("[", "").replace("\"", "");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return planID;
	}

	public static String getSuiteID(String planID, String suitename) {
		String suiteID = null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/plans/" + planID + "/suites?api-version=5.0";
			String response = APIRequest(URL, "GET", "");
			String jsonpath = "$.value.[?(@.name == '" + suitename + "')].id";
			suiteID = JsonPath.read(response, jsonpath).toString();
			suiteID = suiteID.replace("]", "").replace("[", "").replace("\"", "");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return suiteID;
	}

	public static String getTestCaseID(String planID, String suiteID, String testcasename) {
		String testcaseID = null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/plans/" + planID + "/suites/" + suiteID
					+ "/points?api-version=5.0";
			String response = APIRequest(URL, "GET", "");
			String jsonpath = "$..[?(@.name == '" + testcasename + "')].id";
			testcaseID = JsonPath.read(response, jsonpath).toString();
			testcaseID = testcaseID.replace("]", "").replace("[", "").replace("\"", "");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return testcaseID;
	}

	public static String getTestResultIDBody(String runID) {
		String response=null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/runs/" + runID + "/results?api-version=6.0-preview.6";
			response = APIRequest(URL, "GET", "");
			} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return response;
	}
	
	public static String getTestPointID(String planID, String suiteID, String tcID) {
		String pointID = null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/plans/" + planID + "/suites/" + suiteID
					+ "/points?testCaseId="+tcID+"&api-version=5.0";
			String response = APIRequest(URL, "GET", "");
			String jsonpath = "$.value.[*].id";
			pointID = JsonPath.read(response, jsonpath).toString();
			pointID = pointID.replace("]", "").replace("[", "").replace("\"", "");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return pointID;
	}
	
	public static String createTestRun(String RunName, String planID,  ArrayList<String> Points) {
		String runID = null;
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/runs?api-version=5.0";
			String testpoints = "";
			for (String Point : Points)
			 {
				 testpoints+=Point+",\n";
			 }
			 testpoints = testpoints.substring(0,testpoints.lastIndexOf(",\n"));
			 String payload = "{\r\n" + 
			 		"  \"name\": \""+RunName+"\",\r\n" + 
			 		"  \"plan\": {\r\n" + 
			 		"    \"id\": \""+planID+"\"\r\n" + 
			 		"  },\r\n" + 
			 		"  \"pointIds\": [\r\n" + 
			 		"   "+testpoints+"\r\n" + 
			 		"  ]\r\n" + 
			 		"}";
			String response = APIRequest(URL, "POST", payload);
			String jsonpath = "$.id";
			runID = JsonPath.read(response, jsonpath).toString();
			runID = runID.replace("]", "").replace("[", "").replace("\"", "");
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
		return runID;
	}
	
	public static void updateTestStatus(String runID, Map<String,String> Results) {
		try {
			String URL = "https://dev.azure.com/" + System.getProperty("organization") + "/"
					+ System.getProperty("project") + "/_apis/test/runs/"+runID+"/results?api-version=6.0-preview.6";
			String testpoints = "";
			for (Entry<String, String> result : Results.entrySet())
			 {
				 testpoints+="{\r\n" + 
				 		"    \"id\": "+result.getKey()+",\r\n" + 
				 		"    \"outcome\": \""+result.getValue()+"\",\r\n" + 
				 		"    \"state\": \"Completed\",\r\n" + 
				 		"    \"comment\": \"Execution done\",\r\n" + 
				 		"    \"automatedTestType\": \"Maven Test\",\r\n" + 
				 		"    \"automatedTestName\": \"Maven Test\"\r\n" + 
				 		"  }"+",";
			 }
			 String payload ="["+testpoints.substring(0,testpoints.lastIndexOf(","))+"]";
			 APIRequest(URL, "PATCH", payload);
		} catch (Exception e) {
			LOG.error(e.getMessage());
		}
	}

	private static void allowMethods(String... methods) {
		try {
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);
			methodsField.setAccessible(true);
			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);
			methodsField.set(null, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
