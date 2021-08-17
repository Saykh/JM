package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    private int id = 1;
    List<User> users = new ArrayList<>();


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)";
        try {
            connection.createStatement().executeUpdate(sqlCreate);
            System.out.println("Таблица создана!");
        } catch (SQLException e) {
            System.out.println("При создании таблицы произошла ошибка");
        }
    }


    public void dropUsersTable() {
        String sqlDrop = "DROP TABLE IF EXISTS users";
        try {
            connection.createStatement().executeUpdate(sqlDrop);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.out.println("При удалении таблицы произошла ошибка");
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String sqlInsert = "INSERT INTO users VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, id++);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, age);
            preparedStatement.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("При добавлении пользователя в таблицу users произошла ошибка");
        }
    }


    public void removeUserById(long id) {
        String DELETE = "DELETE FROM users WHERE id = ";
        try {
            connection.createStatement().executeUpdate(DELETE + id);
            System.out.println("User was deleted");
        } catch (SQLException b) {
            System.out.println("При удалении пользователя из таблицы произошла ошибка");
        }
    }


    public List<User> getAllUsers() {
        String sqlGetAll = "SELECT * FROM users";
        ResultSet resultSet = null;
        try {
            resultSet = connection.createStatement().executeQuery(sqlGetAll);
            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                users.add(user);
                System.out.println("Список пользователей получен");
            }
        } catch (SQLException e) {
            System.out.println("Произошла ошибка при получении списка пользователей");;
        }
         return users;
    }


        public void cleanUsersTable() {
            String DELETE = "DELETE FROM users";
            try {
                connection.createStatement().executeUpdate(DELETE);
                System.out.println("Таблица очищена");
            } catch (SQLException b) {
                System.out.println("При очистке таблицы произошла ошибка");
            }
        }

    }


