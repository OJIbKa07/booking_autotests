package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TaxisPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement
            discountWindowRu = $("button[aria-label='Скрыть меню входа в аккаунт.']"),
            cookieWindow = $("#onetrust-accept-btn-handler"),
            taxisNav = $("a#airport_taxis"),
            backAndForthButtton = $("div[data-testid='mfe-search-form-radio-buttons__return']"),
            whereFromPlace = $("input#pickup-input-id"),
            wherePlace = $("input#dropoff-input-id"),
            countPassengers = $("[name='passengers']"),
            captchaContainer = $("#captcha-container");


    private ElementsCollection
            whereFromCollection = $$("[data-testid='taxi-autocomplete-search-drop-down__container-pickup']"),
            whereCollection = $$("[data-testid='taxi-autocomplete-search-drop-down__container-dropoff']"),
            buttonCollection = $$("button");


    public void pageReload() {
        sleep(3000);
        if (discountWindowRu.exists()) {
            discountWindowRu.click();
        }
        if (cookieWindow.exists()) {
            cookieWindow.click();
        }
    }

    public TaxisPage openTaxisPage() {
        taxisNav.click();

        return this;
    }

    public TaxisPage activeBackAndForthButton() {
        backAndForthButtton.click();

        return this;
    }

    public TaxisPage enteringPlace () {
        whereFromPlace.setValue("Римский железнодорожный вокзал Тибуртина");
        whereFromCollection.first().click();
        wherePlace.setValue("Аэропорт Рим Фьюмичино");
        whereCollection.first().click();

        return this;
    }

    public TaxisPage enteringDateAndTime() {
        calendarComponent.setDateAndTime();

        return this;
    }

    public TaxisPage setPassengers() {
        countPassengers.selectOption("3");

        return this;
    }

    public TaxisPage submit() {
        buttonCollection.findBy(text("Найти")).click();

        return this;
    }

    public TaxisPage checkCaptcha() {
        captchaContainer.shouldBe(Condition.visible);

        return this;
    }




}
