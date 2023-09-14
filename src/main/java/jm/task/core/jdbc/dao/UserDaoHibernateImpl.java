package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS Users (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(50) NULL,\n" +
                "  `last_Name` VARCHAR(50) NULL,\n" +
                "  `age` TINYINT NULL,\n" +
                "  PRIMARY KEY (`id`));").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS Users;").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").getResultList();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.createNativeQuery("delete from Users;").executeUpdate();
        session.getTransaction().commit();
    }
}
