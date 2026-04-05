# Репозиторий для сдачи домашних заданий в рамках курса **QA.Guru** (поток 40)

<!-- Основные технологии -->
![Java](https://img.shields.io/badge/Java-21-orange?logo=java)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?logo=gradle)
![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5)
![Selenide](https://img.shields.io/badge/Selenide-7.x-red)
![Allure](https://img.shields.io/badge/Allure-2.x-835CC5?logo=allure)

<!-- Статус проекта -->
![GitHub last commit](https://img.shields.io/github/last-commit/agzamovQA/qa_guru_40_HW)
![Repo Size](https://img.shields.io/badge/repo%20size-~1.5%20MB-blue)
![Languages](https://img.shields.io/badge/languages-Java%20(100%25)-blue)
* * * 
## 🗂️ О проекте

Тесты написаны для сайта [DemoQA](https://demoqa.com).

Проект представляет собой автоматизированные тесты, написанные на Java с использованием Selenide и JUnit 5. Тесты
покрывают различные сценарии проверки веб-приложений (UI-тесты) и API. Сборка и управление зависимостями осуществляется
через Gradle.
* * *
## 🔍 Пример теста

Пример автоматизированного теста для заполнения формы регистрации:

```java
package tests.homework.lesson_12;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.pages.PracticeFormPagesObject;
import tests.testdata.TestDataFaker;

import static io.qameta.allure.Allure.step;

public class PracticeFormTestsJenkins extends TestBaseDemoQaJenkins {
    PracticeFormPagesObject practiceFormPages = new PracticeFormPagesObject();
    TestDataFaker testDataFaker = new TestDataFaker();

    @Test
    @DisplayName("[Positive] Fill all fields and check table result")
    void fillPositiveAllFormTest() {
        step("Open registration form ", () -> {
            practiceFormPages.openPage();
        });

        step("Fill registration form ", () -> {
            practiceFormPages.setFirstName(testDataFaker.firstName)
                    .setLastName(testDataFaker.lastName)
                    .setUserEmail(testDataFaker.userEmail)
                    .setUserGender(testDataFaker.userGender)
                    .setUserNumber(testDataFaker.userNumber)
                    .setBirthDate(testDataFaker.dateOfBirth, testDataFaker.monthOfBirth, testDataFaker.yearOfBirth)
                    .setSubject(testDataFaker.userSubjectChoice)
                    .setUserHobbies(testDataFaker.userHobbies)
                    .setUserAdress(testDataFaker.userHomeAdress)
                    .uploadUserPhoto(testDataFaker.userPhoto)
                    .selectState(testDataFaker.userState)
                    .selectCity(testDataFaker.userCity)
                    .submitForm();
        });

        step("Check registration form results", () -> {
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
        });
    }
}
```

Что делает этот тест:

1. Открывает форму регистрации
2. Заполняет все поля (имя, фамилию, email, пол, телефон, дату рождения, предметы, хобби, адрес, фото, штат и город)
3. Отправляет форму
4. Проверяет, что в итоговой таблице отображаются корректные данные
* * *
## 💻 Технологический стек

| Технология                        | Описание                                                                                             |
|-----------------------------------|------------------------------------------------------------------------------------------------------|
| **Java 21**                       | Язык программирования                                                                                |
| **JUnit 5.6.3**                   | Фреймворк для модульного тестирования с поддержкой параметризованных тестов и тегов (SMOKE, REGRESS) |
| **Selenide 7.14.0**               | Обертка над Selenium для удобного UI-тестирования                                                    |
| **Gradle**                        | Система сборки и управления зависимостями                                                            |
| **DataFaker 2.5.4**               | Генерация реалистичных тестовых данных                                                               |
| **Allure 2.29.0**                 | Фреймворк для генерации отчетов                                                                      |
| **Allure Selenide**               | Интеграция Allure с Selenide                                                                         |
| **XLS-Test**                      | Библиотека для тестирования XLS файлов                                                               |
| **PDF-Test**                      | Библиотека для тестирования PDF файлов                                                               |
| **POI-OOXML**                     | Библиотека для работы с Excel файлами                                                                |
| **OpenCSV**                       | Библиотека для работы с CSV файлами                                                                  |
| **Jackson Databind**              | Библиотека для работы с JSON                                                                         |
| **Page Object Model**             | Паттерн проектирования для повышения читаемости и поддержки кода                                     |

* * *
## ▶️ Запуск тестов
Для запуска всех тестов выполните в терминале:

```bash
./gradlew clean test

## Запуск тестов

Для запуска всех тестов выполните в терминале:

```bash
./gradlew clean test
```

Запуск конкретного домашнего задания (по тегу)
В проекте настроен Gradle для запуска тестов, отмеченных определенным тегом (например, @HW_11).

Пример команды:

```bash
./gradlew clean HW_11
```

**Генерация Allure-отчета**

- Выполните тесты.

- Сгенерируйте и откройте отчет:
```bash
./gradlew allureServe
```
- На выходе получаем готовый отчет с Видео, Скриншотами и понятными шагами:
![allureExample.png](src%2Ftest%2Fresources%2FallureExample.png)
* * *
## ✉️ Примечания

- Репозиторий обновляется по мере сдачи новых домашних заданий.
- Автор: Agzamov Vladimir
- Telegram: @agzamovQA
