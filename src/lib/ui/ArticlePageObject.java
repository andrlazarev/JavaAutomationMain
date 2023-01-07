package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_OF_ARTICLE_BY_SUBSTRING_TPL = "//*[contains(@text,'{SUBSTRING}')]",
            FOOTER_ELEMENT = "//*[@text='View article in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST = "org.wikipedia:id/page_save",
            OPTION_ONBOARDING_CHOISE_ADD_LIST = "org.wikipedia:id/snackbar_action",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultArticleElement(String substring)
    {
        return TITLE_OF_ARTICLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /*TEMPLATES METHODS */

    public void waitForTitleArticle(String substring)
    {
        String article_xpath = getResultArticleElement(substring);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find search result with substring" + substring);
    }

    public WebElement waitForTitleArticleAndReturnElement(String substring)
    {
        String article_xpath = getResultArticleElement(substring);
        return this.waitForElementPresent(By.xpath(article_xpath), "Cannot find search result with substring" + substring);
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(OPTIONS_ADD_TO_MY_LIST),
                "Cannot find element 'Save'",
                5
        );

        this.waitForElementAndClick(
                By.id(OPTION_ONBOARDING_CHOISE_ADD_LIST),
                "Cannot find element 'Add to list'",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find text input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5
        );
    }
}
