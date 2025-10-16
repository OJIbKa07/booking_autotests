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
            cookieWindow = $("#onetrust-reject-all-handler"),
            languageWindow = $( "[data-testid='header-language-picker-trigger']" ),
            languagePicker = $("div#header_language_picker"),
            mainHeading = $("[data-testid='herobanner-title1']"),
            searchTravell = $("[placeholder='Where are you going?']"),
            travelersMenu = $("button[data-testid='occupancy-config']"),
            reduceAdults = $("div[data-testid='occupancy-popup'] button[tabindex='-1']"),
            addKids = $x("//label[text()='Children']/following::div[contains(@class, 'e301a14002')]//button[not(@disabled) and @tabindex='-1']"),
            ageKids = $("[aria-label='Age of child 1 on check-out date']"),
            checkPets = $("[for='pets']"),
            searchResult = $("[data-testid='property-card']"),
            currencyWindow = $("button[data-testid='header-currency-picker-trigger']"),
            helpCenterLink = $("[data-testid='header-help-center']"),
            popupLink = $("div[data-testid='wishlist-popover-content'] a[aria-label='Saved to: My next trip']");
    private ElementsCollection
            languageCollection = $$("#header_language_picker span"),
            navButtons = $$("[data-testid='header-xpb'] a"),
            buttonCollection = $$("button"),
            currencyCollection = $$("button[data-testid='selection-item']"),
            divCollection = $$("div"),
            divHouseCollection = $$("div.e7addce19e.f546354b44"),
            sections = $$("div[data-testid='component-tracker']"),
            subsectionDealsWeekend = $$("ul[aria-label='Deals for the weekend'] li");
    private final String
            buttonSearchName = "Search",
            headerHome = "Homes guests love",
            trevelersCount = "1 adult · 1 child · Pets · 1 room",
            sectionName = "Deals for the weekend",
            wishlistButtonLocator = "button[data-testid='wishlist-button']",
            cardTitleLocator = "h3";
    private SelenideElement savedCard,savedCardNameTag;
    private String savedCardName;
    private SelenideElement wishlistButton, section;

    static Stream<Arguments> successfulLanguageChange() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        List.of("Жилье", "Авиабилеты", "Авиабилеты + отели", "Аренда автомобилей", "Досуг", "Такси от/до аэропорта")
                ),
                Arguments.of(
                        Language.UK,
                        List.of("Stays", "Flights", "Flight + Hotel", "Car rental", "Attractions", "Airport taxis")
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

    @Step("Выбрать язык {language}")
    public MainPage languageSelection(Language language) {
        languageCollection.findBy(text(language.country)).click();

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
        open("");

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
    public MainPage enteringPlace(String place) {
        searchTravell.setValue(place);

        return this;
    }

    @Step("Выбрать дату в календаре")
    public MainPage enteringDate() {
        calendarComponent.openCalendar();
        calendarComponent.setDate();

        return this;
    }

    @Step("Ввести количество путешественников")
    public MainPage enteringNumberOfTravelers(String age) {
        travelersMenu.click();
        reduceAdults.click();
        addKids.click();
        ageKids.selectOption(age);
        checkPets.click();

        return this;
    }

    @Step("Проверить, что место путешествия соответствует выбранному")
    public MainPage checkEnteredPlace(String place) {
        searchTravell.shouldHave(attribute("value", place));;

        return this;
    }

    @Step("Проверить, что дата соответствует введенной")
    public MainPage checkEnteredDate() {
        calendarComponent.checkDate();

        return this;
    }

    @Step("Проверить, что количество путешественников соответствует введенным данным")
    public MainPage checkEnteredTravelers() {
        travelersMenu.shouldHave(text(trevelersCount));

        return this;
    }

    @Step("Поиск результатов")
    public MainPage submit() {
        buttonCollection.findBy(Condition.text(buttonSearchName)).click();

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
        divCollection.findBy(Condition.text(headerHome))
                .scrollIntoView(true);
        divHouseCollection.filter(Condition.visible)
                .findBy(Condition.text(expectedSymbol))
                .shouldBe(Condition.visible);

        return this;
    }

    @Step("Открыть страницу Центр помощи")
    public MainPage openHelpCenter() {
        helpCenterLink.shouldBe(visible).click();

        return this;
    }


    @Step("Скроллим до блока с текстом: {sectionName}")
    public MainPage scrollToDealsForWeekends() {
        section = sections.findBy(Condition.text(sectionName));

        section.scrollIntoView(true).shouldBe(visible);

        return this;
    }

    @Step("Находим первую карточку для добавления в избранное")
    public MainPage findFavoritesAndSave() {
        savedCard = subsectionDealsWeekend.first();

        savedCardNameTag = savedCard.$(cardTitleLocator);
        savedCardName = savedCardNameTag.getText();
        wishlistButton = savedCard.$(wishlistButtonLocator);

        return this;
    }


    @Step("Добавляем карточку в избранное")
    public MainPage addToFavorites() {
        wishlistButton.click();

        return this;
    }

    @Step("Переходим на страницу избранного")
    public MainPage goToFavorites() {
        popupLink.shouldBe(Condition.visible)
                .click();
        switchTo().window(1);

        return this;
    }

    @Step("Получаем название сохраненной карточки")
    public String getSavedCardName() {
        return savedCardName;
    }
}
