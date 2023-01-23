package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SearchPageObject extends MainPageObject{

    private static final String
            SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "id:org.wikipedia:id/page_list_item_title",
            SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results']",
            DEFAULT_TEXT_IN_SEARCH_LINE = "Search Wikipedia",
            BUTTON_RETURN_MAIN_PAGE = "xpath://android.widget.ImageButton[@bounds='[0,66][154,220]']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }
    /*TEMPLATES METHODS */
    public void initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking earch element");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT,"Cannot find and click search init element", 5);
    }

    public void waitForCancelButtonToAppear()
    {
        waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INIT_ELEMENT,search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find element with text ",
                15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(
                SEARCH_EMPTY_RESULT_ELEMENT,
                "Cannot find empty result label by the request",
                15
        );
    }

    public void assertThereIsNotResultOfSearch()
    {
        this.assertElementNotPresent(
                SEARCH_RESULT_ELEMENT,
                "We supposed not to find any results"
        );
    }

    public void assertDefaultTextInSearchLine()
    {
        this.assertElementHasText(
                SEARCH_INIT_ELEMENT,
                DEFAULT_TEXT_IN_SEARCH_LINE,
                "Cannot find text 'Search Wikipedia'"
        );
    }

    public void waitSearchResults()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find search results",
                15
        );
    }

    public void assertSearchResultsBySubstring(String substring)
    {
        List<WebElement> listOfElements = driver.findElements(By.id(SEARCH_RESULT_ELEMENT));
        boolean result = false;
        for ( int i=0; i < listOfElements.size(); i++) {
            WebElement element = listOfElements.get(i);
            if (this.checkSubstring(element, substring)) {
                result = true;
            } else {
                fail("Substring missing from search results" + substring);
            }
        }

        assertEquals("Substring missing from search results" + substring,true, result);
    }
}
