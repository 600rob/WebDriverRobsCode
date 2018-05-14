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

public class CssBasicExercise {


    private static WebDriver driver;
    final private static String testUrl = "https://compendiumdev.co.uk/selenium/find_by_playground.php" ;


    @BeforeClass
    public static void startBrowser(){
        driver = new ChromeDriver();
        driver.navigate().to(testUrl);
    }

    @Test
    public void findById(){


        //WebElement element1 = driver.findElement(By.id("p31"));

        //replace with ByCssSelector
        WebElement element1 = driver.findElement(By.cssSelector("#p31"));
        assertThat(element1.getAttribute("name"),is("pName31"));
    }


    @Test
    public void findByName(){
        //WebElement element2 = driver.findElement(By.name("ulName1"));

        //replace with ByCssSelector
        WebElement element2 = driver.findElement(By.cssSelector("[name=ulName1]"));
        assertThat(element2.getAttribute("id"),is("ul1"));


    }

    @Test
    public void findByClassName(){
        //WebElement element3 = driver.findElement(By.className("SpecialDiv"));
        //replace with ByCssSelector
        WebElement element3 = driver.findElement(By.cssSelector(".SpecialDiv"));
        assertThat(element3.getAttribute("id"),is("div1"));

    }

    @Test
    public void findByTagName(){
        //WebElement element1 = driver.findElement(By.tagName("li"));
        //replace with ByCssSelector
        WebElement element1 = driver.findElement(By.cssSelector("li#li1"));
        assertThat(element1.getAttribute("name"),is("liName1"));


    }




    @AfterClass
    public static void closeDriver(){
        driver.quit();
    }


}
