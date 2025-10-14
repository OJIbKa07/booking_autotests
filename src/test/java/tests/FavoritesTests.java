package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.FavoritesPage;
import pages.MainPage;

public class FavoritesTests extends TestBase {
    MainPage mainPage = new MainPage();
    FavoritesPage favoritesPage = new FavoritesPage();

    @Tags({
            @Tag("favorites"),@Tag("smoke")
    })
    @Test
    void checkMainPageSectionsTest() {
        mainPage
                .openPage()
                .pageReload();
        mainPage
                .scrollToDealsForWeekends()
                .findFavoritesAndSave()
                .addToFavorites()
                .goToFavorites();
        favoritesPage
                .setExpectedCardName(mainPage.getSavedCardName())
                .checkAddToFavorites();
    }
}
