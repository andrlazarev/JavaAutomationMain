package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_OF_ARTICLE_BY_SUBSTRING_TPL = "xpath://*[contains(@text,'{SUBSTRING}')]",
            FOLDER_NAME_BY_SUBSTRING_TPL = "xpath://*[@text='{FOLDER}']",
            FOOTER_ELEMENT = "xpath://*[@text='View article in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST = "id:org.wikipedia:id/page_save",
            OPTION_ONBOARDING_CHOISE_ADD_LIST = "id:org.wikipedia:id/snackbar_action",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultArticleElement(String substring)
    {
        return TITLE_OF_ARTICLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String getResultFolderNameElement(String folder_name)
    {
        return FOLDER_NAME_BY_SUBSTRING_TPL.replace("{FOLDER}", folder_name);
    }
    /*TEMPLATES METHODS */

    public void waitForTitleArticle(String substring)
    {
        String article_xpath = getResultArticleElement(substring);
        this.waitForElementPresent(article_xpath, "Cannot find search result with substring" + substring);
    }

    public WebElement waitForTitleArticleAndReturnElement(String substring)
    {
        String article_xpath = getResultArticleElement(substring);
        return this.waitForElementPresent(article_xpath, "Cannot find search result with substring" + substring);
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find element 'Save'",
                5
        );

        this.waitForElementAndClick(
                OPTION_ONBOARDING_CHOISE_ADD_LIST,
                "Cannot find element 'Add to list'",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot find text input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find element 'Save'",
                5
        );

        this.waitForElementAndClick(
                OPTION_ONBOARDING_CHOISE_ADD_LIST,
                "Cannot find element 'Add to list'",
                5
        );

        this.waitForElementAndClick(
                this.getResultFolderNameElement(name_of_folder),
                "Cannot find folder with name" + name_of_folder,
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
        );
    }
}
