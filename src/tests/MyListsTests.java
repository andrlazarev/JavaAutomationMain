package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";
    @Test
    public void testSaveFirstArticleToMyList()
    {
        if (Platform.getInstance().isAndroid()) {
            OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
            onboardingPageObject.skipOnboardingButton();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleArticle("Java (programming language)");

        String article_title = articlePageObject.waitForTitleArticleAndReturnElement("Java (programming language)").getAttribute("name");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList(name_of_folder);
        } else {
            articlePageObject.addArticlesToMySaves();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.returnFromSearch();
        navigationUI.clickToSavedLists();
        navigationUI.closePopupEnabledGps();

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName(name_of_folder);
        }
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testCheckArticleAfterDeletedArticleInFoldier()
    {
        if (Platform.getInstance().isAndroid()){
            OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
            onboardingPageObject.skipOnboardingButton();
        }

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToNewList("My article");
        } else {
            articlePageObject.addArticlesToMySaves();
        }

        articlePageObject.closeArticle();

        searchPageObject.clickCancelSearch();

        searchPageObject.initSearchInput();

        String value = "Appium";
        searchPageObject.typeSearchLine(value);
        searchPageObject.clickByArticleWithSubstring("Automation for Apps");

        if (Platform.getInstance().isAndroid()){
            articlePageObject.addArticleToMyList("My article");
        } else {
            articlePageObject.addArticlesToMySaves();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.returnFromSearch();
        navigationUI.clickToSavedLists();

        if (Platform.getInstance().isIOS()) {
            navigationUI.closePopupEnabledGps();
        }

        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openFolderByName("My article");
        }

        myListsPageObject.swipeByArticleToDelete("Java (programming language)");
        myListsPageObject.waitForArticleToDisappearByTitle("Java (programming language)");
        if (Platform.getInstance().isAndroid()){
            myListsPageObject.waitForArticleToAppearByTitle("Appium");
        } else {
            myListsPageObject.checkFoundOneArticleAfterDeleted();
        }

    }
}
