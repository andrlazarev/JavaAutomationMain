package lib.ui;

import io.appium.java_client.AppiumDriver;

public class NavigationUI extends MainPageObject {

    private static final String
            SAVED_LISTS_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            RETURN_FROM_SEARCH_BUTTON = "xpath://android.widget.ImageButton[@bounds='[0,66][154,220]']";


    public void returnFromSearch()
    {
        this.waitForElementAndClick(
                RETURN_FROM_SEARCH_BUTTON,
                "Cannot find element for return",
                5
        );
    }
    public void clickToSavedLists()
    {
        this.waitForElementAndClick(
                SAVED_LISTS_BUTTON,
                "Cannot find saved lists button",
                5
        );
    }
}
