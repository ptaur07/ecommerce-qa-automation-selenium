package database;

import utils.ConfigReader;

import java.sql.*;

public class DatabaseUtil {

    public static String getOrderStatus(
            String orderId) {

        String query =
                "SELECT status FROM orders " +
                "WHERE order_id = ?";

        try (
            Connection connection =
                DriverManager.getConnection(
                    ConfigReader.get("dbUrl"),
                    ConfigReader.get("dbUsername"),
                    ConfigReader.get("dbPassword"));

            PreparedStatement statement =
                connection.prepareStatement(query)
        ) {

            statement.setString(
                    1,
                    orderId);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return result.getString(
                        "status");
            }

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Database validation failed",
                    e);
        }

        return null;
    }
}