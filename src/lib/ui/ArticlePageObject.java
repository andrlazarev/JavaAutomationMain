package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE_OF_ARTICLE_BY_SUBSTRING_TPL,
            FOLDER_NAME_BY_SUBSTRING_TPL,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST,
            OPTION_ONBOARDING_CHOISE_ADD_LIST,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON;


    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultArticleElement(String substring)
    {
        if (Platform.getInstance().isAndroid()) {
            return TITLE_OF_ARTICLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
        } else {
            return TITLE_OF_ARTICLE_BY_SUBSTRING_TPL;
        }
    }

    private static String getResultFolderNameElement(String folder_name)
    {
        return FOLDER_NAME_BY_SUBSTRING_TPL.replace("{FOLDER}", folder_name);
    }
    /*TEMPLATES METHODS */

    public void waitForTitleArticle(String substring)
    {
        if (Platform.getInstance().isAndroid()) {
            String article_xpath = getResultArticleElement(substring);
            this.waitForElementPresent(article_xpath, "Cannot find search result with substring" + substring);
        } else {
            this.waitForElementPresent(TITLE_OF_ARTICLE_BY_SUBSTRING_TPL, "Cannot find search result with substring");
        }
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

    public void addArticlesToMySaves()
    {
        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST,
                "Cannot find option to add article to reading list",
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
