package com.cucumber.springboottwo.example.library;

import com.google.common.base.Preconditions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.HttpCommandExecutor;
import org.openqa.selenium.support.Color;
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
        ReportLogger.INSTANCE.logMessage("Launched URL: " + url);
    }

    public WebElement getElementFromObjectRepository(String locator) {
        Preconditions.checkNotNull(locator, String.format("Locator: %s must not be empty", locator));
        ReportLogger.INSTANCE.logMessage("Fetching element from Object Repo: " + locator);
        return driverManager.getWebDriverWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        getByUsingSelector(locator)
                )
        );
    }

    public void enterTextIntoInputTextBox(String locator, String inputText) {
        WebElement element = getElementFromObjectRepository(locator);
        element.sendKeys(inputText);
        ReportLogger.INSTANCE.logMessage("Entered text: " + inputText + " , into input field locator: " + locator);
    }

    public void clickButton(String locator) {
        WebElement element = getElementFromObjectRepository(locator);
        driverManager.getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        ReportLogger.INSTANCE.logMessage("Clicked on button: " + locator);
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
        ReportLogger.INSTANCE.logMessage("Fetching element: " + locator + ", text is: " + element.getText());
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

    public void isAlertRaisedWithTextMessage(String message) {
        driverManager.getWebDriverWait().until(ExpectedConditions.alertIsPresent());
        Alert alert = driverManager.getWebDriver().switchTo().alert();
        ReportLogger.INSTANCE.logMessage("Alert contains: " + alert.getText());
    }

    public void closeAlert() {
        Alert alert = driverManager.getWebDriverWait().until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
        ReportLogger.INSTANCE.logMessage("Alert Closed");
    }

    public void switchTab() {
        var driver = driverManager.getWebDriver();
        var currentTab = driver.getWindowHandle();
        var newTab = driver.getWindowHandles().stream().filter(t -> !t.equals(currentTab)).findFirst().get();
        driver.switchTo().window(newTab);
    }

    public Color getBackgroundColorOfElement(String locator) {
        WebElement element = getElementFromObjectRepository(locator);
        return Color.fromString(element.getCssValue("background-color"));
    }

    public void switchIFrameByLocator(String locator) {
        WebElement iframeElement = getElementFromObjectRepository(locator);
        driverManager.getWebDriver().switchTo().frame(iframeElement);
    }

    public void switchToDefault() {
        driverManager.getWebDriver().switchTo().defaultContent();
    }
}
