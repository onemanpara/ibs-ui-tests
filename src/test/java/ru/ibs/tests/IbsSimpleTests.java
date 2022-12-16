package ru.ibs.tests;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import ru.ibs.components.FeedBackFormInFooter;
import ru.ibs.components.HeaderMenu;
import ru.ibs.components.Search;
import ru.ibs.data.Cities;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.ibs.data.DataGenerator;
import ru.ibs.helpers.DriverUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.logging.LogType.BROWSER;
import static ru.ibs.components.FeedBackFormInFooter.*;

public class IbsSimpleTests extends TestBase {

    FeedBackFormInFooter feedBackFormInFooter = new FeedBackFormInFooter();

    DataGenerator dataGenerator = new DataGenerator();

    Search search = new Search();

    HeaderMenu headerMenu = new HeaderMenu();

    @ParameterizedTest(name = "Адрес филиала города {0} виден в публичной части")
    @EnumSource(Cities.class)
    void checkFilialsAddress(Cities city) {
        step("Открываем страницу контактов", () -> {
            open("https://ibs.ru/contacts/");
        });
        step("Кликаем на филиал города" + city + "В табах карты", () -> {
            $(".contacts-tags").$(byText(city.getCityName())).click();
        });
        step("Проверяем, что название выбранного филиала отображается поверх карты", () -> {
            $$(".js-tab-content").
                    filterBy(text(city.getCityName()))
                    .first().shouldBe(visible);
        });
        step("Проверяем, что адрес выбранного филиала отображается поверх карты", () -> {
            $$(".js-tab-content").
                    filterBy(text(city.getCityAddress()))
                    .first().shouldBe(visible);
        });
    }

    @ParameterizedTest(name = "Отраслевое решение {0} отображается на главной странице и кликабельно")
    @ValueSource(strings =
            {"Агропромышленность", "Газовая индустрия",
                    "Государственные программы", "Металлургия",
                    "Нефть и химия", "Промышленность и транспорт",
                    "Телеком", "ТНП и ритейл",
                    "Финансовые институты", "Энергетика и ЖКХ"})
    void checkMainSolutions(String solution) {
        step("Открываем главную страницу", () -> {
            open("https://ibs.ru/");
        });
        step("Кликаем на отраслевое решение с названием" + solution, () -> {
            $(".main-solutions-links").$(byText(solution)).click(ClickOptions.usingJavaScript());
        });
        step("Проверяем, что h1 на открывшейся странице соответствует" + solution, () -> {
            $(".top--media").$("h1").shouldHave(text(solution)).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("Позитивный тест формы обратной связи")
    void feedBackFormPositiveTest() {
        step("Открываем страницу с формой обратной связи", () -> {
            open("https://ibs.ru/about/");
        });
        step("Заполняем форму данными", () -> {
            feedBackFormInFooter
                    .setName(dataGenerator.fullName)
                    .setCompanyName(dataGenerator.companyName)
                    .setPhone(dataGenerator.phone)
                    .setEmail(dataGenerator.email)
                    .setMessage(dataGenerator.messageText);
        });
        step("Проверяем, что кнопка \"Отправить\" неактивна", () -> {
            feedBackFormInFooter.checkSubmitButtonIsDisabled();
        });
        step("Кликаем на чек-бокс согласия обработки перс-данных", () -> {
            feedBackFormInFooter.checkBoxAgreeClick();
        });
        step("Отправляем форму обратной связи", () -> {
            feedBackFormInFooter.clickSubmitButton();
        });
        step("Проверяем, что появился текст об успешной отправке", () -> {
            feedBackFormInFooter.checkResultMessage();
        });
    }

    @Test
    @DisplayName("Негативный тест формы обратной связи")
    void feedBackFormNegativeTest() {
        step("Открываем страницу с формой обратной связи", () -> {
            open("https://ibs.ru/about/");
        });
        step("Заполняем форму данными", () -> {
            feedBackFormInFooter
                    .setName(dataGenerator.fullName)
                    .setCompanyName(dataGenerator.companyName)
                    .setPhone(dataGenerator.phone)
                    .setEmail(dataGenerator.email);
        });
        step("Очищаем все поля ввода", () -> {
            feedBackFormInFooter.clearAllFields();
        });
        step("Проверяем, что рядом с полями появились уведомления об ошибке", () -> {
            feedBackFormInFooter
                    .checkErrorEmptyField(getNameInput())
                    .checkErrorEmptyField(getCompanyInput())
                    .checkErrorEmptyField(getPhoneInput())
                    .checkErrorEmptyField(getEmailInput());
        });
        step("Заполняем поля Email и Телефон не полностью", () -> {
            feedBackFormInFooter
                    .setEmail(dataGenerator.invalidEmail)
                    .setPhone(dataGenerator.invalidPhone);
        });
        step("Проверяем, что рядом с полями появились уточняющие уведомления", () -> {
            feedBackFormInFooter
                    .checkErrorFormatEmailField(getEmailInput())
                    .checkErrorNotFullPhoneField(getPhoneInput());
        });
        step("Проверяем, что кнопка \"Отправить\" неактивна", () -> {
            feedBackFormInFooter.checkSubmitButtonIsDisabled();
        });
    }

    @DisplayName("Поиск по сайту")
    @Test
    void searchTest() {
        step("Открываем главную страницу", () -> {
            open("https://ibs.ru/");
        });
        step("Кликаем по иконке поиска в хедере", () -> {
            search.clickSearchButtonInHeader();
        });
        step("Вводим поисковой запрос и жмём Enter", () -> {
            search.useSearch(dataGenerator.companyName);
        });
        step("Проверяем, что на странице выдачи в плейсхолдере искомый запрос", () -> {
            search.checkInputValueAfterSearch(dataGenerator.companyName);
        });
    }

    @DisplayName("Теги в меню поиска видимы")
    @Test
    void searchTagsIsVisibleTest() {
        step("Открываем главную страницу", () -> {
            open("https://ibs.ru/");
        });
        step("Кликаем на иконку поиска в хедере", () -> {
            search.clickSearchButtonInHeader();
        });
        step("Проверяем, что популярные и недавние запросы видимы", () -> {
            search
                    .popularRequestsIsVisible()
                    .lastRequestsIsVisible();
        });
    }

    @DisplayName("Социальные сети отображаются в бургер-меню")
    @Test
    void socialNetworkInBurgerMenuIsVisible() {
        step("Открываем главную страницу", () -> {
            open("https://ibs.ru/");
        });
        step("Открываем бургер-меню", () -> {
            headerMenu.burgerMenuClick();
        });
        step("Проверяем, что социальные сети видимы", () -> {
            headerMenu.checkSocialNetworksInBurgerMenuIsVisible();
        });
        System.out.println(Selenide.getWebDriverLogs(BROWSER));
    }

    @Test
    @DisplayName("В консоли нет ошибок")
    void consoleShouldNotHaveErrorsTest() {
        step("Открываем главную страницу'", () -> {
            open("https://ibs.ru/");
        });
        step("Проверяем, что в логах консоли нет ошибок (строк с \"SEVERE\")", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";
            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}