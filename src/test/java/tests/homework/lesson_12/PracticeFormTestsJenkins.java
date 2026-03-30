package tests.homework.lesson_12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestDataFaker;

import static io.qameta.allure.Allure.step;

public class PracticeFormTestsJenkins extends TestBaseDemoQaJenkins {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();
    TestDataFaker testDataFaker = new TestDataFaker();

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void fillPositiveAllFormTest()
    {
        step("Open registration form ", () -> {
            practiceFormPages.openPage();
        });

        step("Fill registration form ", () -> {
            practiceFormPages.setFirstName(testDataFaker.firstName)
                .setLastName(testDataFaker.lastName)
                .setUserEmail(testDataFaker.userEmail)
                .setUserGender(testDataFaker.userGender)
                .setUserNumber(testDataFaker.userNumber)
                .setBirthDate(testDataFaker.dateOfBirth,testDataFaker.monthOfBirth, testDataFaker.yearOfBirth)
                .setSubject(testDataFaker.userSubjectChoice)
                .setUserHobbies(testDataFaker.userHobbies)
                .setUserAdress(testDataFaker.userHomeAdress)
                .uploadUserPhoto(testDataFaker.userPhoto)
                .selectState(testDataFaker.userState)
                .selectCity(testDataFaker.userCity)
                .submitForm();
        });

        step("Check registration form results", () -> {
            practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", testDataFaker.firstName + " " + testDataFaker.lastName)
                .verifyContentInTable("Student Email", testDataFaker.userEmail)
                .verifyContentInTable("Gender", testDataFaker.userGender)
                .verifyContentInTable("Mobile", testDataFaker.userNumber)
                .verifyContentInTable("Date of Birth", testDataFaker.dateOfBirth + " " + testDataFaker.monthOfBirth + "," + testDataFaker.yearOfBirth)
                .verifyContentInTable("Subjects", testDataFaker.userSubjectChoice)
                .verifyContentInTable("Hobbies", testDataFaker.userHobbies)
                .verifyContentInTable("Picture", testDataFaker.userPhoto)
                .verifyContentInTable("Address", testDataFaker.userHomeAdress)
                .verifyContentInTable("State and City", testDataFaker.userState + " " + testDataFaker.userCity);
        });
    }
}
