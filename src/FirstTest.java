import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchFieldText()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        MainPageObject.assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Cannot find text 'Search Wikipedia'"
        );
    }

    @Test
    public void testCancelSearchWithArticle()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "java",
                "Cannot find element search input",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Island in Indonesia')]"),
                "Cannot find element with text 'Island in Indonesia'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'High-level programming language')]"),
                "Cannot find element with text 'High-level programming language'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find element 'Cancel search'",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Element find in page",
                5
        );
    }

    @Test
    public void testCheckSearchResult()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "java",
                "Cannot find element search input",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find search result",
                15
        );

        List<WebElement> listOfElements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        boolean result = false;
        for ( int i=0; i < listOfElements.size(); i++) {
            WebElement element = listOfElements.get(i);
            if (MainPageObject.checkSubstring(element, "Java")) {
                result = true;
            } else {
                fail();
            }
        }

        assertEquals("in search have result without 'Java'",true, result);
    }




    @Test
    public void testCheckArticleAfterDeletedArticleInFoldier()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find element search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find element 'Object-oriented programming language' toping searching" + search_line,
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find element 'Save'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find element for return",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Appium",
                "Cannot find element search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Automation for Apps')]"),
                "Cannot find element 'Appium' in search",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find element 'Save'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find element for return",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@bounds='[0,66][154,220]']"),
                "Cannot find element for return",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/nav_tab_reading_lists"),
                "Cannot find element 'Saved'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/recycler_view"),
                "Cannot find element 'Saved'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Cannot find Article in saved",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_save"),
                "Cannot find element 'Save'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Remove from Saved')]"),
                "Cannot find element 'Remove from Saved'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find element for return",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[contains(@text,'Appium')]"),
                "Element 'Appium' don't removed from Saved article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find Article in saved",
                5
        );

        MainPageObject.assertElementHasText(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Java (programming language)",
                "Cannot find title this article"
        );
    }

    @Test
    public void testCheckTitleInThePage()
    {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        String search_line = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_line,
                "Cannot find element search input",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find element 'Object-oriented programming language' toping searching" + search_line,
                15
        );

        MainPageObject.assertElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find title in this page"
        );
    }
}
