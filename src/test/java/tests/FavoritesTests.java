package tests;

import helpers.allure.annotations.Layer;
import helpers.allure.annotations.Microservice;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.FavoritesPage;
import pages.MainPage;

@Owner("oPalushina")
@Layer("ui")
@Microservice("Favorites")
@Tag("ui")
@Epic("Избранное")
@Feature("Добавление товаров в избранное (UI)")
@Story("Проверка добавления карточки в избранное с главной страницы")
@DisplayName("UI: Добавление карточки в избранное с главной страницы")
public class FavoritesTests extends TestBase {
    MainPage mainPage = new MainPage();
    FavoritesPage favoritesPage = new FavoritesPage();

    @Tags({
            @Tag("favorites"),@Tag("smoke")
    })
    @Test
    @Story("Проверка разделов главной страницы и добавление товара в избранное")
    @DisplayName("Проверка добавления товара в избранное через главную страницу")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Открывает главную страницу, добавляет карточку в избранное и проверяет её наличие на странице избранного")
    void checkMainPageSectionsTest() {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .scrollToDealsForWeekends()
                .findFavoritesAndSave()
                .addToFavorites()
                .goToFavorites();
        favoritesPage.checkAddToFavorites(mainPage.getSavedCardName());
    }
}
