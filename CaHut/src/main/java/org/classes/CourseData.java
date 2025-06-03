package org.classes;

import java.util.*;

public class CourseData {
    protected String name;
    protected final TreeMap<String, StudentData> students = new TreeMap<>();
    protected final HashMap <String, QuizData> quizzes = new HashMap<>();

    public CourseData(){}
    public CourseData(String name) {
        this.name = name;
    }

    public Set<String> getQuizzes() {
        return quizzes.keySet();
    }
    public Optional<QuizData> getQuiz(String quizName){
        if(!quizzes.containsKey(quizName))
            return Optional.empty();
        return Optional.of(quizzes.get(quizName));
    }

    public String getName() {
        return name;
    }

    public void addStudent(UserData whoAdds, StudentData newStudent){
        if(!(whoAdds instanceof TeacherData))
            return;
        students.put(newStudent.getUsername(), newStudent);
        newStudent.addCourse(this);
    }

    public void addQuiz(QuizData quiz){
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
