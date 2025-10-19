package tests;

import data.Language;
import helpers.allure.annotations.Layer;
import helpers.allure.annotations.Microservice;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.List;

@Owner("oPalushina")
@Layer("ui")
@Microservice("Settings")
@Tag("ui")
@Epic("Настройки интерфейса")
@Feature("Смена параметров отображения (UI)")
@DisplayName("UI: Тесты изменения параметров интерфейса")
public class ChangeParameterTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Tags({
            @Tag("change"),@Tag("smoke")
    })
    @ParameterizedTest
    @MethodSource("pages.MainPage#currencyDataProvider")
    @Owner("oPalushina")
    @Severity(SeverityLevel.MINOR)
    @Story("Смена валюты отображения цен")
    @DisplayName("UI: Проверка смены валюты на главной странице")
    @Description("Открывает главную страницу, меняет валюту в хедере и проверяет корректность отображения символа валюты")
    void changeCurrencyTest(String currencyCode, String expectedSymbol) {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .openCurrencyWindow()
                .chooseCurrency(currencyCode)
                .pageReload();
        mainPage
                .checkCurrency(expectedSymbol);
    }

    @Tags({
            @Tag("changeLanguage"),@Tag("smoke")
    })
    @MethodSource("pages.MainPage#successfulLanguageChange")
    @ParameterizedTest
    @Story("Смена языка интерфейса сайта")
    @DisplayName("UI: Проверка смены языка интерфейса")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Меняет язык интерфейса через меню выбора языка и проверяет, что заголовки и кнопки отображаются на выбранном языке")
    void successfulLanguageChangeTest(Language language, List<String> expectedButtons) {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .openLanguageMenu()
                .languagePickerVisible()
                .languageSelection(language)
                .pageReload();
        mainPage
                .checkHeadingLanguage(language)
                .checkNavButtonsLanguage(expectedButtons);
    }
}
