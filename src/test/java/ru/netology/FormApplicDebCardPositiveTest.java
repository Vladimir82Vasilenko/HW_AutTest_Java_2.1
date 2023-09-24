package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormApplicDebCardPositiveTest {
    @Test
    void shouldBePositiveTestWithOnlyName() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldBePositiveTestWithNameSurname() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldBePositiveTestWithSurnameHyphen() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$(".input__control[type=text").setValue("Владимир Иванов-Симонов");
        form.$(".input__control[type=tel").setValue("+79210000000");
        form.$(".checkbox__box").click();
        form.$(".button__content").click();
        $("[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! " +
                "Наш менеджер свяжется с вами в ближайшее время."));
    }
}
