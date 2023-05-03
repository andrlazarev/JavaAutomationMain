package lib.ui;

import io.appium.java_client.AppiumDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String
            SAVED_LISTS_BUTTON,
            RETURN_FROM_SEARCH_BUTTON,
            CLOSE_POPUP_ENABLED_GPS;
    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }


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

    public void closePopupEnabledGps()
    {
        this.waitForElementAndClick(
                CLOSE_POPUP_ENABLED_GPS,
                "Cannot find close popup enabled GPS element",
                5
        );
    }
}
