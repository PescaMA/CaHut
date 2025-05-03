package org.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Optional;

public class User implements Comparable<User> {
    protected String name;
    protected String email;

    public String getUsername() {
        return username;
    }

    protected String username;
    protected HashMap<String, Course> courses = new HashMap<>();

    private String passwordHash;
    private final int id;
    private static int maxId;
    {
        maxId++;
        id = maxId;
    }

    public User(String username, String password,String name, String email) {
        this.name = name;
        this.email = email;
        this.username = username;
        setPasswordHash(password);
    }
    public static boolean isEmailValid(String email) {
        return email != null && email.contains("@") &&
                email.lastIndexOf('.') > email.lastIndexOf('@');
    }

    String getHash(String a){

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(a.getBytes());
            return String.format("%032X", new BigInteger(1, hashed));/// 1 = positive. pad with 0s and then hex
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("ERROR ON LOGIN");
            return  String.format("%032X", new BigInteger(1, a.getBytes()));
        }
    }
    private void setPasswordHash(String input) {
        this.passwordHash = getHash(input);
    }

    public boolean canLogin(String password){
        return getHash(password).equals(this.passwordHash);
    }

    public Optional<Course> getCourse(String courseName){
        if(!courses.containsKey(courseName))
            return Optional.empty();
        return Optional.of(courses.get(courseName));
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(User user) {
        return  getId()  - user.getId() ;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
