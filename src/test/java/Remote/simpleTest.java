package Remote;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class simpleTest {

    public static WebDriver driver= null;

    @BeforeClass


    public static void startGrid() {

        DesiredCapabilities capa = DesiredCapabilities.firefox();
        capa.setCapability("platform", Platform.WINDOWS);

        try {

            driver = new RemoteWebDriver
                    (new URL("http://localhost:4444/wd/hub"),
                    capa);

        } catch (MalformedURLException e) {
            e.printStackTrace();

        }
    }


    @Test
    public void runQuickTest(){

        driver.get("http://www.compendiumdev.co.uk" +
                "/selenium/basic_html_form.html");

        WebElement checkBox1 = driver.findElement(
                By.cssSelector("input[value='cb1']"));

        assertFalse("Starts not selected",
                checkBox1.isSelected());

        checkBox1.click();

        assertTrue("Click selects",
                checkBox1.isSelected());
    }





    @AfterClass
    public static void stopGrid(){
        driver.quit();
    }

}
