package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;


/**
 * select multiple select items 1,2,3 then submit the form
 */

public class ManipulationSubmitFormWithMulti123Selected {

    private static WebDriver driver;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
    //Actions hold = new Actions(driver);
    //Actions release = new Actions(driver);

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormwithMulti123Selected(){


//option 1, find each element then click on each to deselect/select as required before submitting form
        //WebElement multi1 = driver.findElement(By.cssSelector("option[value='ms1']"));
        //WebElement multi2 = driver.findElement(By.cssSelector("option[value='ms2']"));
        //WebElement multi3 = driver.findElement(By.cssSelector("option[value='ms3']"));
        //WebElement multi4 = driver.findElement(By.cssSelector("option[value='ms4']"));


        //multi4.click();
        //multi1.click();
        //multi2.click();
        //multi3.click();

        //would probably be better to do all of the above by finding
        // the top level element and store its values in WebElement List as a list using each index position to click on
        // (see below)


        //Option 2 create the list on the top level element, NOT the driver!
        /*List<WebElement> multiSelectList = multiSelect.findElements(By.tagName("option"));
        assertThat(multiSelectList.size(),is(4));

        multiSelectList.get(3).click();
        multiSelectList.get(0).click();
        multiSelectList.get(1).click();
        multiSelectList.get(2).click();
        */

        //option 3 use the select class on a WebElement
        WebElement multiSelect = driver.findElement
                (By.cssSelector("select[multiple='multiple']"));
//instantiate a select object on the Webelement
        Select multiSelector = new Select(multiSelect);
        // just check that this element is multi select!
        assertTrue(multiSelector.isMultiple());



        //use the methods on the Select class to deselect and select the required options before submitting
        multiSelector.deselectByIndex(3);
        multiSelector.selectByIndex(0);
        multiSelector.selectByValue("ms2");
        multiSelector.selectByValue("ms3");





        // Actions releaseshift = release.keyUp(Keys.SHIFT);



        WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        submitButton.click();

        new WebDriverWait(driver,10).
                until(ExpectedConditions.titleContains("Processed"));


        List<WebElement> selectedValues = driver.findElements(By.cssSelector("li[id*='_valuemultiple']"));

        assertThat(selectedValues.size(),is(3));

        assertThat(selectedValues.get(0).getText(),
                is("ms1"));
        assertThat(selectedValues.get(1).getText(),
                is("ms2"));
        assertThat(selectedValues.get(2).getText(),
                is("ms3"));

    }


    @AfterClass
    public static void stopDriver(){

        driver.quit();
    }
}
