package org;

import org.classes.Answer;
import org.classes.Question;
import org.classes.TimerQuiz;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TimerTest {
    @Test
    void testTimer(){
        Question test = new Question("what contains c?",10, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
        Answer answer = new Answer(test);
        TimerQuiz timer = new TimerQuiz(answer);
        timer.run();
    }
}