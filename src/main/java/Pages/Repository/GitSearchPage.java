package Pages.Repository;

import TestBaseClasses.TestBaseClass;
import Utils.CharRemover;
import Utils.GeneralUtils;
import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class GitSearchPage extends BasicGitPage {

    @FindBy(css = "input[placeholder = 'Search']")
    protected WebElement eLocalSearch;

    //Scraps a specific page
    public GitSearchPage scrapPage(int iPage) {
        WebElement eCurrentPage = driver.findElement(By.cssSelector(".current"));
        if (!eCurrentPage.getText().equals(Integer.toString(iPage))) {
            TestBaseClass.getDriver().navigate().to("https://github.com/search?p=" + iPage + "&q=selenium&type=Repositories");
            }
        List<WebElement> eRepositories = TestBaseClass.getDriver().findElements(By.cssSelector(".repo-list-item.d-flex.flex-column.flex-md-row.flex-justify-start.py-4.public.source"));

        for(int i =0; i < eRepositories.size();i++){
            enterLinkToBeValid(i);
            scrapIntoDb(i);
        }



        return this;
    }
    private void enterLinkToBeValid(int index){
        List<WebElement> eRepositories = TestBaseClass.getDriver().findElements(By.cssSelector(".repo-list-item.d-flex.flex-column.flex-md-row.flex-justify-start.py-4.public.source"));
        WebElement eRepository = eRepositories.get(index);

        String attribute = eRepository.findElement(By.cssSelector("a")).getAttribute("href");

        StopWatch timer = new StopWatch();
        timer.start();
        clickElement(eRepository.findElement(By.cssSelector("a")));
        if(eLocalSearch.isEnabled()){
            timer.stop();
            reportLogBeforeAction(CharRemover.removeCharAtString(attribute,18)+ " has a valid link, elapsed time: "+convertToDoubleFromLong(timer.getTime())/convertToDoubleFromLong(1000) + " seconds", Status.PASS);
        }
        else{
            timer.stop();
            reportLogBeforeAction(CharRemover.removeCharAtString(attribute,18)+ " Does not have a valid link", Status.FAIL);

        }
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.history.go(-1)");
    }
    private void scrapIntoDb(int index){
        List<WebElement> eRepositories = TestBaseClass.getDriver().findElements(By.cssSelector(".repo-list-item.d-flex.flex-column.flex-md-row.flex-justify-start.py-4.public.source"));
        WebElement eRepository = eRepositories.get(index);

        int id = GeneralUtils.generateRandomID();
        String sLinkName =  eRepository.findElement(By.cssSelector("a")).getAttribute("href").replace("https://github.com/","");
        String sDescription;
        try {
             sDescription = eRepository.findElement(By.cssSelector("p.pr-4")).getText();
        }
        catch (NoSuchElementException exception){
            sDescription = " ";
        }

        String sTime = eRepository.findElement(By.cssSelector("relative-time")).getAttribute("datetime");
        String sLanguage = eRepository.findElement(By.cssSelector("div.min-width-0")).getText().replace(" ","") ;
        String sStars = eRepository.findElement(By.cssSelector("a.muted-link")).getText().replace(" ","");

        connection.sendQuarry("INSERT INTO link_details(link_id,\n" +
                "link_name,\n" +
                "description,\n" +
                "update_time,\n" +
                "language_name,\n" +
                "stars) VALUES("+id+",'"+sLinkName+"','"+sDescription+"','"+sTime+"','"+sLanguage+"','"+sStars+"');");

        List<WebElement> eTags = eRepository.findElements(By.cssSelector("div.topics-row-container>a"));
        for(WebElement element : eTags ){

            String sTag = element.getText().replace(" ","");
            connection.sendQuarry("INSERT INTO tags_for_links(link_name,tag)\n" +
                    "VALUES ('"+sLinkName+"','"+sTag+"');");

        }

    }
}
