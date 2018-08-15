package TestBaseClasses;

import java.io.IOException;
import java.lang.reflect.Method;

import Utils.CreateReporterFolder;
import Utils.MakeTimeIntoString;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;




public class ReportClassConfig
{
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected static CreateReporterFolder folder;

    @BeforeSuite
    public void InstallReport()
    {
        folder = CreateReporterFolder.getInstance(MakeTimeIntoString.makeCurrentDateIntoString());
        htmlReporter = new ExtentHtmlReporter(CreateReporterFolder.getsPath()+"/report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }
    @BeforeMethod
    public void getTestName(Method method)
    {
        String testname = method.getName();
        test = extent.createTest(testname);
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.fail(MarkupHelper.createLabel(result.getName()+ " test case failed", ExtentColor.RED));
            test.fail(result.getThrowable());

        }
        if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.pass(MarkupHelper.createLabel(result.getName()+ " test case passed" , ExtentColor.GREEN));
        }
        if(result.getStatus() == ITestResult.SKIP)
        {
            test.skip(MarkupHelper.createLabel(result.getName()+ " test case skipped", ExtentColor.YELLOW));
            test.skip(result.getThrowable());
        }
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }

    public static ExtentTest reporter(){
        return test;
    }

}
