package tests.testdata;

import com.github.javafaker.Faker;
import java.util.Locale;

public class TestDataFaker {
    Faker faker = new Faker(new Locale("en"));
    public String firstName = faker.name().firstName(),
            lastName = faker.name().firstName(),
            userEmail = faker.internet().emailAddress(),
            userGender = faker.options().option("Male", "Female"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            yearOfBirth = String.format("%s",faker.number().numberBetween(1900,2100)),
            monthOfBirth = faker.options().option("January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October", "November", "December"),
            dateOfBirth = String.format("%s",faker.number().numberBetween(10, 28)),
            userSubjectChoice = faker.options().option("English", "Chemistry", "Computer Science", "Commerce", "Economics",
                    "Civics"),
            userHobbies = faker.options().option("Sports", "Reading", "Music"),
            userHomeAdress = faker.address().streetAddress(),
            userPhoto = faker.options().option("Jhonny_Silverhand.jpg", "Uaaa.png"),
            userState = faker.options().option("NCR", "Haryana", "Rajasthan"),
            userCity = selectCity(userState);

        public String selectCity(String state) {
            return switch (state) {
                case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
                case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
                case "Haryana" -> faker.options().option("Karnal", "Panipat");
                case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
                default -> null;
            };
    }
}
