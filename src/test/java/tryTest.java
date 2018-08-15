import TestBaseClasses.TestBaseClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class tryTest extends TestBaseClass {

    @Test
    public void doTest(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
