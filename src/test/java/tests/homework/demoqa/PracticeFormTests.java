package tests.homework.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
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
    @DisplayName("[Positive][DemoQa] Проверка формы регистрации с заполнением всех полей и проверкой результата в итогвой таблице")
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

        sleep(6000);
    }
}
