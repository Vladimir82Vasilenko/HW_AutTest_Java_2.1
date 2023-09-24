package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormApplicDebCardNegativeTest {
    @Test
    void shouldBeNegativeTestNullNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue(" ");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldBeNegativeTestLatinLettersNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Vladimir Ivanov");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldBeNegativeTestNumbersNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("234 789");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldBeNegativeTestSymbolNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("@@#@$$##@");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldBeNegativeTestPhoneNumberWith8() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("89210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'].input_invalid .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void shouldBeNegativeTestPhoneNumberWithoutPlus() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'].input_invalid .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void shouldBeNegativeTestPhoneNumberIncorrect() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("+7921000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        form.$("[data-test-id='phone'].input_invalid .input__sub") .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, " +
                "например, +79012345678."));
    }
    @Test
    void shouldBeNegativeTestWithoutCheckbox() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".button__content").click();
        form.$("[data-test-id='agreement'].input_invalid") .shouldBe(visible); // Ошибка при не выставлении
                                                                                      // галочки в чекбоксе
    }
}
