package UserInteractions;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserInteractionsFirstExerciseTest {

    final public static String testUrl = "http://compendiumdev.co.uk/selenium/gui_user_interactions.html";
    private static WebDriver driver;

    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);

    }
    WebElement drag1 = driver.findElement(By.cssSelector("div[id='draggable1']"));
    WebElement drag2 = driver.findElement(By.cssSelector("div[id='draggable2']"));
    WebElement drop1 = driver.findElement(By.cssSelector("div[id='droppable1']"));
    WebElement drop2 = driver.findElement(By.cssSelector("div[id='droppable2']"));
    WebElement canvas = driver.findElement(By.id("canvas"));

    @Test
    public void moveDrag1ToDrop1(){
        //move draggable one onto droppable 1

        new Actions(driver).dragAndDrop(drag1,drop1).perform();

        assertThat(drop1.getText(),is("Dropped!"));

    }


    @Test
    public void moveDrag2ToDrop1(){
        //move draggable one onto droppable 2 and asset the text change

        new Actions(driver).clickAndHold(drag2).moveToElement(drop1).
                release().perform();

        assertThat(drop1.getText(),is("Get Off Me!"));

    }


    @Test
    public void bwaHaHa(){
        //hit ctrl B and assert text change on dragabble 1

        new Actions(driver).keyDown(Keys.CONTROL).sendKeys("b").release().perform();
        //new Actions(driver).sendKeys(Keys.CONTROL).sendKeys("b");

        assertThat(drag1.getText(), is("Bwa! Ha! Ha!"));

    }




    //hit ctrl+space and assert text change on the red square
    //Note this was a red herring.... keyDown actions only work on modifier keys hence
    //they only press space and dont Hold it down, there probably is a way to do this but
    // the exercise highlights the question considering is it worth investing time
    // to automate something that could be tested another way

    /*
     @Test
    public void assertLetsGo() {


        Action hldCTRL = new Actions(driver).keyDown(Keys.CONTROL).build();
        Action hldSPACE = new Actions(driver).keyDown(Keys.SPACE).build();

        hldCTRL.perform();
        hldSPACE.perform();

        assertThat(drop1.getText(), is("Let GO!! "));
        assertThat(drop2.getText(), is("Let GO!! "));
    }
    */


    @Test
    public void canDrawOnCanvas(){
WebElement eventsList = driver.findElement(By.id("keyeventslist"));
int eventCount = eventsList.findElements(By.tagName("li")).size();

        new Actions(driver).clickAndHold(canvas).
                moveByOffset(10,10).
                release().
                perform();

        assertThat(eventCount<(eventsList.findElements(By.tagName("li")).size()),
                is(true));
    }






    @AfterClass
    public static void quitDriver(){
        driver.quit();
    }
}
