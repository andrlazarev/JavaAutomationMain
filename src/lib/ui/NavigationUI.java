package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            SAVED_LISTS_BUTTON = "org.wikipedia:id/nav_tab_reading_lists";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            RETURN_FROM_SEARCH_BUTTON = "//android.widget.ImageButton[@bounds='[0,66][154,220]']";


    public void returnFromSearch()
    {
        this.waitForElementAndClick(
                By.xpath(RETURN_FROM_SEARCH_BUTTON),
                "Cannot find element for return",
                5
        );
    }
    public void clickToSavedLists()
    {
        this.waitForElementAndClick(
                By.id(SAVED_LISTS_BUTTON),
                "Cannot find saved lists button",
                5
        );
    }
}
