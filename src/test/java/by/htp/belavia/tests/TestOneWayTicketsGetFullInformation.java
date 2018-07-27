package by.htp.belavia.tests;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import by.htp.belavia.entity.OneWayDetailsTicket;
import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.comparator.TicketCostComparator;
import by.htp.belavia.pages.MainPage;
import by.htp.belavia.pages.SearchResultPage;

public class TestOneWayTicketsGetFullInformation extends BaseTest {

	private static final String DEPARTURE_COUNTRY = "Минск";
	private static final String ARRIVAL_COUNTRY = "Рига";
	private static final LocalDate DEPARTURE_DATE_START = LocalDate.of(2018, 8, 1);
	private static final LocalDate DEPARTURE_DATE_END = LocalDate.of(2018, 8, 5);
	private static final boolean IS_NOT_RETURN_TICKET = false;

	private SearchFormData searchData;

	@Test
	public void getAvailibleOneWayTicketsMinskRiga() {
		List<OneWayDetailsTicket> detailsOneWayTickets = new ArrayList<>();

		LocalDate curentDate = DEPARTURE_DATE_START;
		while (!curentDate.isAfter(DEPARTURE_DATE_END)) {

			MainPage mainPage = steps.openMainPage();

			searchData = new SearchFormData(DEPARTURE_COUNTRY, ARRIVAL_COUNTRY, curentDate, null, IS_NOT_RETURN_TICKET);
			SearchResultPage resultPage = steps.searchTickets(searchData);
			if (resultPage.isResultFound()) {
				detailsOneWayTickets.addAll(steps.getDetailsOneWayTickets(curentDate));
			}
			curentDate = curentDate.plusDays(1);
		}

		System.out.println("One-Way detail tickets sorted by date: ");
		for (int i = 0; i < detailsOneWayTickets.size(); i++) {
			System.out.println((i + 1) + ". " + detailsOneWayTickets.get(i));
		}

		Collections.sort(detailsOneWayTickets, new TicketCostComparator());
		System.out.println("\nOne-Way detail tickets sorted by cost: ");
		for (int i = 0; i < detailsOneWayTickets.size(); i++) {
			System.out.println((i + 1) + ". " + detailsOneWayTickets.get(i));
		}

	}

}
