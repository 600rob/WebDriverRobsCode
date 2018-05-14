package manipulation;

/**
 *
 so i tried to approach this by running all of the questions, 1- 7 in a single test....got a s far as covering 1-3
 in the first method and realised it would be better to break up into smaller more manageable chunks

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

public class ManipulationFirstExerciseTest {



    final static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";
    private static WebDriver driver;
    private WebElement submitButton;
    private WebElement commentsField;
    private WebElement submittedComments;
    private WebElement radioItems;

    @BeforeClass
    public static void startDriver() {
        driver = new ChromeDriver();
        driver.get(testUrl);

    }

    @Test
    public void submitFormAssertpageTitleChanges() {
        //click on the submit button
        submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.titleIs("Processed Form Details"));

        assertThat(driver.getTitle(), is("Processed Form Details"));



            //navigate back to form page
            driver.navigate().back();
            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.titleContains("HTML"));

//clear text and enter new text
            commentsField = driver.findElement(By.cssSelector("textarea[name='comments']"));
            commentsField.clear();
            commentsField.sendKeys("rob new text");

            //assert that radio 2 is selected
        radioItems= driver.findElement(By.cssSelector("input[value='rd2']"));
        assertThat(radioItems.isSelected(),is(true));

        //submit form
            submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
            submitButton.click();


            new WebDriverWait(driver, 5).
                    until(ExpectedConditions.titleIs("Processed Form Details"));
//check comments entered are correct could als check radio value 2 but i already did that before submission
            submittedComments = driver.findElement(By.cssSelector("li[id='_valuecomments']"));
            assertThat(submittedComments.getText(), is("rob new text"));
        }



        @AfterClass
        public static void closeDriver () {
            driver.quit();
        }


}


