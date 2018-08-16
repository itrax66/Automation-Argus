package Pages;

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

public class BasePageObject {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected ExtentTest reporter;

    public BasePageObject(){

        this.driver = TestBaseClass.getDriver();

        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, RunProperties.getExplicitInSeconds());

        reporter = ReportClassConfig.reporter();
    }
    public BasePageObject clickElement(WebElement element){

        wait.until(ExpectedConditions.elementToBeClickable(element));
        String location = getElementPreparedForImg(element);
        element.click();
        reportLog("Element was successfully clicked",Status.PASS,location);

        return this;
    }
    public BasePageObject submitText(WebElement element,String text){

        wait.until(ExpectedConditions.elementToBeClickable(element));
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
        reportLog("Text was successfully submitted",Status.PASS);

        return this;
    }

    public static String generateRandomImageName(){
        Random r = new Random();
        double num = r.nextInt(999999999)+1000;
        return Double.toString(num);
    }
    protected void scrollToElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        executor.executeScript("arguments[0].style.border='3px solid red'",element);
    }

    protected String makeUpperChar(String text){
        StringBuilder sb = new StringBuilder(text);
        for (int index = 0; index < sb.length(); index++) {
            char c = sb.charAt(index);
            if (Character.isLowerCase(c)) {
                sb.setCharAt(index, Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }
    protected void reportLog(String sMessage,Status status) {


            try {
                String ScreenshotName = CaptureScreenshot.capture(TestBaseClass.getDriver(), generateRandomImageName());
                reporter.log(status, sMessage, MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotName).build());
                if(status.equals(Status.FAIL)){
                    throw new Error(sMessage, new Exception(sMessage));
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
    protected void reportLog(String sMessage,Status status,String sImgLocation) {


        try {
            reporter.log(status, sMessage, MediaEntityBuilder.createScreenCaptureFromPath(sImgLocation).build());
            if(status.equals(Status.FAIL)){
                throw new Error(sMessage, new Exception(sMessage));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public String capturePictureName(){
        try {
            return CaptureScreenshot.capture(TestBaseClass.getDriver(), generateRandomImageName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getElementPreparedForImg(WebElement element){
        scrollToElement(element);
        return capturePictureName();
    }



    }
