package taltech.core.repositories;

import taltech.core.config.PostgreSQLConnection;
import taltech.core.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepository {

    public ArrayList<User> getUsers() throws SQLException {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        ArrayList<User> users = new ArrayList<>();

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery("select * from app_user");

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                users.add(new User(id, name));
            }

            rs.close();
            stmt.close();
            connection.get().close();
        }

        return users;
    }
}
