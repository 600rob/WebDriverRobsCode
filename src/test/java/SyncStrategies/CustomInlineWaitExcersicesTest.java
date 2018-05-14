package SyncStrategies;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;

public class CustomInlineWaitExcersicesTest {


    private WebDriver driver = new ChromeDriver();

    @Test

    public void inlineWait() {
// example of using an anonymous class

        loadPageAndSelectServer();


        /*create an inline wait with an inline expected condition that returns a web element
        and assert on the web element text.
        * this needs to be done for the java option on the page)
NOTE this an example of an anonymous class (Lines 42 to 56)
         */

        //create web element a1, in it store the result of the anonymous class below ( either a2 or null)
        //note were are only storing the result in a1 because we want to assert against this later on in the test
        WebElement a1 = new WebDriverWait(driver, 10)
                .until(
                        //start of the anonymous class
                        //create new expected condition that returns WebElement
                        new ExpectedCondition<WebElement>() {
                            //override the apply method. WebDriver wait always calls the apply method
                            // this means that when we override it we must pass a webdriver variable to the apply method because
                            //we can't change the argument types when overriding
                            @Override
                            public WebElement apply(WebDriver webdriver) {
                                //locate the element that we are waiting for. ( in this case, it is the java option)
                                WebElement a2 = webdriver.findElement(By.cssSelector("option[value='23']"));
                                //This loop does the following, return the element a2 if is displayed. if not the return null
                                //if we find the element is displayed it is stored in element a1, declared a the start of this block
                                if (a2.isDisplayed()) {
                                    return a2;
                                } else {
                                    return null;
                                }

                            }

                        }
                );
        // now select java an assert on the text:
        a1.click();
        assertThat(a1.getText(), is(equalToIgnoringCase("java")));


        driver.quit();


    }


    @Test
    public void anonymousClassExample2() {
        //in this example we will create an anonymous class which returns a boolean as to whether or
        //not the java option is displayed

     loadPageAndSelectServer();

     new WebDriverWait(driver,10).until(
             new ExpectedCondition<Boolean>() {
                 @Override
                 public Boolean apply (WebDriver webdriver){
                     return webdriver.findElement(By.cssSelector("option[value='23']"))
                             .isDisplayed();
                 }
             }
     );
     //in this example we dont need to store anything in a webelement as we are returning a boolean.
        // The anonymous class looks for the java option for up to 10 seconds and returns true if found

    }


    @Test
    public void anaonymousExample3(){

        //in this example we will create an anonymous class which returns a boolean as to whether or
        //not the category text elemant contains "Cat"

        loadPageAndSelectServer();

        new WebDriverWait(driver,10).until(
                new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply (WebDriver webdriver){
                        return webdriver.findElement(By.cssSelector("label[for='combo1']"))
                                .getText().contains("Cat");
                    }
                }

        );


    }




    @Test
    public void inlineExpectedCondition() {

        loadPageAndSelectServer();

        WebElement a1 = new WebDriverWait(driver, 10).until
                (new ExpectedCondition<WebElement>() {
                     @Override
                     public WebElement apply(WebDriver web) {
                         WebElement a2 = web.findElement(By.cssSelector("option[value='23']"));
                         if (a2.isDisplayed()) {
                             return a2;
                         } else {
                             return null;
                         }
                     }

                 }
                );

        a1.click();

        System.out.println(a1.getText());



    }




    @Test
    public void inlineExpectedConditionTake2() {

        loadPageAndSelectServer();

       WebElement t1 = new WebDriverWait(driver,10).until(
               new ExpectedCondition<WebElement>(){
                   @Override
                   public WebElement apply(WebDriver webdriver){
                       WebElement t2 = webdriver.findElement(By.cssSelector("option[value='23']"));
                       if(t2.isDisplayed()){
                           return t2;}
                       else{
                           return null;}
                   }
               }
               );
       t1.click();
        System.out.println(t1.getText());

        driver.quit();




    }




    private void loadPageAndSelectServer() {

        driver.get("http://compendiumdev.co.uk/selenium/" +
                "basic_ajax.html");

        // select Server
        WebElement categorySelect = driver.findElement(By.id("combo1"));
        categorySelect.findElement(By.cssSelector("option[value='3']")).click();

    }


}






