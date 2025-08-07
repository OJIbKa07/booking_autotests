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

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Tag("Smoke")
    @MethodSource("pages.MainPage#successfulLanguageChange")
    @ParameterizedTest
    void successfulLanguageChange(Language language, List<String> expectedButtons) {
        step("Открываем главную страницу и закрываем баннеры", () -> {
            mainPage.openPage().pageReload();
        });
        step("Открываем меню выбора языка", () -> {
            mainPage.openLanguageMenu();
        });
        step("Проверяем, что меню языков видимо на странице", () -> {
            mainPage.languagePickerVisible();
        });
        step("Кликаем по языку {language}", () -> {
            mainPage.languageSelection(language).pageReload();
        });
        step("Проверяем, что язык основного заголовка соответствует {language}", () -> {
            mainPage.checkHeadingLanguage(language);
        });
        step("Проверяем, что язык кнопок навигации соответствует {language}", () -> {
            mainPage.checkNavButtonsLanguage(expectedButtons);
      });
    }

    @Tag("Smoke")
    @Test
    void housingSearch() {
        step("Открываем главную страницу и закрываем баннеры", () -> {
            mainPage.openPage().pageReload();
        });
        step("Заполняем форму куда хотим поехать", () -> {
            mainPage.enteringPlace()
                    .enteringDate()
                    .enteringNumberOfTravelers();
        });
        step("Проверяем корректность заполненной формы", () -> {
            mainPage.checkEnteredPlace()
                    .checkEnteredDate()
                    .checkEnteredTravelers();
        });
        step("Отправка формы", () -> {
            mainPage.submit();
        });
        step("Проверяем корректность заполненной формы после ее отправки", () -> {
            mainPage.checkEnteredPlace()
                    .checkEnteredDate()
                    .checkEnteredTravelers();
        });
        step("Проверяем, что появилось окно с результатами", () -> {
            mainPage.checkSearchResult();
        });
    }

    @Tag("Smoke")
    @Test
    void leisureOptions() {
        step("Открываем главную страницу и закрываем баннеры", () -> {
            mainPage.openPage().pageReload();
        });
        step("Переходим на страницу выбора вариантов досуга и закрываем баннеры", () -> {
            attractionsPage.openAttractionsPage().pageReload();
        });
        step("Заполняем форму выбора досуга", () -> {
            attractionsPage.enteringPlace().enteringDate();
        });
        step("Отправляем заполненную форму", () -> {
            attractionsPage.checkPrices();
        });
        step("Проверяем, что окно с результатами появилось", () -> {
            attractionsPage.checkPageResults();
        });
        step("Проверяем, что карточки предложений появились", () -> {
            attractionsPage.checkCardResults();
        });
        step("Сортируем цены по возрастанию", () -> {
            attractionsPage.clickLowPrices();
        });
        step("Проверяем, что окно с результатами появилось после сортировки", () -> {
            attractionsPage.clickLowPrices();
        });
        step("Проверяем, что цены отсортированы по возрастанию", () -> {
            attractionsPage.checkSort();
        });
    }

    @Tag("Smoke")
    @Test
    void orderingTaxiHumanCheck() {
        step("Открываем главную страницу и закрываем баннеры", () -> {
            mainPage.openPage().pageReload();
        });
        step("Переходим на страницу выбора вариантов досуга и закрываем баннеры", () -> {
            taxisPage.openTaxisPage().pageReload();
        });
        step("Заполняем форму заказа такси", () -> {
            taxisPage.activeBackAndForthButton()
                    .enteringPlace()
                    .enteringDateAndTime()
                    .setPassengers();
        });
        step("Отправка формы", () -> {
            taxisPage.submit();
        });
        step("Проверяем, что появилась проверка на робота", () -> {
            taxisPage.checkCaptcha();
        });
    }

    @ParameterizedTest
    @MethodSource("pages.MainPage#currencyDataProvider")
    void changeCurrencyTest(String currencyCode, String expectedSymbol) {
        step("Открываем главную страницу и закрываем баннеры", () -> {
            mainPage.openPage().pageReload();
        });
        step("Открываем меню выбора валюты", () -> {
            mainPage.openCurrencyWindow();
        });
        step("Выбираем валюту и закрываем баннеры", () -> {
            mainPage.chooseCurrency(currencyCode).pageReload();
        });
        step("Проверяем, что символ валюты {expectedSymbol} соответствует {currencyCode}", () -> {
            mainPage.checkCurrency(expectedSymbol);
        });
    }

}
