package interogation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class CssPaths {

    private static WebDriver driver;
    private static String testUrl = "https://compendiumdev.co.uk/selenium/find_by_playground.php";

    @BeforeClass

    public static void startDriver(){
        driver  = new ChromeDriver();
        driver.get(testUrl);
    }


    @Test
    public void directDescendent(){
        /*this test uses a Css path to find an element that is the direct descendant of an other.
    for example, find element B any where under element A. Note the element you are looking for
     needs to be directly under the parent.
     as an example the following method finds and asserts there are 41 paragraphs directly below a div element and no list
     items directly below a div */
        assertEquals("there should be 41 P elements below a div element on the page",41,
                driver.findElements(By.cssSelector("div > p")).size());
        assertEquals("there are no list items directly below a div",
                0, driver.findElements(By.cssSelector("div > li")).size());
        // Note, list items are directly below ul elements which are below div elements
    }

    @Test
    public void anyDescendant() {
        /*this checks that there are 41 paragraph elemnets ANYWHERE below a Div and 25 list items ANYWHERE below a div.
        it basically finds 25 list items that are under ui elements which are in turn below div elements */

        assertEquals("there should be 41 P elements ANYWHERE below a div element on the page", 41,
                driver.findElements(By.cssSelector("div p")).size());
        assertEquals("there are 25 list items ANYWHERE below a div", 25,
                driver.findElements(By.cssSelector("div li")).size());
    }

        @Test
                public void siblingsOf(){

            assertEquals("25 total list elements",25,
                    driver.findElements(By.cssSelector("div li")).size());
            assertEquals("24 list elements have siblings that are list elements", 24,
                    driver.findElements(By.cssSelector("li +li")).size());




        }




@AfterClass
    public static void quitDriver(){
        driver.close();
    }
}
