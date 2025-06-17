package ru.netology.service.mode;

import java.sql.*;

public class DBUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/app"; // твоя БД
    private static final String USER = "app";
    private static final String PASSWORD = "pass";

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
}
