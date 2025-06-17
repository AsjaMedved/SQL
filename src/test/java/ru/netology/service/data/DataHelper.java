package ru.netology.service.data;

import lombok.Value;
import org.junit.jupiter.api.DisplayName;

import java.sql.*;

@DisplayName(" логин пароль" +
        "код верификации" +
        "номера карт")

public class DataHelper {

    private DataHelper() {
    }

    public static AuthInfo getTheFirstUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getTheSecondUser() {
        return new AuthInfo("petya", "qwerty123");
    }

    public static AuthInfo getIncorrectUser(AuthInfo original) {
        return new AuthInfo("petya", "qwerty132");
    }

    public static VerificationCode getOtherVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("0000");
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}
