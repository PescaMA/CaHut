package org;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        User test = new Student ();
        User test1 = new User ();
        User test2 = new Teacher ();
        System.out.println(test.getId() + " " +  test1.getId() + " " + test2.getId());
    }
}