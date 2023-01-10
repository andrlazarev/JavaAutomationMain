package tests;

import lib.CoreTestCase;
import lib.ui.OnboardingPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "Linkin Park discography";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_result = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We fount too few result",
                amount_of_search_result > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        String search_line = "weascfeeqdAWD";
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNotResultOfSearch();
    }

    @Test
    public void testSearchFieldText()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.assertDefaultTextInSearchLine();
    }

    @Test
    public void testCancelSearchWithArticle()
    {

        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_line = "java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult("Island in Indonesia");
        searchPageObject.waitForSearchResult("High-level programming language");
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCheckSearchResult()
    {
        OnboardingPageObject onboardingPageObject = new OnboardingPageObject(driver);
        onboardingPageObject.skipOnboardingButton();

        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        String search_line = "java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitSearchResults();
        searchPageObject.assertSearchResultsBySubstring("Java");
    }
}
