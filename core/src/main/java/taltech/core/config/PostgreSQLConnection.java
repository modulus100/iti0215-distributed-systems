package taltech.core.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class PostgreSQLConnection {

    public static Optional<Connection> getConnection() {
        Optional<Connection> connection = Optional.empty();
        String url = "jdbc:postgresql://pgdb:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        try {
            connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
        } catch (SQLException ex) {
            // log
        }

        return connection;
    }
}
