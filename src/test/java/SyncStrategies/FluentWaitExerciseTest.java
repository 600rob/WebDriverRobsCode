package SyncStrategies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class FluentWaitExerciseTest {

    String testUrl = "https://compendiumdev.co.uk/selenium/javascript_countdown.html";
    WebDriver driver = new ChromeDriver();
    WebElement countdown;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @Before
    public void setUp() {

        driver.get(testUrl);

        countdown = driver.findElement(By.id("javascript_countdown_time"));

        wait.until(ExpectedConditions.visibilityOf(countdown));

    }


    @After
    public void tearDown() {

        driver.quit();
    }


//FluentWait until can apply to anything
//e.g. WebElement, String, Time, Files etc.
//and can return anything
//WebDriverWait until applies to WebDriver
//and can return anything




    @Test
    public void fluentWaitOnTimer() {
//Create a fluent wait that returns a String with the time when the last two chars are “04”

 // user fluent wait to return a string which we can assert on

String time = new FluentWait<WebElement>(countdown).
        withTimeout(10, TimeUnit.SECONDS).
pollingEvery(100,TimeUnit.MILLISECONDS).
until(new Function<WebElement, String>(){
    @Override
    public String apply(WebElement element){

        return element.getText().endsWith("04") ? element.getText():null;
    }
      }
);
    }


    @ Test

    public void webDriverWaitOnTimer() {
        //Create a webdriver wait that returns a String with the time when the last two chars are “04”

  String time = new WebDriverWait(driver, 10,100).
          until(new Function<WebDriver, String>() {
              @Override
              public String apply(WebDriver driver) {
                  return countdown.getText().endsWith("04") ? countdown.getText(): null;
              }
          });

    }

    }



