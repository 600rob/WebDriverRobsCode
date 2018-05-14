package interogation;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FindElementsTest {

    private static WebDriver driver;
    private static String testUrl = "https://compendiumdev.co.uk/selenium/find_by_playground.php";


    @BeforeClass
    public static void startDriver(){
        driver = new ChromeDriver();
        driver.navigate().to(testUrl);
    }
    @Test
    public void assert19DivElements(){

        //the exercise asks to assert the number of div elements.... there is nothing on the dom specifically called a div element
        //if you search for "div" you get back 20 items./
        //what you need to do is search for "//div"

        List<WebElement> elements;
        elements = driver.findElements(By.tagName("div"));
        assertThat(elements.size(),is(19));


    }

    @Test
    public void assert25Hrefelements(){
/*again, in order to work out how many anchors there are with href, by the way, href indicates
the anchor specifies a link or has a link location!
searching for "//a" will return 50 items. we need to search for the ones which contain a link
do this by using the following search: "//a[@href]. this returns 25.
In order to actually find these elements use something that they all have in common.
in this case the all contain the link text "jump to" hence we can use partialLinkText

 */
        List<WebElement> elements;
        elements = driver.findElements(By.partialLinkText("jump to"));
        assertThat(elements.size(),is(25));


    }



    @Test
    public void assertNumberOfParagraphs() {

        /* we are able to find the number of paragraphs in the dom using find //p. this tells us there are 41 paragraphs in total.
        without using xpaths we can look through them ana manually count the 16 which contain the text "nested para" * note, this is
        not the ideal method! we would normally use xpaths but weve not been given that info for this example.
        Anyway, we know that there are 16 nested paragraphs and 41  total paragraphs that we need to verify in out tests. do it like this
        */

        //first of all add all the elements with the tag name "p" to our list
        //tha can be used to verify the total number of paragraphs
        List<WebElement> elements;
        elements = driver.findElements(By.tagName("p"));

        // to verify the number of nested paragraphs we need to manually count them and assert the expected answer

        int i = 0;
        for(WebElement a : elements){
            if(a.getText().contains("nested para")){
                i++; }

        }

        assertThat(i,is(16));
        assertThat(elements.size(),is(41));

        System.out.println("number of nested paras is "+ i +"." + " total number of paras is " + elements.size());


    }
    @AfterClass
    public static void closeDriver(){
        driver.quit();

    }



}
