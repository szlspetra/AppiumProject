package org.example.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import org.example.constants.ElementConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import static java.time.Duration.ofSeconds;

/**
 * Provides reusable utility methods for interacting with mobile UI elements
 * Handles common operations like waiting, scrolling, and visibility checks
 */
public class ElementActions {

    /**
     * Waits for a web element to become clickable within the maximum wait duration
     * @param driver AndroidDriver instance for mobile automation
     * @param element Target WebElement to wait for
     * @throws org.openqa.selenium.TimeoutException If element doesn't become clickable within timeout
     */
    public static void waitForElementToBeClickable(AndroidDriver driver, WebElement element) {
        new WebDriverWait(driver, ElementConstants.MAX_WAIT_DURATION)
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Continuously scrolls down the screen until specified element becomes visible
     * @param driver AndroidDriver instance for mobile automation
     * @param selector Element ID to scroll to (resource-id)
     * @implNote Uses JavaScript-based scroll gesture with screen dimension calculations.
     *           May run indefinitely if element doesn't exist in scrollable area
     */
    public static void scrollToElement(AndroidDriver driver, String selector) {
        boolean elementFound = false;
        Integer screenHeight = driver.manage().window().getSize().height;
        Integer screenWidth = driver.manage().window().getSize().width;
        elementFound = isElementDisplayed(driver, selector, 1);
        while (!elementFound) {
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of("left", 0, "top",
                    0, "width", screenWidth, "height", screenHeight / 1.2, "direction", "down", "percent", 1.0));
            elementFound = isElementDisplayed(driver, selector, 1);
        }
    }

    /**
     * Checks if element is visible on screen within specified timeout
     * @param driver AndroidDriver instance for mobile automation
     * @param selector Element ID to check (resource-id)
     * @param duration Maximum wait time in seconds
     * @return true if element becomes visible within timeout, false otherwise
     * @implNote Temporarily modifies implicit wait timeout, resets to previous value after check
     */
    public static boolean isElementDisplayed(AndroidDriver driver, String selector, Integer duration) {
        driver.manage().timeouts().implicitlyWait(ofSeconds(duration));
        try {
            WebDriverWait wait = new WebDriverWait((driver), Duration.ofSeconds(duration));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(selector))));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
