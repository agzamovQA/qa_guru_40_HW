package tests.homework.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.testdata.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void successfulFillRegistrationFormTest() {
        open("");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
        """);
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#userEmail").setValue(TestData.userEmail);
        $("#genterWrapper").$(byText(TestData.userGender)).click();
        $("#userNumber").setValue(TestData.userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText(TestData.yearOfBirth)).click();
        $(".react-datepicker__month-select").$(byText(TestData.monthOfBirth)).click();
        $(".react-datepicker__month").$(byText(TestData.dateOfBirth)).click();

        //Выбор предмета с обычном вводом текста
        $("#subjectsInput").setValue(TestData.userSubjectChoice).pressEnter();

//        Выбор предмета с вводом первой буквы и выбором совпадения из меню с автокомплитом
//        $("#subjectsInput").setValue("E");
//        $(".subjects-auto-complete__menu").$(byText(userSubjectChoice)).click();

        $("#hobbiesWrapper").$(byText(TestData.userHobbies)).click();
        $("#currentAddress").setValue(TestData.userHomeAdress);
        $("#uploadPicture").uploadFromClasspath(TestData.userPhoto);

        $("#state").click();
        $("#state").$(byText(TestData.userState)).click();
        $("#city").click();
        $("#city").$(byText(TestData.userCity)).click();

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        //Проверяем соответствие ключ-значение
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(TestData.firstName + " " + TestData.lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(TestData.userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(TestData.userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(TestData.userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(TestData.dateOfBirth + " " + TestData.monthOfBirth + "," + TestData.yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(TestData.userSubjectChoice));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(TestData.userHobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Jhonny_Silverhand.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(TestData.userHomeAdress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(TestData.userState + " " + TestData.userCity));

    }

    @Test
    @DisplayName("[Positive] Fill only required fields")
    void fillOnlyRequiredForm () {

        open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#genterWrapper").$(byText(TestData.userGender)).click();
        $("#userNumber").setValue(TestData.userNumber);

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(TestData.firstName + " " + TestData.lastName));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(TestData.userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(TestData.userNumber));

    }

    @Test
    @DisplayName("[Negative] Fill form with email without @")
    void unvalidUserEmail () {

        open("");
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        $("#firstName").setValue(TestData.firstName);
        $("#lastName").setValue(TestData.lastName);
        $("#genterWrapper").$(byText(TestData.userGender)).click();
        $("#userNumber").setValue(TestData.userNumber);
        $("#userEmail").setValue("samurai2023nc.com");

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldNot(visible);

    }
}
