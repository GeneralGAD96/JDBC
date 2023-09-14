package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Util {

    private static Connection connection;
    private static String dbURL = "jdbc:mysql://localhost:3306/mydbtest";
    private static String dbUserName = "root";
    private static String dbPassword = "root";

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Metadata getMetadata() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("dialect", "org.hibernate.dialect.MySQLDialect");
        settings.put("hibernate.connection.url", dbURL);
        settings.put("hibernate.connection.username", dbUserName);
        settings.put("hibernate.connection.password", dbPassword);
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(User.class);
        Metadata metadata = metadataSources.buildMetadata();
        return metadata;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = getMetadata().getSessionFactoryBuilder().build();
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        getSessionFactory().close();
    }
}

