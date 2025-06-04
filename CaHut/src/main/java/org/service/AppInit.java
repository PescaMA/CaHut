package org.service;

import org.classes.QuizData;
import org.classes.StudentData;
import org.classes.TeacherData;
import org.models.*;
import org.postgresql.core.v3.QueryExecutorImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AppInit {
    private final HashMap<String, UserDB> users = new HashMap<>();
    private final HashMap<Long, UserDB> usersByPk = new HashMap<>();
    private final HashMap<String, CourseDB> courses = new HashMap<>();
    private final HashMap<Long, CourseDB> coursesByPk = new HashMap<>();
    private final HashMap<String, QuizDB> quizzes = new HashMap<>();
    private final HashMap<Long, QuizDB> quizzesByPk = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    StudentDB studentManager;
    TeacherDB teacherManager;
    CourseDB courseManager;
    CourseStudentDB courseStudentManager;
    QuizDB quizManager;
    QuizCourseDB quizCourseManager;
    QuestionDB questionManager;

    static AppInit instance = null;

    static public AppInit getInstance() {
        if(instance == null)
            instance = new AppInit();
        return instance;
    }
    private AppInit(){
        createManagers();
        loadUsers();
        loadCourses();
        loadQuizzes();
        loadQuestions();
    }
    protected void createManagers(){
        studentManager = new StudentDB(true);
        teacherManager = new TeacherDB(true);
        courseManager = new CourseDB(true);
        courseStudentManager = new CourseStudentDB(true);
        quizManager = new QuizDB(true);
        quizCourseManager = new QuizCourseDB(true);
        questionManager = new QuestionDB(true);
    }

    public HashMap<String, UserDB> getUsers() {
        return users;
    }
    public Scanner getScanner() {
        return scanner;
    }
    public void addUser(UserDB user){
        users.put(user.getUsername(), user);
        usersByPk.put(user.pk(), user);
    }
    public void addCourse(CourseDB course){
        coursesByPk.put(course.pk(), course);
        courses.put(course.getName(), course);
    }
    public void addQuiz(QuizDB quiz){
        quizzesByPk.put(quiz.pk(), quiz);
        quizzes.put(quiz.getName(),quiz);
    }

    public void linkStudent(String courseName, String studentName){
        new CourseStudentDB(courses.get(courseName), (StudentData) users.get(studentName) ).save();
    }
    public void linkQuiz(String courseName, String quizName){
        new QuizCourseDB(courses.get(courseName), quizzes.get(quizName)).save();
    }

    protected void loadUsers(){
        ArrayList<UserDB> allUsers = studentManager.loadAllUsers();
        allUsers.addAll(teacherManager.loadAllUsers());

        for(UserDB user : allUsers){
            addUser(user);
        }
    }
    protected void loadCourses(){

        ArrayList<CourseDB> allCourses = courseManager.loadAll();
        for(CourseDB course:allCourses){
            addCourse(course);
            TeacherData t = (TeacherData) usersByPk.get(course.getTeacher_pk());

            System.out.println(t + " " + course.getTeacher_pk());
            if(t == null)
                return;


            t.addCourse(course);

        }

        ArrayList<CourseStudentDB> allCourseStudentRelations = courseStudentManager.loadAll();
        for(CourseStudentDB courseStudent:allCourseStudentRelations){
            StudentData s = (StudentData) usersByPk.get(courseStudent.getStudent_pk());
            CourseDB c = coursesByPk.get(courseStudent.getCourse_pk());
            TeacherData t = (TeacherData) usersByPk.get(c.getTeacher_pk());
            t.addStudent(c.getName(), s);
        }
    }
    protected void loadQuizzes(){
        ArrayList<QuizDB> allQuizzes = quizManager.loadAll();

        for(QuizDB quiz : allQuizzes){
            addQuiz(quiz);
        }

        ArrayList<QuizCourseDB> allQuizCourseRelations = quizCourseManager.loadAll();
        for(QuizCourseDB courseStudent:allQuizCourseRelations){
            QuizData q = quizzesByPk.get(courseStudent.getQuiz_pk());
            CourseDB c = coursesByPk.get(courseStudent.getCourse_pk());
            if(c!= null)
                c.addQuiz(q);
        }
    }
    protected void loadQuestions(){
        ArrayList<QuestionDB> allQuestions = questionManager.loadAll();

        for(QuestionDB question : allQuestions){
            QuizDB quiz = quizzesByPk.get(question.getQuiz_pk());
            quiz.addQuestion(question);
        }
    }
}
