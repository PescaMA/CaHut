package org;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void differentUsersTest(){
        User test = new Student ();
        User test1 = new User ();
        User test2 = new Teacher ();
        Assertions.assertEquals(test.getId() + 2, test2.getId());
    }
}