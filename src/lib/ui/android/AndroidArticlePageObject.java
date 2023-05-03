package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static {
        TITLE_OF_ARTICLE_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]";
        FOLDER_NAME_BY_SUBSTRING_TPL = "xpath://*[@text='{FOLDER}']";
        FOOTER_ELEMENT = "xpath://*[@text='View article in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        OPTIONS_ADD_TO_MY_LIST = "id:org.wikipedia:id/page_save";
        OPTION_ONBOARDING_CHOISE_ADD_LIST = "id:org.wikipedia:id/snackbar_action";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
    }

    public AndroidArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
