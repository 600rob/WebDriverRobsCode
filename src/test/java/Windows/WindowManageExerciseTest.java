package Windows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.assertEquals;

public class WindowManageExerciseTest {

    private WebDriver driver= new ChromeDriver();
    final private String testUrl = "https://compendiumdev.co.uk/selenium/bounce/html";
    private Dimension windowSize = driver.manage().window().getSize();



    @Before
    public void startDriver(){
        driver.get(testUrl);



    }

    @Test
    public void maximiseWindow() {

        //check the window size.

        if (windowSize.width < 1050) {
            driver.manage().window().maximize();
        }

        assertEquals("window is maximised", windowSize,
                driver.manage().window().getSize());


    }


        @Test
                public void reduceDefaultWindowSize() {

            assertEquals("window width is 1050",1050,
                windowSize.width);
            assertEquals("window height is 708",708,
                    windowSize.height);

            int newHeight = windowSize.height / 2;
            int newWidth = windowSize.width / 2;

           driver.manage().window().setSize(new Dimension(newWidth, newHeight));

            Dimension newWindowsize = new Dimension(newWidth, newHeight);

            assertEquals("new window is half the width of original", newWindowsize.width,
                    windowSize.width/2);
            assertEquals("new window is half the height of original", newWindowsize.height,
                    windowSize.height/2);

        }

        @Test
        public void halfDefaultSizeMoveToCentre(){



            int newHeight = windowSize.height / 2;
            int newWidth = windowSize.width / 2;
            driver.manage().window().setSize(new Dimension(newWidth, newHeight));

            Dimension newWindowsize = new Dimension(newWidth, newHeight);

            //move the re-sized window to the centre of the page

            driver.manage().window().setPosition(new Point(windowSize.width/4,windowSize.width/4));

        }



    @After
    public void stopDriver(){
        driver.quit();
    }
}
