package com.webflow.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.webflow.TestCases.BaseClass;


public class ExtentReport extends TestListenerAdapter implements ITestListener {
    
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;
    
   
    String repName;

    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Tets-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//Extent_Reports//" + repName);
        sparkReporter.config().setDocumentTitle("Automation Framwork Project");
        sparkReporter.config().setReportName("WebFlow WebSites Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("Applcation name", "Web Flow");
        extentReports.setSystemInfo("User name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Operation System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User", "Sanjiv");
    }

    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        //extentTest.log(Status.PASS, "Test Passed"); //it is applied in last one line
        extentTest.log(Status.PASS,MarkupHelper.createLabel("Test Passed:- "+result.getName(),ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
       // extentTest.log(Status.FAIL, "Test Failed"); //it is applied in last one line
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
        extentTest.log(Status.FAIL,MarkupHelper.createLabel("Test Failed:- "+result.getName(),ExtentColor.RED));
    
        captureScreenShot(result.getMethod().getMethodName());
    }

	public void onTestSkipped(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.createNode(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
       // extentTest.log(Status.SKIP, "Test Skipped"); //it is applied in last one line
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
        extentTest.log(Status.SKIP,MarkupHelper.createLabel("Test Skipped:- "+result.getName(),ExtentColor.PURPLE));
    }

    public void onFinish(ITestContext testContext) {
        extentReports.flush();
    }
    
    private void captureScreenShot(String methodName) {
        // Use a library like Selenium to capture the screen
        // and save it to a file

        // Example code using Selenium and WebDriver
        //WebDriver driver = new ChromeDriver();
        File screenshot = ((TakesScreenshot) BaseClass.driver).getScreenshotAs(OutputType.FILE);

        // Specify the directory where you want to save the screenshots
        String screenshotDir = System.getProperty("user.dir")+"//Screenshots//";
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String screenshotPath = screenshotDir + methodName +timeStamp+ ".png";

        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            Reporter.log("Screenshot captured: <a href='" + screenshotPath + "'>screenshot</a>");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            BaseClass.driver.quit();
        }
    }
    
    
}