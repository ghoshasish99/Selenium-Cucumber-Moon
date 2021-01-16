package stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeHooks extends MasterStepDefs {

	public static Integer passedCount;
	public static Integer failedCount;
	public static Map<String, String> status = new HashMap<String, String>();
	public static Map<String, String> features = new HashMap<String, String>();

	@Before
	public static void setUp(Scenario scenario) {
		LOG.info("Starting - " + scenario.getName());
		String scId = scenario.getId();
		String feature = scId.substring(scId.lastIndexOf('/')+1).split(".feature")[0];
		if (scenario.getSourceTagNames().contains("@UITest"))
			features.put("UITest:" + feature, scenario.getName());
		else if (scenario.getSourceTagNames().contains("@APITest"))
			features.put("APITest:" + feature, scenario.getName());
		else
			features.put("UIRWDTest:" + feature, scenario.getName());
	}

	@After
	public static void tearDown(Scenario scenario) {
		LOG.info("=================== Inside After Hook ================ ");
		status.put(scenario.getName(), scenario.getStatus().toString());
		if (scenario.isFailed()) {
		}
	}
}