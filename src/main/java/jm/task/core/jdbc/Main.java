package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Vasily", "Vasiliev", (byte) 20);
        service.saveUser("Vasil", "Vasili", (byte) 54);
        service.saveUser("Vasilia", "Vasilieva", (byte) 27);
        service.saveUser("Valery", "Jmishenko", (byte) 27);
        System.out.println(service.getAllUsers());
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}

