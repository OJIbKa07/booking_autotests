package pages;

import com.codeborne.selenide.SelenideElement;
import data.FaqSection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HelpPage {

    private final SelenideElement
            helpHeader = $("h1"),
            faqHeader = $("h2"),
            cookieWindow = $("#onetrust-accept-btn-handler");

    private final String
            faqHeaderName = "Frequently asked questions",
            headerHelpCenter = "Welcome to the Help Center";

    private SelenideElement sectionButton, panel;
    private String panelId;


    @Step("Проверяем, что страница Help Center загружена")
    public HelpPage checkHelpCenterLoaded() {
        helpHeader.shouldBe(visible)
                .shouldHave(text(headerHelpCenter));
        return this;
    }

    @Step("Проверяем, что блок 'Часто задаваемые вопросы' отображается")
    public HelpPage checkFaqBlockVisible() {
        faqHeader.shouldHave(text(faqHeaderName));
        return this;
    }

    @Step("Проверяем, что все секции FAQ существуют и содержат кнопки")
    public HelpPage checkAllFaqSections() {
        for (FaqSection section : FaqSection.values()) {
            checkFaqSection(section);
        }
        return this;
    }

    @Step("Проверяем секцию FAQ: {section}")
    private void checkFaqSection(FaqSection section) {
        sectionButton = $$("button[data-testid='hc-faq-trigger']")
                .findBy(text(section.getSectionName()));
        sectionButton.shouldBe(visible).click();
        panelId = sectionButton.getAttribute("aria-controls");
        panel = $("[id='" + panelId + "']");
        panel.shouldBe(visible);
        panel.$$("button").shouldHave(sizeGreaterThan(0));
    }

    @Step("Закрыть баннеры и куки при наличии")
    public void pageReload() {
        sleep(10000);
        if (cookieWindow.exists()) {
            cookieWindow.click();
        }
    }
}
