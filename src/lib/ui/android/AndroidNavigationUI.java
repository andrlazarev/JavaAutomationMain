package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class AndroidNavigationUI extends NavigationUI {
    static {
        SAVED_LISTS_BUTTON = "id:org.wikipedia:id/nav_tab_reading_lists";
        RETURN_FROM_SEARCH_BUTTON = "xpath://android.widget.ImageButton[@bounds='[0,66][154,220]']";
    }

    public AndroidNavigationUI(AppiumDriver driver)
    {
        super(driver);
    }
}
