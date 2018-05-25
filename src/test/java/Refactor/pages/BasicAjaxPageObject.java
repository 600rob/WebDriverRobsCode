package Refactor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicAjaxPageObject {

    private WebDriver driver;

    //This class represents our page under test ( in this case the Basic ajax page)
    // first thing to do in our page object class is the create a constructor which takes a WebDriver when we instantiate the class.
    // dont forget to Set up the webDriver as a field on the class. doing these things mean we have a page object with a constructor and the class
    //knows about the driver. Any methods addeed to the class can use the driver to do stuff

    public BasicAjaxPageObject(WebDriver aDriver) {

        driver = aDriver;
    }

    public void get() {

        driver.get("http://compendiumdev.co.uk/selenium/basic_ajax.html");
    }

    public void categorySelect(int categortyValue) {

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value="+ categortyValue +"]")).click();
// we can wrap our wait in this class or use it separatley by calling it from a test

new WebDriverWait(driver,10).until(ajaxActionIsComplete());

// we could also wait until the element we want to click on becomes available
        /*
         //wait til java option is selectable
        new WebDriverWait(driver,10).until(
                ExpectedConditions.visibilityOfElementLocated
                        (By.cssSelector("option[value='23']")));


         */


    }

    public ExpectedCondition<Boolean> ajaxActionIsComplete() {
        return
                ExpectedConditions.invisibilityOfElementLocated(By.id("ajaxBusy"));
    }

    public void selectLanguage(int languageValue) {
        // select Java
        WebElement languageSelect = driver.findElement(By.id("combo2"));
        languageSelect.findElement(By.cssSelector("option[value="+languageValue+"]")).click();


    }

    public void clickCodeInIt() {
        // Submit the form
        WebElement codeInIt = driver.findElement(By.name("submitbutton"));
        codeInIt.click();
    }


public void selectOptionMethod(){

    TestPageObject testPage = new TestPageObject(driver);

    testPage.selectOptionMethod(1);

}

}


