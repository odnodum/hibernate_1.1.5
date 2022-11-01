//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class UserDaoJDBCImpl implements UserDao {
//    Connection connection = util.getConnection();
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable(){
//        Statement statement = null;
//
//        try {
//            statement = connection.createStatement();
//            String SQL = "CREATE TABLE users "
//                    + "(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, username VARCHAR(20) NOT NULL, "
//                    + "lastname VARCHAR(20), age INT NOT NULL)";
//            statement.execute(SQL);
//        } catch (SQLException e) {
//            System.out.println("Такая таблица уже существует");
//        }
//    }
//
//    public void dropUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "DROP TABLE users";
//            statement.execute(SQL);
//        } catch (SQLException e) {
//            System.out.println("Такой таблицы не существует");
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) throws SQLException {
//        PreparedStatement preparedStatement =
//                connection.prepareStatement("INSERT INTO users (username, lastname, age) VALUES (?, ?, ?)");
//
//        preparedStatement.setString(1, name);
//        preparedStatement.setString(2, lastName);
//        preparedStatement.setByte(3, age);
//
//        preparedStatement.executeUpdate();
//        System.out.println("User с именем – " + name + " добавлен в базу данных");
//
//    }
//
//    public void removeUserById(long id) throws SQLException {
//        PreparedStatement preparedStatement =
//                connection.prepareStatement("DELETE FROM users WHERE id=?");
//        preparedStatement.setLong(1, id);
//
//        preparedStatement.executeUpdate();
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//
//        List<User> users = new ArrayList<>();
//
//        Statement statement = connection.createStatement();
//        String SQL = "SELECT * FROM users";
//
//        ResultSet resultSet = statement.executeQuery(SQL);
//
//        while (resultSet.next()) {
//            User user = new User();
//            user.setId(resultSet.getLong("id"));
//            user.setName(resultSet.getString("username"));
//            user.setLastName(resultSet.getString("lastname"));
//            user.setAge(resultSet.getByte("age"));
//
//            users.add(user);
//        }
//        System.out.println(users);
//        return users;
//    }
//
//    public void cleanUsersTable() throws SQLException {
//        Statement statement = connection.createStatement();
//        String SQL = "DELETE FROM users";
//
//        statement.execute(SQL);
//    }
//}
