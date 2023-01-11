package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = getConnection();
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, username VARCHAR(45), lastname VARCHAR(45), age INT)";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            connection.commit();
            System.out.println("Table created.");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Something happened while trying to rollback");
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to create users table");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something happened while trying to close connection.");
            }
            System.out.println("Connection closed.");
        }
    }

    public void dropUsersTable() {
        Connection connection = getConnection();
        String sql = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            connection.commit();
            System.out.println("Table successfully dropped");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Something happened while trying to rollback");
                ex.printStackTrace();
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to drop users table.");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Something happened while trying to close connection");
            }
            System.out.println("Connection closed");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = getConnection();

        String sql = "INSERT INTO users (username, lastname, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("В таблицу был добавлен User c именем " + name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Something happened while trying to rollback.");
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to save user");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Something happened while trying to close connection.");
            }
            System.out.println("Connection closed.");
        }
    }

    public void removeUserById(long id) {
        Connection connection = getConnection();
        String sql = "DELETE FROM users WHERE ID =?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Something happened while trying to rollback.");
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to remove user by id = " + id);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Something happened while trying to close connection.");
            }
            System.out.println("Connection closed.");
        }
    }

    public List<User> getAllUsers() {
        Connection connection = getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, username, lastname, age FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("username"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Something happened while trying to rollback.");
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to get all users");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Something happened while trying to close connection.");
            }
            System.out.println("Connection closed.");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        Connection connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            connection.commit();
            System.out.println("Cleaned the mess!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println("Something happened while trying to rollback.");
            }
            e.printStackTrace();
            System.out.println("Something happened while trying to clean users table");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Something happened while trying to close the connection");
            }
            System.out.println("Connection closed.");
        }
    }
}
