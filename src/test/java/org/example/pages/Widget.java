package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.ElementActions;
import org.example.utils.ReporterSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.testng.Assert.fail;

public class Widget {
    AndroidDriver driver;
    ExtentTest reporter;

    String sLogin ="android:id/button1";
    String sAutofillNo ="android:id/autofill_save_no";

    public Widget(AndroidDriver driver, ExtentTest reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }
    public void clickLogin() {
        try {
            ElementActions.scrollToElement(driver,sLogin);
            WebElement element = driver.findElement(By.id(sLogin));
            element.click();
            reporter.info("Login button clicked successfully");
        }
        catch (Exception e) {
            try {
                reporter.fail("Error clicking login button: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

public void clickAutofillSaveNo() {
    try {
        List<WebElement> elements = driver.findElements(By.id(sAutofillNo));
        if(elements.size()>0) {
            elements.get(0).click();
            reporter.info("Autofill save no clicked successfully");
        }
    } catch (Exception e) {
        try {
            reporter.fail("Error clicking autofill save no button: " + e.toString(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
            fail(e.toString());
        } catch (Exception ignored) {
            reporter.fail("Could not attach screenshot.");
        }
    }
    }
}
