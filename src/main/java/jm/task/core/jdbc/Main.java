package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDaoHibernateImpl us = new UserDaoHibernateImpl();
        us.saveUser("Ramil", "Khanov", (byte) 3);
        us.saveUser("Tanya", "Stukova", (byte) 35);
        us.saveUser("Sasha", "Petrov", (byte) 23);
        us.saveUser("Lina", "Ivanova", (byte) 71);
        us.createUsersTable();
        us.dropUsersTable();
        us.removeUserById(2);
        us.cleanUsersTable();
        us.getAllUsers();

    }
}
