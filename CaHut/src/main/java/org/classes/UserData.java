package org.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public abstract class UserData implements Comparable<UserData>  {
    protected String name;
    protected String email;
    protected String username;
    protected HashMap<String, CourseData> courses = new HashMap<>();

    protected String passwordHash;
    public UserData(){}

    public UserData(String username, String password, String name, String email) {
        this.name = name;
        this.email = email;
        this.username = username;
        setPasswordHash(password);
    }

    public String getUsername() {
        return username;
    }
    public static boolean isEmailValid(String email) {
        return true;
//        return email != null && email.contains("@") &&
//                email.lastIndexOf('.') > email.lastIndexOf('@');
    }

    String getHash(String a){

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(a.getBytes());
            return String.format("%032X", new BigInteger(1, hashed));
            /// X = uppercase hex, 32 = size in bytes (1 byte is 2 hex), 0 means pad with 0
            /// 1 = positive
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Password hash not found!");
            System.exit(1);
            return "";
        }
    }
    private void setPasswordHash(String input) {
        this.passwordHash = getHash(input);
    }

    public boolean canLogin(String password){
        return getHash(password).equals(this.passwordHash);
    }

    public Optional<CourseData> getCourse(String courseName){
        if(!courses.containsKey(courseName))
            return Optional.empty();
        return Optional.of(courses.get(courseName));
    }

    @Override
    public int compareTo(UserData user) {
        return  username.compareTo(user.username);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

}

