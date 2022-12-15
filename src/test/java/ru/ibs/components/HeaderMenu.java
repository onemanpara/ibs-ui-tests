package ru.ibs.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class HeaderMenu {

    private static SelenideElement burgerMenuButton = $(".header-burger");
    private static ElementsCollection socialsLink = $$(".navigation-socials a");


    public HeaderMenu checkSocialNetworksInIsVisible() {
        Integer sourceSocials = socialsLink.size();
        assertThat(sourceSocials).isEqualTo(5);
        for (int i = 0; i < sourceSocials; i++) {
            socialsLink.get(i).shouldBe(visible);
        }
        return this;
    }

    public HeaderMenu burgerMenuClick() {
        burgerMenuButton.click();
        return this;
    }
}
