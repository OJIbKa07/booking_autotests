package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.Language;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;
import data.Language;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement
            discountWindowRu = $("button[aria-label='Скрыть меню входа в аккаунт.']"),
            discountWindowUk = $("button[aria-label='No quiero iniciar sesión.']"),
            cookieWindow = $("#onetrust-accept-btn-handler"),
            languageWindow = $( "[data-testid='header-language-picker-trigger']" ),
            languagePicker = $("div#header_language_picker"),
            mainHeading = $("[data-testid='herobanner-title1']"),
            searchTravell = $("[placeholder='Куда вы хотите поехать?']"),
            travelersMenu = $("button[data-testid='occupancy-config']"),
            reduceAdults = $("div[data-testid='occupancy-popup'] button[tabindex='-1']"),
            addKids = $x("//label[text()='Детей']/following::div[contains(@class, 'e301a14002')]//button[not(@disabled) and @tabindex='-1']"),
            ageKids = $("[aria-label='Возраст ребенка на момент отъезда']"),
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
                        List.of("Жилье", "Авиабилеты", "Аренда автомобилей", "Досуг", "Такси от/до аэропорта")
                ),
                Arguments.of(
                        Language.UK,
                        List.of("Stays", "Flights", "Car rental", "Attractions", "Airport taxis")
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

    public MainPage languageSelection(Language language) {
        divSpanCollection.findBy(text(language.country)).click();

        return this;
    }

    public MainPage checkHeadingLanguage(Language language) {
        mainHeading.shouldHave(text(language.description));

        return this;
    }

    public void pageReload() {
        sleep(3000);
        if (discountWindowRu.exists()) {
            discountWindowRu.click();
        }
        if (discountWindowUk.exists()) {
            discountWindowUk.click();
        }
        if (cookieWindow.exists()) {
            cookieWindow.click();
        }
    }

    public MainPage openPage() {
        open("/");

        return this;
    }

    public MainPage openLanguageMenu() {
        languageWindow.click();

        return this;
    }

    public MainPage languagePickerVisible() {
        languagePicker.click();

        return this;
    }

    public MainPage checkNavButtonsLanguage(List<String> expectedButtons) {
        navButtons.shouldHave(texts(expectedButtons));

        return this;
    }

    public MainPage enteringPlace() {
        searchTravell.setValue("Швейцария");

        return this;
    }

    public MainPage enteringDate() {
        calendarComponent.openCalendar();
        calendarComponent.setDate();

        return this;
    }

    public MainPage enteringNumberOfTravelers() {
        travelersMenu.click();
        reduceAdults.click();
        addKids.click();
        ageKids.selectOption("10 лет");
        checkPets.click();

        return this;
    }

    public MainPage checkEnteredPlace() {
        searchTravell.shouldHave(attribute("value", "Швейцария"));;

        return this;
    }


    public MainPage checkEnteredDate() {
        calendarComponent.checkDate();

        return this;
    }

    public MainPage checkEnteredTravelers() {
        travelersMenu.shouldHave(
                text("1 взрослый"),
                text("1 ребенок"),
                text("Животные"),
                text("1 номер")
        );

        return this;
    }

    public MainPage submit() {
        buttonCollection.findBy(Condition.text("Найти")).click();

        return this;
    }

    public MainPage checkSearchResult() {
        searchResult.should(appear);

        return this;
    }

    public MainPage openCurrencyWindow() {
        currencyWindow.shouldBe(Condition.visible).click();

        return this;
    }

    public MainPage chooseCurrency(String currencyCode) {
        currencyCollection.findBy(Condition.text(currencyCode))
                .click();

        return this;
    }

    public MainPage checkCurrency(String expectedSymbol) {
        divCollection.findBy(Condition.text("Дома, которые нравятся гостям"))
                .scrollIntoView(true);
        divHouseCollection.filter(Condition.visible)
                .findBy(Condition.text(expectedSymbol))
                .shouldBe(Condition.visible);

        return this;
    }


}
