package Javascript;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ASyncJavaScriptExercise1Test {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void aSyncJSExercise1(){
// in this example we use the async code example to assert on the time incrementing.
        //just use any page to navigate to
        driver.get("http://www.compendiumdev.co.uk/selenium/basic_ajax.html");

        // we must set a script time out for ASync scripts
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        //this is tha sampel code taken from the java doc for executeASyncScript
        long start = System.currentTimeMillis();
        ((JavascriptExecutor)driver).executeAsyncScript(
                "window.setTimeout(arguments[arguments.length - 1], 500);");
        System.out.println(
                "Elapsed time: " + (System.currentTimeMillis() - start));

        //just add an assert to verify that some time has passed


        driver.quit();
    }




}
