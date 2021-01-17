package stepdefinitions;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CukeHooks extends MasterStepDefs {

	@Before
	public static void setUp(Scenario scenario) {
		LOG.info("Starting - " + scenario.getName());
	}

	/*
	 * @After public static void tearDown(Scenario scenario) {
	 * LOG.info("=================== Inside After Hook ================ "); }
	 */
}