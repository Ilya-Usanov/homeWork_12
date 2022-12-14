package com.demoqa.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    static void configure() {
        String LOGIN = "user1";
        String PASSWORD = "1234";
        String REMOTE = "https://" + LOGIN + ":" + PASSWORD + "@selenoid.autotests.cloud/wd/hub";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        if (System.getProperty("remote").equals("YES")) {
            Configuration.browser = System.getProperty("browser_name");
            Configuration.browserVersion = System.getProperty("browser_version");
            Configuration.browserSize = System.getProperty("browser_size");
            Configuration.remote = REMOTE;
        } else {
            Configuration.browser = "chrome";
            Configuration.browserSize = "1920x1180";
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("last screenshot");
        Attach.pageSource();
        Attach.browseConsoleLogs();
        Attach.addVideo();
    }
}
