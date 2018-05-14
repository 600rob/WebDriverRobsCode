package Frames;

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
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FramesExercise1Test {

    private WebDriver driver;
    final private String testUrl = "https://compendiumdev.co.uk/selenium/frames/index.html";
    private WebDriverWait wait;

    @Before
    public void startDriver(){
        driver = new ChromeDriver();
        driver.navigate().to(testUrl);
        wait = new WebDriverWait(driver,10);
}

@Test
public void navigateToGreenPageandBackToOriginal(){

        // switch to the frame called related
    driver.switchTo().frame("content");
//within the frame search for the webelement required and navigate to the green page
  driver.findElement(By.cssSelector("li a[href='green.html']")).click();


    //make sure that we made it to the green page
    wait.until(presenceOfElementLocated(By.cssSelector("h1[id='green']")));

    //find the link to go to the original page and click on it
    driver.findElement(By.cssSelector("a[href='content.html']")).click();
//make sure that we are back on the original page by checking h1 element updates to show content
    wait.until(presenceOfElementLocated(By.xpath("//h1[.='Content']")));

}

@Test
public void workignWithIframe() {

    driver.switchTo().frame("menu");
    //find the web element we want to click with in the frame, dont bother with a web element object as we only use it once
    driver.findElement(By.cssSelector("a[href='iframe.html")).click();

    //make sure we're on the correct page
    assertEquals("Iframe Below", driver.findElement(By.tagName("h4")).getText());

// the iframe does not have a name so locate it by  we could also use the frame number index which is zero. *
    //we know it is zero because its the only frame on that page ( maybe refctor using index number!
    WebElement iFrameElement = driver.findElement(By.cssSelector("iframe[src='iframe_contents.html']"));
// switch to frame on the web element
    driver.switchTo().frame(iFrameElement);


//put the more example options into a list
    List<WebElement> moreExamples = driver.findElements(By.cssSelector("[target~=_top]"));
//check that the list size is correct
    assertEquals(5, moreExamples.size());
    // navigate to example 5 using the list element to click on
    moreExamples.get(4).click();

    wait.until(titleIs("Frameset Example Title (Example 5)"));
    //Switch to the content frame
    driver.switchTo().frame("content");

    //click on load main frames example: first find the web element we want to click with in the frame
    driver.findElement(By.cssSelector("a[href='index.html']")).click();
    wait.until(titleIs("Frameset Example Title (Example 6)"));
}



    @After
    public void stopDriver(){
      driver.quit();

    }


}
