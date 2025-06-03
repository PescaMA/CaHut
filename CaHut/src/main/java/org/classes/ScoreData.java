package org.classes;

import java.util.ArrayList;

public class ScoreData {
    @Override
    public String toString() {
        return "{ score=" + score +
                ", streak=" + streak + "}";
    }
    protected ArrayList<Integer> scores = new ArrayList<>();

    protected int score = 0;
    protected int streak = 0;

    static private final int BASE_SCORE = 1000;
    
    public ScoreData(){}
    
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
        double timeRemainingPrc = 1 - ( (double) timeElapsed / (totalTime * 1000)); /// totalTime is in seconds, timeElapsed in ms
        double speedMultiplier = Math.min(Math.max(timeRemainingPrc,  .2), .8);
        score += (int) Math.ceil(Math.max(Math.log((double) streak + 1), 1.d) * speedMultiplier * BASE_SCORE);
        scores.add(score);
    }
    
}
