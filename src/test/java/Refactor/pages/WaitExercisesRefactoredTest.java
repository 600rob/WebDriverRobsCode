package Refactor.pages;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class WaitExercisesRefactoredTest {



    private WebDriver driver = new ChromeDriver();
    private BasicAjaxPageObject basicAjaxPage;



//This is the original set of Synchronisation exercises that i wrote in SyncStrategies . I'll use this as my basis to refactor
//to the WaitExercisesRefactoredTests class which will use the BasicAjaxPageObject

//keep this page as is and use it for reference, the refactored code can be seen in the pages package



    @Before
    public void setupTest(){

       basicAjaxPage = new BasicAjaxPageObject(driver);
       basicAjaxPage.get();
    }

    @Test

    public void checkServer()  {

        basicAjaxPage.categorySelect(3);


        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();
    }



    @Test
    public void chooseJavaAfterAjaxComboDissapears(){

        basicAjaxPage.categorySelect(3);



        //select Java then click submit
        selectJavaSubmitFormAndCheckResult();
    }



    //wait until the java element becomes located
    @Test
    public void createInLineWaitUntilJavaOptionLocated(){

        basicAjaxPage.categorySelect(3);


        // select java and submit
        selectJavaSubmitFormAndCheckResult();
    }

    //wait until the java element becomes visible
    @Test
    public void createInLineWaitUntilJavaOptionVisible(){

        basicAjaxPage.categorySelect(3);



        // select java and submit
        selectJavaSubmitFormAndCheckResult();
    }

    //wait unti the java element becomes clickable

    @Test

    public void waitUntilJavaElementIsClickable(){

        basicAjaxPage.categorySelect(3);



        selectJavaSubmitFormAndCheckResult();

    }





    // helper methods:

    private void selectJavaSubmitFormAndCheckResult() {

        basicAjaxPage.selectLanguage(23);


        basicAjaxPage.clickCodeInIt();


 ProcessedFormDetails.processedFormDetails.waitUntilfomDetsilsPageIsLoaded();

        


        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));

        // don't have to synchronise with other browsers but do with GeckoDriver
        //WebElement languageWeUsed = driver.findElement(By.id("_valuelanguage_id"));
        WebElement languageWeUsed = new WebDriverWait(driver,10).until(elementToBeClickable( By.id("_valuelanguage_id")));
        assertEquals("Expected Java code", "23",languageWeUsed.getText());
    }



}