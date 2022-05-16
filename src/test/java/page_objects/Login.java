package page_objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

public class Login {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    private static final By UserNameLocator = AppiumBy.accessibilityId("test-Username");
    private static final By PasswordLocator = AppiumBy.accessibilityId("test-Password");
    private static final By LoginLocator = AppiumBy.accessibilityId("test-LOGIN");
    private static final By ErrorText = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Error message']/android.widget.TextView");
    private static final By ProductHeader = AppiumBy.xpath("//android.view.ViewGroup[@content-desc='test-Cart drop zone']/android.view.ViewGroup/android.widget.TextView");

    private AppiumDriver driver;

    public Login(AppiumDriver driver) {
        this.driver = driver;
    }

    public Login enterUserName(String value) {
        driver.findElement(UserNameLocator).clear();
        driver.findElement(UserNameLocator).sendKeys(value);
        LOGGER.debug("Entered Username: " + value);
        return this;
    }

    public Login enterPassword(String value) {
        driver.findElement(PasswordLocator).clear();
        driver.findElement(PasswordLocator).sendKeys(value);
        LOGGER.debug("Entered Password: " + value);
        return this;
    }

    public Login clickOnLoginButton() {
        driver.findElement(LoginLocator).click();
        return this;
    }

    public Login unsuccessfulLoginError(String expectedErrorText) {
        String actualText = driver.findElement(ErrorText).getText();
        Assert.assertEquals(expectedErrorText, actualText);
        return this;
    }

    public Login loginIsSuccessful(String expectedText) {
        String actualText = driver.findElement(ProductHeader).getText();
        Assert.assertEquals(expectedText, actualText);
        return this;
    }
}
