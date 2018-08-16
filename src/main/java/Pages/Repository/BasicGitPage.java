package Pages.Repository;

import Pages.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasicGitPage extends BasePageObject {

    @FindBy(css = "input[placeholder = 'Search GitHub']")
    protected WebElement eSearchBar;

    @FindBy(css = "#jump-to-results>li")
    protected WebElement eJumpToResults;

    public GitSearchPage enterSearchPage(String sSearch){
        submitText(eSearchBar,sSearch).clickElement(eJumpToResults);
        return new GitSearchPage();
    }

}
