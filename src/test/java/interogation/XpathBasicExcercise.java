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
import static org.junit.Assert.assertEquals;

public class XpathBasicExcercise {

    private static WebDriver driver;
   final static private String testUrl = "https://compendiumdev.co.uk/selenium/find_by_playground.php";

   @BeforeClass
    public static void startDriver(){
       driver = new ChromeDriver();
       driver.navigate().to(testUrl);

   }

   @Test
    public void findElementByID(){

       //WebElement element1 = driver.findElement(By.id("p31"));
       // replace with xpath locator
       WebElement element1 = driver.findElement(By.xpath("//p[@id='p31']"));
       assertEquals(element1.getAttribute("name"),"pName31");
       System.out.println(element1.getAttribute("name"));

   }

    @Test
    public void findByName(){
        //WebElement element2 = driver.findElement(By.name("ulName1"));

        //replace with XpathSelector
        WebElement element2 = driver.findElement(By.xpath("//ul[@name='ulName1']"));
        assertThat(element2.getAttribute("id"),is("ul1"));


    }

    @Test
    public void findByClassName(){
        //WebElement element3 = driver.findElement(By.className("SpecialDiv"));
        //replace with Xpath
        WebElement element3 = driver.findElement(By.xpath("//div[@class='specialDiv']"));
        assertThat(element3.getAttribute("id"),is("div1"));

    }

    @Test
    public void findByTagName(){
        //WebElement element1 = driver.findElement(By.tagName("li"));
        //replace with ByXpath
        WebElement element1 = driver.findElement(By.xpath("//li[@id='li1']"));
        assertThat(element1.getAttribute("name"),is("liName1"));


    }



   @AfterClass
    public static void closeDriver(){
        driver.quit();
   }





}
