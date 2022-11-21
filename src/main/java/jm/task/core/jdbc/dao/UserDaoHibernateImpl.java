package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Iterator;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private Session session;
    private SessionFactory factory;

    public UserDaoHibernateImpl() {
        factory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            final String SQL = "CREATE TABLE if NOT EXISTS users "
                    + "(id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, username VARCHAR(20) NOT NULL, "
                    + "lastname VARCHAR(20), age INT NOT NULL)";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            final String SQL = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            final String SQL = "FROM " + User.class.getName();
            users = session.createQuery(SQL).list();
            for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
                User user = it.next();
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            final String SQL = "DELETE FROM users";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
