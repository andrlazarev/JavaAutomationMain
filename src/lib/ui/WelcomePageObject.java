package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
        STEP_LEARN_MORE_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about Wikipedia']",
        STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
        STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "xpath://XCUIElementTypeStaticText[@name='Add or edit preferred languages']",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name='Learn more about data collected']",
        NEXT_LINK = "xpath://XCUIElementTypeButton[@name='Next']",
        GET_STARTED_BUTTON = "xpath://XCUIElementTypeButton[@name='Get started']";

    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForMoreLearnLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void waitForNewWayToExploreText() {
        this.waitForElementPresent(
                STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'Learn more about Wikipedia' link",
                10
        );
    }

    public void waitForAddOrEditPreferredLangText()
    {
        this.waitForElementPresent(
                STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find link 'Add or edit preferred languages'",
                10
        );
    }

    public void waitForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find link 'Learn more about data collected'",
                10
        );
    }

    public void clickNextButton()
    {
        this.waitForElementAndClick(
                NEXT_LINK,
                "Cannot find button 'Next'",
                10
        );
    }

    public void clickGetStartedButton()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "Cannot find button 'Get started'",
                10
        );
    }
}
