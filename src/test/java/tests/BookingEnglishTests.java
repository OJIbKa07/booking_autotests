package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import pages.MainPage;
import pages.AttractionsPage;
import pages.TaxisPage;

import io.qameta.allure.*;
import utils.RandomUtils;

@Epic("Booking Flow")
@Feature("Booking Functional Tests")
@Story("General Booking Scenarios")
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
    @DisplayName("Housing Search Test")
    @Owner("oPalushina")
    @Severity(SeverityLevel.BLOCKER)
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
    @DisplayName("Leisure Options Test")
    @Owner("oPalushina")
    @Severity(SeverityLevel.NORMAL)
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
            @Tag("booking"),@Tag("smoke")
    })
    @Test
    @DisplayName("Ordering Taxi Human Check Test")
    @Owner("oPalushina")
    @Severity(SeverityLevel.CRITICAL)
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
