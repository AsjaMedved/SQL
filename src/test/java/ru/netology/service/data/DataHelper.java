package ru.netology.service.data;

import lombok.Value;
import org.junit.jupiter.api.DisplayName;

import java.sql.*;

@DisplayName(" логин пароль" +
        "код верификации" +
        "номера карт")

public class DataHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/app"; // твоя БД
    private static final String USER = "app";
    private static final String PASSWORD = "pass";

private DataHelper(){
    }

    public static String getValidVerificationCode() {
        String code = null;
        String query = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        try (
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()
        ) {
            if (rs.next()) {
                code = rs.getString("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
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
