package com.cucumber.springboottwo.example.library;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ElementActions {

    @Autowired
    DriverManager driverManager;

    public void launch(String url) {
        Preconditions.checkNotNull(url, String.format("URL: %s must not be empty", url));
        driverManager.getWebDriver().get(url);
    }

    public WebElement getElementFromObjectRepository(String locator) {
        Preconditions.checkNotNull(locator, String.format("Locator: %s must not be empty", locator));
        return driverManager.getWebDriverWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        getByUsingSelector(locator)
                )
        );
    }

    public void enterTextIntoInputTextBox(String locator, String inputText) {
        WebElement element = getElementFromObjectRepository(locator);
        element.sendKeys(inputText);
    }

    public void clickButton(String locator) {
        WebElement element = getElementFromObjectRepository(locator);
        driverManager.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public By getByUsingSelector(String elementSelector) {

        String[] parts = elementSelector.split("=", 2);
        Preconditions.checkArgument(parts.length == 2, "Illegal format of selector");
        return switch (parts[0].toLowerCase()) {
            case "id" -> By.id(parts[1]);
            case "css" -> By.cssSelector(parts[1]);
            case "xpath" -> By.xpath(parts[1]);
            default -> null;
        };

    }

    public String getElementText(String locator) {
        WebElement element  = getElementFromObjectRepository(locator);
        return element.getText();
    }

    public boolean isTextPresentInsideElement(String locator, String text) {
        return driverManager.getWebDriverWait().until(
                ExpectedConditions.textToBePresentInElementLocated(
                        getByUsingSelector(locator),
                        text
                )
        );
    }

}
