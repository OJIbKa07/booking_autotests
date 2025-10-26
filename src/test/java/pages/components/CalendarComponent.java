package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.RandomUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate
            today = LocalDate.now(),
            checkInDate = today.plusMonths(1),
            checkOutDate = checkInDate.plusDays(7),
            checkInDateTaxi = today.plusDays(1),
            checkOutDateTaxi = today.plusDays(1);

    String
            checkInStr = checkInDate.format(formatter),
            checkOutStr = checkOutDate.format(formatter),
            checkInTaxiStr = checkInDateTaxi.format(formatter),
            checkOutTaxiStr = checkOutDateTaxi.format(formatter);


    private SelenideElement calendarEl =  $("[data-testid='date-display-field-start']"),
            dateStart = $("span[data-date='" + checkInStr + "']"),
            dateEnd = $("span[data-date='" + checkOutStr + "']"),
            dateStartTaxi = $("span[data-date='" + checkInTaxiStr + "']"),
            dateEndTaxi = $("span[data-date='" + checkOutTaxiStr + "']"),
            fieldStart = $("span[data-testid='date-display-field-start']"),
            fieldsEnd = $("span[data-testid='date-display-field-end']"),
            hoursOption = $("[name='hours']"),
            minutesOption = $("[name='minutes']"),
            taxiDateStart = $("button[data-testid='taxi-date-time-picker-form-element__button-oneway']"),
            taxiDateEnd = $("button[data-testid='taxi-date-time-picker-form-element__button-return']"),
            buttonPrevMonth = $("[aria-label='Previous month']");

    RandomUtils faker = new RandomUtils();

    private String startTime, endTime;
    private String[] startParts, endParts;

    @Step("Открыть календарь")
    public void openCalendar() {
        calendarEl.click();

    }

    @Step("Ввести дату")
    public void setDate() {
        dateStart.shouldBe(Condition.visible).click();
        dateEnd.shouldBe(Condition.visible).click();

    }

    @Step("Проверить введенную дату")
    public void checkDate() {
        fieldStart.shouldHave(Condition.text(String.valueOf(checkInDate.getDayOfMonth())));
        fieldsEnd.shouldHave(Condition.text(String.valueOf(checkOutDate.getDayOfMonth())));
    }

    @Step("Присвоить дату и время")
    public void setDateAndTime() {
        startTime = faker.getTime();
        endTime = faker.getTime();

        startParts = startTime.split(":");
        endParts = endTime.split(":");

        taxiDateStart.click();
        if (!dateStartTaxi.is(Condition.visible)) {
            buttonPrevMonth.click();
        }
        dateStartTaxi.shouldBe(Condition.visible).click();
        hoursOption.selectOption(startParts[0]);
        minutesOption.selectOption(startParts[1]);

        taxiDateEnd.click();
        taxiDateStart.click();
        if (!dateStartTaxi.is(Condition.visible)) {
            buttonPrevMonth.click();
        }
        dateEndTaxi.shouldBe(Condition.visible).click();
        hoursOption.selectOption(endParts[0]);
        minutesOption.selectOption(endParts[1]);
    }
}
