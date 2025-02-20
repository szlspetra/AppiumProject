package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.ElementActions;
import org.example.utils.ReporterSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.By.*;
import static org.testng.Assert.fail;

/**
 * Page Object Model class for handling home screen interactions
 * Contains methods and elements related to the main application screen
 */
public class HomePage {
    AndroidDriver driver;
    ExtentTest reporter;

    String sProfile ="com.interticket.budapest13:id/toolBarProfileImage";
    String sProfileMonogram ="com.interticket.budapest13:id/toolBarProfileMonogram";

    public HomePage(AndroidDriver driver, ExtentTest reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

    /**
     * Performs click action on profile image with error handling and reporting
     * Includes screenshot capture on failure
     */
    public void clickProfileImg() {
        try {
            WebElement element = driver.findElement(By.id(sProfile));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.click();
            reporter.info("Profile icon clicked successfully");
        } catch (Exception e) {
            try {
                reporter.fail("Failed to click profile image: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }
    public WebElement getProfileMonogram() {
        try {
            By profileMonogramLocator = By.id(sProfileMonogram);

            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(profileMonogramLocator));
            ElementActions.waitForElementToBeClickable(driver,element);
            reporter.info("Profile monogram icon exists");
            return element;
        } catch (Exception e) {
            try {
                reporter.fail("Profile monogram element not found: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
        return null;
    }
}
