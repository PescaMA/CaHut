package org.classes;

import java.util.ArrayList;
import java.util.Collection;

public class QuizData {
    public UserData creator;
    protected String name;
    private final ArrayList<QuestionData> questions;

    public String getName() {
        return name;
    }
    public QuizData(){questions = new ArrayList<>();}
    public QuizData(UserData creator, String name, Collection<QuestionData> q){
        this.creator = creator;
        this.name = name;
        questions = new ArrayList<>(q);}

    public void addQuestion(QuestionData question){
        questions.add(question);
    }

    public ScoreData startQuiz(){
        ScoreData finalScore = new ScoreData();
        for(QuestionData question : questions){
            AnswerData answer = new AnswerData(question);
            TimerQuizData timer = new TimerQuizData(answer);
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