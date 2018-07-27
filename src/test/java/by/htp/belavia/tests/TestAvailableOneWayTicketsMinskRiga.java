package by.htp.belavia.tests;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;
import by.htp.belavia.entity.comparator.TicketCostComparator;
import by.htp.belavia.entity.comparator.TicketDateComparator;
import by.htp.belavia.pages.MainPage;

public class TestAvailableOneWayTicketsMinskRiga extends BaseTest {

	private static final String DEPARTURE_COUNTRY = "Минск";
	private static final String ARRIVAL_COUNTRY = "Рига";
	private static final LocalDate DEPARTURE_DATE_START = LocalDate.of(2018, 8, 1);
	private static final LocalDate DEPARTURE_DATE_END = LocalDate.of(2018, 11, 1);
	private static final boolean IS_NOT_RETURN_TICKET = false;

	private SearchFormData searchData;

	@Test
	public void getAvailibleOneWayTicketsMinskRiga() {
		MainPage mainPage = steps.openMainPage();
		searchData = new SearchFormData(DEPARTURE_COUNTRY, ARRIVAL_COUNTRY, DEPARTURE_DATE_START, DEPARTURE_DATE_END,
				IS_NOT_RETURN_TICKET);

		steps.searchTickets(searchData);
		steps.navigateToFareCalendar();
		List<Ticket> tickets = steps.getListOfOneWayTickets(searchData);

		System.out.println("List of one-way tickets sorted by cost");
		Collections.sort(tickets, new TicketCostComparator());
		int i = 1;
		for (Ticket ticket : tickets) {
			System.out.println(i++ + ". " + ticket);
		}

		System.out.println("/nList of one-way tickets csorted by date");
		Collections.sort(tickets, new TicketDateComparator());
		i = 1;
		for (Ticket ticket : tickets) {
			System.out.println(i++ + ". " + ticket);
		}

	}

}
