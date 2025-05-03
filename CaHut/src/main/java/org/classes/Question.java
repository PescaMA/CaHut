package org.classes;

import java.util.*;

public class Question {
    protected String body;
    private final HashMap<String,Boolean> allAnswers = new HashMap<>();
    protected ArrayList<String> displayedAnswers = new ArrayList<>();

public int getTimeAlloc() {
        return timeAlloc;
    }

    protected int timeAlloc;

    public Question(String body, int timeAlloc, List<String> correctAnswers, List<String> wrongAnswers) {
        this.body = body;
        this.timeAlloc = timeAlloc;
        for(String answer:wrongAnswers){
            allAnswers.put(answer,false);
        }
        for(String answer:correctAnswers){
            allAnswers.put(answer,true);
        }
        init();
    }
    public void init(){
        displayedAnswers = new ArrayList<>(allAnswers.keySet());
        Collections.shuffle(displayedAnswers);
    }
    public boolean isCorrect(ArrayList<Boolean> answers){
        if(answers.size() != displayedAnswers.size()) return false;
        for (int idx = 0 ; idx < displayedAnswers.size() ; idx ++){
            if(answers.get(idx) != allAnswers.get(displayedAnswers.get(idx)) )
                return false;
        }
        return true;
    }

    public ArrayList<Boolean> getInitialAnswers(){
        ArrayList<Boolean> result = new ArrayList<>(Arrays.asList(new Boolean[displayedAnswers.size()]));
        Collections.fill(result, Boolean.FALSE);
        return result;
    }

    public StringBuilder getAnswerChoices(ArrayList<Boolean> answers){
        if(answers.size() != displayedAnswers.size()) throw new RuntimeException("not enough answers");
        StringBuilder out = new StringBuilder(body);
        for (int idx = 0 ; idx < displayedAnswers.size() ; idx ++){
            out.append("\n").append(idx + 1).append(". ");
            if(answers.get(idx))
                out.append("[X] ");
            else
                out.append("[ ] ");
            out.append(displayedAnswers.get(idx));
        }
        return out;
    }
}
