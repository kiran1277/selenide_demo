package runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

// This class uses JUnit Platform Suite to run Cucumber tests.
// It replaces the older @RunWith(Cucumber.class) from JUnit 4.
@Suite // Marks this class as a test suite for JUnit Platform
@IncludeEngines("cucumber") // Specifies that the Cucumber test engine should be used
@SelectClasspathResource("features") // Tells Cucumber where to find feature files (looks in src/test/resources/features)
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps, hooks") // Tells Cucumber where to find step definitions and hooks
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, json:build/cucumber-reports/cucumber.json, html:build/cucumber-reports/cucumber-report.html")
// Defines the plugins for Cucumber reports:
// "pretty" - prints Gherkin scenarios to the console in a readable format.
// "json:build/cucumber-reports/cucumber.json" - generates a JSON report.
// "html:build/cucumber-reports/cucumber-report.html" - generates an HTML report.

// Optional: You can add more @ConfigurationParameter annotations for other Cucumber options:
// @ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features") // Redundant if using @SelectClasspathResource
// @ConfigurationParameter(key = TAGS_PROPERTY_NAME, value = "@smoke and not @wip") // Example for running specific tags
public class CucumberTestRunner {
    // This class remains empty.
    // The annotations above configure the test execution.
    // To run tests, you would typically run this class as a JUnit test from your IDE,
    // or use the 'cucumber' or 'test' Gradle tasks defined in build.gradle.
}
