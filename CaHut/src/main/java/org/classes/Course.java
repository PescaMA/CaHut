package org.classes;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class Course {
    private String name;
    private final TreeSet <Student> students = new TreeSet<>();
    private final HashSet <Quiz> quizzes = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudents(User whoAdds, List<Student> newStudents){
        if(!(whoAdds instanceof Teacher))
            return;
        students.addAll(newStudents);
        for ( Student student : newStudents)
            student.addCourse(this);
    }

    public void endQuiz(Quiz quiz){

    }
    public void runQuiz(Quiz quiz){

    }

    public void addQuiz(Quiz quiz){
        quizzes.add(quiz);
    }

    public TreeSet<Student> getStudents() {
        return students;
    }

}
