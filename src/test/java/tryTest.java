import Pages.PageNavigator;
import TestBaseClasses.TestBaseClass;
import org.testng.annotations.Test;

public class tryTest extends TestBaseClass {

    @Test
    public void doTest(){
        PageNavigator.BasicGitPage().enterSearchPage("selenium");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
