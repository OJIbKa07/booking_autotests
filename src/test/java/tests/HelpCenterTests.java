package tests;

import data.FaqSection;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.HelpPage;
import pages.MainPage;

public class HelpCenterTests extends TestBase {
    MainPage mainPage = new MainPage();
    HelpPage helpPage = new HelpPage();

    @Test
    @Tags({
            @Tag("helpCenter"),@Tag("smoke")
    })
    @DisplayName("Open Help Center without login")
    @Owner("oPalushina")
    @Severity(SeverityLevel.TRIVIAL)
    void openHelpCenterTest() {
        mainPage
                .openPage()
                .pageReload();
        mainPage.openHelpCenter();
        helpPage
                .checkHelpCenterLoaded()
                .checkFaqBlockVisible()
                .checkAllFaqSections();
    }
}
