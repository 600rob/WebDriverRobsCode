package Javascript;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class JavaScriptExersciseTest {

    String testUrl = "https://compendiumdev.co.uk/selenium/canvas_basic.html";
    WebDriver driver = new ChromeDriver();




    @Before
    public void setUp(){
        driver.get(testUrl);
    }


    @After
    public void tearDown(){

        driver.quit();

    }


    @Test

    public void callDrawFunction(){

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("draw(1, 250, 250, 20, '#f49e42');");

        //ebElement cmd = driver.findElement(By.id("commandlist"));

//        List<WebElement> actions = cmd.findElements(By.tagName("li"));


//String drawnAction = actions.get(2).getText();

  //      assertThat(drawnAction,is("draw(1,250,250,20,#f49e42)"));

    //    System.out.println(drawnAction);


    }


    @Test
    // add arguments using a script and assert the result
    public void addArgumentsAndReturn(){

        JavascriptExecutor js = (JavascriptExecutor)driver;

        // we can execute a simple return script, passing arguments like so:

        System.out.println(
        js.executeScript("return(arguments[0] + arguments[1]);",
                20, //arg 0
                20)); //arg 1

        //if we want to verify the result we just use an assert, like so:

        assertEquals("40 expected", 40L,
                js.executeScript("return(arguments[0] + arguments[1]);",
                        20,
                        20));

    }

    @Test

    public void callExecuteScriptWithArgs(){

        //set some variables then pass them in as args in the javaScriptExecute method. Note, ive passe din the col
        int a =1; //arg 0
        int b = 250; //arg 1
        int c = 250; //arg 2
        int d = 20; //arg 3
        String e = "#f49e42"; //arg 4
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //js.executeScript("draw(1, 250, 250, 20, '#f49e42');");

        js.executeScript("draw(arguments[0],arguments[1],arguments[2],arguments[3],'arguments[4]');",
                a,
                b,
                c,
                d,
                e
        );


    }

    @Test
    public void changeTitleUsingJs(){

        //we can run a javascript statemenr to change the page title.

        //first check the current title

        assertEquals("default title is correct", "Javascript Canvas Example",
                driver.getTitle() );

        //use JS to change the title:

        JavascriptExecutor js = (JavascriptExecutor)driver;

        js.executeScript("document.title= arguments[0]", "robert testing");

        assertEquals("default title is correct", "robert testing",
                driver.getTitle() );


    }

    @Test
    public void useJQueryToHide(){
        // we can use JQuery to hide aspects on the page

        JavascriptExecutor js = (JavascriptExecutor)driver;

        //this script hides the entire body on the page
        js.executeScript("$('body').hide();");

        // no the body is hidden we can assert on a element to verify if it is displayed;

        assertFalse(driver.findElement(By.cssSelector("#commands"))
                .isDisplayed());


    }


    @Test
    public void passAWebElementAnUseJQueryTohide(){
        // following on from the last example we can pass in a specific element as an argument,
        // then use jquery to hide it then check to see if its displayed

        //check for our element that we want to hide:
        assertTrue(driver.findElement(By.cssSelector("#commands")).isDisplayed());

        JavascriptExecutor js = (JavascriptExecutor) driver;

        //hide the element by passing it in as an argument
        js.executeScript("$(arguments[0]).hide();",
                driver.findElement(By.cssSelector("#commands")));

        //verify that it is hidden

        assertFalse(driver.findElement(By.cssSelector("#commands")).isDisplayed());





    }



    @Test

    public void addJavaScriptToThePage(){
        //we can add some add some java script to the page which may be useful in creating tests where
        //the devs have not done everything yet. we'd do this as follows:

        //we can create an anonymous function which leaves no trace one it is disposed of

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // this runs as an anonymous function leaving no trace behind
        js.executeScript("alert('alert test 123');");

        assertThat(driver.switchTo().alert().getText(),is("alert test 123"));
        driver.switchTo().alert().accept();

        // if we wanted to leave some trace of the javascript that we added, we would need to add the function into the page and to
        // the global window like so:

        //this creates the alert
        js.executeScript("window.webdriveralert = function(){alert('stored alert triggered by webdriver');};" +
                "window.webdriveralert.call();");

        driver.switchTo().alert().accept();

        //because we created the script in the global window it means we can call it any time like this using script executor

        js.executeScript("window.webdriveralert.call();");


        assertThat(driver.switchTo().alert().getText(), is("stored alert triggered by webdriver"));




    }













}
