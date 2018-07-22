package by.htp.belavia.pages;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
	private static final String DEPARTURE_DATE_INPUT_XPATH = "//*[@id='step-2']/div[2]/div[1]/div/a";
	private static final String RETURN_DATE_INPUT_XPATH = "//*[@id='step-2']/div[2]/div[2]/div/a";
	private static final String CALENDAR_XPATH = "//*[@id=\"calendar\"]/div";
	private static final String CALENDAR_YEAR_XPATH = "//*[@id='calendar']/div/div[1]/div/div/span[2]";
	private static final String CALENDAR_MONTH_XPATH = "//*[@id='calendar']/div/div[1]/div/div/span[1]";
	private static final String CALENDAR_LAST_MONTH_XPATH = "//*[@id='calendar']/div/div[2]/div/div/span[1]";
	private static final String CALENDAR_NEXT_MONTH_BUTTON_XPATH = "//*[@id='calendar']/div/div[2]/div/a";
	private static final String CALENDAR_DAYS_TABLE_XPATH = "//*[@id='calendar']/div/div[1]/table";
	private static final String CALENDAR_DAY_IN_DAYS_TABLE_XPATH = "tbody/tr/td";
	private static final String CALENDAR_DAYS_TABLE_IN_LAST_MONTH_XPATH = "//*[@id='calendar']/div/div[2]/table";
	private static final String SUBMIT_BUTTON_XPATH = "//*[@id='step-2']/div[4]/div/button";

	private WebElement departureCountryCombobox;
	private WebElement arrivalCountryCombobox;
	private WebElement oneWayTicketRadioButton;
	private WebElement returnTicketRadioButton;
	private WebElement departureDateInput;
	private WebElement returnDateInput;
	private WebElement submitButton;
	private WebElement calendarYear;
	private WebElement calendarMonthName;
	private WebElement calendarLastMonth;
	private WebElement calendarNextMonthButton;
	private WebElement calendarDaysTable;

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
		setDate(ticket.getDepartureDate());

		submitButton = driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
		submitButton.click();

	}

	private void setDate(LocalDate date) {
		calendarYear = driver.findElement(By.xpath(CALENDAR_YEAR_XPATH));
		while (date.getYear() != Integer.parseInt(calendarYear.getText())) {
			clickNextButtonOnCalendar();
			calendarYear = driver.findElement(By.xpath(CALENDAR_YEAR_XPATH));
		}

		String expectedMonth = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("ru"));
		calendarMonthName = driver.findElement(By.xpath(CALENDAR_MONTH_XPATH));

		String current = null;
		while (!expectedMonth.equals(calendarMonthName.getText())) {
			current = calendarMonthName.getText();
			clickNextButtonOnCalendar();
			calendarMonthName = driver.findElement(By.xpath(CALENDAR_MONTH_XPATH));
			if (current.equals(calendarMonthName.getText())) {
				calendarLastMonth = driver.findElement(By.xpath(CALENDAR_LAST_MONTH_XPATH));
				if (expectedMonth.equals(calendarLastMonth.getText())) {
					current = "last";
					break;
				} else {
					throw new IllegalArgumentException();
				}
			}
		}

		String expectedDay = Integer.toString(date.getDayOfMonth());
		if (current.equals("last")) {
			calendarDaysTable = driver.findElement(By.xpath(CALENDAR_DAYS_TABLE_IN_LAST_MONTH_XPATH));
		} else {
			calendarDaysTable = driver.findElement(By.xpath(CALENDAR_DAYS_TABLE_XPATH));
		}

		for (WebElement calendarDay : calendarDaysTable.findElements(By.xpath(CALENDAR_DAY_IN_DAYS_TABLE_XPATH))) {
			if (expectedDay.equals(calendarDay.getText())) {
				calendarDay.click();
				break;
			}
		}
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
