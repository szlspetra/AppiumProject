package org.example.tests;

import com.aventstack.extentreports.ExtentTest;
import org.example.constants.TestContextConstants;
import org.example.constants.TestData;
import org.example.pages.*;
import org.example.utils.GmailTest;
import org.example.utils.SuiteWideStorage;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class RegistrationTest  extends org.example.TestBase {
    private HomePage homePage;
    private RegistrationPage registrationPage;
    private Login login;
    private ExtentTest reporter;
    private AreaOfInterests interests;
    private Widget widget;


    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(ITestContext context, ITestResult result) {
        reporter = SuiteWideStorage.testReport.createTest(
                result.getMethod().getMethodName(),
                result.getMethod().getDescription()
        );
        context.setAttribute(TestContextConstants.REPORTER, reporter);
    }

        @Test
        public void testSuccessfulRegistration() throws Exception {
            HomePage homePage = new HomePage(driver, reporter);

            reporter.info("Current activity: " +driver.currentActivity());
            homePage.clickProfileImg();

            login = new Login(driver,reporter);
            login.clickRegistrationLink();;

            registrationPage = new RegistrationPage(driver, reporter);
            registrationPage.setFirstName(TestData.REG_FIRST_NAME);
            registrationPage.setLastName(TestData.REG_LAST_NAME);

            /**
             * Sets email from GMAIL_ADDRESS environment variable
             * Sets app password from APP_PWD environment variable
             * Security Note: Never hardcode credentials in source code
             */
            registrationPage.setEmail(System.getenv("GMAIL_ADDRESS"));
            registrationPage.setPwd1(System.getenv("APP_PWD"));
            registrationPage.setPwd2(System.getenv("APP_PWD"));
            registrationPage.clickPolicyCheckbox();
            registrationPage.clickTCCheckbox();
            registrationPage.clickNextBtn();

            interests = new AreaOfInterests(driver, reporter);
            interests.clickSkipBtn();

            String html = GmailTest.getFolder(TestData.EMAIL_SENDER_EMAIL).get(0).get("ConfirmationLink");
            assertFalse(html.length()==0, "Email verification failed. Missing confirmation link: "+GmailTest.getFolder(TestData.EMAIL_SENDER_EMAIL).get(0).get("HTML"));
            reporter.info("Email verification passed. Confirmation email: " + html);

            tearDown();
            setup();
            driver.get(html);
            widget = new Widget(driver,reporter);
            widget.clickLogin();

            login = new Login(driver,reporter);
            login.setEmail(System.getenv("GMAIL_ADDRESS"));
            login.setPwd(System.getenv("APP_PWD"));
            login.clickLoginBtn();

            widget = new Widget(driver,reporter);
            widget.clickAutofillSaveNo();

            homePage = new HomePage(driver,reporter);
            assertTrue(homePage.getProfileMonogram().isEnabled(), "Login wasn't succcessfull.");
            reporter.info("Login was successfull");
        }

}
