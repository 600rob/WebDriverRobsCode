package manipulation;
/**
 * submit a form with Only checkbox 1 selected
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ManipulationSubmitFormCheckBox1Selected {

    private static WebDriver driver ;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormWithRadio2Selected(){

        //check box 3 is already selected by default. find it and deselect it
        WebElement checkBox3 = driver.findElement(By.cssSelector("input[value='cb3']"));
        checkBox3.click();
        assertFalse(checkBox3.isSelected());

        //find checkbox 1 and select it
        WebElement checkBox1 = driver.findElement(By.cssSelector("input[value='cb1']"));
        checkBox1.click();
        assertTrue(checkBox1.isSelected());

//submit the form
        //WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        //submitButton.click();
        clickSumbmit();

        new WebDriverWait(driver,10).
                until(ExpectedConditions.titleContains("Processed"));

//double check the output value of the selected radio button
        WebElement radioValue = driver.findElement(By.cssSelector("li[id='_valuecheckboxes0']"));
        assertThat(radioValue.getText(),is("cb1"));

/*
This test works fine however, revisit the test and write some additional code to make it more robust in terms of checking
checkbox 2 and three and handling them so that they are in the correct state. ie if they are selected, click to de-select
 */

    }

    // Additional help methods to keep code tidy
    public void clickSumbmit(){
        WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        submitButton.click();

    }



    @AfterClass
    public static void quitDriver(){
        driver.quit();

    }



}
