package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;



public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Saykhan","Edilov", (byte) 27);
        userService.saveUser("Ivan","Markov", (byte) 29);
        userService.saveUser("Nikolay","Bezrukov", (byte) 30);
        userService.saveUser("Saha","Petrov", (byte) 31);

        userService.dropUsersTable();

        userService.removeUserById(1);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();


    }
}
