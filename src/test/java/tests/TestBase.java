package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentHelper.*;

public class TestBase {

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.startMaximized = true;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/";
    }

    @Step("Открываем страницу и проверяем, что форма имеет заголовок Practice Form")
    public void openPageAndCheckTitle(String url, String form_title) {
        open(url);
        $(".main-header").shouldHave(text(form_title));
    }

    @Step("Заполняем форму")
    public void fillInTheForm(Data data, String dayOfBirth, String monthOfBirth, String yearOfBirth) {
        $("#firstName").setValue(data.getFirstName());
        $("#lastName").setValue(data.getLastName());
        $("#userEmail").setValue(data.getUserEmail());
        $("[class='custom-control custom-radio custom-control-inline'], [id ='gender-radio-1']").click();
        $("#userNumber").setValue(data.getUserNumber());

        // date
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();

        $("#subjectsInput").setValue(data.getSubjectsInput()).pressEnter();

        $("[class='custom-control custom-checkbox custom-control-inline'], [id ='hobbies-checkbox-1']").click();

        //pict
        $("#uploadPicture").uploadFile(new File("src/test/java/docs/1.jpg"));

        $("#currentAddress").setValue(data.getCurrentAddress());
        $("#react-select-3-input").setValue(data.getState()).pressEnter();
        $("#react-select-4-input").setValue(data.getCity()).pressEnter();
    }

    @Step("Нажимаем Подтвердить заполнение формы")
    public void submitForm() {
        $("#submit").click();
    }


    @Step("Поверяем корректность заполения формы данными")
    public void checkDataInForm(Data data) {
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

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        attachVideo();
        closeWebDriver();
    }
}
