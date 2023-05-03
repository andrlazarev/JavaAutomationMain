package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

import static org.junit.Assert.assertEquals;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            BUTTON_FOR_DELETE_ARTICLE,
            LIST_OF_ARTICLE_ELEMENTS;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByArticle(String article_title)
    {
        if (Platform.getInstance().isAndroid()) {
            return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
        } else {
            return ARTICLE_BY_TITLE_TPL;
        }

    }

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByArticle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title" + article_title,
                15
        );
    }
    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByArticle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article still present with title" + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByArticle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article with name" + article_title
        );

        if (Platform.getInstance().isIOS()) {
            this.waitForElementAndClick(
                    BUTTON_FOR_DELETE_ARTICLE,
                    "Cannot find element for delete article",
                    5
            );
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void checkFoundOneArticleAfterDeleted(){
        int amountOfElements = this.sumOfElementsOnPageByLocator(
                LIST_OF_ARTICLE_ELEMENTS,
                "Cannot find locators" + LIST_OF_ARTICLE_ELEMENTS,
                5
                );
        assertEquals("Element not deleted on save list" , 1, amountOfElements);
    }

}
