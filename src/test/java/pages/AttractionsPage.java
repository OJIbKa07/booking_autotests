package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import pages.components.CalendarComponent;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttractionsPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    private SelenideElement
            attractionsNav = $("a#attractions"),
            discountWindowRu = $("button[aria-label='Скрыть меню входа в аккаунт.']"),
            discountWindowUs = $("button[aria-label='Dismiss sign-in info.']"),
            cookieWindow = $("#onetrust-accept-btn-handler"),
            destinationInput = $("input[data-testid='search-input-field']"),
            searchResult = $("div#attr-search-results-page-main-content");

    private ElementsCollection
            searchBar = $$("[data-testid='search-bar-result']"),
            buttonCollection = $$("button"),
            cardCollection = $$("[data-testid*='card']"),
            labelCollection = $$("label");

    @Step("Открыть раздел выбора досуга")
    public AttractionsPage openAttractionsPage() {
        attractionsNav.click();

        return this;
    }

    @Step("Закрыть баннеры и куки при наличии")
    public void pageReload() {
        sleep(3000);
        if (discountWindowRu.exists()) {
            discountWindowRu.click();
        }
        if (discountWindowUs.exists()) {
            discountWindowUs.click();
        }
        if (cookieWindow.exists()) {
            cookieWindow.click();
        }
    }

    @Step("Ввести место, где планируется искать досуг")
    public AttractionsPage enteringPlace() {
        destinationInput.sendKeys("London");
        sleep(1000);
        searchBar.first().click();

        return this;
    }

    @Step("Ввести дату")
    public AttractionsPage enteringDate() {
        calendarComponent.setDate();

        return this;
    }

    @Step("Поиск результатов")
    public AttractionsPage checkPrices() {
        buttonCollection.findBy(text("Search")).click();

        return this;
    }

    @Step("Проверить, что меню с результатами появилось")
    public AttractionsPage checkPageResults() {
        searchResult.should(appear);

        return this;
    }

    @Step("Проверить, что карточки с результатами появились")
    public AttractionsPage checkCardResults() {
        cardCollection.first().should(appear);

        return this;
    }

    @Step("Отсортировать цены по возрастанию")
    public AttractionsPage clickLowPrices() {
        labelCollection.findBy(text("Lowest price")).click();

        return this;
    }

    @Step("Проверить, что цены отсортированы по возрастанию")
    public AttractionsPage checkSort() {
        List<Integer> prices = $$("[data-testid='activity-card']")
                .filter(Condition.visible)
                .stream()
                .map(card -> {
                    String priceText = card.$(By.cssSelector("[data-testid='price-and-discounted-price']")).getText();
                    return extractPrice(priceText);
                })
                .filter(p -> p > 0)
                .collect(Collectors.toList());

        assertTrue(isSortedAscending(prices), "Цены не отсортированы по возрастанию!");

        return this;
    }

    private int extractPrice(String text) {
        try {
            String digits = text.replaceAll("[^0-9]", "");
            return digits.isEmpty() ? 0 : Integer.parseInt(digits);
        } catch (Exception e) {
            return 0;
        }
    }

    private boolean isSortedAscending(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }


}
