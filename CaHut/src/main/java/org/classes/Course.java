package org.classes;

import java.util.*;
import java.util.stream.Collectors;

public class Course {
    private final String name;
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

    public void addStudent(User whoAdds, Student newStudent){
        if(!(whoAdds instanceof Teacher))
            return;
        students.put(newStudent.getUsername(), newStudent);
        newStudent.addCourse(this);
    }

    public void addQuiz(Quiz quiz){
        quizzes.put(quiz.getName(), quiz);
    }

    public Set<String> getStudents() {
        return students.keySet();
    }

    public List<Map.Entry<String, Integer>> getStudentsByScore(){

        Map<String, Integer> scores = new HashMap<>();
        for( String student : students.keySet()){
            int score = 0;
            for( String quizName : quizzes.keySet()){
                score += students.get(student).getScore(quizName);
            }
            scores.put(student, score);
        }
        List< Map.Entry<String, Integer> > entries = new ArrayList<>(scores.entrySet());

        entries.sort((a, b) -> b.getValue() - a.getValue()); // descending

        return entries;
    }

}
