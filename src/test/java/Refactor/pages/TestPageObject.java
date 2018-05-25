package Refactor.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestPageObject {

    private WebDriver driver;

    public TestPageObject(WebDriver aDriver){

        driver = aDriver;
    }

    public void selectOptionMethod(int selectionValue) {

        WebElement optionSelect = driver.findElement(By.id("optionselector"));
        optionSelect.findElement(By.cssSelector("option[value=" + selectionValue)).click();

    }
}
