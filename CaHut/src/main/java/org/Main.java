package org;
import org.database.Database;
import org.service.LoginService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        LoginService.start();
        Database.closeConnection();

    }
}