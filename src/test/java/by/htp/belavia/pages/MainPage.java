package by.htp.belavia.pages;

import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public MainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = DEPARTURE_COUNTRY_COMBOBOX_ID)
	private WebElement departureCountryCombobox;

	@FindBy(id = ARRIVAL_COUNTRY_COMBOBOX_ID)
	private WebElement arrivalCountryCombobox;

	@FindBy(xpath = ONE_WAY_TICKET_RADIO_BUTTON_XPATH)
	private WebElement oneWayTicketRadioButton;

	@FindBy(xpath = RETURN_TICKET_RADIO_BUTTON_XPATH)
	private WebElement returnTicketRadioButton;

	@FindBy(xpath = DEPARTURE_DATE_INPUT_XPATH)
	private WebElement departureDateInput;

	@FindBy(xpath = CALENDAR_NEXT_MONTH_BUTTON_XPATH)
	private WebElement calendarNextMonthButton;

	@FindBy(xpath = SUBMIT_BUTTON_XPATH)
	private WebElement submitButton;

	public void searchTickets(SearchFormData ticket) {

		departureCountryCombobox.sendKeys(ticket.getDepartureCountry() + Keys.ENTER);
		arrivalCountryCombobox.sendKeys(ticket.getArrivalCountry() + Keys.ENTER);

		if (!ticket.isReturnTicket()) {
			oneWayTicketRadioButton.click();
		} else {
			returnTicketRadioButton.click();
		}

		departureDateInput.click();
		selectDate(ticket.getDepartureDateStart());

		if (ticket.isReturnTicket()) {
			selectDate(ticket.getReturnDateEnd());
		}

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
		calendarNextMonthButton.click();
	}

	@Override
	public void openPage() {
		driver.get(BASE_URL);
	}
}
