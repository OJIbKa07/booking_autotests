package tests.notValid;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class NotValidTest {

    void carRentalSearch() {
        open("/");
        //pageReload();
        $("a#cars").click();
        //pageReload();
        $("[placeholder='Аэропорт, город или станция'").should(appear).click();
        $("[placeholder='Аэропорт, город или станция'").sendKeys("Рим");
        $(byTagAndText("div", "Аэропорт Рим Фьюмичино (FCO)")).shouldBe(visible).click();
        LocalDate today = LocalDate.now();
        LocalDate checkInDate = today.plusDays(2);
        LocalDate checkOutDate = checkInDate.plusDays(3);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String checkInStr = checkInDate.format(formatter);
        String checkOutStr = checkOutDate.format(formatter);
        $("[aria-label='Выберите дату получения'").click();
        $("span[data-date='" + checkInStr + "']").shouldBe(Condition.visible).click();
        $("[aria-label='Время взятия автомобиля'").click();
        $("[name='pick-up-time']").selectOption("15:00");
        $("[aria-label='Выберите дату возврата'").click();
        $("span[data-date='" + checkOutStr + "']").shouldBe(Condition.visible).click();
        $("[aria-label='Время возврата автомобиля'").click();
        $("[name='pick-up-time']").selectOption("19:00");
        $("[name='drivers-age']").click();
        $("[name='drivers-age-value']").setValue("22");
        $$("button").findBy(Condition.text("Поиск")).click();
        $("[data-testid='property-card']").should(appear);
        Selenide.closeWebDriver();
    }
}
