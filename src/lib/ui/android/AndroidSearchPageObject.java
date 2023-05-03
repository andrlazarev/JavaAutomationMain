package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
        SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']";
        SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']";
        DEFAULT_TEXT_IN_SEARCH_LINE = "Search Wikipedia";
        BUTTON_RETURN_MAIN_PAGE = "xpath://android.widget.ImageButton[@bounds='[0,66][154,220]']";
    }


    public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
