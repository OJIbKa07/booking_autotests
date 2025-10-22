package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
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

        String remoteUrl = System.getProperty("remoteWebDriverUrl", config.remoteWebDriverUrl());

        if (!remoteUrl.startsWith("http://") && !remoteUrl.startsWith("https://")) {
            remoteUrl = "https://" + remoteUrl;
        }

        String user = System.getProperty("selenoidUser");
        String password = System.getProperty("selenoidPassword");

        if (user != null && !user.isEmpty() && password != null && !password.isEmpty()) {
            remoteUrl = remoteUrl.replaceFirst("://", String.format("://%s:%s@", user, password));
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

        System.out.println(remoteUrl);
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