package DriverManagerTests;

import driver.manager.DriverManager;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class NavigateUsingDriverManagerClassTest {

    // 1 . set static web driver to use in the the class
    static WebDriver driver;

    //2 In our at before class we want to run a get method which returns the type of driver we wish to use in out tests
//start building this out into an abstract class
    @BeforeClass
    public static void getDriver() {
// this get acts on the driver class and gets the driver type NOT a url
        // dont get this confused with the get method which acts on a WebDriver Object
        driver = DriverManager.get();
    }
    //3. start writing a simple test which runs a browser and navigates to it using FF
    @Test
    public void createAFFDriver(){

        System.setProperty("selenium2Basics.driver", "firefox");

       assertBrowserTestRuns();
    }

    //4. do the same as above but use a default driver which is used in if we dont set a driver in system properties
    //also refactor repeated code
    @Test
    public void createADefaultDriver(){

        //System.setProperty("selenium2Basics.driver", "firefox");

        assertBrowserTestRuns();
    }


    public void assertBrowserTestRuns(){
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(),is("Basic Web Page Title"));


    }

    @After
    public void quitDriver(){

        driver.quit();
    }







}
