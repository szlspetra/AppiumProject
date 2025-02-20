package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.appium.java_client.android.AndroidDriver;
import org.example.constants.Config;
import org.example.tests.RegistrationTest;
import org.example.utils.ReporterSetup;
import org.example.utils.SuiteWideStorage;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Base test class containing setup and teardown methods for Android test automation
 * Handles driver initialization, capability configuration, and report management
 */
public class TestBase {
    protected AndroidDriver driver;

    /**
     * Configures and initializes the Android driver before test suite execution
     * Sets up Appium capabilities and ExtentReports instance
     *
     * @throws MalformedURLException If Appium server URL is invalid
     */
    @BeforeSuite
    public void setup() throws MalformedURLException {

        System.setProperty("java.version", "17");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platformName", Config.PLATFORM_NAME);
        caps.setCapability("appium:platformVersion", Config.PLATFORM_VERSION);
        caps.setCapability("appium:deviceName", Config.DEVICE_NAME);
        caps.setCapability("appium:appPackage", Config.APP_PACKAGE);
        caps.setCapability("appium:appActivity", Config.APP_ACTIVITY);
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:autoGrantPermissions", true);
        caps.setCapability("appium:noReset", false);
        caps.setCapability("appium:autoLaunch", true);
        caps.setCapability("appium:newCommandTimeout", 600);

        driver = new AndroidDriver(new URL(Config.DRIVER_URL), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        SuiteWideStorage.testReport = new ExtentReports();
        SuiteWideStorage.testReport.attachReporter(new ReporterSetup().createReporter());
    }

    @BeforeSuite(alwaysRun = true)
    public void initReport() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("target/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Appium Tesztjelentés");
        htmlReporter.config().setReportName("Regisztrációs Tesztkörnyezet");

        SuiteWideStorage.testReport = new ExtentReports();
        SuiteWideStorage.testReport.attachReporter(htmlReporter);
        SuiteWideStorage.testReport.setSystemInfo("Környezet", "QA");
    }


    /**
     * Cleans up after test suite execution
     * Terminates app and quits driver instance
     */
    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            //driver.terminateApp(Config.APP_PACKAGE);
            driver.quit();
        }
    }

    /**
     * Cleans up after test suite execution
     * Terminates app and quits driver instance
     */
    @AfterSuite(alwaysRun = true)
    public void baseAfterSuite() {
        RegistrationTest.getReporter().flush();
        System.out.println("Report generated: " + ReporterSetup.getReporterPath() );
    }


}
