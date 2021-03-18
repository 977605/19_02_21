package tests.pageobjects.steps;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class TextBoxRegistrationPage {

    private SelenideElement
            pageHeader = $(".main-header"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName");

    public void checkPageHeader(String value) {
        pageHeader.shouldHave(text(value));
    }

    public void setFirstName(String value) {
        firstNameInput.setValue(value);
    }

    public void setLastName(String value) {
        lastNameInput.setValue(value);
    }

    public void setDate(String monthOfBirth, String yearOfBirth, String dayOfBirth) {
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();
    }
}
