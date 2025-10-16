package config;

import com.codeborne.selenide.Configuration;
import com.google.common.base.Strings;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfiguration {
    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    public static void configure() {

        Configuration.baseUrl = System.getProperty("baseUrl", config.baseUrl());
        Configuration.browser = System.getProperty("browser", config.browser());
        Configuration.browserVersion = System.getProperty("browserVersion", config.browserVersion());
        Configuration.browserSize = System.getProperty("browserVersion", config.browserSize());

        String remoteUrl = System.getProperty("remoteWebDriverUrl", config.remoteWebDriverUrl());
        
        if (!Strings.isNullOrEmpty(remoteUrl)) {
            Configuration.remote = remoteUrl;
            Configuration.browserVersion = config.browserVersion();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }
}