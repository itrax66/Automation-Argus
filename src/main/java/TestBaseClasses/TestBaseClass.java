package TestBaseClasses;

import java.util.concurrent.TimeUnit;

import FileManagers.RunProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestBaseClass extends ReportClassConfig
{
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void InstallTest()
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(RunProperties.getPageToLoadInSeconds(), TimeUnit.SECONDS);

        driver.manage().timeouts().implicitlyWait(RunProperties.getImplicitInSeconds(), TimeUnit.SECONDS);

        driver.get(RunProperties.getUrl());

    }

    @AfterMethod(alwaysRun = true)
    public void AfterTest()
    {
        driver.quit();
    }
    public static WebDriver getDriver()
    {
        return driver;
    }
}

