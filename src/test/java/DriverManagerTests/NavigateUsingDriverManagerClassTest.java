package DriverManagerTests;

import driver.manager.DriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class NavigateUsingDriverManagerClassTest {


    public static final String SELENIUM2_BASICS_DRIVER = "selenium2Basics.driver";
    WebDriver driver;


    //2 In our at before class we want to run a get method which returns the type of driver we wish to use in out tests
//start building this out into an abstract class

    //3. start writing a simple test which runs a browser and navigates to it using FF
    @Test
    public void createAFFDriver(){

        System.setProperty(SELENIUM2_BASICS_DRIVER, "firefox");

       assertBrowserTestRuns();
    }

    //4. do the same as above but use a default driver which is used in if we dont set a driver in system properties
    //also refactor repeated code. note ive used chrome as the default driver
    @Test
    public void createADefaultDriver(){

        //System.setProperty("selenium2Basics.driver", "firefox");

        assertBrowserTestRuns();
    }

    @Test
    public void createAnIeDriverTest(){
        //set the driver location string
        String ieDriverLocationLGC = ("C:\\Automation Tools\\IEDriver\\IEDriverServer.exe");
        //use the string to set the driver property
        System.setProperty("webdriver.ie.driver", ieDriverLocationLGC);

        //set the property to call a new ie driver instance
        System.setProperty(SELENIUM2_BASICS_DRIVER, "ie");

        assertBrowserTestRuns();


    }


    public void assertBrowserTestRuns(){
        driver = DriverManager.get();
        driver.get("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertThat(driver.getTitle(),is("Basic Web Page Title"));


    }

    @After
    public void quitDriver(){
        driver.quit();
    }







}
