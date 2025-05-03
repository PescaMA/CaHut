package org.classes;

import java.util.ArrayList;

public class Score {
    @Override
    public String toString() {
        return "{ score=" + score +
                ", streak=" + streak + "}";
    }
    protected ArrayList<Integer> scores = new ArrayList<>();

    protected int score = 0;
    protected int streak = 0;

    static private final int BASE_SCORE = 1000;
    
    public Score(){}
    
    public int getScore() {
        return score;
    }
    public void calculate(long timeElapsed, int totalTime,  boolean correct){
        if(!correct){
            streak = 0;
            scores.add(score);
            return;
        }
        streak+=1;

        double timeProc = Math.max(1 - ( (double) timeElapsed / (totalTime * 1000)),  .2);
        score += (int) Math.ceil(Math.max(Math.log((double) streak + 1), 1.d) * timeProc * BASE_SCORE);
        scores.add(score);
    }
    
}
