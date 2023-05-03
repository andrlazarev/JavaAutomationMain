package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        SAVED_LISTS_BUTTON = "id:Saved";
        RETURN_FROM_SEARCH_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
        CLOSE_POPUP_ENABLED_GPS = "id:Close";
    }

    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
