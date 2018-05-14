package Windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class WindowsExperimenting {

    private WebDriver driver;
    final private String testUrl = "https://compendiumdev.co.uk/selenium/frames";
    private WebDriverWait wait;

    @Before
    public void startDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(testUrl);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void switchBetweenWindows() {

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

        assertEquals(1, driver.getWindowHandles().size());

        String framesWindowHandle = driver.getWindowHandle();


        driver.switchTo().frame("content");
        driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();

        //check for two windows open
        assertEquals(2, driver.getWindowHandles().size());


        Set<String> myWindows = driver.getWindowHandles();
        String newWindowHandle = "";

        for (String aHandle : myWindows) {
            if (!framesWindowHandle.contentEquals(aHandle)) {
                newWindowHandle = aHandle;
                break;

            }
        }

        driver.switchTo().window(newWindowHandle);
        assertTrue("Expected Selenium Simplified site",
                driver.getTitle().contains("Selenium Simplified"));

        //switch back to the frames window
        driver.switchTo().window(framesWindowHandle);
        assertTrue("Page title should be Frameset example title 6 ",
                driver.getTitle().contains("Frameset Example Title (Example 6)"));

        //go back to the new window, close it and verify we only have only the original window open.

        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(framesWindowHandle);

        assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));


    }


    @Test
                public void switchBetweenWindowsByName(){

            assertThat(driver.getTitle(), is("Frameset Example Title (Example 6)"));

            assertEquals(1, driver.getWindowHandles().size());

            String framesWindowHandle = driver.getWindowHandle();


            //switch to the content frame in order to interact with the content options
            driver.switchTo().frame("content");

            //open the evil tester window
            driver.findElement(By.xpath("//a[@id='goevil']")).click();
            //open the compendium dev window
            driver.findElement(By.xpath("//a[@target='compdev']")).click();

            //switch to the evil tester window by name (by java script)
            driver.switchTo().window("evil");
            assertTrue("page title is evil tester.com",driver.getTitle().equals("EvilTester.com"));

            //switch to the compdev window by name ( by HTML name)
            driver.switchTo().window("compdev");
            assertTrue("page title is evil tester.com",driver.getTitle().contains("Testing Consultancy"));

            //switch back to the frameset window using the window handle object

            driver.switchTo().window(framesWindowHandle);
            assertTrue("page title contains frameset example",
                    driver.getTitle().contains("Frameset Example"));


            //Tidy up
            //now go back to the other two windows, close each one down and
            // verify that we have the correct number of windows open

            driver.switchTo().window("evil");
            driver.close();
            assertEquals("only two windows are left open",2,
                    driver.getWindowHandles().size());
            //repeat with the compdev window

            driver.switchTo().window("compdev");
            driver.close();
            assertEquals("only one window is left open", 1,
                    driver.getWindowHandles().size());

        }




    @After
    public void stopDriver() {
        driver.quit();
    }
}

