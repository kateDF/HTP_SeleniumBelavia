package by.htp.belavia.pages;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import by.htp.belavia.entity.OneWayDetailsTicket;
import by.htp.belavia.entity.TicketClassEnum;

public class SuccessSearchResultPage extends SearchResultPage {

	private static final String FARE_CALENDAR_BUTTON_XPATH = "//div[@id='outbound']/div[1]//a";
	private static final String DEPARTURE_COUNTRY_XPATH = "//div[@class='flight-avail']/div[@class='departure']/a";
	private static final String ARRIVAL_COUNTRY_XPATH = "//div[@class='flight-avail']/div[@class='arrival']/a";
	private static final String FLIGHT_DATE_XPATH = "//*[@id=\"outbound\"]//h3";
	private static final String DEPARTURE_TIME_XPATH = "//div[@class='flight-avail']/div[@class='departure']/strong";
	private static final String ARRIVAL_TIME_XPATH = "//div[@class='flight-avail']/div[@class='arrival']/strong";
	private static final String ECONOMY_PROMOTION_COST_XPATH = "//div[@class='ep fare']/label";
	private static final String ECONOMY_RESTRICTED_COST_XPATH = "//div[@class='er fare']/label";
	private static final String ECONOMY_SEMI_FLEXIBLE_COST_XPATH = "//div[@class='sf fare']/label";
	private static final String ECONOMY_FLEXIBLE_COST_XPATH = "//div[@class='ef fare']/label";
	private static final String BUSINESS_COST_XPATH = "//div[@class='bc fare']/label";
	private static final String COST_WITH_TAXES_XPATH = "//*[@id='price']/div/span[@class='amount']";
	private static final String CURRENCY_XPATH = "//*[@id='price']/div/span[@class='currency']";

	@FindBy(xpath = DEPARTURE_COUNTRY_XPATH)
	private WebElement departureCountry;

	@FindBy(xpath = ARRIVAL_COUNTRY_XPATH)
	private WebElement arrivalCountry;

	@FindBy(xpath = FLIGHT_DATE_XPATH)
	private WebElement flightDate;

	@FindBy(xpath = DEPARTURE_TIME_XPATH)
	private WebElement departureTime;

	@FindBy(xpath = ARRIVAL_TIME_XPATH)
	private WebElement arrivalTime;

	@FindBy(xpath = CURRENCY_XPATH)
	private WebElement currency;

	@FindBy(xpath = ECONOMY_PROMOTION_COST_XPATH)
	private WebElement economyPromotion;

	@FindBy(xpath = ECONOMY_RESTRICTED_COST_XPATH)
	private WebElement economyRestricted;

	@FindBy(xpath = ECONOMY_SEMI_FLEXIBLE_COST_XPATH)
	private WebElement economySemiFlexible;

	@FindBy(xpath = ECONOMY_FLEXIBLE_COST_XPATH)
	private WebElement economyFlexible;

	@FindBy(xpath = BUSINESS_COST_XPATH)
	private WebElement businessClass;

	@FindBy(xpath = FARE_CALENDAR_BUTTON_XPATH)
	private WebElement fareCalendarButton;

	private WebElement costWithTaxes;
	private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm");

	public SuccessSearchResultPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public List<OneWayDetailsTicket> getDetailsTicket(LocalDate flightExpectedDate) {
		List<OneWayDetailsTicket> tickets = new ArrayList<>();

		OneWayDetailsTicket commonTicketDetails = getCommonDetails(flightExpectedDate);

		try {
			OneWayDetailsTicket newTicket = getTicketWithTax(TicketClassEnum.ECONOMY_PROMOTION, economyPromotion,
					commonTicketDetails);
			tickets.add(newTicket);
		} catch (NoSuchElementException e) {

		}

		try {
			OneWayDetailsTicket newTicket = getTicketWithTax(TicketClassEnum.ECONOMY_RESTRICTED, economyRestricted,
					commonTicketDetails);
			tickets.add(newTicket);
		} catch (NoSuchElementException e) {

		}

		try {
			OneWayDetailsTicket newTicket = getTicketWithTax(TicketClassEnum.ECONOMY_SEMI_FLEXIBLE, economySemiFlexible,
					commonTicketDetails);
			tickets.add(newTicket);
		} catch (NoSuchElementException e) {

		}

		try {
			OneWayDetailsTicket newTicket = getTicketWithTax(TicketClassEnum.ECONOMY_FLEXIBLE, economyFlexible,
					commonTicketDetails);
			tickets.add(newTicket);
		} catch (NoSuchElementException e) {

		}

		try {
			OneWayDetailsTicket newTicket = getTicketWithTax(TicketClassEnum.BUSINESS, businessClass,
					commonTicketDetails);
			tickets.add(newTicket);
		} catch (NoSuchElementException e) {

		}

		for (OneWayDetailsTicket t : tickets) {
			System.out.println(t);
		}
		return tickets;
	}

	public void navigateToFareCalendar() {
		fareCalendarButton.click();
	}

	private OneWayDetailsTicket createNewTicket(OneWayDetailsTicket detailsTicket) {
		OneWayDetailsTicket newTicket = new OneWayDetailsTicket();
		newTicket.setDepartureCountry(detailsTicket.getDepartureCountry());
		newTicket.setArrivalCountry(detailsTicket.getArrivalCountry());
		newTicket.setDepartureTime(detailsTicket.getDepartureTime());
		newTicket.setArrivalTime(detailsTicket.getArrivalTime());
		newTicket.setDate(detailsTicket.getDate());
		newTicket.setCurrency(detailsTicket.getCurrency());
		return newTicket;
	}

	private OneWayDetailsTicket getCommonDetails(LocalDate flightExpectedDate) {
		OneWayDetailsTicket commonTicketDetails = new OneWayDetailsTicket();

		commonTicketDetails.setDepartureCountry(departureCountry.getAttribute("title"));
		commonTicketDetails.setArrivalCountry(arrivalCountry.getAttribute("title"));

		String[] fligthDateInf = flightDate.getText().split(" ");
		LocalDate actualFlightDate = LocalDate.of(flightExpectedDate.getYear(), flightExpectedDate.getMonth(),
				Integer.parseInt(fligthDateInf[1]));
		commonTicketDetails.setDate(actualFlightDate);

		commonTicketDetails.setDepartureTime(LocalTime.parse(departureTime.getText(), timeFormat));
		commonTicketDetails.setArrivalTime(LocalTime.parse(arrivalTime.getText(), timeFormat));

		commonTicketDetails.setCurrency(currency.getText());

		return commonTicketDetails;
	}

	private OneWayDetailsTicket getTicketWithTax(TicketClassEnum tce, WebElement element, OneWayDetailsTicket common) {
		OneWayDetailsTicket newTicket = createNewTicket(common);
		newTicket.setTicketClass(tce);
		String[] s = element.getText().split(" ");
		newTicket.setCost(Double.parseDouble(s[0].replaceAll(",", ".")));

		element.click();

		newTicket.setCostWithTaxes(getCostsWithTaxes());
		newTicket.setCurrency(getCurrency());
		return newTicket;
	}

	private double getCostsWithTaxes() {
		double costWithTax = 0;
		try {
			Thread.sleep(2000);
			costWithTaxes = driver.findElement(By.xpath(COST_WITH_TAXES_XPATH));
			costWithTax = Double.parseDouble(costWithTaxes.getText().replaceAll(",", "."));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return costWithTax;
	}

	private String getCurrency() {
		currency = driver.findElement(By.xpath(CURRENCY_XPATH));
		return currency.getText();
	}

}
