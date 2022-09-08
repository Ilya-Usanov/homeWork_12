package com.demoqa.test;

import com.demoqa.data.TestData;
import com.demoqa.pages.RegistrationFormPage;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class DemoqaFormTest extends TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();
    TestData testData = new TestData();

    @Test
    void formTest() {
        step("Opening the registration form and filling in the fields", () -> registrationFormPage
                .openPage()
                .fillsElements(testData));
        step("Checking the registration form", () -> registrationFormPage.checkFillsForm(testData));
    }
}
