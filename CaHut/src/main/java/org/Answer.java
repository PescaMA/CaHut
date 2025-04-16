package org;

import java.util.ArrayList;
import java.util.Scanner;

public class Answer {
    protected ArrayList<Boolean> answer;

    public Question getQuestion() {
        return question;
    }

    protected Question question;

    public Answer(Question question) {
        this.question = question;
        this.answer = question.getInitialAnswers();
    }
    public ArrayList<Boolean> getAnswer() {
        return answer;
    }

    boolean validateIdx(int idx){
        return 0 < idx && idx < answer.size();
    }

    public void addAnswer(){
        Scanner myObj = new Scanner(System.in);
        System.out.println(question.getAnswerChoices(answer));
        System.out.println("Enter answer position (or 0 to submit): ");
        int idx = myObj.nextInt();

        if(idx == 0)
            return;

        if(!validateIdx(idx))
            System.out.println("Incorrect position! Values should be between 1 - " + answer.size());
        else
            answer.set(idx, !answer.get(idx));


        addAnswer();

    }

    public void printAnswer(){
        System.out.println(question.getAnswerChoices(answer));
    }
}
