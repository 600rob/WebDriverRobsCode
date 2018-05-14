package SyncStrategies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;


/**
 * Tests taken from the following online source and used to help study custom conditions
 *
 * https://seleniumjava.com/2017/01/06/how-to-create-custom-expected-conditions-in-selenium/
 *
 *
 */

public class OnlineCustomexpecteConditionTest {


    WebDriver driver;
    WebDriverWait wait;

    By searchFieldXpath = By.xpath("//input[@id='search']");
            By resultLinkLocator = By.xpath("//button[@class='siteSearchSubmit outline']");

    String homeUrl = "https://www.lgcstandards.com/";
    String homeTitle = "Reference Materials, Reference Standards & Proficiency Testing | LGC Standards";

    String resultsTitle = "Search | LGC Standards";
    String resultsUrl = "https://www.lgcstandards.com/GB/en/search/";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() {
        driver.get(homeUrl);

        //click on the uk link
        driver.findElement(By.xpath("//a[@href='/GB/en']")).click();



        if (!wait.until(titleContains(homeTitle)) ||
                !wait.until(urlContains(homeUrl)))
            throw new RuntimeException("home page is not displayed");

        WebElement searchField = wait.until(
                elementToBeClickable(searchFieldXpath));
        searchField.click();
        searchField.sendKeys("ibuprofen");

        WebElement searchButton = wait.until(
                elementToBeClickable(resultLinkLocator));
        searchButton.click();

        if (!wait.until(titleContains(resultsTitle)) ||
                !wait.until(urlContains(resultsUrl)))
            throw new RuntimeException("results page is not displayed");
    }
/*
We use 2 expected conditions for verifying if a page is displayed.

Selenium does not include by default an expected condition that checks that both the page title and url are correct.

This is where we can create custom expected conditions.
 */


/*
A custom expected condition is a class that

implements the ExpectedCondition interface
has a constructor with the parameters of the expected condition
overrides the apply method
Lets rewrite the previous exercise with a custom expected condition that verifies if a page is displayed:
 */

    @Test
    public void test2() {
        driver.get(homeUrl);

        //click on the uk link
        driver.findElement(By.xpath("//a[@href='/GB/en']")).click();


        if(!wait.until(new PageLoaded1(homeTitle, homeUrl)))
            throw new RuntimeException("home page is not displayed");




        WebElement searchField = wait.until(
                elementToBeClickable(searchFieldXpath));
        searchField.click();
        searchField.sendKeys("keyword");

        WebElement searchButton = wait.until(
                elementToBeClickable(resultLinkLocator));
        searchButton.click();

        if (!wait.until(new PageLoaded1(resultsTitle, resultsUrl)))
            throw new RuntimeException("results page is not displayed");
    }
// We have abstracted away the PageLoded Class

    private class PageLoaded1 implements ExpectedCondition<Boolean> {

         String expectedTitle;
         String expectedUrl;

        public PageLoaded1(String expectedTitle, String expectedUrl) {
            this.expectedTitle = expectedTitle;
            this.expectedUrl = expectedUrl;
        }

        @Override
        public Boolean apply(WebDriver driver){
            Boolean correctTitle =
                    driver.getTitle().contains(expectedTitle);

            Boolean correctUrl =
                    driver.getTitle().contains(expectedUrl);
            return correctTitle && correctUrl;


        }
    }

}





