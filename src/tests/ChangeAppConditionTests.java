package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class ChangeAppConditionTests extends CoreTestCase {

        private MainPageObject MainPageObject;

        protected void setUp() throws Exception
        {
            super.setUp();

            MainPageObject = new MainPageObject(driver);
        }

    @Test
    public void testScreenOrientationOnSearchResults()
    {

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);

        String title_before_rotation = articlePageObject.waitForTitleArticleAndReturnElement("Java (programming language").getAttribute("text");

        this.rotateScreenLandscape();

        String title_after_rotation = articlePageObject.waitForTitleArticleAndReturnElement("Java (programming language").getAttribute("text");

        assertEquals(
                "Article have been changed after rotation",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = articlePageObject.waitForTitleArticleAndReturnElement("Java (programming language").getAttribute("text");

        assertEquals(
                "Article have been changed after rotation",
                title_before_rotation,
                title_after_second_rotation
        );
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
