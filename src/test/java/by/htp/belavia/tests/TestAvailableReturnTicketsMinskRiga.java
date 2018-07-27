package by.htp.belavia.tests;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import by.htp.belavia.entity.ReturnTicket;
import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.comparator.ReturnTicketDateComparator;
import by.htp.belavia.entity.comparator.TicketCostComparator;
import by.htp.belavia.pages.MainPage;

public class TestAvailableReturnTicketsMinskRiga extends BaseTest {
	private static final String DEPARTURE_COUNTRY = "Минск";
	private static final String ARRIVAL_COUNTRY = "Рига";
	private static final LocalDate DEPARTURE_DATE_START = LocalDate.of(2018, 8, 1);
	private static final LocalDate DEPARTURE_DATE_END = LocalDate.of(2018, 9, 1);
	private static final LocalDate RETURN_DATE_START = LocalDate.of(2018, 8, 1);
	private static final LocalDate RETURN_DATE_END = LocalDate.of(2018, 9, 1);
	private static final boolean IS_RETURN_TICKET = true;

	private SearchFormData searchData;

	@Test
	public void getAvailibleReturnTicketsMinskRiga() {
		MainPage mainPage = steps.openMainPage();
		searchData = new SearchFormData(DEPARTURE_COUNTRY, ARRIVAL_COUNTRY, DEPARTURE_DATE_START, DEPARTURE_DATE_END,
				IS_RETURN_TICKET, RETURN_DATE_START, RETURN_DATE_END);

		steps.searchTickets(searchData);
		steps.navigateToFareCalendar();
		List<ReturnTicket> returnTickets = steps.getListOfReturnTickets(searchData);

		System.out.println("All return tickets  sorted by cost");
		Collections.sort(returnTickets, new TicketCostComparator());
		showTickets(returnTickets);

		System.out.println("\nAll return tickets  sorted by date");
		Collections.sort(returnTickets, new ReturnTicketDateComparator());
		showTickets(returnTickets);
	}
}
