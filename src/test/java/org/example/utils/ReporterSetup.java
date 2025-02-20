package org.example.utils;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.api.client.util.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReporterSetup {

    private final String reportFolderPath = System.getProperty("user.dir") + File.separator + "reports";
    private final String reportFileName = "Report_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".html";

    public ExtentSparkReporter createReporter() {
        //Create report folder first if it does not exist
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) reportFolder.mkdir();

        ExtentSparkReporter reporter = new ExtentSparkReporter(new File(reportFolderPath + File.separator + reportFileName));
        reporter.config().thumbnailForBase64(true);

        return reporter;
    }

    public static String captureScreenshotAsBase64(WebDriver driver) throws IOException {
        // Take a screenshot of the entire visible part of page/screen
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage startImg = ImageIO.read(screenshot);
        BufferedImage finalImg = new BufferedImage(startImg.getWidth(), startImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        finalImg.createGraphics().drawImage(startImg, 0, 0, Color.WHITE, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(finalImg, "jpeg", baos);
        return Base64.encodeBase64String(baos.toByteArray());
    }

    public static String getReporterPath(){
        return ("");
    }
}

