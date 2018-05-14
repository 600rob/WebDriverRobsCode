package Windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WindowsRefreshTest{

private String testUrl = "https://compendiumdev.co.uk/selenium/frames";
private WebDriver driver;


@Before
    public void startDriver(){

    driver = new ChromeDriver();
    driver.get(testUrl);
}


@Test
public void switchToNewWindow() {


    String framesWindowHandle = driver.getWindowHandle();
    assertEquals("Expected only 1 current window", 1, driver.getWindowHandles().size());

    driver.switchTo().frame("content");
    driver.findElement(By.cssSelector("a[href='http://www.seleniumsimplified.com']")).click();
    assertEquals("Expected a New Window opened", 2, driver.getWindowHandles().size());

    Set<String> myWindows = driver.getWindowHandles();
    String newWindowHandle = "";

    for (String aHandle : myWindows) {
        if (!framesWindowHandle.contentEquals(aHandle)) {
            newWindowHandle = aHandle;
            break;
        }
    }

    driver.switchTo().window(newWindowHandle);

    // for Marionette Geckodriver need to switchTo defaultContent to check title
    driver.switchTo().defaultContent();

    assertTrue("Expected Selenium Simplified site",
            driver.getTitle().contains("Selenium Simplified"));


    //switch back to the frame window

    driver.switchTo().window(framesWindowHandle);

    assertThat(driver.getTitle(),is("Frameset Example Title (Example 6)"));

    //swtich to the content frame

    driver.switchTo().frame("content");

  driver.findElement(By.xpath("//a[@id='goevil']")).click();
  driver.findElement(By.cssSelector("a[target='compdev']")).click();

  driver.switchTo().window("evil");
  assertThat(driver.getTitle(),is("EvilTester.com"));
  driver.switchTo().window("compdev");
  assertTrue(driver.getTitle().contains("Software"));




}



@After
    public void quitDrtiver(){

        driver.quit();
}


}
