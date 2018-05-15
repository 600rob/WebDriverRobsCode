package navigation;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class FirefoxDriverManagerTest {

    @Test
    public void canRunFireFoxBrowserTest(){
/*lines 16 to 20 show how we would use the marionette driver if it is not on our system path.
We basically set a system property in the code, which tells the code where to look for the marionette driver
note, ive stored it in a location with in this project for this example! This is useful if we
want to use different versions of gecko driver without changing the path*/

        /*String currentDir = System.getProperty("user.dir");
        //currentDir uses the root path of THIS project!
        String marionetteDriverLocation = currentDir+
                "/Tools/Marionette/geckodriver.exe";
        System.setProperty("webdriver.gecko.driver",marionetteDriverLocation);*/

        WebDriver driver = new FirefoxDriver();

        driver.get("http://compendiumdev.co.uk/selenium");

        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));

        driver.quit();

    }
}
