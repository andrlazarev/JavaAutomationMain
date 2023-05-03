package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {

    static {
        TITLE_OF_ARTICLE_BY_SUBSTRING_TPL = "id:Java (programming language)";
        FOOTER_ELEMENT = "xpath://*[@name='View article in browser']";
        OPTIONS_ADD_TO_MY_LIST = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public iOSArticlePageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
