/**
 * This class is the follow up to feel the pain test.
 * It explores the different ways we can synchronise with the page effectivley
 */

package Refactor;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class WaitExercisesTest {

    private WebDriver driver = new ChromeDriver();



//This is the original set of Synchronisation exercises that i wrote in SyncStrategies . I'll use this as my basis to refactor
//to the WaitExercisesRefactoredTests class which will use the BasicAjaxPageObject

//keep this page as is and use it for reference, the refactored code can be seen in the pages package

    @Test

    public void checkServer()  {
        driver.get("http://compendiumdev.co.uk/selenium/" +
                                                      "basic_ajax.html");

    // select Server
    WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();


        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();
}



    @Test
    public void chooseJavaAfterAjaxComboDissapears(){
        startBrowserAndSelectServer();
        // wait til ajax busy becomes invisible
        new WebDriverWait(driver,10).until(
                ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy")));
        //select Java then click submit
        selectJavaSubmitFormAndCheckResult();
    }



       //wait until the java element becomes located
    @Test
    public void createInLineWaitUntilJavaOptionLocated(){

        startBrowserAndSelectServer();

        //wait til java option is selectable
        new WebDriverWait(driver,10).until(
                ExpectedConditions.presenceOfElementLocated
                        (By.cssSelector("option[value='23']")));
        // select java and submit
        selectJavaSubmitFormAndCheckResult();
    }

    //wait until the java element becomes visible
    @Test
    public void createInLineWaitUntilJavaOptionVisible(){

        startBrowserAndSelectServer();

        //wait til java option is selectable
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated
                        (By.cssSelector("option[value='23']")));
        // select java and submit
        selectJavaSubmitFormAndCheckResult();
    }

    //wait unti the java element becomes clickable

    @Test

    public void waitUntilJavaElementIsClickable(){
        startBrowserAndSelectServer();

        //wait tin the java element is clickable

        new WebDriverWait(driver,10).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("option[value='23']")));
        //select java and submit
        selectJavaSubmitFormAndCheckResult();

    }





    // helper methods:

    public void startBrowserAndSelectServer() {
      driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();
    }

    private void selectJavaSubmitFormAndCheckResult() {
        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }



}


