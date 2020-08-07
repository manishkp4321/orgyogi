package listeners;

import config.EnvProperty;
import helper.AppConstants;
import helper.DriverInitilization;
import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
import org.testng.annotations.BeforeClass;
import reporter.LogToReporterAppender;

import util.UtilityGeneric;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;


import static io.qameta.allure.Allure.addByteAttachmentAsync;


@Slf4j
public class TestInitilizationListener extends TestListenerAdapter {
    private EnvProperty envProperty;
    private static final String pattern = "%d{HH:mm:ss.SSS} %-5level %logger{0} - %msg%n";



    @Override
    public void onStart(ITestContext context) {

        UtilityGeneric.registerReporterAppender(pattern);
        super.onStart(context);
        context.setAttribute(DriverInitilization.class.getName(), new DriverInitilization((EnvProperty) context.getSuite().getAttribute(EnvProperty.class.getName())));
        this.envProperty = (EnvProperty) context.getSuite().getAttribute(EnvProperty.class.getName());

    }

    @Override
    public void onTestStart(ITestResult result) {

          System.out.println("Test is starting... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("Test has been Passed... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
        attachTestLogs(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test is Skipped... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss_SSS");
        System.err.println("Test Failed... " + result.getTestClass().getRealClass().getSimpleName() + " --> " + result.getName());
        DriverInitilization driverInitilization = (DriverInitilization) result.getTestContext().getAttribute(DriverInitilization.class.getName());
        if (driverInitilization.getMyDriver() == null) {
            System.out.println("Screenshot not taken because execution has no active driver");
        } else {
            TakesScreenshot screenShot = ((TakesScreenshot) driverInitilization.getDriver());
            File file = screenShot.getScreenshotAs(OutputType.FILE);
            Path content = Paths.get(String.valueOf(file));
            try (InputStream is = Files.newInputStream(content)) {
                Allure.addAttachment(result.getMethod().getMethodName() + "_" + dateFormat.format(new Date(result.getEndMillis())), "image/png", is, ".png");
                attachTestLogs(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private CompletableFuture<byte[]> attachTestLogs(ITestResult currentTestResult) {
        return addByteAttachmentAsync("Test log", "text/plain", LogToReporterAppender.getLogsString(currentTestResult)::getBytes);
    }


    @Override
    public void onFinish(ITestContext testContext) {

        super.onFinish(testContext);
        DriverInitilization driverInitilization = (DriverInitilization) testContext.getAttribute(DriverInitilization.class.getName());
        driverInitilization.quitDriver();
    }





}
