package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import com.google.common.base.Strings;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = System.getProperty("baseUrl", config.baseUrl());
        Configuration.browser = System.getProperty("browser", config.browser());
        Configuration.browserVersion = System.getProperty("browserVersion", config.browserVersion());
        Configuration.browserSize = config.browserSize();

        String remoteUrl = config.remoteWebDriverUrl();
        String user = System.getProperty("selenoidUser");
        String password = System.getProperty("selenoidPassword");
        
        if (user != null && password != null && !user.isEmpty() && !password.isEmpty()) {
            remoteUrl = remoteUrl.replace("https://", String.format("https://%s:%s@", user, password));
        }

        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void addListenerAndRuCookie() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}