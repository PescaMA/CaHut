package org;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void makeTest(){
        Question test = new Question("what?",1, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
        System.out.println(test.printWithAnswers(test.getInitialAnswers()));
    }
}