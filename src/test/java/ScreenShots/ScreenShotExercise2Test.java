package ScreenShots;

import driver.manager.DriverManager;
import org.junit.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;

public class ScreenShotExercise2Test {


    WebDriver driver;

    @Test

    public void checkScreenShotAndSaveFile() {

        System.setProperty("selenium2Basics.driver", "chrome");

        driver = DriverManager.get();
        driver.get("http:seleniumsimplified.com");


        //check if we can take screen shots using Has capabilities.
        // Cast a Driver to has capabilities then use get capabilities to check if
        //it can take a screen shot
        if (((HasCapabilities) driver).getCapabilities().is(
                CapabilityType.TAKES_SCREENSHOT
        )) {






//cast driver to TakeScreenshot obj
            TakesScreenshot camera = (TakesScreenshot)driver;

            //use object to take a screen shot an put it in a temporary file
            File tempScreenshot = camera.getScreenshotAs(OutputType.FILE);
// we can prove the screen shot was stored by prinitnign its patch name out
            //System.out.println(tempScreenshot.getAbsolutePath());

            //At this point the screen shot is only a temp file, in order to store it and use it in our test reporting we need to do a few more steps

            //create new folder to store screen shot
            File screenShotDirectory = new File("c:\\temp\\screenshots\\");

            // now instantiate screen shot object, and specify its location and name
            //using the location we set earlier

            File myScreenShot = new File(screenShotDirectory, "page_screenShot");
            driver.get("file\\" + myScreenShot.getAbsolutePath());



            driver.quit();


        }


    }



}
