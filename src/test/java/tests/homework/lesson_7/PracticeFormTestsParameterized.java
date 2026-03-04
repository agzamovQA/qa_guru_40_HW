package tests.homework.lesson_7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import tests.homework.demoqa.TestBase;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestDataFaker;

public class PracticeFormTestsParameterized extends TestBase {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();
    TestDataFaker testDataFaker = new TestDataFaker();

    @Tag("Regress")
    @ParameterizedTest(name = "Проверка полной анкеты с Хобби - {0}")
    @ValueSource(strings = {
            "Sports", "Reading", "Music"
    })
    void fillPositiveAllFormTest(String userHobbiesParametr)
    {
        practiceFormPages.openPage()
                .setFirstName(testDataFaker.firstName)
                .setLastName(testDataFaker.lastName)
                .setUserEmail(testDataFaker.userEmail)
                .setUserGender(testDataFaker.userGender)
                .setUserNumber(testDataFaker.userNumber)
                .setBirthDate(testDataFaker.dateOfBirth,testDataFaker.monthOfBirth, testDataFaker.yearOfBirth)
                .setSubject(testDataFaker.userSubjectChoice)
                .setUserHobbies(userHobbiesParametr)
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
                .verifyContentInTable("Hobbies", userHobbiesParametr)
                .verifyContentInTable("Picture", testDataFaker.userPhoto)
                .verifyContentInTable("Address", testDataFaker.userHomeAdress)
                .verifyContentInTable("State and City", testDataFaker.userState + " " + testDataFaker.userCity);
    }

    @Tag("Smoke")
    @ParameterizedTest(name = "Регистрируется пользователь с именем {0} и почтой {3}")
    @CsvSource(value = {
            "Agzamurai | Vi | agzamurai@ya.ru | Night City, st ChinaTown, appart 77",
            "Pushinka | Pero | pushinka@ya.ru | Night City, st ChinaTown, appart 37"
    }, delimiter = '|')
    void fillRequiredFormTest(String userFirstNameParametr,String userLastNameParametr, String userEmailParametr, String userAdressParametr)
    {
        practiceFormPages.openPage()
                .setFirstName(userFirstNameParametr)
                .setLastName(userLastNameParametr)
                .setUserEmail(userEmailParametr)
                .setUserGender(testDataFaker.userGender)
                .setUserNumber(testDataFaker.userNumber)
                .setUserAdress(userAdressParametr)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", userFirstNameParametr + " " + userLastNameParametr)
                .verifyContentInTable("Student Email", userEmailParametr)
                .verifyContentInTable("Gender", testDataFaker.userGender)
                .verifyContentInTable("Mobile", testDataFaker.userNumber)
                .verifyContentInTable("Address", userAdressParametr);
    }

    @Tag("Smoke")
    @ParameterizedTest(name = "Регистрируется пользователь с именем {0} и почтой {1}")
    @CsvFileSource(resources = "/test_data/usersForFillFormDataTests.csv" , delimiter = '|')
    void fillRequiredFormTestWithCsvFile(String userFirstNameParametr,String userLastNameParametr, String userEmailParametr, String userAdressParametr)
    {
        practiceFormPages.openPage()
                .setFirstName(userFirstNameParametr)
                .setLastName(userLastNameParametr)
                .setUserEmail(userEmailParametr)
                .setUserGender(testDataFaker.userGender)
                .setUserNumber(testDataFaker.userNumber)
                .setUserAdress(userAdressParametr)
                .submitForm();

        practiceFormPages.verifyOpenedTable()
                .verifyContentInTable("Student Name", userFirstNameParametr + " " + userLastNameParametr)
                .verifyContentInTable("Student Email", userEmailParametr)
                .verifyContentInTable("Gender", testDataFaker.userGender)
                .verifyContentInTable("Mobile", testDataFaker.userNumber)
                .verifyContentInTable("Address", userAdressParametr);
    }

}