package SyncStrategies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

/**
 * This class uses custom Expected conditions to synchronise on the page
 */

public class CustomWaitExcersicesTest {

    String testUrl = "https://compendiumdev.co.uk/selenium/basic_redirect.html";
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 15);
    By twoSecTransport = By.cssSelector("a[id='delaygotobounce']");
    By fiveSecTransport = By.cssSelector("a[id='delaygotobasic']");


    @Before
    public void setUp() {
        driver.get(testUrl);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    /*
    navigate to the redirect page, click on each of the links and verify that the page changes.
    Also create a custom expected condition that ensures the title does not contain a string
     */
    //the following approach uses a private class
    @Test
    public void waitOnPageTitleUsingPrivateClass() {


        wait.
                until(ExpectedConditions.titleIs("Basic Redirects"));

        driver.findElement(twoSecTransport).click();


        assertEquals("Bounce",
                wait.until(
                        new TitleDoesNotContain("Redirects")));
        //the whole wait statement returns or actual result which is used in the assert

    }


//        assertTrue(driver.getTitle().contains("Bounce"));


    //the following approach uses an anonymous class, wrapped in private method

    @Test
    public void waitOnPageTitleAnonClassInMethod() {

        wait.until(ExpectedConditions.titleIs("Basic Redirects"));
        //use this to sync on the 2 second button as the test sometimes runs too fast
        wait.until(ExpectedConditions.visibilityOfElementLocated(twoSecTransport));
        driver.findElement(twoSecTransport).click();

        assertEquals("page title is bounce", "Bounce",
                wait.until(titledontContainACM("Redirects")));

    }




    private ExpectedCondition<String> titledontContainACM(final String titlenotContained) {



     return new ExpectedCondition<String>() {


                @Override
                public String apply (WebDriver webdriver){
                String currentTitle = webdriver.getTitle();
                if (currentTitle.contains(titlenotContained)){
                    return null;}
                else{
                    return currentTitle;
                }
            }


            };

        }













            private class TitleDoesNotContain implements ExpectedCondition<String> {
                String titletext;

                public TitleDoesNotContain(String stringNotContained) {

                    this.titletext = stringNotContained;
                }

                @Override
                public String apply(WebDriver webdriver) {

                    String currentTitle = (webdriver.getTitle());
                    if (currentTitle.contains(titletext)) {
                        return null;
                    } else {
                        return currentTitle;
                    }
                }
            }
}

