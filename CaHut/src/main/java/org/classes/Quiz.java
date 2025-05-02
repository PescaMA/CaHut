package org.classes;

import java.util.ArrayList;
import java.util.Collection;

public class Quiz {
    public User creator;



    protected String name;
    private ArrayList<Question> questions;

    public String getName() {
        return name;
    }
    public Quiz(User creator, String name,  Collection<Question> q){
        this.creator = creator;
        questions = new ArrayList<>(q);}

    void addQuestions(Collection<Question> q){
        questions.addAll(q);
    }

    public void startQuiz(Student student){
        for(Question question : questions){
            Answer answer = new Answer(question);
            TimerQuiz timer = new TimerQuiz(answer);
            timer.run();
        }
    }
}