package by.htp.belavia.pages;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;

public class FareCalendarOneWayTicketsPage extends AbstractFareCalendarPage {

	public FareCalendarOneWayTicketsPage(WebDriver driver) {
		super(driver);
	}

	public List<Ticket> getListOfTickets(SearchFormData searchData) {
		List<Ticket> tickets = new ArrayList<Ticket>();

		LocalDate startDate = searchData.getDepartureDateStart();
		LocalDate endDate = searchData.getDepartureDateEnd();
		LocalDate actualDate = null;
		do {
			List<WebElement> ticketsElements = driver.findElements(By.xpath(ELEMENTS_WITH_PRICE_XPATH));
			for (WebElement w : ticketsElements) {
				WebElement input = w.findElement(By.xpath(INPUT_WITH_VALUE_DATE_XPATH));
				actualDate = LocalDate.parse(input.getAttribute("value"), FORMATTER);
				if (!actualDate.isBefore(startDate) && !actualDate.isAfter(endDate)) {
					Ticket ticket = createTicket(w, searchData, actualDate);
					tickets.add(ticket);
				}
			}
			if (!actualDate.isAfter(endDate)) {
				clickNextRightButton();
			}
		} while (!actualDate.isAfter(endDate));
		return tickets;
	}

	private Ticket createTicket(WebElement parentElement, SearchFormData searchData, LocalDate actualDate) {
		Ticket ticket = new Ticket();

		ticket.setDepartureCountry(searchData.getDepartureCountry());
		ticket.setArrivalCountry(searchData.getArrivalCountry());
		ticket.setDate(actualDate);

		WebElement label = parentElement.findElement(By.xpath(LABEL_WITH_COST_XPATH));
		String[] s = label.getText().split(" ");
		double cost = Double.parseDouble(s[0].replaceAll(",", "."));

		ticket.setCost(cost);
		ticket.setCurrency(s[1]);
		return ticket;
	}

}
