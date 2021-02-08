package taltech.core.repositories;

import lombok.SneakyThrows;
import taltech.core.config.PostgreSQLConnection;
import taltech.core.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class UserRepository {

    @SneakyThrows
    public ArrayList<User> getAll() {
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

    @SneakyThrows
    public User getById(int id) {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        User user = null;
        String query = "select * from app_user where id = " + id;

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                user = new User(id, name);
            }

            rs.close();
            stmt.close();
            connection.get().close();
        }

        return user;
    }

    @SneakyThrows
    public void deleteById(int id) {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        String query = "delete from app_user where id = " + id;

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            stmt.execute(query);
            stmt.close();
            connection.get().close();
        }
    }

    @SneakyThrows
    public void create(User user) {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        String query = "insert into app_user(name)values ('" + user.getName() + "')";

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            stmt.execute(query);
            stmt.close();
            connection.get().close();
        }
    }
}
