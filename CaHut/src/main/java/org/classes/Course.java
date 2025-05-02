package org.classes;

import java.util.*;

public class Course {
    private String name;
    private final TreeMap<String, Student> students = new TreeMap<>();

    private final HashMap <String, Quiz> quizzes = new HashMap<>();

    public Set<String> getQuizzes() {
        return quizzes.keySet();
    }
    public Optional<Quiz> getQuiz(String quizName){
        if(!quizzes.containsKey(quizName))
            return Optional.empty();
        return Optional.of(quizzes.get(quizName));
    }

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(User whoAdds, Student newStudent){
        if(!(whoAdds instanceof Teacher))
            return;
        students.put(newStudent.getUsername(), newStudent);
        newStudent.addCourse(this);
    }

    public void endQuiz(Quiz quiz){

    }
    public void runQuiz(Quiz quiz){

    }

    public void addQuiz(Quiz quiz){
        quizzes.put(quiz.getName(), quiz);
    }

    public Set<String> getStudents() {
        return students.keySet();
    }

}
