package steps;

import com.codeborne.selenide.Selenide;
import pages.HomePage;
import pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Condition.text;


// Step definitions for the login feature
public class LoginSteps {

    private LoginPage loginPage; // Instance of LoginPage
    private HomePage homePage;   // Instance of HomePage

    // This would ideally come from a configuration file
    private final String baseUrl = System.getProperty("selenide.baseUrl", "https://parabank.parasoft.com"); // Default URL

    // Constructor: Initializes the LoginPage
    public LoginSteps() {
        this.loginPage = new LoginPage();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        // Open the login page URL
        // Selenide.open() navigates the browser to the given URL
        // It's good practice to have a base URL configured in Selenide
        open(baseUrl + "/parabank/index.htm"); // Assuming /login is the path to your login page
        // Initialize the page object after navigation
        this.loginPage = new LoginPage();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        // Use the LoginPage to enter the username
        loginPage.enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        // Use the LoginPage to enter the password
        loginPage.enterPassword(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        // Use the LoginPage to click the login button
        // This might navigate to a new page, so the result is stored
        // The actual page transition is handled within the LoginPage method
        // For scenarios expecting success:
        if (!url().endsWith("/login")) { // A simple check, could be more robust
            this.homePage = loginPage.clickLoginButton();
        } else {
            // For scenarios expecting to stay on login page (e.g. due to error)
            loginPage.clickLoginButtonExpectingError();
        }
    }

    @Then("I should be redirected to the home page")
    public void iShouldBeRedirectedToTheHomePage() {
        // Assert that the current URL is the home page URL
        // This assertion can be more specific based on the application's behavior
        assertTrue(url().contains(baseUrl + "/parabank/overview"), "Not redirected to the home page. Current URL: " + url());
        // Ensure homePage object is initialized if not already
        if (this.homePage == null) {
            this.homePage = Selenide.page(HomePage.class);
        }
    }

    @And("I should see {string} on the home page")
    public void iShouldSeeMessageOnTheHomePage(String expectedMessage) {
        // Use the HomePage to verify the welcome message
        homePage.getWelcomeMessageElement().shouldHave(text(expectedMessage));
    }

    @Then("I should see an error message {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        // Use the LoginPage to verify the error message
        assertTrue(loginPage.isErrorMessageDisplayed(), "Error message is not displayed.");
        assertEquals(expectedErrorMessage, loginPage.getErrorMessageText(), "Error message text does not match.");
    }

    @And("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        // Assert that the current URL is still the login page URL
        assertTrue(url().contains(baseUrl + "/login"), "Not on the login page. Current URL: " + url());
    }
}
