package ru.ibs.components;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class FeedBackFormInFooter {

    protected static final SelenideElement
            nameInput = $("#name"),
            companyInput = $("#company"),
            phoneInput = $("#phone"),
            emailInput = $("#email"),
            messageInput = $("#message"),
            submitButton = $("#feedback button[type=submit]"),
            checkBoxAgree = $("input[name=agree]"),
            resultMessage = $(".feedback__result");

    public FeedBackFormInFooter setName(String name) {
        nameInput.setValue(name);
        return this;
    }

    public FeedBackFormInFooter setCompanyName(String company) {
        companyInput.setValue(company);
        return this;
    }

    public FeedBackFormInFooter setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public FeedBackFormInFooter setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public FeedBackFormInFooter setMessage(String message) {
        messageInput.setValue(message);
        return this;
    }

    public FeedBackFormInFooter checkErrorEmptyField(SelenideElement fieldSelector) {
        fieldSelector.sibling(0).shouldHave(text("Заполните поле"));
        return this;
    }

    public FeedBackFormInFooter checkErrorNotFullPhoneField(SelenideElement fieldSelector) {
        fieldSelector.sibling(0).shouldHave(text("Напишите телефон полностью"));
        return this;
    }

    public FeedBackFormInFooter checkErrorFormatEmailField(SelenideElement fieldSelector) {
        fieldSelector.sibling(0).shouldHave(text("В адресе ошибка"));
        return this;
    }

    public FeedBackFormInFooter checkSubmitButtonIsDisabled() {
        submitButton.shouldHave(attribute("disabled", "true"));
        return this;
    }

    public FeedBackFormInFooter clickSubmitButton() {
        submitButton.click(ClickOptions.usingJavaScript());
        return this;
    }

    public FeedBackFormInFooter clearAllFields() {
        nameInput.clear();
        companyInput.clear();
        phoneInput.clear();
        emailInput.clear();
        messageInput.clear();
        return this;
    }

    public FeedBackFormInFooter checkBoxAgreeClick() {
        checkBoxAgree.click(ClickOptions.usingJavaScript());
        return this;
    }

    public FeedBackFormInFooter checkResultMessage() {
        resultMessage.shouldBe(visible);
        return this;
    }

    public static SelenideElement getNameInput() {
        return nameInput;
    }

    public static SelenideElement getCompanyInput() {
        return companyInput;
    }

    public static SelenideElement getPhoneInput() {
        return phoneInput;
    }

    public static SelenideElement getEmailInput() {
        return emailInput;
    }

}
