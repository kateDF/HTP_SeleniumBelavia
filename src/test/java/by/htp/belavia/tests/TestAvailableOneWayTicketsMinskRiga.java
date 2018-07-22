package by.htp.belavia.tests;

import java.time.LocalDate;

import org.testng.annotations.Test;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.pages.MainPage;

public class TestAvailableOneWayTicketsMinskRiga extends BaseTest {

	private static final String DEPARTURE_COUNTRY = "Минск";
	private static final String ARRIVAL_COUNTRY = "Рига";
	private static final LocalDate DEPARTURE_DATE = LocalDate.of(2018, 8, 1);
	private static final boolean IS_NOT_RETURN_TICKET = false;

	private SearchFormData ticket;

	@Test
	public void getAvailibleOneWayTicketsMinskRiga() {
		MainPage mainPage = steps.openMainPage();
		ticket = new SearchFormData(DEPARTURE_COUNTRY, ARRIVAL_COUNTRY, DEPARTURE_DATE, IS_NOT_RETURN_TICKET);

		steps.searchOneWayTickets(ticket);
	}

}
