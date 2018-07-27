package by.htp.belavia.pages;

import org.openqa.selenium.WebDriver;

public class SearchFormPage extends AbstractPageWithSearchForm {

	private static final String BASE_URL = "https://booking.belavia.by/";

	public SearchFormPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void openPage() {
		driver.get(BASE_URL);
	}

}
