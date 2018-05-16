package driver.manager;

        import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
        import org.openqa.selenium.ie.InternetExplorerDriver;


public class DriverManager {


    public static final String SELENIUM2_BASICS_DRIVER = "selenium2Basics.driver";

    public static WebDriver get() {
        String browserToUse ="";
        //in order for our driver manager to decide which driver to use we need to create an if statement
        //which assesses the system properties set, it uses the result of that to set a string browserToUse

        if(System.getProperties().containsKey(SELENIUM2_BASICS_DRIVER)){
            //set browserToUse to the property passed in by the test, if nothing s passed in
            //nothing gets set and we use the default browser (see below)
            browserToUse = System.getProperty(SELENIUM2_BASICS_DRIVER);
        }

        //now use a switch statement to determine which browser we want to return, based on the system property set

        switch(browserToUse) {
            case "firefox":
                return new FirefoxDriver();
            case "ie":
                return new InternetExplorerDriver();
            default:
                return new ChromeDriver();


        }
    }
}
