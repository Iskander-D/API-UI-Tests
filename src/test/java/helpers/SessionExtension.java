package helpers;

import api.AuthorizationApi;
import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SessionExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
//
        open("/favicon.ico");

        LoginResponse authResponse = AuthorizationApi.authResponse();
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
    }


}
