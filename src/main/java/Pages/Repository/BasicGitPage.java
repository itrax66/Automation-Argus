package Pages.Repository;

import Pages.BasePageObject;
import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicGitPage extends BasePageObject {

    @FindBy(css = "input[placeholder = 'Search GitHub']")
    protected WebElement eSearchBar;

    @FindBy(css = "#jump-to-results>li.navigation-focus")
    protected WebElement eJumpToResults;

    public GitSearchPage enterSearchPage(String sSearch){
        submitText(eSearchBar,sSearch);

        StopWatch timer = new StopWatch();
        timer.start();

        clickElement(eJumpToResults);
        timer.stop();

        reportLogBeforeAction("Submitted search, elapsed time: "+convertToDoubleFromLong(timer.getTime())/convertToDoubleFromLong(1000)+" seconds",Status.PASS );
        return new GitSearchPage();
    }

}
