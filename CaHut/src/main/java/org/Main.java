package org;

import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Question test = new Question("what contains c?",10, Arrays.asList("c1","c2"), Arrays.asList("g1","g2"));
        Answer answer = new Answer(test);
        TimerQuiz timer = new TimerQuiz(answer);
        timer.run();
    }
}