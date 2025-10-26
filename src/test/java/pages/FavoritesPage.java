package pages;


import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class FavoritesPage {
    private String expectedCardName;
    private ElementsCollection wishlistCollection = $$("div");

    @Step("Проверяем, что элемент добавился в избранное")
    public FavoritesPage checkAddToFavorites(String expectedCardName) {
        wishlistCollection.findBy(text(expectedCardName))
                .shouldBe(visible);

        return this;
    }
}
