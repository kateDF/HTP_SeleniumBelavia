package by.htp.belavia.pages;

import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;

public class FareCalendarPage extends SearchResultPage {

	private static final String TIKETS_MATRIX_ID = "matrix";

	public FareCalendarPage(WebDriver driver) {
		super(driver);
	}

	public Set<Ticket> getListOfTickets(SearchFormData searchData) {
		Set<Ticket> tickets = new TreeSet<>();
		WebElement ticketMatrix = driver.findElement(By.id(TIKETS_MATRIX_ID));

		return tickets;
	}

}
