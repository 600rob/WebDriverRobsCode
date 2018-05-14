package SyncStrategies;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Date;
import java.util.concurrent.TimeUnit;


public class ImplicitWait {

    public WebDriver driver = new ChromeDriver();

    @Test
    public void implicitTest()  {


            //driver.manage().window().maximize();

            // Loading a URL
        driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

//select server option from drop down box
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

        System.out.println("Wait starts:"+new Date());
        //find the ajaxBusy element
        WebElement ajax = driver.findElement(By.id("ajaxBusy"));
        System.out.println("Wait ends:"+new Date());


        // then select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value='23']")).click();

        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();

        new WebDriverWait(driver,10).until(ExpectedConditions.titleIs("Processed Form Details"));


            //Closing browser
            driver.quit();
            System.out.println("browser closed");


        }

        @Test

    public void implicitTest1(){
            // Loading a URL
            driver.get("https://www.redbus.in/");
            driver.manage().window().maximize();

            // Locating and typing in From text box.
            WebElement fromTextBox= driver.findElement(By.id("src"));
            fromTextBox.sendKeys("Ban");
            // setting implicit time
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            System.out.println("Wait starts:"+new Date());
            System.out.println("Typed Ban");
            // Clicking on first search result
            driver.findElement(By.xpath("//li[@select-id='results[0]']")).click();
            System.out.println("Selected Bangalore");
            System.out.println("Wait ends:"+new Date());
            //Closing browser
            driver.quit();
            System.out.println("browser closed");



        }
    }




