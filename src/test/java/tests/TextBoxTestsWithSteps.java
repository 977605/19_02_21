package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

public class TextBoxTestsWithSteps {

    final TestBase steps = new TestBase();

    private static final String URL = "https://demoqa.com/automation-practice-form";
    private static final String FORM_TITLE = "Practice Form";
    private static final String DAY_OF_BIRTH = "10";
    private static final String MONTH_OF_BIRTH = "May";
    private static final String YEAR_OF_BIRTH = "1988";

    Faker faker = new Faker();

    Data data = new Data(
            faker.name().firstName(), faker.name().lastName(),
            faker.internet().emailAddress(), faker.phoneNumber().subscriberNumber(10),
            "Chemistry", faker.address().fullAddress(),
            "NCR", "Noida");

    @Test
    void dataAppearsTestForPracticeFormWithSteps() {

        steps.openPageAndCheckTitle(URL, FORM_TITLE);
        steps.fillInTheForm(data, DAY_OF_BIRTH, MONTH_OF_BIRTH, YEAR_OF_BIRTH);
        steps.submitForm();
        steps.checkDataInForm(data);

    }

}

