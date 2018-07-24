package by.htp.belavia.pages;

import javax.naming.OperationNotSupportedException;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

	protected WebDriver driver;

	public abstract void openPage() throws OperationNotSupportedException;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

}
