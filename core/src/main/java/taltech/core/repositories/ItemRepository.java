package taltech.core.repositories;

import lombok.SneakyThrows;
import taltech.core.config.PostgreSQLConnection;
import taltech.core.models.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class ItemRepository {

    @SneakyThrows
    public ArrayList<Item> getAll() {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        ArrayList<Item> items = new ArrayList<>();

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery("select * from item");

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("app_user_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                items.add(new Item(id, userId, name, description));
            }

            rs.close();
            stmt.close();
            connection.get().close();
        }

        return items;
    }

    @SneakyThrows
    public Item getById(int id) {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        Item item = null;
        String query = "select * from item where id = " + id;

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int userId = rs.getInt("app_user_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                item = new Item(id, userId, name, description);
            }

            rs.close();
            stmt.close();
            connection.get().close();
        }

        return item;
    }

    @SneakyThrows
    public void deleteById(int id) {
        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        String query = "delete from item where id = " + id;

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            stmt.execute(query);
            stmt.close();
            connection.get().close();
        }
    }

    @SneakyThrows
    public int create(Item item) {
        int id = 0;

        Optional<Connection> connection = PostgreSQLConnection.getConnection();
        String query =
                "insert into item(app_user_id, name, description) " +
                "values (" + item.getUserId() + ", '" + item.getName() + "', '" + item.getDescription() + "') returning id;";

        if (connection.isPresent()) {
            Statement stmt = connection.get().createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("id");
            }

            rs.close();
            stmt.close();
            connection.get().close();
        }

        return id;
    }
}
