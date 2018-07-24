package by.htp.belavia.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;

public class FareCalendarPage extends SearchResultPage {

	private static final String ELEMENTS_WITH_PRICE_XPATH = "//div[@class='price']/div";
	private static final String INPUT_WITH_VALUE_DATE_XPATH = "input";
	private static final String LABEL_WITH_COST_XPATH = "label";
	private static final String NEXT_SEVEN_DAYS_BUTTON_XPATH = "//div[@class='nav-right']/a";

	private WebElement nextDaysButton;

	public FareCalendarPage(WebDriver driver) {
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
				actualDate = LocalDate.parse(input.getAttribute("value"), DateTimeFormatter.ofPattern("yy-MM-dd"));
				if (!actualDate.isBefore(startDate) && !actualDate.isAfter(endDate)) {
					Ticket ticket = new Ticket();

					ticket.setDepartureCountry(searchData.getDepartureCountry());
					ticket.setArrivalCountry(searchData.getArrivalCountry());
					ticket.setDate(actualDate);

					WebElement label = w.findElement(By.xpath(LABEL_WITH_COST_XPATH));
					String[] s = label.getText().split(" ");
					double cost = Double.parseDouble(s[0].replaceAll(",", "."));

					ticket.setCost(cost);
					ticket.setCurrency(s[1]);
					tickets.add(ticket);
				}
			}
			if (!actualDate.isAfter(endDate)) {
				nextDaysButton = driver.findElement(By.xpath(NEXT_SEVEN_DAYS_BUTTON_XPATH));
				nextDaysButton.click();
			}
		} while (!actualDate.isAfter(endDate));
		for (Ticket t : tickets) {
			System.out.println(t);
		}
		return tickets;
	}

}
