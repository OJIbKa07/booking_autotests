package tests;

import helpers.allure.annotations.Layer;
import helpers.allure.annotations.Microservice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import pages.MainPage;
import pages.AttractionsPage;
import pages.TaxisPage;

import io.qameta.allure.*;
import utils.RandomUtils;

@Owner("oPalushina")
@Layer("ui")
@Microservice("Booking")
@Tag("ui")
@Epic("Процесс бронирования")
@Feature("Функциональные тесты бронирования (UI)")
@Story("Общие сценарии бронирования")
@DisplayName("UI: Тесты бронирования на английской версии сайта")
public class BookingEnglishTests extends TestBase {
    MainPage mainPage = new MainPage();
    AttractionsPage attractionsPage = new AttractionsPage();
    TaxisPage taxisPage = new TaxisPage();
    RandomUtils faker = new RandomUtils();
    String place, years, whereFrom, whereIs;

    @Tags({
            @Tag("booking"),@Tag("smoke")
    })
    @Test
    @DisplayName("UI: Проверка поиска жилья")
    @Owner("oPalushina")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Вводит город, даты и количество путешественников, подтверждает ввод и проверяет корректность отображения результатов поиска жилья")
    void housingSearchTest() {
        place = faker.getCity();
        years = faker.getAge();
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .enteringPlace(place)
                .enteringDate()
                .enteringNumberOfTravelers(years)
                .checkEnteredPlace(place)
                .checkEnteredDate()
                .checkEnteredTravelers()
                .submit()
                .checkEnteredPlace(place)
                .checkEnteredDate()
                .checkEnteredTravelers()
                .checkSearchResult();
    }

    @Tags({
            @Tag("booking"),@Tag("smoke")
    })
    @Test
    @DisplayName("UI: Проверка вариантов досуга")
    @Owner("oPalushina")
    @Severity(SeverityLevel.NORMAL)
    @Description("Открывает страницу развлечений, вводит город и дату, проверяет корректность отображения цен, карточек и сортировки по цене")
    void leisureOptionsTest() {
        place = faker.getCity();
        mainPage
                .openPage()
                .pageReload();
        attractionsPage
                .openAttractionsPage()
                .enteringPlace(place)
                .enteringDate()
                .checkPrices()
                .checkPageResults()
                .checkCardResults()
                .clickLowPrices()
                .clickLowPrices()
                .checkSort();
    }

    @Tags({
            @Tag("booking"),@Tag("smoke"),@Tag("taxi")
    })
    @Test
    @DisplayName("UI: Проверка заказа такси и проверки на бота")
    @Owner("oPalushina")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Переходит на страницу такси, вводит маршрут, дату, время и количество пассажиров, отправляет форму и проверяет появление капчи")
    void orderingTaxiHumanCheckTest() {
        whereFrom = faker.getCity();
        whereIs = faker.getCity();
        int count = faker.getCountPassengers();
        mainPage
                .openPage()
                .pageReload();
        taxisPage
                .openTaxisPage()
                .pageReload();
        taxisPage
                .activeBackAndForthButton()
                .enteringPlace(whereFrom, whereIs)
                .enteringDateAndTime()
                .setPassengers(count)
                .submit()
                .checkCaptcha();
    }
}
