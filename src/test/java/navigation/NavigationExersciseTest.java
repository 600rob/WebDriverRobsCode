package navigation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class NavigationExersciseTest {

    static WebDriver driver;
    final private String PROTOCOL = "http";
    final private String DOMAIN = "www.compendiumdev.co.uk";
    final private String ROOT_URL = PROTOCOL + "://" + DOMAIN;


    @BeforeClass()
    public static void createDriver() {
        driver = new ChromeDriver();
    }

    @Test
    //build the url with string contcat
    public void checkSeleniumsimplifiedTitle() {
        driver.get(ROOT_URL + "/selenium");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified"));
    }


    @Test
    public void checkSeleniumSearchEngine() {


        driver.navigate().to(ROOT_URL + "/selenium/search.php");
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }

/*we can also use a URL class in our navigate to and get methods instead of concatenating strings.
 this method need to be used with malformed url exception*/

    @Test
    public void checkSeleniumSearchEngineWithURL() throws MalformedURLException {
        URL searchpage = new URL(PROTOCOL, DOMAIN, "/selenium/search.php");

        driver.navigate().to(searchpage);
        assertTrue(driver.getTitle().startsWith("Selenium Simplified Search Engine"));
    }


    @Test
    public void checkTitleOnNavBackandForward() {
        driver.navigate().to(ROOT_URL + "/selenium/basic_html_form.html");
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().to(ROOT_URL + "/selenium/basic_web_page.html");
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));

        driver.navigate().back();
        assertTrue(driver.getTitle().startsWith("HTML Form Elements"));

        driver.navigate().forward();
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }


    @Test
    public void checkSeleniumBasicWebPage() {
        driver.navigate().to("http://compendiumdev.co.uk/selenium/basic_web_page.html");
        assertTrue(driver.getTitle().startsWith("Basic Web Page Title"));
    }

/* on this next method, the page what we navigate to changes title each time it is refreshed.
The page title contains a string and a number. every time the page is refreshed, the string
stays the same but the number will always change, going up in value
 */
    @Test
    public void navigateWithRefresh() {
        //navigate to the refresh page
        driver.navigate().to(ROOT_URL + "/selenium/refresh.php");
// create a  String to verify the title against
        final String refreshTitleConstant = "Refreshed Page on ";
        // create a variable to use as the page title
        String pageTitle = driver.getTitle();
// check the title starts with the correct string
        assertTrue(pageTitle.
                startsWith(refreshTitleConstant));

        // what we do here is take our page title and replace the first instance of "refreshTitleConstant"
        //with an empty String this means our page title would be just the number that gets shown at the end of the page refresh title
        // something like " 12345455"
        // in order to convert this String to a long, we parse it using parselong. This gives us a long and assigns it
        //to startTime
        long startTime = Long.parseLong(
                pageTitle.
                        replaceFirst(refreshTitleConstant, ""));

        // synchronise using sleep to guarantee time moves on and the page title generates a new number after a few seconds
        // the only time we sleep is waiting for time,
        // never sleep waiting for page objects
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {/*ignore interrupt */}
        ;
//now do a refresh and check that we are still on the correct page
        driver.navigate().refresh();
        assertTrue(driver.getTitle().
                      startsWith(refreshTitleConstant));

        //new WebDriverWait(driver, 10).until(titleIsNoLonger(pageTitle));

//repeat the process of taking the number from the title and store it in endTime
        long endTime = Long.parseLong(
                driver.getTitle().
                        replaceFirst(refreshTitleConstant, ""));

        //now verifiy that the startTime is greater than the endTime
        //NOTE the vlues stored as longs are NOT actually Times!! they are just incremental numbers!!
        assertTrue("expected " + endTime + " > " + startTime, endTime > startTime);

        assertThat(endTime, greaterThan(startTime));
    }

/*
    private ExpectedCondition<Boolean> titleIsNoLonger(final String pageTitle) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !(pageTitle.equals(input.getTitle()));
            }
        };*/



    @AfterClass
        public static void quitDriver () {
            driver.quit();
        }
    }





