package by.htp.belavia.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import by.htp.belavia.steps.CommonSteps;

public abstract class BaseTest {

	protected CommonSteps steps;

	@BeforeSuite
	public void setUpTest() {
		steps = new CommonSteps();
		steps.initBrowser();
	}

	@AfterSuite
	public void closeDriver() {
		steps.closeDriver();
	}

}
