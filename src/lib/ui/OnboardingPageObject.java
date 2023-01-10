package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class OnboardingPageObject extends MainPageObject {

    private static final String
            SKIP_ONBOARDING_BUTTON = "org.wikipedia:id/fragment_onboarding_skip_button";

    public OnboardingPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void skipOnboardingButton()
    {
        this.waitForElementAndClick(
                By.id(SKIP_ONBOARDING_BUTTON),
                "Cannot find element SKIP",
                5
        );
    }
}
