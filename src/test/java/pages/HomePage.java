package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;

// HomePage representing the home page or dashboard after login
public class HomePage {

    // SelenideElement for a welcome message or a distinctive element on the home page
    // Uses CSS selector to find an element with id 'welcome-message'
    private final SelenideElement welcomeMessageElement = $("p.smallText");

    // SelenideElement for a user profile link/button
    // Uses CSS selector to find an element with id 'user-profile-link'
    private final SelenideElement userProfileLink = $("#user-profile-link");

    // SelenideElement for a logout button
    // Uses CSS selector to find an element with id 'logout-button'
    private final SelenideElement logoutButton = $("#logout-button");


    /**
     * Gets the SelenideElement representing the welcome message.
     * This allows step definitions to perform assertions on this element (e.g., check text, visibility).
     * @return The SelenideElement for the welcome message.
     */
    public SelenideElement getWelcomeMessageElement() {
        return welcomeMessageElement.shouldBe(visible);
    }

    /**
     * Checks if the welcome message is displayed.
     * @return True if the welcome message is visible, false otherwise.
     */
    public boolean isWelcomeMessageDisplayed() {
        return welcomeMessageElement.isDisplayed();
    }

    /**
     * Gets the text of the welcome message.
     * @return The text content of the welcome message.
     */
    public String getWelcomeMessageText() {
        return welcomeMessageElement.getText();
    }

    /**
     * Clicks the logout button.
     * This method assumes that clicking logout navigates back to the LoginPage.
     * @return A new LoginPage object.
     */
    public LoginPage clickLogoutButton() {
        logoutButton.shouldBe(visible, enabled).click();
        // After logout, we expect to be back on the LoginPage
        return com.codeborne.selenide.Selenide.page(LoginPage.class);
    }

    /**
     * Checks if the user profile link is visible.
     * @return True if the user profile link is visible, false otherwise.
     */
    public boolean isUserProfileLinkVisible() {
        return userProfileLink.shouldBe(visible).isDisplayed();
    }
}
