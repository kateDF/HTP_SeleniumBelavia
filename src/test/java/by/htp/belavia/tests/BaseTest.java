package by.htp.belavia.tests;

import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import by.htp.belavia.entity.Ticket;
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

	protected static <T extends Ticket> void showTickets(List<T> tickets) {
		for (int i = 0; i < tickets.size(); i++) {
			System.out.println((i + 1) + ". " + tickets.get(i));
		}
	}

}
