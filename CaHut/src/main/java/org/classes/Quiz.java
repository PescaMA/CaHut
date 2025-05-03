package org.classes;

import java.util.ArrayList;
import java.util.Collection;

public class Quiz {
    public User creator;



    protected String name;
    private final ArrayList<Question> questions;

    private final int id;
    private static int maxId;
    {
        maxId++;
        id = maxId;
    }

    public String getName() {
        return id + "." + name;
    }
    public Quiz(User creator, String name,  Collection<Question> q){
        this.creator = creator;
        this.name = name;
        questions = new ArrayList<>(q);}

    public Score startQuiz(){
        Score finalScore = new Score();
        for(Question question : questions){
            Answer answer = new Answer(question);
            TimerQuiz timer = new TimerQuiz(answer);
            long time = timer.run(); /// note: will change answer.
            if(question.isCorrect(answer.answer)){
                System.out.println("CORRECT!");
            }
            else{
                System.out.println("WRONG!");
            }
            finalScore.calculate(time, question.getTimeAlloc(), question.isCorrect(answer.answer));
            System.out.println(finalScore);
        }
        return finalScore;
    }
}