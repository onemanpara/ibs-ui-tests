package ru.ibs.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class Search {

    private static final SelenideElement
            searchInputInHeader = $("input[placeholder=\"Поиск по сайту\"]"),
            searchInputAtSearchPage = $(".search-page").$("input[placeholder=\"Поиск по сайту\"]"),
            searchButtonInHeader = $(".js-header-search"),
            firstPopularSearchRequest = $(".search-popular__items a"),
            firstLastSearchRequest = $(".search-last__items a");

    private static final ElementsCollection
            popularSearchRequests = $$(".search-popular__items a"),
            lastSearchRequests = $$(".search-last__items a");

    public Search useSearch(String searchText) {
        searchInputInHeader.setValue(searchText).pressEnter();
        return this;
    }

    public Search checkInputValueAfterSearch(String searchText) {
        searchInputAtSearchPage.shouldHave(attribute("value", searchText));
        return this;
    }

    public Search clickSearchButtonInHeader() {
        searchButtonInHeader.click();
        return this;
    }

    public Search popularRequestsIsVisible() {
        Integer size = popularSearchRequests.size();
        assertThat(size).isEqualTo(3);
        for (int i = 0; i < size; i++) {
            popularSearchRequests.get(i).shouldBe((visible));
        }
        return this;
    }

    public Search lastRequestsIsVisible() {
        Integer size = lastSearchRequests.size();
        assertThat(size).isEqualTo(3);
        for (int i = 0; i < size; i++) {
            lastSearchRequests.get(i).shouldBe((visible));
        }
        return this;
    }

}
