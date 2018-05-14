package SyncStrategies;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * use this class to experiment with the Expected conditions methods
 * class to try and understand what each method does.
 * note We are just using the standard expected conditions here, NOT creating anaymous classes!
 * NOTE each expected condition within the expected conditions class is
 * ACTUALLY an anonymous class wrapped in method!
 * in order to use them we just call the method and dont need to actually use the anonymouns class
 *
 */

public class ExperimentWithExpectedConditionsClassTest {

    private WebDriver driver = new ChromeDriver();


    @Test
    public void exploreTitleIsMethod(){
//The title is method returns a boolean value on whether or not the
// string passed in matched the actual title

        driver.get("https://compendiumdev.co.uk/default.php");

        new WebDriverWait(driver,2).
                until(ExpectedConditions.titleIs
                        ("Software Testing Consultancy, Books and Online Training"));
        //the To String method returns an error message in the case that expected and actual results dont match
        driver.quit();
    }



    @Test
    public void exploreUrlContainsMethod(){
//The contains method returns a boolean value on whether or not the
// string passed in is contained within the url

        driver.get("https://compendiumdev.co.uk/default.php");

        new WebDriverWait(driver,2).
                until(ExpectedConditions.urlContains("comp"));
        driver.quit();
    }

    @Test
    public void exploreVisibilityOfelementLocated(){


        driver.get("https://compendiumdev.co.uk/selenium/bounce/html");

        new WebDriverWait(driver,2).
                until(ExpectedConditions.visibilityOfElementLocated
                        (By.cssSelector("a[href='https://twitter.com/evilteste']")));

        driver.quit();

    }


}







