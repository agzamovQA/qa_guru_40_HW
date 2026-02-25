package tests.homework.demoqa;

import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;

public class TestBase {
    @BeforeAll
    static void setupSelenideConfig() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "2560x1440";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }
}
