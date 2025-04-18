package org;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("unused")
public class User implements Comparable<User> {
    protected String name;
    protected String email;
    protected String username;
    private String passwordHash;
    private final int id;
    private static int maxId;

    {
        maxId++;
        id = maxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@") &&
                email.lastIndexOf('.') > email.lastIndexOf('@');
    }

    public void setEmail(String email) {
        if (isEmailValid(email))
            this.email = email;
        else
            throw new IllegalArgumentException("Invalid email format.");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String name, String email, String username) {
        this.name = name;
        this.email = email;
        this.username = username;
    }

    public User() {
    }

    private void setPasswordHash(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashed = md.digest(input.getBytes());
        passwordHash = String.format("%032X", new BigInteger(1, hashed));/// 1 = positive. pad with 0s and then hex
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(User user) {
        return  getId()  - user.getId() ;
    }
}
