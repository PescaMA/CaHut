package org.classes;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TimerQuiz {
    protected int timeLimit;
    protected Answer answer;
    static protected int CHECK_TIME = 5; /// print every CHECK_TIME seconds

    public TimerQuiz(Answer answer) {
        this.timeLimit = answer.getQuestion().getTimeAlloc();
        this.answer = answer;
    }

    public long run(){
        Future<ArrayList<Boolean>> futureAns = null; /// will hold the result.

        Callable<ArrayList<Boolean>> answerTask = () -> {
            answer.addAnswer();
            return answer.getAnswer(); }; /// this is the program (=task) that will run in another thread.

        long startTime = System.currentTimeMillis();
        long remainingTime ;
        ExecutorService executor = Executors.newSingleThreadExecutor(); /// this manages threads
        try{
            futureAns = executor.submit(answerTask); /// new thread executes the task

            do {
                long currentTime = System.currentTimeMillis();
                remainingTime = timeLimit - (currentTime - startTime) / 1000;
                System.out.println("Remaining time: " + remainingTime + " seconds. ");

                if (remainingTime <= 0)
                    throw new TimeoutException("Time is up");

                int checkTime = CHECK_TIME;

                for (int i = 0; i < checkTime * 10; i++) {
                    if(i%10 == 0){
                        currentTime = System.currentTimeMillis();
                        remainingTime = timeLimit - (currentTime - startTime) / 1000;
                        if (remainingTime <= CHECK_TIME)
                            checkTime = 1;
                    }

                    //noinspection BusyWait
                    Thread.sleep(100);
                    if (futureAns.isDone())
                        break;
                }

            } while (!futureAns.isDone());

        }
        catch (TimeoutException e){
            System.out.println("Time's up! ");
            futureAns.cancel(true);
        }
        catch (InterruptedException e){
            System.out.println("Error while getting answer! ");
        }
        finally {
            executor.shutdown();
        }
        return (System.currentTimeMillis() - startTime) ; /// time passed in ms
    }
}
