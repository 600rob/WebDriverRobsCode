package ScreenShots;

import driver.manager.DriverManager;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.fail;

public class ScreenShotExercise1Test {


    WebDriver driver;

    @Test

    public void checkScreenShotUsingCapabilities() {


        driver = DriverManager.get();
        driver.get("https://compendiumdev.co.uk/selenium/search.php");


        //check if we can take screen shots using Has capabilities.
        // Cast a Driver to has capabilities then use get capabilities to check if
        //it can take a screen shot
        if (((HasCapabilities) driver).getCapabilities().is(
                CapabilityType.TAKES_SCREENSHOT
        )) {


            //cast driver to TakeScreenShot Ojb. this allows us to create an instance o capture screen shots
            TakesScreenshot camera = (TakesScreenshot)driver;

            //create a file, using the screen shot obj to capture it. Note it is only a temp file at this point
            File tempScreenShot = camera.getScreenshotAs(OutputType.FILE);
           assertThat(tempScreenShot.length(), is(greaterThan(0L)));

//print the patch to the temp file to prove the screen shot was taken
            System.out.println(tempScreenShot.getAbsolutePath());

            driver.get("file://"+tempScreenShot.getAbsolutePath());
        }else{
            fail("driver does not support screen shots");
        }

driver.quit();
    }

@Test
    public void checkScreenShotUsingCast(){

    System.setProperty("selenium2Basics.driver", "chrome");
    driver = DriverManager.get();
    driver.get("https://compendiumdev.co.uk/selenium/search.php");


    try {
        TakesScreenshot snapper = (TakesScreenshot)driver;

        File screenShot = snapper.getScreenshotAs(OutputType.FILE);

        assertThat(screenShot.length(), is(greaterThan(0L)));

        driver.get("file:\\"+screenShot.getAbsolutePath());

    }catch (ClassCastException e)
    {// if we a re unable to cast to screen shot we'll get a ClassCastException so put an error message here
        System.out.println(e);
    fail("Driver does not support screen shots");}

    driver.quit();

}

@Test
    public void stopStart(){

  WebDriver driver = new ChromeDriver();
    driver.get("https://compendiumdev.co.uk/selenium/search.php");

    driver.quit();

}


}
