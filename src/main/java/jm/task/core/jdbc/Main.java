package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Anton1", "Georg1", (byte) 21);
        userService.saveUser("Anton2", "Georg2", (byte) 22);
        userService.saveUser("Anton3", "Georg3", (byte) 23);
        userService.saveUser("Anton4", "Georg4", (byte) 24);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
