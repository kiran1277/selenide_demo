package hooks;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import config.ProjectConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
// For Allure reports, if you add Allure
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.OutputType;

// Cucumber Hooks for setup and teardown actions
public class BrowserHooks {

    private static ProjectConfig config;

    // This method runs before any scenario.
    @Before(order = 0) // order=0 ensures this runs before other @Before hooks if any
    public static void setUp() {
        // Load project configuration using OWNER
        config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

        // Configure Selenide based on properties from ProjectConfig
        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser(); // e.g., "chrome", "firefox"
        Configuration.headless = config.headless(); // true or false
        Configuration.timeout = config.timeout(); // Default timeout for Selenide operations
        Configuration.pageLoadStrategy = "normal"; // "normal", "eager", or "none"
        Configuration.screenshots = true; // Take screenshots on failure
        Configuration.savePageSource = false; // Save page source on failure
        Configuration.browserSize = config.browserSize();

        // Example: Setting browser window size
        // Configuration.browserSize = "1920x1080";

        // Example: If you want to use a remote WebDriver (e.g., Selenium Grid or Selenoid)
        // Configuration.remote = "http://localhost:4444/wd/hub";
        // DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setCapability("enableVNC", true);
        // capabilities.setCapability("enableVideo", false);
        // Configuration.browserCapabilities = capabilities;

        // Add Allure listener for Selenide events (if using Allure reports)
        // Make sure to add Allure dependencies to build.gradle if you use this
//         SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
//                .screenshots(true)
//                .savePageSource(true));

        System.out.println("Setting up browser configuration: " + Configuration.browser + ", Headless: " + Configuration.headless + ", BaseURL: " + Configuration.baseUrl);
    }

    // This method runs after each scenario.
    @After
    public void tearDown(Scenario scenario) {
        // Take a screenshot if the scenario failed
        if (scenario.isFailed()) {
            // Selenide automatically takes screenshots on failure if Configuration.screenshots is true.
            // This is an example of how to take one manually and attach it if needed,
            // or if you want to attach it to a specific reporting tool.
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            if (screenshot != null) {
                scenario.attach(screenshot, "image/png", "FailureScreenshot_" + scenario.getName());
            }
            System.out.println("Scenario failed: " + scenario.getName());
        } else {
            System.out.println("Scenario passed: " + scenario.getName());
        }

        // Close the browser window after each scenario.
        // Selenide.closeWebDriver() closes the browser and WebDriver instance.
        // Use Selenide.closeWindow() to just close the current window if you manage WebDriver lifecycle differently.
        Selenide.closeWebDriver();
        System.out.println("Browser closed after scenario: " + scenario.getName());
    }

    // Example: A hook that runs only before scenarios tagged with @database
    @Before("@database")
    public void setUpDatabase() {
        System.out.println("Setting up database for @database tagged scenario...");
        // Add database setup logic here
    }

    // Example: A hook that runs only after scenarios tagged with @database
    @After("@database")
    public void tearDownDatabase() {
        System.out.println("Tearing down database for @database tagged scenario...");
        // Add database teardown logic here
    }
}
