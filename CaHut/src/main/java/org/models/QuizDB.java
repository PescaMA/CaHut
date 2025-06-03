package org.models;

import org.classes.*;
import org.database.DatabaseClass;

import java.util.*;

public class QuizDB extends QuizData implements DatabaseClass<QuizDB> {
    protected long pk = -1;

    public QuizDB(boolean create){
        if(create) createTable();
    }
    public QuizDB(UserData creator, String name, Collection<QuestionData> q){
        super(creator,name,q);
    }

    @Override
    public QuizDB makeNew(){return new QuizDB(false);}

    @Override
    public String tableName() {
        return "quiz";
    }

    @Override
    public ArrayList<Map.Entry<String, String>> tableColumns() {
        ArrayList<Map.Entry<String, String>> columns = new ArrayList<>();

        columns.add(new AbstractMap.SimpleEntry<>("name", "varchar(64)"));

        return columns;
    }

    @Override
    public ArrayList<String> tableValues() {
        ArrayList<String> values = new ArrayList<>();

        values.add(String.valueOf(name));

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

        name = values.get().getFirst();

    }

    @Override
    public QuizDB save() {
        pk = insert();
        return this;
    }

}
