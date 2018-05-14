package manipulation;

/**
 * run a test to clear the comments field, enter some new comments,
 * submit the form and assert the newly entered comments
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ManipulationClearFormSumbitNewCommentsTest {

    private static WebDriver driver;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";


    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void canClearandSubmitNewComments(){
// clear the comments box, add new comments and submit the form
        //see help methods for code detail
       clearComments();
       addComments("test text");

        clickSumbmit();



      new WebDriverWait(driver,10).
              until(ExpectedConditions.titleContains("Processed") );

        WebElement submitedComments = driver.findElement(By.cssSelector("li[id='_valuecomments']"));

        assertThat(submitedComments.getText(),is("test text"));

    }

    // Additional help methods to keep code tidy
    public void clickSumbmit(){
    WebElement submitButton = driver.findElement(By.cssSelector("input[value='submit']"));
    submitButton.click();

    }

    public void clearComments(){
        //find web elements for the comments box and submit buttons
        WebElement commentsBox = driver.findElement(By.cssSelector("textarea[name='comments']"));
        commentsBox.clear();


    }

    public void addComments(String commentsText){
        //find web elements for the comments box and submit buttons
        WebElement commentsBox = driver.findElement(By.cssSelector("textarea[name='comments']"));

        commentsBox.sendKeys(commentsText);
    }






    @AfterClass
    public static void quitDriver(){

        driver.quit();
    }


}
