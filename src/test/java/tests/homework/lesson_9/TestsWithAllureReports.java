package tests.homework.lesson_9;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class TestsWithAllureReports extends TestBaseForAllureTests {

    private static final String REPOSITORY = "agzamovQA/qa_guru_40_HW";
    private static final int ISSUE = 1;

    @Test
    @DisplayName("Чистый Selenide")
    public void simpleIssueTest() {

        open("");

        $(".input-button").click();
        $("#query-builder-test").sendKeys(REPOSITORY);
        $("#query-builder-test").submit();

        $(linkText(REPOSITORY)).click();
        $("[data-content='Issues']").click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Лямбда шаги через step")
    public void testLambdaStep() {

        step("Открываем главную страницу", () -> {
            open("");
        });

        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".input-button").click();
            $("#query-builder-test").sendKeys(REPOSITORY);
            $("#query-builder-test").submit();
        });

        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issues", () -> {
            $("[data-content='Issues']").click();
        });

        step("Проверяем наличие Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void testAnnotatedStep() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssueTab();
        steps.shouldSeeIssueWithNumber(ISSUE);
        steps.takeScreenshot();

    }
}