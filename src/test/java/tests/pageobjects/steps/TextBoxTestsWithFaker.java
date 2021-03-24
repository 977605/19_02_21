package tests.pageobjects.steps;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.Data;
import tests.TestBase;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTestsWithFaker extends TestBase {

    TextBoxRegistrationPage textBoxRegistrationPage = new TextBoxRegistrationPage();

    Faker faker = new Faker();

    Data data = new Data(
            faker.name().firstName(), faker.name().lastName(),
            faker.internet().emailAddress(), faker.phoneNumber().subscriberNumber(10),
            "Chemistry", faker.address().fullAddress(),
            "NCR", "Noida");

    String dayOfBirth = "10",
            monthOfBirth = "May",
            yearOfBirth = "1988";

    @Test
    void dataAppearsTestForPracticeFormWithFaker() {

        open("https://demoqa.com/automation-practice-form");
        textBoxRegistrationPage.checkPageHeader("Practice Form");
        textBoxRegistrationPage.setFirstName(data.getFirstName());
        textBoxRegistrationPage.setLastName(data.getLastName());

        $("#userEmail").setValue(data.getUserEmail());
        $("[class='custom-control custom-radio custom-control-inline'], [id ='gender-radio-1']").click();
        $("#userNumber").setValue(data.getUserNumber());


        // date
        textBoxRegistrationPage.setDate(monthOfBirth, yearOfBirth, dayOfBirth);

        $("#subjectsInput").setValue(data.getSubjectsInput()).pressEnter();

        $("[class='custom-control custom-checkbox custom-control-inline'], [id ='hobbies-checkbox-1']").click();

        //pict
        $("#uploadPicture").uploadFile(new File("src/test/java/docs/1.jpg"));

        $("#currentAddress").setValue(data.getCurrentAddress());
        $("#react-select-3-input").setValue(data.getState()).pressEnter();
        $("#react-select-4-input").setValue(data.getCity()).pressEnter();

        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        //form testing
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(texts(data.getFirstName() + " " + data.getLastName()));
        $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(texts(data.getUserEmail()));
        $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts("Male"));
        $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(data.getUserNumber()));
        $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(texts("10 May,1988"));
        $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(data.getSubjectsInput()));
        $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts("Sports"));
        $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts("1.jpg"));
        $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(data.getCurrentAddress()));
        $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(data.getState() + " " + data.getCity()));
    }

    @Test
    @Tag("negative")
    void negativedDataAppearsTestForPracticeFormWithFaker() {

        open("https://demoqa.com/automation-practice-form");
        textBoxRegistrationPage.checkPageHeader("Practice Form");
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
}

