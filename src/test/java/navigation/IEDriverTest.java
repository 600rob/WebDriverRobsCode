package navigation;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.Assert.assertTrue;

public class IEDriverTest {

    @Test

    public void canRunIEDriverTest(){

       //change the location of the driver if home config stores the exe in a differnt location
        String ieDriverLocationLGC = ("C:\\Automation Tools\\IEDriver\\IEDriverServer.exe");
        //String ieDriverLocationHOME = ("C:\\Automation Tools\\IEDriver\\IEDriverServer.exe");

        System.setProperty("webdriver.ie.driver", ieDriverLocationLGC);

        WebDriver iedriver = new InternetExplorerDriver();

        iedriver.get("http://compendiumdev.co.uk/selenium");

        assertTrue(iedriver.getTitle().startsWith("Selenium Simplified"));

        iedriver.quit();

    }


    @Test
    public void getSysProp(){
        //quick experimentation with system properties to try and understand what gwan?!

        String userDir = System.getProperty("user.home");

        System.out.println(userDir);

        System.setProperty("webdriver.ie.driver", "C:\\Automation Tools\\IEDriver\\IEDriverServer.exe");

        String iedir = System.getProperty("webdriver.ie.driver");

        System.out.println(iedir);

    }


}
