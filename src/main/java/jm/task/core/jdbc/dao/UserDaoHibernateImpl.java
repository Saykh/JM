package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String createSql = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(createSql).executeUpdate();
            System.out.println("Таблица создана!");
            transaction.commit();
        } catch (Exception e) {
                System.out.println("Произошла ошибка при создании таблицы!");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(dropSql).executeUpdate();
            System.out.println("Таблица удалена!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Произошла ошибка при добавлении пользователя!");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            System.out.println("Пользователи добавлены!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Произошла ошибка при добавлении нового пользователя!");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query deleteQuery = session.createQuery("DELETE FROM User u WHERE u.id =:uId");
            deleteQuery.setParameter("uId", id);
            int numberOfUser = deleteQuery.executeUpdate();
            transaction.commit();
            System.out.println("Пользователь " + numberOfUser + " удалён!");
        } catch (Exception e) {
            System.out.println("Произошла ошибка при удалении пользователя!");
        }
    }


    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("SELECT a FROM User a", User.class).getResultList();
        }
    }


    @Override
    public void cleanUsersTable() {
        String cleanSql = "DELETE FROM users";
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(cleanSql).executeUpdate();
            System.out.println("Таблица очищена!");
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Произошла ошибка при попытке очистить таблицу!");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}