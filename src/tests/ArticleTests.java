package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class ArticleTests extends CoreTestCase {

    @Test
    public void testCompareArticleTitle()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        //onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleArticle("Java (programming language)");
        WebElement title_element = articlePageObject.waitForTitleArticleAndReturnElement("Java (programming language)");
        if (Platform.getInstance().isAndroid()){
            String article_title = title_element.getAttribute("name");

            assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title
            );
        } else {
            String article_title = title_element.getAttribute("name");

            assertEquals(
                    "We see unexpected title",
                    "Java (programming language)",
                    article_title
            );
        }



    }

    @Test
    public void testSwipeArticle()
    {
        if (Platform.getInstance().isAndroid()){
            OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
            onboardingPageObject.skipOnboardingButton();
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckTitleInThePage()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        searchPageObject.initSearchInput();
        String search_line = "java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleArticle("Object-oriented programming language");
    }
}
