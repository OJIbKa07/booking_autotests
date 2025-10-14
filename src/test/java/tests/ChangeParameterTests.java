package tests;

import data.Language;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.List;

public class ChangeParameterTests extends TestBase {
    MainPage mainPage = new MainPage();

    @Tags({
            @Tag("change"),@Tag("smoke")
    })
    @ParameterizedTest
    @MethodSource("pages.MainPage#currencyDataProvider")
    @DisplayName("Change Currency Test")
    @Owner("oPalushina")
    @Severity(SeverityLevel.MINOR)
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
    @DisplayName("Successful Language Change")
    @Owner("oPalushina")
    @Severity(SeverityLevel.CRITICAL)
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
