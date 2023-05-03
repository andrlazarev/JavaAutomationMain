package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_BY_TITLE_TPL = "id:Java (programming language)";
        BUTTON_FOR_DELETE_ARTICLE = "id:swipe action delete";
        LIST_OF_ARTICLE_ELEMENTS = "xpath://*/XCUIElementTypeCell";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
