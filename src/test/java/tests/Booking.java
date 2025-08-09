package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import data.Language;
import pages.MainPage;
import pages.AttractionsPage;
import pages.TaxisPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;


public class Booking extends TestBase{
    MainPage mainPage = new MainPage();
    AttractionsPage attractionsPage = new AttractionsPage();
    TaxisPage taxisPage = new TaxisPage();

    @Tag("Smoke")
    @MethodSource("pages.MainPage#successfulLanguageChange")
    @ParameterizedTest
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

    @Tag("Smoke")
    @Test
    void housingSearchTest() {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .enteringPlace()
                .enteringDate()
                .enteringNumberOfTravelers()
                .checkEnteredPlace()
                .checkEnteredDate()
                .checkEnteredTravelers()
                .submit()
                .checkEnteredPlace()
                .checkEnteredDate()
                .checkEnteredTravelers()
                .checkSearchResult();
    }

    @Tag("Smoke")
    @Test
    void leisureOptionsTest() {
        mainPage
                .openPage()
                .pageReload();
        attractionsPage
                .openAttractionsPage()
                .pageReload();
        attractionsPage
                .openAttractionsPage()
                .pageReload();
        attractionsPage
                .enteringPlace()
                .enteringDate()
                .checkPrices()
                .checkPageResults()
                .checkCardResults()
                .clickLowPrices()
                .clickLowPrices()
                .checkSort();
    }

    @Tag("Smoke")
    @Test
    void orderingTaxiHumanCheckTest() {
        mainPage
                .openPage()
                .pageReload();
        taxisPage
                .openTaxisPage()
                .pageReload();
        taxisPage
                .activeBackAndForthButton()
                .enteringPlace()
                .enteringDateAndTime()
                .setPassengers()
                .submit()
                .checkCaptcha();
    }

    @ParameterizedTest
    @MethodSource("pages.MainPage#currencyDataProvider")
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

}
