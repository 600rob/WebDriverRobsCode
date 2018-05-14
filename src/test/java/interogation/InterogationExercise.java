package interogation;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.CoreMatchers.is;

public class InterogationExercise {
//create a WebDriver instance statically that we can use in an @before method
    //this means we don't have to instantiate a new driver for every test that we want to run
  private static WebDriver driver;

    @BeforeClass
    public static void startDriver() {
        driver = new ChromeDriver();


    }
final private String testUrl = "https://www.compendiumdev.co.uk/selenium/basic_web_page.html" ;



    @Test
    public void canCheckTheBasicWebPage() {


        driver.navigate().to(testUrl);
        assertEquals("Title is Basic web Page", "Basic Web Page Title", driver.getTitle());
        assertThat("assert url is as expected",
                driver.getCurrentUrl(), is(testUrl));
        assertThat(driver.getPageSource(), containsString("A paragraph of text"));
    }

        @AfterClass
        public static void closeDriver () {

            driver.quit();
        }



}