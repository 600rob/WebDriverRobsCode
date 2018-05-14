package interogation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindElementTest {

    private static WebDriver driver;
    private static String testUrl = "https://compendiumdev.co.uk/selenium/find_by_playground.php";

    @BeforeClass
    public static void startDriver() {
        driver = new ChromeDriver();
        driver.navigate().to(testUrl);
    }



    @Test
    public void canAssertByElementId() {
        WebElement element1 = driver.findElement(By.id("p1"));
        assertThat(element1.getText(),is("This is a paragraph text"));

    }

    @Test
    public void canAssertByAttribute() {
        WebElement element2 = driver.findElement(By.linkText("jump to para 5"));
        assertThat(element2.getAttribute("id"),is("a31"));
    }

    @Test
    public void canAssertByName() {
        WebElement element3 = driver.findElement(By.name("pName11"));
        assertThat(element3.getAttribute("name"),is("pName11"));

    }

    @Test
    public void canAssertByPartialLinkText() {
        //using partial link text will return the first instance of that text being searched for
        // so do a couple of different examples to illustrate this

        // this will find the first instance containing "jump to" on the page
        WebElement element4 = driver.findElement(By.partialLinkText("jump to"));
        //check whe have the correct element
        assertThat(element4.getAttribute("name"),is("aName26"));


        element4 = null;
        //again this will find the first instance which in this case is the same web element
        element4= driver.findElement(By.partialLinkText("0"));
        //check it
        assertThat(element4.getText(),is("jump to para 0"));

        element4 = null;
       //find a different element, it will find "jump to para 7" not 17
        element4= driver.findElement(By.partialLinkText("7"));
        //check it
        assertThat(element4.getText(),is("jump to para 7"));
    }

    @Test
    public void canAssertByClassName() {
        WebElement element5 = driver.findElement(By.className("specialDiv"));
        assertThat(element5.getAttribute("name"),is("mydivname"));

    }

    @AfterClass
    public static void stopDriver() {
        driver.quit();
    }

}
