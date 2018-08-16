package Pages.Repository;

import Scraper.PageScraper;
import Utils.CharRemover;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GitSearchPage extends BasicGitPage {

    @FindBy(css = ".current")
    protected WebElement eCurrentPage;

    @FindBy(css = "input[placeholder = 'Search']")
    protected WebElement eLocalSearch;

    public GitSearchPage scrapPage(int iPage) {
        if(!eCurrentPage.getText().equals(Integer.toString(iPage))){
            for(WebElement element : driver.findElements(By.cssSelector(".d-flex.d-md-inline-block.pagination > a"))){
                if(element.getText().equals(Integer.toString(iPage))){
                    clickElement(element);
                }
            }
        }

        List<WebElement> eRepositories = driver.findElements(By.cssSelector(".repo-list-item.d-flex.flex-column.flex-md-row.flex-justify-start.py-4.public.source"));

        for(int i =0; i < eRepositories.size();i++){
            enterLinkToBeValid(i);
        }

        return this;
    }
    private void enterLinkToBeValid(int index){
        List<WebElement> eRepositories = driver.findElements(By.cssSelector(".repo-list-item.d-flex.flex-column.flex-md-row.flex-justify-start.py-4.public.source"));
        WebElement eRepository = eRepositories.get(index);
        String attribue = eRepository.findElement(By.cssSelector("a")).getAttribute("href");
        clickElement(eRepository.findElement(By.cssSelector("a")));
        if(eLocalSearch.isEnabled()){
            reportLog(CharRemover.removeCharAtString(attribue,18)+ " has a valid link", Status.PASS);
        }
        else{
            reportLog(CharRemover.removeCharAtString(attribue,18)+ " Does not have a valid link", Status.FAIL);

        }
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("window.history.go(-1)");
    }
}
