package by.htp.belavia.steps;

import org.openqa.selenium.WebDriver;

import by.htp.belavia.driver.DriverSingleton;
import by.htp.belavia.pages.MainPage;

public class CommonSteps {

	private WebDriver driver;

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public MainPage openMainPage() {
		MainPage mainPage = new MainPage(driver);
		mainPage.openPage();
		return mainPage;
	}

}
