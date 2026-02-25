package tests.homework.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.homework.demoqa.TestBase;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestDataFaker;

public class PracticeFormTestsJavaFaker extends TestBase {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void fillPositiveAllFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(TestDataFaker.firstName)
                .setLastName(TestDataFaker.lastName)
                .setUserEmail(TestDataFaker.userEmail)
                .setUserGender(TestDataFaker.userGender)
                .setUserNumber(TestDataFaker.userNumber)
                .setBirthDate(TestDataFaker.dateOfBirth,TestDataFaker.monthOfBirth, TestDataFaker.yearOfBirth)
                .setSubject(TestDataFaker.userSubjectChoice)
                .setUserHobbies(TestDataFaker.userHobbies)
                .setUserAdress(TestDataFaker.userHomeAdress)
                .uploadUserPhoto(TestDataFaker.userPhoto)
                .selectState(TestDataFaker.userState)
                .selectCity(TestDataFaker.userCity)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", TestDataFaker.firstName + " " + TestDataFaker.lastName)
                .verifyContentInTable("Student Email", TestDataFaker.userEmail)
                .verifyContentInTable("Gender", TestDataFaker.userGender)
                .verifyContentInTable("Mobile", TestDataFaker.userNumber)
                .verifyContentInTable("Date of Birth", TestDataFaker.dateOfBirth + " " + TestDataFaker.monthOfBirth + "," + TestDataFaker.yearOfBirth)
                .verifyContentInTable("Subjects", TestDataFaker.userSubjectChoice)
                .verifyContentInTable("Hobbies", TestDataFaker.userHobbies)
                .verifyContentInTable("Picture", TestDataFaker.userPhoto)
                .verifyContentInTable("Address", TestDataFaker.userHomeAdress)
                .verifyContentInTable("State and City", TestDataFaker.userState + " " + TestDataFaker.userCity);
    }

    @Test
    @DisplayName("[Positive] Fill only required fields")
    void fillRequiredFormTest()
    {
        practiceFormPages.openPage()
                .setFirstName(TestDataFaker.firstName)
                .setLastName(TestDataFaker.lastName)
                .setUserGender(TestDataFaker.userGender)
                .setUserNumber(TestDataFaker.userNumber)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", TestDataFaker.firstName + " " + TestDataFaker.lastName)
                .verifyContentInTable("Gender", TestDataFaker.userGender)
                .verifyContentInTable("Mobile", TestDataFaker.userNumber);
    }

    @Test
    @DisplayName("[Negative] Fill form with email without @")
    void fillNegativeRequiredFormTest()
    {
            practiceFormPages.openPage()
                    .setFirstName(TestDataFaker.firstName)
                    .setLastName(TestDataFaker.lastName)
                    .setUserGender(TestDataFaker.userGender)
                    .setUserNumber("")
                    .submitForm();

            practiceFormPages.verifyResultNegativeOpenedTable();
    }
}