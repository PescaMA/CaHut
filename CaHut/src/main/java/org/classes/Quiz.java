package org.classes;

import java.util.ArrayList;
import java.util.Collection;

public class Quiz {
    public User creator;
    ArrayList<Question> questions;

    public Quiz(User creator, Collection<Question> q){
        this.creator = creator;
        questions = new ArrayList<>(q);}

    void addQuestions(Collection<Question> q){
        questions.addAll(q);
    }

    void startQuiz(Student student){
        for(Question question : questions){
            Answer answer = new Answer(question);
            TimerQuiz timer = new TimerQuiz(answer);
            timer.run();
        }
    }
}