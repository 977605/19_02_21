package tests.pageobjects.cases;

import org.junit.jupiter.api.Test;
import tests.TestBase;

public class TextBoxTestsPageObjectTests extends TestBase {

    TextBoxRegistrationPageCases textBoxRegistrationPageCases = new TextBoxRegistrationPageCases();

    @Test
    void dataAppearsTestForPracticeFormWithFaker() {

        textBoxRegistrationPageCases.openPage();
        textBoxRegistrationPageCases.fillForm();
        textBoxRegistrationPageCases.checkData();

    }
}

