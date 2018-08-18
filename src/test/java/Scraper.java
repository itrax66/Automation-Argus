import Pages.PageNavigator;
import TestBaseClasses.TestBaseClass;
import org.testng.annotations.Test;

public class Scraper extends TestBaseClass {

    @Test
    public void ScrapGitHubTest(){
        PageNavigator.BasicGitPage().enterSearchPage("selenium").scrapPage(1)
        .scrapPage(2).scrapPage(3).scrapPage(4).scrapPage(5);
    }
}
