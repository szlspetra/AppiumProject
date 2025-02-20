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
 * Page Object Model class for handling user registration screen
 * Encapsulates all registration form interactions and validations
 */
public class RegistrationPage {
    AndroidDriver driver;
    ExtentTest reporter;

    String sFirstName ="com.interticket.budapest13:id/firstName";
    String sLastName ="com.interticket.budapest13:id/lastName";
    String sEmail ="com.interticket.budapest13:id/email";
    String sPwd1 ="com.interticket.budapest13:id/newPassword";
    String sPwd2 ="com.interticket.budapest13:id/newPasswordConfirmation";
    String sPolicyCheckbox ="com.interticket.budapest13:id/acceptDPPolicyCheckbox";
    String sTCCheckbox ="com.interticket.budapest13:id/acceptTCCheckbox";
    String sNextBtn = "com.interticket.budapest13:id/next";

    /**
     * Constructor initializing driver and reporter
     * @param driver AndroidDriver instance
     * @param reporter ExtentTest reporter for logging
     */
    public RegistrationPage(AndroidDriver driver, ExtentTest reporter) {
        this.driver = driver;
        this.reporter = reporter;
    }

    /**
     * Sets the first name field value
     * @param value Text to input in first name field
     */
    public void setFirstName(String value) {
        try {
            ElementActions.scrollToElement(driver,sFirstName);
            WebElement element = driver.findElement(By.id(sFirstName));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.sendKeys(value);
            reporter.info("First name field filled successfully: " + value);
        }
        catch (Exception e) {
            try {
                reporter.fail("Error filling first name field: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    public void setLastName(String value) {
        try {
            ElementActions.scrollToElement(driver,sLastName);
            WebElement element = driver.findElement(By.id(sLastName));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.sendKeys(value);
            reporter.info("Last name field filled successfully: " + value);
        }
        catch (Exception e) {
            try {
                reporter.fail("Error filling last name field: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    public void setEmail(String value) {
        try {
            ElementActions.scrollToElement(driver,sEmail);
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

    public void setPwd1(String value) {
        try {
            ElementActions.scrollToElement(driver,sPwd1);
            WebElement element = driver.findElement(By.id(sPwd1));
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

    public void setPwd2(String value) {
        try {
            ElementActions.scrollToElement(driver,sPwd2);
            WebElement element = driver.findElement(By.id(sPwd2));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.sendKeys(value);
            reporter.info("Password confirmation field filled successfully: " + value);
        }
        catch (Exception e) {
            try {
                reporter.fail("Error filling password confirmation field: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    /**
     * Toggles privacy policy acceptance checkbox
     * @implNote Uses explicit wait before clicking
     */
    public void clickPolicyCheckbox() {
        try {
            ElementActions.scrollToElement(driver,sPolicyCheckbox);
            WebElement element = driver.findElement(By.id(sPolicyCheckbox));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.click();
            reporter.info("Policy checkbox clicked successfully");
        }
        catch (Exception e) {
            try {
                reporter.fail("Error clicking policy checkbox: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    /**
     * Toggles terms & conditions acceptance checkbox
     * @implNote Does NOT use explicit wait (different behavior example)
     */
    public void clickTCCheckbox() {
        try {
            ElementActions.scrollToElement(driver,sTCCheckbox);
            WebElement element = driver.findElement(By.id(sTCCheckbox));
            element.click();
            reporter.info("Terms and conditions checkbox clicked successfully");
        }
        catch (Exception e) {
            try {
                reporter.fail("Error clicking terms and conditions checkbox: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

    /**
     * Finalizes registration by clicking next button
     * @implNote Includes screenshot on failure
     */
    public void clickNextBtn() {
        try {
            ElementActions.scrollToElement(driver,sNextBtn);
            WebElement element = driver.findElement(By.id(sNextBtn));
            ElementActions.waitForElementToBeClickable(driver,element);
            element.click();
            reporter.info("Next button clicked successfully");
        }
        catch (Exception e) {
            try {
                reporter.fail("Error clicking next button: "+e.toString(),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ReporterSetup.captureScreenshotAsBase64(driver)).build());
                fail(e.toString());
            } catch (Exception ignored) {
                reporter.fail("Could not attach screenshot.");
            }
        }
    }

}
