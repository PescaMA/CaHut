package org.models;

import org.classes.*;
import org.database.DatabaseClass;

import java.util.*;

public class QuestionDB extends QuestionData implements DatabaseClass<QuestionDB> {
    protected long pk = -1;
    protected long quiz_pk = -1;
    List<String> correctAnswers = null;
    List<String> wrongAnswers = null;

    public QuestionDB(boolean create){
        if(create) createTable();
    }
    public QuestionDB(String body, int timeAlloc, List<String> correctAnswers, List<String> wrongAnswers) {
        super(body,timeAlloc,correctAnswers,wrongAnswers);
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }
    public long getQuiz_pk() {
        return quiz_pk;
    }

    public void setQuiz_pk(long quiz_pk) {
        this.quiz_pk = quiz_pk;
    }

    @Override
    public QuestionDB makeNew(){return new QuestionDB(false);}

    @Override
    public String tableName() {
        return "question";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("body", "varchar(200)"));
        columns.add(new AbstractMap.SimpleEntry<>("time_alloc", "integer "));
        columns.add(new AbstractMap.SimpleEntry<>("correct", "varchar(100) array"));
        columns.add(new AbstractMap.SimpleEntry<>("wrong", "varchar(100) array"));
        columns.add(new AbstractMap.SimpleEntry<>("quiz_pk", "int references quiz(quiz_id)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(body));
        values.add(String.valueOf(timeAlloc));
        values.add("{" + String.join(",", correctAnswers) + "}");
        values.add("{" + String.join(",", wrongAnswers) + "}");
        values.add(String.valueOf(quiz_pk));

        return values;
    }

    @Override
    public long pk() {
        return pk;
    }

    @Override
    public void load(long queryPk) {
        pk = queryPk;
        Optional<ArrayList<String>> values = queryByPk(queryPk);
        if (values.isEmpty()) return;

        body = values.get().getFirst();
        timeAlloc = Integer.parseInt(values.get().get(1));
        correctAnswers = Arrays.asList(values.get().get(2).replaceAll("[{}]", "").split(","));
        wrongAnswers = Arrays.asList(values.get().get(3).replaceAll("[{}]", "").split(","));
        quiz_pk = Long.parseLong(values.get().get(4));

        init(correctAnswers,wrongAnswers);

    }

    @Override
    public QuestionDB save() {
        pk = insert();
        return this;
    }

}
