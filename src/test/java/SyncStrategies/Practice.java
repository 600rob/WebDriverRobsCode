package SyncStrategies;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Practice {

    WebDriver driver = new ChromeDriver();


    @Test
    public void waitTest() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://toolsqa.com/automation-practice-switch-windows/");

new WebDriverWait(driver,50).
       until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p button[id='timingAlert']")));

        driver.findElement(By.cssSelector("p button[id='timingAlert']")).click();


        new WebDriverWait(driver,10).
                until(ExpectedConditions.alertIsPresent());


        driver.switchTo().alert().accept();

        driver.quit();

    }

    @ Test

    public void checkImplicitWait(){
driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

driver.get("https://www.lgcstandards.com/");

        WebElement check = driver.findElement(By.cssSelector("a[href='/GB/en']"));

        check.click();


    }


}