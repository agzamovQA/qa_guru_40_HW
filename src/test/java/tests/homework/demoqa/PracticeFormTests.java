package tests.homework.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

    String firstName = "Jhony";
    String lastName = "Silverhand";
    String userEmail = "samurai2023@nc.com";
    String userGender = "Male";
    String userNumber = "0202320770";
    String yearOfBirth = "2000";
    String monthOfBirth = "November";
    String dateOfBirth = "23";
    String userSubjectChoice = "English";
    String userHobbies = "Music";
    String userHomeAdress = "Night City, District Kabuki, Home 20, apart 77";
    String userState = "NCR";
    String userCity = "Noida";

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void successfulFillRegistrationFormTest() {
        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#userNumber").setValue(userNumber);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText(yearOfBirth)).click();
        $(".react-datepicker__month-select").$(byText(monthOfBirth)).click();
        $(".react-datepicker__month").$(byText(dateOfBirth)).click();

        //Выбор предмета с обычном вводом текста
        $("#subjectsInput").setValue(userSubjectChoice).pressEnter();

//        Выбор предмета с вводом первой буквы и выбором совпадения из меню с автокомплитом
//        $("#subjectsInput").setValue("E");
//        $(".subjects-auto-complete__menu").$(byText(userSubjectChoice)).click();

        $("#hobbiesWrapper").$(byText(userHobbies)).click();
        $("#currentAddress").setValue(userHomeAdress);
        $("#uploadPicture").uploadFromClasspath("Jhonny_Silverhand.jpg");

        $("#state").click();
        $("#state").$(byText(userState)).click();
        $("#city").click();
        $("#city").$(byText(userCity)).click();

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        //Проверяем соответствие ключ-значение
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dateOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(userSubjectChoice));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(userHobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("Jhonny_Silverhand.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(userHomeAdress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(userState + " " + userCity));

    }

    @Test
    @DisplayName("[Positive] Fill only required fields")
    void fillOnlyRequiredForm () {

        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#userNumber").setValue(userNumber);

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(userGender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));

    }

    @Test
    @DisplayName("[Negative] Fill form with email without @")
    void unvalidUserEmail () {

        open("/automation-practice-form");

        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(userGender)).click();
        $("#userNumber").setValue(userNumber);
        $("#userEmail").setValue("samurai2023nc.com");

        $("#submit").click();

        //Проверяем ответ в таблице
        $(".modal-header").shouldNot(visible);

    }
}
