package Alerts;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

public class AlertsExersiceTest {

    private static WebDriver driver;
    final private static String testURL = "http://compendiumdev.co.uk/selenium/alerts.html";


    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.navigate().to(testURL);

    }

    WebElement alertBox = driver.findElement(By.cssSelector("input[id='alertexamples']"));
    WebElement confirmBox = driver.findElement(By.cssSelector("input[id='confirmexample']"));
    WebElement promptBox = driver.findElement(By.cssSelector("input[id='promptexample']"));
    WebElement confirmReturn = driver.findElement(By.id("confirmreturn"));
    WebElement promptReturn = driver.findElement(By.id("promptreturn"));

    @Test
    public void acceptAlert(){

        alertBox.click();
        assertEquals("I am an alert box!",driver.switchTo().
                alert().getText());
        driver.switchTo().alert().accept();

    }


    @Test
    public void dismissAlert(){

        alertBox.click();
        assertEquals("I am an alert box!",driver.switchTo().
                alert().getText());
        driver.switchTo().alert().dismiss();

    }



    @Test
    public void acceptConfirm(){
//accept the confirm box, assert the changes on each
        confirmBox.click();
        assertEquals("I am a confirm alert",driver.switchTo().
                alert().getText());
        driver.switchTo().alert().accept();
        assertEquals("true", confirmReturn.getText());
    }


    @Test
    public void dismissConfirm(){
        confirmBox.click();
        assertEquals("I am a confirm alert",driver.switchTo().
                alert().getText());
        driver.switchTo().alert().dismiss();
        assertEquals("false", confirmReturn.getText());
    }

    @Test
    public void acceptPrompt() {
//accept the prompt box and assert then changes

        assertEquals("pret", promptReturn.getText());
        promptBox.click();

        assertEquals("I prompt you", driver.switchTo().
                alert().getText());

        driver.switchTo().alert().accept();


        assertEquals("change me", promptReturn.getText());

    }


        @Test
                public void dismissPrompt(){
//dismiss the prompt box and assert the changes
            assertEquals("pret",promptReturn.getText());
        promptBox.click();
        assertEquals("I prompt you",driver.switchTo().
                alert().getText());


        driver.switchTo().alert().dismiss();
        assertEquals("pret", promptReturn.getText());

        }

        @Test
        public void changePromptTextAccept(){
                    //open the prompt box, change the text, accept and assert the changes

            promptBox.click();

            driver.switchTo().alert().sendKeys("changed text");
            driver.switchTo().alert().accept();

            assertEquals("changed text", promptReturn.getText());


        }

    @Test
    public void changePromptTextDismiss(){
        //open the prompt box, change the text, dismiss  and assert the changes

        promptBox.click();

        driver.switchTo().alert().sendKeys("changed text again");
        driver.switchTo().alert().dismiss();

        assertEquals("pret", promptReturn.getText());


    }








    @AfterClass
    public static void stoprDriver(){
        driver.quit();

    }
}
