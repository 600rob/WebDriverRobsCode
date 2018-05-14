package DriverManagerTests;

import driver.manager.Driver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class NavigateUsingDriverClassTest {

    // 1 . set static web driver to use in the the class
    static WebDriver driver;

    //2 In our at before class we want to run a get method which returns the type of driver we wish to use in out tests
//start building this out into an abstract class
    @BeforeClass
    public static void getDriver() {
// this get acts on the driver class and gets the driver type NOT a url
        // dont get this confused with the get method which acts on a WebDriver Object
        driver = Driver.get();
    }
    //3. start writing a simple test which runs a browser and navigates to it
    @Test
    public void canNavigateUsingChosenBrowser(){

        driver = new ChromeDriver();
        driver.get("www.compendiumdev.co.uk");
    }







}
