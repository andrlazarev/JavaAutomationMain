import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","9");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","Users/andrejlazarev/Desktop/JavaAppiumAutomation/JavaAutomationMain/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }


    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'SKIP')]"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find element Search Wikipedia",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "java",
                "Cannot find element search input",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Element not find in this page",
                20
        );
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Cannot find element search input",
                15
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search fild",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find element 'Cancel search'",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Element find in page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "java",
                "Cannot find element search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//*[@text='Object-oriented programming language']"),
                "Cannot find element SKIP",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[contains(@text,'Java (programming language)')]"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSearchFieldText()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        assertElementHasText(
                By.id("org.wikipedia:id/search_src_text"),
                "Search Wikipedia",
                "Cannot find text 'Search Wikipedia'"
        );
    }

    @Test
    public void testCancelSearchWithArticle()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "java",
                "Cannot find element search input",
                15
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'Island in Indonesia')]"),
                "Cannot find element with text 'Island in Indonesia'",
                15
        );

        waitForElementPresent(
                By.xpath("//*[contains(@text,'High-level programming language')]"),
                "Cannot find element with text 'High-level programming language'",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find element 'Cancel search'",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Element find in page",
                5
        );
    }

    @Test
    public void testCheckSearchResult()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find element SKIP",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find element 'Search Wikipedia'",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "java",
                "Cannot find element search input",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find search result",
                15
        );

        List<WebElement> listOfElements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        boolean result = false;
        for ( int i=0; i < listOfElements.size(); i++) {
            WebElement element = listOfElements.get(i);
            if (checkSubstring(element, "Java")) {
                result = true;
            } else {
                Assert.fail();
            }
        }

        Assert.assertEquals("in search have result without 'Java'",true, result);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMessage)
    {
       return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMassage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMassage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMassage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expected_text, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);

        String text = element.getAttribute("text");
        Assert.assertEquals(error_message, text, expected_text);
    }

    private boolean checkSubstring(WebElement element, String substring)
    {
        String text = element.getAttribute("text");
        return text.contains(substring);
    }
}
