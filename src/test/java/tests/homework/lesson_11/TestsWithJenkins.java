package tests.homework.lesson_11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class TestsWithJenkins extends TestBaseForJenkinsTests {

    private static final String REPOSITORY = "agzamovQA/qa_guru_40_HW";
    private static final int ISSUE = 1;

    @Test
    @DisplayName("Успешный поиск Issue в репозитории")
    public void testAnnotatedStepWithAllure() {
        WebSteps steps = new WebSteps();

        steps.openMainPage()
                .searchForRepository(REPOSITORY)
                .clickOnRepositoryLink(REPOSITORY)
                .openIssueTab()
                .shouldSeeIssueWithNumber(ISSUE)
                .takeScreenshot();

    }
}
