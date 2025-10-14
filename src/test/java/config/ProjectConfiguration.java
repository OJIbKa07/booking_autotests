package config;

import com.codeborne.selenide.Configuration;
import com.google.common.base.Strings;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfiguration {
    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    public static void configure() {

        Configuration.baseUrl = config.baseUrl();
        Configuration.browser = config.browser();
        Configuration.browserSize = config.browserSize();

        System.out.println("ProjectConfiguration.configure() called");
        System.out.println("Base URL: " + Configuration.baseUrl);
        System.out.println("Browser: " + Configuration.browser);
        System.out.println("Browser size: " + Configuration.browserSize);

        String remoteUrl = config.remoteWebDriverUrl();
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