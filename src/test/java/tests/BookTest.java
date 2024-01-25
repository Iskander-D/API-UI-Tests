package tests;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import api.AuthorizationApi;
import api.BooksApi;
import models.LoginResponse;

public class BookTest extends TestBase {
    private final String TEXT_IN_COLLECTION = "No rows found";
    @Test
    @Tag("API+UI")
    @WithLogin
    @DisplayName("Удаление книги из коллекции пользователя")
    void successfulDeleteBookTest() {
        LoginResponse authResponse = step("Делаем запрос на авторизацию", () ->
                AuthorizationApi.authResponse()
        );

        step("Удаляем все книги из Profile", () ->
                BooksApi.deleteAllBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Добавляем книгу в Profile", () ->
                BooksApi.addBooks(authResponse.getToken(), authResponse.getUserId())
        );

        step("Открываем страницу Profile", () ->
                open("/profile")
        );

        step("Кликаем по значку корзины", () ->
                $("#delete-record-undefined").click()
        );

        step("Подтверждаем запрос на удаление", () ->
                $("#closeSmallModal-ok").click()
        );

        step("Проверяем, что книга не отображается в таблице", () ->
                $(".rt-noData").shouldBe(visible)
                        .shouldHave(text(TEXT_IN_COLLECTION))
        );
    }
}
