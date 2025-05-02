package org;

import org.classes.Question;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class QuestionTest {

    @Test
    void makeTest(){
        Question test = new Question("what?",1, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
        System.out.println(test.getAnswerChoices(test.getInitialAnswers()));
    }
}