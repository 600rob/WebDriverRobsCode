package SyncStrategies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


public class FeelThePainTest {

    private WebDriver driver = new ChromeDriver();
    private WebDriverWait wait =  new WebDriverWait(driver,50);


    @Before

    public void startDriver() {
        driver.get("https://compendiumdev.co.uk/selenium/basic_ajax.html");
    }


    @Test
    public void tryToClickCodeInIt(){

        //make sure we are on correct page
        assertEquals("page title is Basic Ajax", "Basic Ajax",
                driver.getTitle());

        List<WebElement> categoryOptions = driver.findElements
                (By.xpath("//select[@id='combo1']// option[@value]"));
        categoryOptions.get(2).click();

      //wait.until(invisibilityOfElementLocated
        //      (By.xpath("//span[@style='display: inline;']")));
        
       wait.until(presenceOfElementLocated
             (By.xpath("//span[@style='display: none;']")));



        List<WebElement> languageOptions = driver.findElements
                (By.xpath("//select[@id='combo2']// option[@value]"));

        assertThat(languageOptions.size(),is(4));

        languageOptions.get(3).click();

        driver.findElement(By.xpath("//input[@type='submit']")).click();

       wait.until(titleIs
                ("Processed Form Details"));

        assertThat(driver.findElement(By.xpath("//li[@id='_valuelanguage_id']")).getText(),
                is("23"));









    }




    @After
    public void closeDriver(){
        driver.quit();
    }

}
