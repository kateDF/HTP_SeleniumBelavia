package by.htp.belavia.pages;

import javax.naming.OperationNotSupportedException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage extends AbstractPage {

	private static final String SEARCH_ALLERT_ERROR_XPATH = "//div[@class='alert-error alert']";

	private WebElement errorAllert;

	public SearchResultPage(WebDriver driver) {
		super(driver);
	}

	public boolean isResultFound() {
		try {
			errorAllert = driver.findElement(By.xpath(SEARCH_ALLERT_ERROR_XPATH));
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

	@Override
	public void openPage() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

}
