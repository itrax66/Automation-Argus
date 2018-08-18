package Pages;

import DbConnection.DbConnection;
import FileManagers.RunProperties;
import TestBaseClasses.ReportClassConfig;
import TestBaseClasses.TestBaseClass;
import Utils.CaptureScreenshot;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Random;
/*
This class is a basic class, that defines the basic function of the object,page.
It controls the basic functions of the class, including clicking, and put text inside textboxes.
 */
public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest reporter;
    protected DbConnection connection;

    public BasePageObject(){

        //Defines connection to the DB
        connection = DbConnection.getInstance();

        //Allows using local instance of the driver;
        this.driver = TestBaseClass.getDriver();

        /*Selenium has a class that allows using annotations for mapping elements.
        this initialization allows using the annotations in the code
         */
        PageFactory.initElements(driver, this);

        //The driver that is used for waiting action (explicit wait)
        wait = new WebDriverWait(driver, RunProperties.getExplicitInSeconds());

        reporter = ReportClassConfig.reporter();
    }

    /**
     * put an element, for performing clicking action
    @param element
     */
    protected BasePageObject clickElement(WebElement element){

        wait.until(ExpectedConditions.elementToBeClickable(element));
        String location = getElementPreparedForImg(element);
        element.click();
        reportlog("Element was successfully clicked",Status.PASS,location);

        return this;
    }
    /**
     * put an element, for putting text inside the element
     @param element
     */
    protected BasePageObject submitText(WebElement element,String text){

        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
        reportLogBeforeAction("Text was successfully submitted",Status.PASS);

        return this;
    }

    //Allows to generate ID for the screenshots
    private static String generateRandomImageName(){
        Random r = new Random();
        double num = r.nextInt(999999999)+1000;
        return Double.toString(num);
    }
    //Performs scrolling and marking for the element
    private void scrollToElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        executor.executeScript("arguments[0].style.border='3px solid red'",element);
    }

    //Marks and take picture of the element BEFORE the action is performed
    protected void reportLogBeforeAction(String sMessage, Status status) {
            try {
                String ScreenshotName = CaptureScreenshot.capture(TestBaseClass.getDriver(), generateRandomImageName());
                reporter.log(status, sMessage, MediaEntityBuilder.createScreenCaptureFromPath("screenshots/"+ScreenshotName).build());
                if(status.equals(Status.FAIL)){
                    throw new Error(sMessage, new Exception(sMessage));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
    //Marks and take picture of the element AFTER the action is performed
    protected void reportlog(String sMessage, Status status, String sImgLocation) {
        try {
            reporter.log(status, sMessage, MediaEntityBuilder.createScreenCaptureFromPath("screenshots/"+sImgLocation).build());
            if(status.equals(Status.FAIL)){
                throw new Error(sMessage, new Exception(sMessage));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //Capturing screenshot
    private String capturePictureName(){
        try {
            return CaptureScreenshot.capture(TestBaseClass.getDriver(), generateRandomImageName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Performing the capturing before the action
    private String getElementPreparedForImg(WebElement element){
        scrollToElement(element);
        return capturePictureName();
    }
    //Allows making exact seconds available
    protected Double convertToDoubleFromLong(long num){
        Long l = new Long(num);
        return l.doubleValue();

    }



    }
