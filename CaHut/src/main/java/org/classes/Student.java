package org.classes;

import java.util.HashMap;
import java.util.Set;

@SuppressWarnings("unused")
public class Student extends User {
    protected HashMap<String, Score> scores = new HashMap<>();

    public Student(String username, String password, String name, String email){
        super(username,password,name, email);
    }

    public void addCourse(Course course){
        courses.put(course.getName(), course);
    }
    public Set<String> getCourses(){
        return courses.keySet();
    }
    public void updateScore(String quizName, Score newScore){
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
}
