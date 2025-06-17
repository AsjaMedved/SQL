package ru.netology.service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.service.mode.DBUtils;
import ru.netology.service.page.LoginPage;
import ru.netology.service.data.DataHelper;

import static com.codeborne.selenide.Selenide.open;

public class DataValidationTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("пустое поле логин 1й пользователь")
    void incorrectVerificationCode1user() {
        var validLogin = DataHelper.getTheFirstUser();
        var loginPage = new LoginPage();
        loginPage.registration("", validLogin.getPassword());
        loginPage.checkErrorIsVisibleLogin();
    }

    @Test
    @DisplayName("пустое поле пароль 1й пользователь")
    void campoPasswordVuoto1user() {
        var validLogin = DataHelper.getTheFirstUser();
        var loginPage = new LoginPage();
        loginPage.registration(validLogin.getLogin(), "");
        loginPage.checkErrorIsVisiblePassword();
    }

    @Test
    @DisplayName("пустое поле логин 2й пользователь")
    void incorrectVerificationCode2user() {
        var validLogin = DataHelper.getTheSecondUser();
        var loginPage = new LoginPage();
        loginPage.registration("", validLogin.getPassword());
        loginPage.checkErrorIsVisibleLogin();
    }

    @Test
    @DisplayName("пустое поле пароль 2й пользователь")
    void campoPasswordVuoto2user() {
        var validLogin = DataHelper.getTheSecondUser();
        var loginPage = new LoginPage();
        loginPage.registration(validLogin.getLogin(), "");
        loginPage.checkErrorIsVisiblePassword();
    }

    @Test
    @DisplayName("не верный логин/пароль")
    void invalidUser() {
        var originalAuthInfo = DataHelper.getTheFirstUser();
        var validLogin = DataHelper.getIncorrectUser(originalAuthInfo);
        var loginPage = new LoginPage();
        loginPage.registration(validLogin.getLogin(), validLogin.getPassword());
        loginPage.checkErrorVisibleWithText("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    @DisplayName("Вход с невалидным кодом верификации")
    void incorrectVerificationCode() {
        var validLogin = DataHelper.getTheFirstUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.dataEntry(validLogin.getLogin(), validLogin.getPassword());
        var invalidCode = DataHelper.getOtherVerificationCodeFor(validLogin);
        verificationPage.verificationCode(invalidCode.getCode());
        verificationPage.incorrectVerificationCode();
    }

    @Test
    @DisplayName("Вход с валидным кодом верификации")
    void loginIthAValidVerificationCode() {
        var validLogin = DataHelper.getTheFirstUser();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.dataEntry(validLogin.getLogin(), validLogin.getPassword());
        String code = DBUtils.getValidVerificationCode();
        var verificationCode = new DataHelper.VerificationCode(code);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName("3 неудачных попытки входа, затем успешная")
    void threeInvalidLoginsThenValidLogin() {
        var validLogin = DataHelper.getTheFirstUser();
        var loginPage = new LoginPage();

        for (int i = 0; i < 3; i++) {
            var invalidAuth = DataHelper.getIncorrectUser(validLogin);
            loginPage.registration(invalidAuth.getLogin(), invalidAuth.getPassword());
            loginPage.checkErrorVisibleWithText("Ошибка! Неверно указан логин или пароль");
        }

        loginPage.dataEntry(validLogin.getLogin(), validLogin.getPassword());
        loginPage.checkErrorVisibleWithText("Пользователь заблокирован");
    }
}



