package org;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TimerQuiz {
    protected int timeLimit;
    protected Answer answer;

    public TimerQuiz(Answer answer) {
        this.timeLimit = answer.getQuestion().getTimeAlloc();
        this.answer = answer;
    }

    public void run(){
        Future<ArrayList<Boolean>> futureAns = null; /// will hold the result.

        Callable<ArrayList<Boolean>> answerTask = () -> {
            answer.addAnswer();
            return answer.getAnswer(); }; /// this is the program (=task) that will run in another thread.

        ExecutorService executor = Executors.newSingleThreadExecutor(); /// this manages threads
        try{
            futureAns = executor.submit(answerTask); /// new thread executes the task

            ArrayList<Boolean> answer = futureAns.get(timeLimit, TimeUnit.SECONDS); ///this will have the answer
            System.out.println(answer);
        }
        catch (TimeoutException e){
            System.out.println("Time's up! ");
            futureAns.cancel(true);
        }
        catch (InterruptedException | ExecutionException e){
            System.out.println("Error while getting answer! ");
        }
        finally {
            executor.shutdown();
        }
    }
}
