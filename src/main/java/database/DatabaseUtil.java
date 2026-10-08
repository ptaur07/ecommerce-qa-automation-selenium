package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DatabaseUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/ecommerce_qa";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            "root";

    public static String getOrderStatus(
            String orderId) {

        String status = null;

        String query =
                "SELECT status FROM orders WHERE order_id=?";

        try {

            Connection connection =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD);

            PreparedStatement statement =
                    connection.prepareStatement(query);

            statement.setString(1, orderId);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                status =
                        result.getString("status");
            }

            result.close();
            statement.close();
            connection.close();

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return status;
    }
}