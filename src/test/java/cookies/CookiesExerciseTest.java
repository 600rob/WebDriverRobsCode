package cookies;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

public class CookiesExerciseTest {

    WebDriver driver = new ChromeDriver();

    String testUrl = "https://compendiumdev.co.uk/selenium/search.php";
    String visits = "seleniumSimplifiedSearchNumVisits";
    String lastVisit ="seleniumSimplifiedSearchLastVisit";
    String lastSearch= "seleniumSimplifiedLastSearch";
    String domain ="compendiumdev.co.uk";
    String path ="/selenium";



    @Before
    public void setUp(){

        driver.get(testUrl);
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

    }

    @After
    public void tearDown(){

        driver.quit();
    }
//use cookies to check the number of page visits
@Test
public void checkNumberOfVisits(){


    Cookie visitsCookie = driver.manage().getCookieNamed(visits);

    assertEquals("number of visits is equal to 1", 1,
            Integer.parseInt(visitsCookie.getValue()));

}


@Test
public void checkSerchCookieNextValue(){
    // set the number if visits value to 42, perform a new search and ensure the value is set to 43
    // do this by cloning the cookie

    Cookie visitsCookie = driver.manage().getCookieNamed(visits);

//clone the new cookie using values from the original one. We can set specific values as seen with the number of visits

    Cookie newVisits = new Cookie(visitsCookie.getName(),
            String.valueOf(42),
            visitsCookie.getDomain(),
            visitsCookie.getPath(),
            visitsCookie.getExpiry());


    driver.manage().deleteCookie(visitsCookie);
    driver.manage().addCookie(newVisits);

    driver.findElement(By.cssSelector("input[type='submit']")).click();
    // need to get the new cookie object each time it changes
    newVisits = driver.manage().getCookieNamed(visits);

   assertEquals("number of visist should be 43", 43,
           Integer.parseInt(newVisits.getValue()));


}

@Test
    public void countVisitsCookieBuiltFromScratch(){

        driver.manage().deleteCookieNamed(visits);


        //if creating a new cookie from scratch you still have to use the builder
        driver.manage().addCookie(
                new Cookie.Builder(visits, String.valueOf(42)).
                        domain(domain).
                        path(path).build());


        driver.findElement(By.cssSelector("input[type='submit']")).click();

        Cookie numOfVisits = driver.manage().getCookieNamed(visits);

        System.out.println(numOfVisits.getValue());



}


    @Test
    public void countVisitsUsingCookieBuilder(){

        Cookie baseCookie = driver.manage().getCookieNamed(visits);

        Cookie newCookie = new Cookie.Builder(baseCookie.getName(),String.valueOf(100)).
                domain(baseCookie.getDomain()).
                path(baseCookie.getPath()).
                expiresOn(baseCookie.getExpiry()).build();

        driver.manage().deleteCookie(baseCookie);
        driver.manage().addCookie(newCookie);

        driver.findElement(By.cssSelector("input[type='submit']")).click();
        newCookie = driver.manage().getCookieNamed(visits);

        assertEquals("visits should be 101",101,
                Integer.parseInt(newCookie.getValue()));







    }


}
