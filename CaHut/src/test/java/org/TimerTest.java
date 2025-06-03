package org;

import org.classes.AnswerData;
import org.classes.QuestionData;
import org.classes.TimerQuizData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TimerTest {
    @Test
    void testTimer(){
        QuestionData test = new QuestionData("what contains c?",10, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
        AnswerData answer = new AnswerData(test);
        TimerQuizData timer = new TimerQuizData(answer);
        long remainingTime = timer.run();
        System.out.println(remainingTime);
    }
}