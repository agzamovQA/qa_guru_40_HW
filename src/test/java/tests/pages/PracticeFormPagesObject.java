package tests.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.pages.components.CalendarComponents;
import tests.pages.components.RegistrationResultModalComponents;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormPagesObject {
    CalendarComponents calendarComponents = new CalendarComponents();
    RegistrationResultModalComponents registrationResultModalComponents = new RegistrationResultModalComponents();

    //Elements
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userGenderChoice = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectChoice = $("#subjectsInput"),
            userHobbiesChoice = $("#hobbiesWrapper"),
            adressInput = $("#currentAddress"),
            userPhotoDownloader = $("#uploadPicture"),
            userState = $("#state"),
            userCity = $("#city"),
            submit = $("#submit");

    //Actions

    public PracticeFormPagesObject removeAds () {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
        """);

        return this;
    }
    @Step("Open registration page https://demoqa.com")
    public PracticeFormPagesObject openPage () {
        open("");
        removeAds();
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        return this;
    }

    @Step("Type First Name \"{value}\"")
    public PracticeFormPagesObject setFirstName (String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Type Last Name \"{value}\"")
    public PracticeFormPagesObject setLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Type Email \"{value}\"")
    public PracticeFormPagesObject setUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Set Gender \"{value}\"")
    public PracticeFormPagesObject setUserGender (String value) {
        userGenderChoice.$(byText(value)).click();

        return this;
    }

    @Step("Type User Number \"{value}\"")
    public PracticeFormPagesObject setUserNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Set Birthdate: \"{day}\" - day, \"{month}\" - month, \"{year}\" - year.")
    public PracticeFormPagesObject setBirthDate (String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    @Step("Type Subjects \"{value}\"")
    public PracticeFormPagesObject setSubject (String value) {
        subjectChoice.setValue(value).pressEnter();

        return this;
    }

    @Step("Set Hobbies \"{value}\"")
    public PracticeFormPagesObject setUserHobbies (String value) {
        userHobbiesChoice.$(byText(value)).click();

        return this;
    }

    @Step("Set user address \"{value}\"")
    public PracticeFormPagesObject setUserAdress (String value) {
        adressInput.setValue(value);

        return this;
    }

    @Step("Upload Photo \"{value}\"")
    public PracticeFormPagesObject uploadUserPhoto (String value) {
        userPhotoDownloader.uploadFromClasspath(value);

        return this;
    }

    @Step("Select state \"{state}\"")
    public PracticeFormPagesObject selectState (String state) {
        userState.click();
        userState.$(byText(state)).click();

        return this;
    }

    @Step("Select city \"{city}\"")
    public PracticeFormPagesObject selectCity (String city) {
        userCity.click();
        userCity.$(byText(city)).click();

        return this;
    }

    @Step("Click submit form")
    public PracticeFormPagesObject submitForm () {
        submit.click();

        return this;
    }

    @Step("Verify Opened Table")
    public PracticeFormPagesObject verifyOpenedTable () {
        registrationResultModalComponents.verifyOpenedTable();

        return this;
    }

    @Step("Check Result \"{key}\" has \"{value}\"")
    public PracticeFormPagesObject verifyContentInTable (String key, String value) {
        registrationResultModalComponents.verifyContentInTable(key, value);

        return this;
    }

    public PracticeFormPagesObject verifyResultNegativeOpenedTable () {
        registrationResultModalComponents.verifyNegativeOpenedTable();

        return this;
    }
}
