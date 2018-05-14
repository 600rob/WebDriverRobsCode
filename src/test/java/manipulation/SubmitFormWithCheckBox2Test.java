package manipulation;

/**
 * submit the form with radio button 2 selected
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SubmitFormWithCheckBox2Test {

    private static WebDriver driver ;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormWithRadio2Selected(){

        //radio button 2 is already selected by default so just assert this then submit the form

       WebElement radioButton = driver.findElement(By.cssSelector("input[name='radioval'][value='rd2']"));
        //assertThat(radioButton.isSelected(),is(true));

        // a better way of doing the line above is to say: is radio button 2 selected already?
        //if so then just submit the form. If not then click radio button 2

        if (!radioButton.isSelected()) {
            radioButton.click();
        }

        WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        submitButton.click();

        new WebDriverWait(driver,10).
                until(ExpectedConditions.titleContains("Processed"));
//double check the output value of the selected radio button
        WebElement radioValue = driver.findElement(By.cssSelector("li[id='_valueradioval']"));
        assertThat(radioValue.getText(),is("rd2"));



    }


    @AfterClass
    public static void quitDriver(){
        driver.quit();

    }

}
