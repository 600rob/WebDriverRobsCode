package SyncStrategies;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class PageLoaded implements ExpectedCondition<Boolean> {

    public String expectedTitle;
    public String expectedUrl;

    public PageLoaded(String expectedTitle, String expectedUrl) {
        this.expectedTitle = expectedTitle;
        this.expectedUrl = expectedUrl;
    }

    @Override
    public Boolean apply(WebDriver driver){
     Boolean correctTitle =
                driver.getTitle().contains(expectedTitle);

        Boolean correctUrl =
                driver.getTitle().contains(expectedUrl);
return correctTitle && correctUrl;


    }
}
