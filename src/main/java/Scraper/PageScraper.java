package Scraper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PageScraper {

    static List<WebElement> allRepositoryViewers;

    public static void scrapePage(int iPage,WebDriver driver){


        allRepositoryViewers = driver.findElements(By.cssSelector(""));

    }

}
