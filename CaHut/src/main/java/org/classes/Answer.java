package org.classes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Answer {
    protected ArrayList<Boolean> answer;
    protected Question question;


    private boolean validateIdx(int idx){
        return 0 < idx && idx < answer.size();
    }

    public Answer(Question question) {
        this.question = question;
        this.answer = question.getInitialAnswers();
    }
    public Question getQuestion() {
        return question;
    }
    public ArrayList<Boolean> getAnswer() {
        return answer;
    }

    public void addAnswer() throws InterruptedException, IOException {
        final Reader rdr = new InputStreamReader(System.in); /// to check for input without stopping thread
        final Scanner scanner = new Scanner(System.in);

        System.out.println(question.getAnswerChoices(answer));
        System.out.println("Enter answer position (or 0 to submit): ");

        int idx;

        while (true){
            if(Thread.currentThread().isInterrupted())
                return;

            if(rdr.ready()) { ///  check if user submitted input
                try {
                    idx = scanner.nextInt();
                }
                catch(InputMismatchException e){
                    scanner.next();
                    System.out.println("Incorrect position! Values should be between 1 - " + answer.size());
                    continue;
                }
                break;
            }

            //noinspection BusyWait
            Thread.sleep(50);
        }

        if(idx == 0) ///  signal to lock in answer
            return;

        if(!validateIdx(idx))
            System.out.println("Incorrect position! Values should be between 1 - " + answer.size());
        else
            answer.set(idx-1, !answer.get(idx-1));

        addAnswer();
    }


}
