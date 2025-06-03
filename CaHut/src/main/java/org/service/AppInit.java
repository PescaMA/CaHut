package org.service;

import org.classes.UserData;

import java.util.HashMap;
import java.util.Scanner;

public class AppInit {
    private final HashMap<String, UserData> users = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    protected AppInit(){}
    static AppInit getInstance() { return new AppInit();}

    public HashMap<String, UserData> getUsers() {
        return users;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
