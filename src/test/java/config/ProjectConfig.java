package config;
// src/main/java/com/example/config/ProjectConfig.java
// (Note: placing config interfaces in src/main/java allows them to be potentially shared
// if you have main code that needs them, or keep them in src/test/java if purely for tests)

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE) // Merge properties from all sources
@Sources({
        "system:properties", // Load from system properties (highest priority)
        "system:env",        // Load from system environment variables
        "file:src/test/resources/config/test.properties", // Load from a file in the classpath
        "classpath:config/default.properties" // Optional: a default properties file bundled in resources
})
public interface ProjectConfig extends Config {

    @Key("selenide.baseUrl")
    @DefaultValue("https://parabank.parasoft.com") // Default value if not found in any source
    String baseUrl();

    @Key("selenide.browser")
    @DefaultValue("chrome")
    String browser();

    @Key("selenide.headless")
    @DefaultValue("false")
    boolean headless();

    @Key("selenide.timeout")
    @DefaultValue("10000") // Default timeout in milliseconds (10 seconds)
    long timeout();

    @Key("user.admin.username")
    String adminUsername();

    @Key("user.admin.password")
    String adminPassword();

    @Key("selenide.browserSize")
    @DefaultValue("1920x1080")
    String browserSize();

}

// src/test/resources/config/test.properties
/*
# Selenide Configuration
selenide.baseUrl=https://your-staging-environment.com
selenide.browser=firefox
selenide.headless=true
selenide.timeout=15000

# Application User Credentials
user.admin.username=admin_user
user.admin.password=secure_password123
*/
