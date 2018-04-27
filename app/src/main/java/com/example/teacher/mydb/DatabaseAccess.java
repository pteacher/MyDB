package com.example.teacher.mydb;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TEACHER on 27.04.2018.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }
    public void addQuote(String element) {
        this.database.execSQL("INSERT INTO questions values (NULL, '" + element +  "', '42', '2', '2', '2')");
    }

    /*
    class Question {
        private String question;
        private String[] answers = new String[4];

        public Question(String question, String[] answers) {
            this.question = question;
            this.answers[0] = answers[0];
            this.answers[1] = answers[1];
            this.answers[2] = answers[2];
            this.answers[3] = answers[3];
        }

        public String getQuestion() {
            return question;
        }
    }
    */

    public List<String> getQuotes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM questions", null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            // String[] answers = {cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)};
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
