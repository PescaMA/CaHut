package org.classes;

import org.models.UserDB;

import java.util.*;

@SuppressWarnings("unused")
public class StudentData extends UserDB {
    protected HashMap<String, ScoreData> scores = new HashMap<>();

    public StudentData(){}
    public StudentData(String username, String password, String name, String email){
        super(username,password,name, email);
    }

    public void addCourse(CourseData course){
        courses.put(course.getName(), course);
    }
    public Set<String> getCourses(){
        return courses.keySet();
    }
    public void updateScore(String quizName, ScoreData newScore){
        scores.put(quizName, newScore);
    }
    public int getScore(String quizName){
        if(!scores.containsKey(quizName))
            return -1;
        return scores.get(quizName).getScore();
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                '}';
    }


    @Override
    public StudentData makeNew(){ return new StudentData(); }

}
