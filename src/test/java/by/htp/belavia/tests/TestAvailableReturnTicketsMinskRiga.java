package by.htp.belavia.tests;

import java.time.LocalDate;

import org.testng.annotations.Test;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.pages.MainPage;

public class TestAvailableReturnTicketsMinskRiga extends BaseTest {
	private static final String DEPARTURE_COUNTRY = "Минск";
	private static final String ARRIVAL_COUNTRY = "Рига";
	private static final LocalDate DEPARTURE_DATE_START = LocalDate.of(2018, 8, 1);
	private static final LocalDate DEPARTURE_DATE_END = LocalDate.of(2018, 11, 1);
	private static final LocalDate RETURN_DATE_START = LocalDate.of(2018, 10, 1);
	private static final LocalDate RETURN_DATE_END = LocalDate.of(2018, 11, 1);
	private static final boolean IS_RETURN_TICKET = true;

	private SearchFormData ticket;

	@Test
	public void getAvailibleOneWayTicketsMinskRiga() {
		MainPage mainPage = steps.openMainPage();
		ticket = new SearchFormData(DEPARTURE_COUNTRY, ARRIVAL_COUNTRY, DEPARTURE_DATE_START, DEPARTURE_DATE_END,
				IS_RETURN_TICKET, RETURN_DATE_START, RETURN_DATE_END);

		steps.searchTickets(ticket);
	}
}
