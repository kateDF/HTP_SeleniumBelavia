package by.htp.belavia.steps;

import java.util.List;

import org.openqa.selenium.WebDriver;

import by.htp.belavia.driver.DriverSingleton;
import by.htp.belavia.entity.ReturnTicket;
import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;
import by.htp.belavia.pages.FareCalendarPage;
import by.htp.belavia.pages.MainPage;
import by.htp.belavia.pages.SearchResultPage;
import by.htp.belavia.pages.SuccessSearchResultPage;

public class CommonSteps {

	private WebDriver driver;

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public MainPage openMainPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		return mainPage;
	}

	public SearchResultPage searchTickets(SearchFormData ticket) {
		MainPage mainPage = new MainPage(driver);
		mainPage.searchTickets(ticket);
		return new SearchResultPage(driver);
	}

	public FareCalendarPage navigateToFareCalendar() {
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		if (searchResultPage.isResultFound()) {
			SuccessSearchResultPage resultPage = new SuccessSearchResultPage(driver);
			return resultPage.navigateToFareCalendar();
		}
		return new FareCalendarPage(driver);
	}

	public List<Ticket> getListOfTickets(SearchFormData searchData) {
		FareCalendarPage fareCalendar = new FareCalendarPage(driver);
		List<Ticket> tickets = fareCalendar.getListOfTickets(searchData);
		return tickets;
	}

	public List<ReturnTicket> getListOfReturnTickets(SearchFormData searchData) {
		FareCalendarPage fareCalendar = new FareCalendarPage(driver);
		List<ReturnTicket> tickets = fareCalendar.getListOfReturnTickets(searchData);
		return tickets;
	}

}
