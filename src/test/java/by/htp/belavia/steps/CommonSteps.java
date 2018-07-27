package by.htp.belavia.steps;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.WebDriver;

import by.htp.belavia.driver.DriverSingleton;
import by.htp.belavia.entity.ReturnTicket;
import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;
import by.htp.belavia.pages.AbstractFareCalendarPage;
import by.htp.belavia.pages.FareCalendarOneWayTicketsPage;
import by.htp.belavia.pages.FareCalendarReturnTicketsPage;
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

	public void navigateToFareCalendar() {
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		if (searchResultPage.isResultFound()) {
			SuccessSearchResultPage resultPage = new SuccessSearchResultPage(driver);
			resultPage.navigateToFareCalendar();
		}
	}

	public List<Ticket> getListOfOneWayTickets(SearchFormData searchData) {
		AbstractFareCalendarPage fareCalendar = new FareCalendarOneWayTicketsPage(driver);
		List<Ticket> tickets = fareCalendar.getListOfTickets(searchData);
		return tickets;
	}

	public List<ReturnTicket> getListOfReturnTickets(SearchFormData searchData) {
		AbstractFareCalendarPage fareCalendar = new FareCalendarReturnTicketsPage(driver);
		List<ReturnTicket> tickets = fareCalendar.getListOfTickets(searchData);
		return tickets;
	}

	public void getDetailsTicket(LocalDate flightExpectedDate) {
		SuccessSearchResultPage sp = new SuccessSearchResultPage(driver);
		sp.getDetailsTicket(flightExpectedDate);
	}

}
