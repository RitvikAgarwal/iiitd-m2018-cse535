package com.mobcomp.ritvik.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class quesListFragment extends Fragment implements RecyclerViewClickListener {

    private QuizAdapter mAdapter;
    private SQLiteDatabase mDatabase;


    public quesListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_ques_list, container, false);

        QuizDBHelper dbHelper = new QuizDBHelper(getContext());
        mDatabase = dbHelper.getWritableDatabase();

        try {
            InputStream qinputStream = getResources().openRawResource(R.raw.quizquestions);
            InputStream ainputStream = getResources().openRawResource(R.raw.quizanswers);
            BufferedReader qbufferedReader= new BufferedReader(new InputStreamReader(qinputStream));
            BufferedReader abufferedReader= new BufferedReader(new InputStreamReader(ainputStream));

            String question;
            String answer;
            while ((question = qbufferedReader.readLine()) != null && (answer = abufferedReader.readLine()) != null) {
                ContentValues cv = new ContentValues();
                cv.put(QuestionList.question.COl_QUES, question);
                cv.put(QuestionList.question.COL_CorrAns, answer);

                Log.d("File reading", "onCreate: " + question);

                mDatabase.insert(QuestionList.question.TABLE_NAME, null, cv);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        RecyclerView recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setHasFixedSize(true);

        //Log.i("LITERAL DEBUG", "onCreate: Class of getAllItems(), needs to be cursor  " +  getAllItems().getClass().getName());

        mAdapter = new QuizAdapter(getContext(), (Cursor) getAllItems(), this);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    private Cursor getAllItems()
    {
        return mDatabase.query(QuestionList.question.TABLE_NAME, null, null, null, null, null, null);
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {

    }
}

