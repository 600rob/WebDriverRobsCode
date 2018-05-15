package driver.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManager {


    public static WebDriver get() {
        String browserToUse ="";
        //in order for our driver manager to decide which driver to use whe need to create an if statement
        //which assesses the system properties set, it uses the result of that to set a string browserToUse

        if(System.getProperties().containsKey("selenium2Basics.driver")){
            //set browserToUse to the property passed in by the test, if nothing s passed in
            //nothing gets set
             browserToUse = System.getProperty("selenium2Basics.driver");
        }

        //now use a switch statment to determine which browser we want to return, based on the system property set

        switch(browserToUse) {
            case "firefox":
                return new FirefoxDriver();
            default:
                return new ChromeDriver();

        }
     }
}
