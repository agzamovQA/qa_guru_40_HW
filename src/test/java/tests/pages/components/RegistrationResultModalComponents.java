package tests.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultModalComponents {
    SelenideElement tableContent = $(".modal-content");
    SelenideElement tableResult = $(".table-responsive");

    //Проверяем, что таблица открылась с определенным заголовком
    public void verifyOpenedTable () {
         tableContent.shouldHave(text("Thanks for submitting the form"));
    }

    //Провереям ответ таблицы (ключ-значение)
    public void verifyContentInTable (String key, String value) {
        tableResult.$(byText(key)).parent().shouldHave(text(value));
    }

    //Провереям, что таблица не открылась.
    public void verifyNegativeOpenedTable () {
        tableContent.shouldNot(visible);
    }
}
