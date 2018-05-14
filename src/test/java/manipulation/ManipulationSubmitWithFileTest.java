package manipulation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * sumbit the form with a file. Then check the file name on output
 */

public class ManipulationSubmitWithFileTest {



    private static WebDriver driver;
    final private static String testUrl = "http://www.compendiumdev.co.uk/selenium/basic_html_form.html";



    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.get(testUrl);
    }

    @Test
    public void submitFormWithFile(){

        //JavascriptExecutor jse = (JavascriptExecutor).driver;

        WebElement fileButton = driver.findElement(By.cssSelector("input[name='filename']"));
        fileButton.sendKeys("C:\\test data\\testfile.txt");

        /*jse.executeScript("file.getElementsByName('hiddenfield')[0].setAttribute('type', 'text');");
        WebElement hiddenfield = driver.findElement(By.cssSelector("input[name='hiddenField']"));
        hiddenfield.clear();
        hiddenfield.sendKeys("testfile.txt");*/

        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        new WebDriverWait(driver,10).
                until(ExpectedConditions.titleContains("Processed"));

        WebElement fileOutput = driver.findElement(By.cssSelector("li[id='_valuefilename']"));
        assertThat(fileOutput.getText(),is("testfile.txt"));

    }


    @AfterClass
    public static void quitDriver(){
        driver.quit();

    }

}
