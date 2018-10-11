package com.mobcomp.ritvik.assignment3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobcomp.ritvik.assignment3.QuestionList.*;

public class QuizDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "QuizApp.db";
    public static final int DB_VER = 1;

    public QuizDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TBL = "CREATE TABLE IF NOT EXISTS " +
                question.TABLE_NAME + "(" +
                question._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                question.COl_QUES + " TEXT NOT NULL, " +
                question.COL_CorrAns + " INTEGER NOT NULL, " +
                question.COL_UserAns + " INTEGER" + ");";
        Log.d("SQLiteQuery", "onCreate: " + SQL_CREATE_TBL);
        db.execSQL(SQL_CREATE_TBL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + question.TABLE_NAME);
        onCreate(db);
    }
}
