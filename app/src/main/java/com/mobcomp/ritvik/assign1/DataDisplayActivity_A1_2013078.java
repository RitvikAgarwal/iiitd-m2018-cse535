package com.mobcomp.ritvik.assign1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DataDisplayActivity_A1_2013078 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed to Created");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed to Created",
                Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_data_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String roll = intent.getStringExtra("ROLL");
        String branch = intent.getStringExtra("BRANCH");
        String C1 = intent.getStringExtra("C1");
        String C2 = intent.getStringExtra("C2");
        String C3 = intent.getStringExtra("C3");
        String C4 = intent.getStringExtra("C4");

        TextView TV = (TextView) findViewById(R.id.DispText);
        TV.setText(
                new StringBuilder().append("Name : " + name + "\n").
                        append("Roll number : " + roll + "\n").
                        append("Branch : " + branch + "\n").
                        append("Course 1 : " + C1 + "\n").
                        append("Course 2 : " + C2 + "\n").
                        append("Course 3 : " + C3 + "\n").
                        append("Course 4 : " + C4 + "\n").toString());

    }

    public void onStart()
    {
        super.onStart();
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed from Created to Started");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Created to Started",
                Toast.LENGTH_SHORT).show();
    }
    public void onRestart()
    {
        super.onRestart();
        Log.i("INFO", "State of activity DataDisplayActivity changed from Stopped to Started");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Stopped to Started",
                Toast.LENGTH_SHORT).show();
    }
    public void onResume()
    {
        super.onResume();
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed from Started/Paused to Resumed");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Started/Paused to Resumed",
                Toast.LENGTH_SHORT).show();
    }
    public void onPause()
    {
        super.onPause();
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed from Resumed to Paused");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Resumed to Paused",
                Toast.LENGTH_SHORT).show();
    }
    public void onStop()
    {
        super.onStop();
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed from Paused to Stopped");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Paused to Stopped",
                Toast.LENGTH_SHORT).show();
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("INFO", "State of activity DataDisplayActivity " +
                "changed from Stopped to Destroyed");
        Toast.makeText(this,
                "State of activity DataDisplayActivity changed from Stopped to Destroyed",
                Toast.LENGTH_SHORT).show();
    }

}
