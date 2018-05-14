package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * select drop down item 5 then submit the form
 */

public class ManipulationSubmitFormWithDropdown5Selected {

    private static WebDriver driver;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormwithDropDown5Selected(){

        //first find the top level element that contains option 5
        WebElement dropDown = driver.findElement(By.cssSelector("select[name='dropdown']"));
// then find the exact element with in the drop down menu that we require
        WebElement optionFive = dropDown.findElement(By.cssSelector("option[value='dd5']"));
        optionFive.click();

        WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        submitButton.click();

        new WebDriverWait(driver,10).
                until(ExpectedConditions.titleContains("Processed"));

        WebElement dropDownValue = driver.findElement(By.cssSelector("li[id='_valuedropdown']"));
        assertThat(dropDownValue.getText(),is("dd5"));

    }








    @AfterClass
    public static void stopDriver(){

        driver.quit();
    }
}
