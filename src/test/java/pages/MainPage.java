package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Language;
import io.qameta.allure.Step;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement
            discountWindowRu = $("button[aria-label='Скрыть меню входа в аккаунт.']"),
            discountWindowUk = $("button[aria-label='No quiero iniciar sesión.']"),
            discountWindowUs = $("button[aria-label='Dismiss sign-in info.']"),
            cookieWindow = $("#onetrust-accept-btn-handler"),
            languageWindow = $( "[data-testid='header-language-picker-trigger']" ),
            languagePicker = $("div#header_language_picker"),
            mainHeading = $("[data-testid='herobanner-title1']"),
            searchTravell = $("[placeholder='Where are you going?']"),
            travelersMenu = $("button[data-testid='occupancy-config']"),
            reduceAdults = $("div[data-testid='occupancy-popup'] button[tabindex='-1']"),
            addKids = $x("//label[text()='Children']/following::div[contains(@class, 'e301a14002')]//button[not(@disabled) and @tabindex='-1']"),
            ageKids = $("[aria-label='Age of child at check-out']"),
            checkPets = $("[for='pets']"),
            searchResult = $("[data-testid='property-card']"),
            currencyWindow = $("button[data-testid='header-currency-picker-trigger']");
    private ElementsCollection
            divSpanCollection = $$("div span"),
            navButtons = $$("[data-testid='header-xpb'] a"),
            buttonCollection = $$("button"),
            currencyCollection = $$("button[data-testid='selection-item']"),
            divCollection = $$("div"),
            divHouseCollection = $$("div.e7addce19e.f546354b44");

    static Stream<Arguments> successfulLanguageChange() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        List.of("Жилье", "Авиабилеты", "Авиабилеты + отели", "Аренда автомобилей", "Досуг", "Такси от/до аэропорта")
                ),
                Arguments.of(
                        Language.UK,
                        List.of("Stays", "Flights", "Flight + Hotel", "Car rental", "Activities", "Airport taxis")
                )
        );
    }

    static Stream<Arguments> currencyDataProvider() {
        return Stream.of(
                Arguments.of("USD", "$"),
                Arguments.of("EUR", "€"),
                Arguments.of("KZT", "KZT")
        );
    }

    @Step("Выбрать языке {language}")
    public MainPage languageSelection(Language language) {
        divSpanCollection.findBy(text(language.country)).click();

        return this;
    }

    @Step("Проверить, что язык в заголовке соответствует {language}")
    public MainPage checkHeadingLanguage(Language language) {
        mainHeading.shouldHave(text(language.description));

        return this;
    }

    @Step("Закрыть баннеры и куки при наличии")
    public void pageReload() {
        sleep(3000);
        if (discountWindowRu.exists()) {
            discountWindowRu.click();
        }
        if (discountWindowUk.exists()) {
            discountWindowUk.click();
        }
        if (discountWindowUs.exists()) {
            discountWindowUs.click();
        }
        if (cookieWindow.exists()) {
            cookieWindow.click();
        }
    }

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("/");

        return this;
    }

    @Step("Открыть меню смены языка страницы")
    public MainPage openLanguageMenu() {
        languageWindow.click();

        return this;
    }

    @Step("Проверить, что меню выбора языка видимо")
    public MainPage languagePickerVisible() {
        languagePicker.click();

        return this;
    }

    @Step("Проверить, что текст кнопок навигации соответствует выбранному языку")
    public MainPage checkNavButtonsLanguage(List<String> expectedButtons) {
        navButtons.shouldHave(texts(expectedButtons));

        return this;
    }

    @Step("Ввести место путешествия")
    public MainPage enteringPlace() {
        searchTravell.setValue("Switzerland");

        return this;
    }

    @Step("Выбрать дату в календаре")
    public MainPage enteringDate() {
        calendarComponent.openCalendar();
        calendarComponent.setDate();

        return this;
    }

    @Step("Ввести количество путешественников")
    public MainPage enteringNumberOfTravelers() {
        travelersMenu.click();
        reduceAdults.click();
        addKids.click();
        ageKids.selectOption("10 years old");
        checkPets.click();

        return this;
    }

    @Step("Проверить, что место путешествия соответствует выбранному")
    public MainPage checkEnteredPlace() {
        searchTravell.shouldHave(attribute("value", "Switzerland"));;

        return this;
    }

    @Step("Проверить, что дата соответствует введенной =")
    public MainPage checkEnteredDate() {
        calendarComponent.checkDate();

        return this;
    }

    @Step("Проверить, что количество путешественников соответствует введенным данным")
    public MainPage checkEnteredTravelers() {
        travelersMenu.shouldHave(text("1 adult · 1 child · Pets · 1 room"));

        return this;
    }

    @Step("Поиск результатов")
    public MainPage submit() {
        buttonCollection.findBy(Condition.text("Search")).click();

        return this;
    }

    @Step("Проверить, что окно с результатами появилось")
    public MainPage checkSearchResult() {
        searchResult.should(appear);

        return this;
    }

    @Step("Открыть меню выбора валюты")
    public MainPage openCurrencyWindow() {
        currencyWindow.shouldBe(Condition.visible).click();

        return this;
    }

    @Step("Выбрать валюту {currencyCode}")
    public MainPage chooseCurrency(String currencyCode) {
        currencyCollection.findBy(Condition.text(currencyCode))
                .click();

        return this;
    }

    @Step("Проверить, что валюта на страницу соответствует {expectedSymbol}")
    public MainPage checkCurrency(String expectedSymbol) {
        divCollection.findBy(Condition.text("Homes guests love"))
                .scrollIntoView(true);
        divHouseCollection.filter(Condition.visible)
                .findBy(Condition.text(expectedSymbol))
                .shouldBe(Condition.visible);

        return this;
    }


}
