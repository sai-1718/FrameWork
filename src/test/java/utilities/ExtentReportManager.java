package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements   ITestListener
{

    ExtentSparkReporter sparkReporter;
    ExtentReports extentReports;
    ExtentTest extentTest;
    String repName;

    public void onStart(ITestContext context )
    {
//        SimpleDateFormat format=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//        Date date=new Date();
//        String currentDate=format.format(date);

        //////////// adding dyanamic name to report by ading time stamp
        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName="Test-Report-"+timeStamp+".html";
        sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);

        sparkReporter.config().setDocumentTitle("Automation Test");
        sparkReporter.config().setReportName("Sai Test Report");
        sparkReporter.config().setTheme(Theme.DARK);

        extentReports=new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        extentReports.setSystemInfo("Name","SAI DASARI");
        extentReports.setSystemInfo("Application","OpenCart");
        extentReports.setSystemInfo("Module","Admin");
        extentReports.setSystemInfo("Sub Module","Customers");
        extentReports.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment","QA");

        String os=context.getCurrentXmlTest().getParameter("os");
        extentReports.setSystemInfo("os",os);

        String browser=context.getCurrentXmlTest().getParameter("browser");
        extentReports.setSystemInfo("browser",browser);

        List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
        if (!includedGroups.isEmpty())
            extentReports.setSystemInfo("Groups",includedGroups.toString());
    }

    public void onTestStart(ITestResult result)
    {

    }

    public void onTestSuccess(ITestResult result)
    {
        extentTest=extentReports.createTest(result.getTestClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());  ///// to display groups in report
        extentTest.log(Status.PASS,result.getName()+"  is successfully passed");
    }

    public void onTestFailure(ITestResult result)
    {
        extentTest=extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());

        extentTest.log(Status.FAIL,result.getName()+" got failed ");
        extentTest.log(Status.INFO , result.getThrowable().getMessage());

        try {
            String imgPath=new BaseClass().captureScreen(result.getName());
            extentTest.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onTestSkipped(ITestResult result)
    {
        extentTest=extentReports.createTest(result.getClass().getName());
        extentTest.assignCategory(result.getMethod().getGroups());

        extentTest.log(Status.SKIP,result.getName()+" is skipped..");
        extentTest.log(Status.SKIP,result.getThrowable().getMessage());

    }


    //@SneakyThrows
    public void onFinish(ITestContext context)
    {
        extentReports.flush();

        String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport=new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

