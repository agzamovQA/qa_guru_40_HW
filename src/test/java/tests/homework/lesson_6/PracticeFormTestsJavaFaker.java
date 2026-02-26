package tests.homework.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.homework.demoqa.TestBase;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestDataFaker;

public class PracticeFormTestsJavaFaker extends TestBase {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();
    TestDataFaker testDataFaker = new TestDataFaker();

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void fillPositiveAllFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(testDataFaker.firstName)
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
    }

    @Test
    @DisplayName("[Positive] Fill only required fields")
    void fillRequiredFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(testDataFaker.firstName)
                .setLastName(testDataFaker.lastName)
                .setUserGender(testDataFaker.userGender)
                .setUserNumber(testDataFaker.userNumber)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", testDataFaker.firstName + " " + testDataFaker.lastName)
                .verifyContentInTable("Gender", testDataFaker.userGender)
                .verifyContentInTable("Mobile", testDataFaker.userNumber);
    }

    @Test
    @DisplayName("[Negative] Fill form with email without @")
    void fillNegativeRequiredFormTest()
    {
            practiceFormPages.openPage()
                    .setFirstName(testDataFaker.firstName)
                    .setLastName(testDataFaker.lastName)
                    .setUserGender(testDataFaker.userGender)
                    .setUserNumber("")
                    .submitForm();

            practiceFormPages.verifyResultNegativeOpenedTable();
    }
}