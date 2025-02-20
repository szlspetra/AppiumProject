package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import io.appium.java_client.android.AndroidDriver;
import org.example.utils.ElementActions;
import org.example.utils.ReporterSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.fail;

/**
 * Page Object class for handling login screen interactions
 * Provides methods for authentication-related operations
 */
public class Login {
    AndroidDriver driver;
    ExtentTest reporter;

    String sRegistrationLink ="com.interticket.budapest13:id/registrationLink";
    String sEmail ="com.interticket.budapest13:id/email";
    String sPwd ="com.interticket.budapest13:id/password";
    String sLoginBtn ="com.interticket.budapest13:id/loginButton";

    public Login(AndroidDriver driver, ExtentTest reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

    /**
     * Clicks registration link with error handling and reporting
     * Includes screenshot capture on failure
     */
    public void clickRegistrationLink() {
        try {
            WebElement element = driver.findElement(By.id(sRegistrationLink));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.click();
            reporter.info("Registration link clicked successfully");
        }
        catch (Exception e) {
            try {
                reporter.fail("Failed to click registration link: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.info("Could not attach screenshot.");
            }
        }
    }
    public void setEmail(String value) {
        try {
            ElementActions.scrollToElement(driver, sEmail);
            WebElement element = driver.findElement(By.id(sEmail));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.sendKeys(value);
            reporter.info("Email field filled successfully: " + value);
        }
        catch (Exception e) {
            try {
                reporter.fail("Error filling email field: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    public void setPwd(String value) {
        try {
            ElementActions.scrollToElement(driver,sPwd);
            WebElement element = driver.findElement(By.id(sPwd));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.sendKeys(value);
            reporter.info("Password field filled successfully: " + value);
        }
        catch (Exception e) {
            try {
                reporter.fail("Error filling password field: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    public void clickLoginBtn() {
        try {
            ElementActions.scrollToElement(driver,sLoginBtn);
            WebElement element = driver.findElement(By.id(sLoginBtn));
            ElementActions.waitForElementToBeClickable(driver,element);
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
}
