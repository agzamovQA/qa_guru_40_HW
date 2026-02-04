import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckSearchTests {

    @Test
    void duckDuckQaGuruSimpleSearchTest() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("QA Guru").pressEnter();
        $("#web_content_wrapper").shouldHave(text("qa.guru"));
    }

    @Test
    void duckDuckQaGuruSearchTest() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("QA Guru").pressEnter();
        $("#web_content_wrapper").shouldHave(text("qa.guru"));
    }
}
