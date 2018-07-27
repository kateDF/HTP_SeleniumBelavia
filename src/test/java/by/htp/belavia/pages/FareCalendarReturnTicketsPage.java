package by.htp.belavia.pages;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.entity.ReturnTicket;
import by.htp.belavia.entity.SearchFormData;

public class FareCalendarReturnTicketsPage extends AbstractFareCalendarPage {

	private static final String NEXT_UP_DAYS_BUTTON_XPATH = "//div[@class='d-inbound']/div[@class='nav-right']/a";
	private static final String NEXT_DOWN_DAYS_BUTTON_XPATH = "//div[@class='d-inbound']/div[@class='nav-left']/a";

	private WebElement nextUpDaysButton;
	private WebElement nextDownDaysButton;

	public FareCalendarReturnTicketsPage(WebDriver driver) {
		super(driver);
	}

	public List<ReturnTicket> getListOfTickets(SearchFormData searchData) {
		List<ReturnTicket> returnTickets = new ArrayList<ReturnTicket>();
		LocalDate startDate = searchData.getDepartureDateStart();
		LocalDate endDate = searchData.getDepartureDateEnd();
		LocalDate startReturnDate = searchData.getReturnDateStart();
		LocalDate endReturnDate = searchData.getReturnDateEnd();
		LocalDate actualDate = null;
		LocalDate actualReturnDate = null;

		int mark = 0;
		do {
			List<WebElement> ticketsElements = driver.findElements(By.xpath(ELEMENTS_WITH_PRICE_XPATH));
			for (WebElement w : ticketsElements) {
				ReturnTicket returnTicket = null;

				WebElement input = w.findElement(By.xpath(INPUT_WITH_VALUE_DATE_XPATH));
				String[] dates = input.getAttribute("value").split(":");
				actualDate = LocalDate.parse(dates[0], DateTimeFormatter.ofPattern("yy-MM-dd"));
				actualReturnDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("yy-MM-dd"));

				if (!actualDate.isBefore(startDate) && !actualDate.isAfter(endDate)
						&& !actualReturnDate.isBefore(startReturnDate) && !actualReturnDate.isAfter(endReturnDate)) {
					returnTicket = new ReturnTicket();
					returnTicket.setDepartureCountry(searchData.getDepartureCountry());
					returnTicket.setArrivalCountry(searchData.getArrivalCountry());
					returnTicket.setDate(actualDate);
					returnTicket.setReturnDate(actualReturnDate);

					WebElement label = w.findElement(By.xpath(LABEL_WITH_COST_XPATH));
					String[] s = label.getText().split(" ");
					double cost = Double.parseDouble(s[0].replaceAll(",", "."));

					returnTicket.setCost(cost);
					returnTicket.setCurrency(s[1]);
					returnTickets.add(returnTicket);
				}
			}

			if (mark == 0) {
				try {
					clickNextUpDaysButton();
				} catch (NoSuchElementException e) {
					if (!actualDate.isAfter(searchData.getDepartureDateEnd())) {
						clickNextRightButton();
						mark = 1;
					}
				}
			} else if (mark == 1 && !actualDate.isAfter(searchData.getDepartureDateEnd())) {
				if (!actualReturnDate.isAfter(searchData.getReturnDateEnd())) {
					clickNextDownDaysButton();
				} else {
					clickNextRightButton();
					mark = 0;
				}
			}

		} while (!actualDate.isAfter(searchData.getDepartureDateEnd()));

		return returnTickets;
	}

	private void clickNextUpDaysButton() {
		nextUpDaysButton = driver.findElement(By.xpath(NEXT_UP_DAYS_BUTTON_XPATH));
		nextUpDaysButton.click();
	}

	private void clickNextDownDaysButton() {
		nextDownDaysButton = driver.findElement(By.xpath(NEXT_DOWN_DAYS_BUTTON_XPATH));
		nextDownDaysButton.click();
	}

}
