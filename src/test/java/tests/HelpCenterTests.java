package tests;

import helpers.allure.annotations.Layer;
import helpers.allure.annotations.Microservice;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.HelpPage;
import pages.MainPage;

@Owner("oPalushina")
@Layer("ui")
@Microservice("HelpCenter")
@Tag("ui")
@Epic("Центр поддержки")
@Feature("Доступ к помощи без авторизации (UI)")
@Story("Пользователь открывает центр помощи без входа в аккаунт")
@DisplayName("UI: Тесты центра поддержки")
public class HelpCenterTests extends TestBase {
    MainPage mainPage = new MainPage();
    HelpPage helpPage = new HelpPage();

    @Test
    @Tags({
            @Tag("helpCenter"),@Tag("smoke")
    })
    @DisplayName("UI: Открытие Центра помощи без авторизации")
    @Owner("oPalushina")
    @Severity(SeverityLevel.TRIVIAL)
    @Description("Проверяет, что неавторизованный пользователь может открыть Центр помощи, и видны основные элементы (FAQ, категории вопросов)")
    void openHelpCenterTest() {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .openHelpCenter()
                .pageReload();
        helpPage
                .checkHelpCenterLoaded()
                .checkFaqBlockVisible()
                .checkAllFaqSections();
    }
}
