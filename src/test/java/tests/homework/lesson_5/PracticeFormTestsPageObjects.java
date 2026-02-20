package tests.homework.lesson_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.homework.demoqa.TestBase;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestData;

public class PracticeFormTestsPageObjects extends TestBase {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void fillPositiveAllFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(TestData.firstName)
                .setLastName(TestData.lastName)
                .setUserEmail(TestData.userEmail)
                .setUserGender(TestData.userGender)
                .setUserNumber(TestData.userNumber)
                .setBirthDate(TestData.dateOfBirth,TestData.monthOfBirth, TestData.yearOfBirth)
                .setSubject(TestData.userSubjectChoice)
                .setUserHobbies(TestData.userHobbies)
                .setUserAdress(TestData.userHomeAdress)
                .uploadUserPhoto(TestData.userPhoto)
                .selectState(TestData.userState)
                .selectCity(TestData.userCity)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", TestData.firstName + " " + TestData.lastName)
                .verifyContentInTable("Student Email", TestData.userEmail)
                .verifyContentInTable("Gender", TestData.userGender)
                .verifyContentInTable("Mobile", TestData.userNumber)
                .verifyContentInTable("Date of Birth", TestData.dateOfBirth + " " + TestData.monthOfBirth + "," + TestData.yearOfBirth)
                .verifyContentInTable("Subjects", TestData.userSubjectChoice)
                .verifyContentInTable("Hobbies", TestData.userHobbies)
                .verifyContentInTable("Picture", TestData.userPhoto)
                .verifyContentInTable("Address", TestData.userHomeAdress)
                .verifyContentInTable("State and City", TestData.userState + " " + TestData.userCity);
    }

    @Test
    @DisplayName("[Positive] Fill only required fields")
    void fillRequiredFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(TestData.firstName)
                .setLastName(TestData.lastName)
                .setUserGender(TestData.userGender)
                .setUserNumber(TestData.userNumber)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", TestData.firstName + " " + TestData.lastName)
                .verifyContentInTable("Gender", TestData.userGender)
                .verifyContentInTable("Mobile", TestData.userNumber);
    }

    @Test
    @DisplayName("[Negative] Fill form with email without @")
    void fillNegativeRequiredFormTest()
    {
            practiceFormPages.openPage()
                    .setFirstName(TestData.firstName)
                    .setLastName(TestData.lastName)
                    .setUserGender(TestData.userGender)
                    .setUserNumber("")
                    .submitForm();

            practiceFormPages.verifyResultNegativeOpenedTable();
    }
}