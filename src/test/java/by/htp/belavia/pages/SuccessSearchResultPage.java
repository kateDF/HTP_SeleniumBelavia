package by.htp.belavia.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SuccessSearchResultPage extends SearchResultPage {

	private static final String FARE_CALENDAR_BUTTON_XPATH = "//div[@id='outbound']/div[1]//a";

	private WebElement fareCalendarButton;

	public SuccessSearchResultPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToFareCalendar() {
		fareCalendarButton = driver.findElement(By.xpath(FARE_CALENDAR_BUTTON_XPATH));
		fareCalendarButton.click();

	}

}
