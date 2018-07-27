package by.htp.belavia.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import by.htp.belavia.entity.SearchFormData;
import by.htp.belavia.entity.Ticket;

public abstract class AbstractFareCalendarPage extends SearchResultPage {

	protected static final String ELEMENTS_WITH_PRICE_XPATH = "//div[@class='price']/div";
	protected static final String INPUT_WITH_VALUE_DATE_XPATH = "input";
	protected static final String LABEL_WITH_COST_XPATH = "label";
	protected static final String NEXT_RIGHT_DAYS_BUTTON_XPATH = "//div[@class='d-outbound']/div[@class='nav-right']/a";

	protected WebElement nextRightDaysButton;

	public AbstractFareCalendarPage(WebDriver driver) {
		super(driver);
	}

	public abstract <T extends Ticket> List<T> getListOfTickets(SearchFormData searchData);

	protected void clickNextRightButton() {
		nextRightDaysButton = driver.findElement(By.xpath(NEXT_RIGHT_DAYS_BUTTON_XPATH));
		nextRightDaysButton.click();
	}

}
