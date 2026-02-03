import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckSearchTests {

    @Test
    void duckDuckQaGuruHardestSearchTest() {
        open("https://duckduckgo.com/");
        $("#searchbox_input").setValue("QA Guru").pressEnter();
        $("#web_content_wrapper").shouldHave(text("qa.guru"));
    }
}
