package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormApplicDebCardNegativeTest {
    @Test
    void NegativeTestNullNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue(" ");
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void NegativeTestLatinLettersNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Vladimir Ivanov");
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void NegativeTestNumbersNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("234 789");
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void NegativeTestSymbolNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("@@#@$$##@");
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void NegativeTestPhoneNumberWith8() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("89210000000");
        //form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'] .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void NegativeTestPhoneNumberWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("79210000000");
        //form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'] .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void NegativeTestPhoneNumberIncorrect() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("+7921000");
        //form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'] .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void NegativeTestWithoutCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".button__content").click();
        form.$("[data-test-id='agreement'] .input_invalid") .isDisplayed(); // Ошибка при не выставлении
                                                                                      // галочки в чекбоксе
    }
}
