package tests.pages;

import com.codeborne.selenide.SelenideElement;
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
    public PracticeFormPagesObject openPage () {
        open("");
        removeAds();
        $$(".card-body").findBy(text("Forms")).click();
        $$(".router-link").findBy(text("Practice Form")).click();

        return this;
    }

    public PracticeFormPagesObject setFirstName (String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public PracticeFormPagesObject setLastName (String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public PracticeFormPagesObject setUserEmail (String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public PracticeFormPagesObject setUserGender (String value) {
        userGenderChoice.$(byText(value)).click();

        return this;
    }

    public PracticeFormPagesObject setUserNumber (String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public PracticeFormPagesObject setBirthDate (String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponents.setDate(day, month, year);

        return this;
    }

    public PracticeFormPagesObject setSubject (String value) {
        subjectChoice.setValue(value).pressEnter();

        return this;
    }

    public PracticeFormPagesObject setUserHobbies (String value) {
        userHobbiesChoice.$(byText(value)).click();

        return this;
    }

    public PracticeFormPagesObject setUserAdress (String value) {
        adressInput.setValue(value);

        return this;
    }

    public PracticeFormPagesObject uploadUserPhoto (String value) {
        userPhotoDownloader.uploadFromClasspath(value);

        return this;
    }

    public PracticeFormPagesObject selectState (String state) {
        userState.click();
        userState.$(byText(state)).click();

        return this;
    }

    public PracticeFormPagesObject selectCity (String city) {
        userCity.click();
        userCity.$(byText(city)).click();

        return this;
    }

    public PracticeFormPagesObject submitForm () {
        submit.click();

        return this;
    }

    public PracticeFormPagesObject verifyOpenedTable () {
        registrationResultModalComponents.verifyOpenedTable();

        return this;
    }

    public PracticeFormPagesObject verifyContentInTable (String key, String value) {
        registrationResultModalComponents.verifyContentInTable(key, value);

        return this;
    }

    public PracticeFormPagesObject verifyResultNegativeOpenedTable () {
        registrationResultModalComponents.verifyNegativeOpenedTable();

        return this;
    }
}
