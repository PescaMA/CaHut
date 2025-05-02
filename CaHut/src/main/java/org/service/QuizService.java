package org.service;

import org.classes.Question;
import org.classes.Quiz;
import org.classes.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuizService {
    public static Quiz makeQuiz(Teacher teacher){
        return new Quiz(teacher, "Name", Collections.singletonList(new Question()));
    }
}
