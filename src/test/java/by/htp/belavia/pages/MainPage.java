package by.htp.belavia.pages;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import by.htp.belavia.entity.SearchFormData;

public class MainPage extends AbstractPage {

	private static final String BASE_URL = "https://belavia.by/";

	private static final String DEPARTURE_COUNTRY_COMBOBOX_ID = "OriginLocation_Combobox";
	private static final String ARRIVAL_COUNTRY_COMBOBOX_ID = "DestinationLocation_Combobox";
	private static final String ONE_WAY_TICKET_RADIO_BUTTON_XPATH = "//label[@for='JourneySpan_Ow']";
	private static final String RETURN_TICKET_RADIO_BUTTON_XPATH = "//label[@for='JourneySpan_Rt']";
	private static final String DEPARTURE_DATE_INPUT_XPATH = "//input[@id='DepartureDate_Datepicker']/../a";
	private static final String CALENDAR_NEXT_MONTH_BUTTON_XPATH = "//*[@id='calendar']/div/div[2]/div/a";
	private static final String SUBMIT_BUTTON_XPATH = "//*[@id='step-2']/div[4]/div/button";
	private static final String DATE_XPATH = "//td[@data-month='%d'][@data-year='%d']/a[text()='%d']";

	private WebElement departureCountryCombobox;
	private WebElement arrivalCountryCombobox;
	private WebElement oneWayTicketRadioButton;
	private WebElement returnTicketRadioButton;
	private WebElement departureDateInput;
	private WebElement calendarNextMonthButton;
	private WebElement submitButton;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void searchTickets(SearchFormData ticket) {

		departureCountryCombobox = driver.findElement(By.id(DEPARTURE_COUNTRY_COMBOBOX_ID));
		departureCountryCombobox.sendKeys(ticket.getDepartureCountry() + Keys.ENTER);

		arrivalCountryCombobox = driver.findElement(By.id(ARRIVAL_COUNTRY_COMBOBOX_ID));
		arrivalCountryCombobox.sendKeys(ticket.getArrivalCountry() + Keys.ENTER);

		if (!ticket.isReturnTicket()) {
			oneWayTicketRadioButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(ONE_WAY_TICKET_RADIO_BUTTON_XPATH))));
			oneWayTicketRadioButton.click();
		} else {
			returnTicketRadioButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions
					.elementToBeClickable(driver.findElement(By.xpath(RETURN_TICKET_RADIO_BUTTON_XPATH))));
			returnTicketRadioButton.click();
		}

		departureDateInput = driver.findElement(By.xpath(DEPARTURE_DATE_INPUT_XPATH));
		departureDateInput.click();
		selectDate(ticket.getDepartureDate());

		if (ticket.isReturnTicket()) {
			selectDate(ticket.getReturnDate());
		}

		submitButton = driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
		submitButton.click();

	}

	private void selectDate(LocalDate date) {
		WebElement departureDate = null;
		do {
			try {
				System.out.println(
						String.format(DATE_XPATH, date.getMonthValue() - 1, date.getYear(), date.getDayOfMonth()));
				departureDate = driver.findElement(By.xpath(
						String.format(DATE_XPATH, date.getMonthValue() - 1, date.getYear(), date.getDayOfMonth())));
			} catch (NoSuchElementException e) {
				clickNextButtonOnCalendar();
			}
		} while (departureDate == null);
		departureDate.click();
	}

	private void clickNextButtonOnCalendar() {
		calendarNextMonthButton = driver.findElement(By.xpath(CALENDAR_NEXT_MONTH_BUTTON_XPATH));
		calendarNextMonthButton.click();
	}

	@Override
	public void openPage() {
		driver.get(BASE_URL);
	}
}
