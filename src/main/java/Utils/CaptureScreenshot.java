package Utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CaptureScreenshot {

    public static String capture(WebDriver driver,String screenshotName) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = CreateReporterFolder.getsPath()+"/screenshots/"+screenshotName+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);
        String sImageName = screenshotName+".png";
        return sImageName;
    }
}
