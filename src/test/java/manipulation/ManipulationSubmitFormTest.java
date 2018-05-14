package manipulation;

import org.junit.After;
import org.junit.Before;
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
 *run a test to submit the form page and assert the title changes
 */

public class ManipulationSubmitFormTest {


    private WebDriver driver;
    final private String testUrl= "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";

    @Before
    public void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormWithButtonClick(){
        //click on the submit button
        //WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        //submitButton.click();
      clickSumbmit();

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.titleIs("Processed Form Details"));

        assertThat(driver.getTitle(), is("Processed Form Details"));

    }

    @Test
    public void submitFormWithSubmit() {
        //click on the submit button
        //WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        //submitButton.submit();
        clickSumbmit();

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.titleIs("Processed Form Details"));

        assertThat(driver.getTitle(), is("Processed Form Details"));
    }


    // Additional help methods to keep code tidy
    public void clickSumbmit(){
        WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
        submitButton.click();

    }


    @After
    public void quitDriver(){
        driver.quit();
    }
}
