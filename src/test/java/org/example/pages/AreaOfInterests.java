package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.ElementActions;
import org.example.utils.ReporterSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.fail;

public class AreaOfInterests {
    AndroidDriver driver;
    ExtentTest reporter;

    String sSkipBtn ="com.interticket.budapest13:id/skip";

    public AreaOfInterests(AndroidDriver driver, ExtentTest reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

    public void clickSkipBtn() {
        try {
            ElementActions.scrollToElement(driver, sSkipBtn);
            WebElement element = driver.findElement(By.id(sSkipBtn));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.click();
            reporter.info("Skip link click action completed successfully");
        } catch (Exception e) {
            try {
                reporter.fail("Error occurred during skip button click operation: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }
}
