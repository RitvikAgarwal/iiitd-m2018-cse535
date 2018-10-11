package com.mobcomp.ritvik.assignment3;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quesListFragment frag = new quesListFragment();
//        android.app.FragmentManager FragManage = getFragmentManager();
//        android.app.FragmentTransaction FTobj = FragManage.beginTransaction();
//        FTobj.add(R.id.mainFrame, frag);
//        FTobj.commit();
        getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, frag, "FragTag").disallowAddToBackStack().commit();

    }
}
