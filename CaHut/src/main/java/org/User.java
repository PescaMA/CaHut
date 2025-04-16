package org;

@SuppressWarnings("unused")
public class User implements Comparable {
    protected String name;
    protected String email;
    protected String username;
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

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Object o) {
        User user = (User) o;
        return  getId()  - user.getId() ;

    }
}
