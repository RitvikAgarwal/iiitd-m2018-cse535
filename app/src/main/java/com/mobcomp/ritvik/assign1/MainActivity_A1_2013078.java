package com.mobcomp.ritvik.assign1;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity_A1_2013078 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("INFO", "State of activity MainActivity " +
                "changed to Created");
        Toast.makeText(this,
                "State of activity MainActivity changed to Created",
                Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_main);

        final Button ClearB;
        ClearB = findViewById(R.id.clearBut);
        ClearB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Clear button is pressed");
                clearForm(v);
            }
        });

        final Button SubmitB;
        SubmitB = findViewById(R.id.submitBut);
        SubmitB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("DEBUG", "Submit button is pressed");
                submitFunc(v);
            }
        });


    }

    public void onStart()
    {
        super.onStart();
        Log.i("INFO", "State of activity MainActivity " +
                "changed from Created to Started");
        Toast.makeText(this,
                "State of activity MainActivity changed from Created to Started",
                Toast.LENGTH_SHORT).show();
    }
    public void onRestart()
    {
        super.onRestart();
        Log.i("INFO", "State of activity MainActivity changed from Stopped to Started");
        Toast.makeText(this,
                "State of activity MainActivity changed from Stopped to Started",
                Toast.LENGTH_SHORT).show();
    }
    public void onResume()
    {
        super.onResume();
        Log.i("INFO", "State of activity MainActivity " +
                "changed from Started/Paused to Resumed");
        Toast.makeText(this,
                "State of activity MainActivity changed from Started/Paused to Resumed",
                Toast.LENGTH_SHORT).show();
    }
    public void onPause()
    {
        super.onPause();
        Log.i("INFO", "State of activity MainActivity " +
                "changed from Resumed to Paused");
        Toast.makeText(this,
                "State of activity MainActivity changed from Resumed to Paused",
                Toast.LENGTH_SHORT).show();
    }
    public void onStop()
    {
        super.onStop();
        Log.i("INFO", "State of activity MainActivity " +
                "changed from Paused to Stopped");
        Toast.makeText(this,
                "State of activity MainActivity changed from Paused to Stopped",
                Toast.LENGTH_SHORT).show();
    }
    public void onDestroy()
    {
        super.onDestroy();
        Log.i("INFO", "State of activity MainActivity " +
                "changed from Stopped to Destroyed");
        Toast.makeText(this,
                "State of activity MainActivity changed from Stopped to Destroyed",
                Toast.LENGTH_SHORT).show();
    }


    public void clearForm(View v) {
        ((EditText) findViewById(R.id.editText2)).setText("");
        ((EditText) findViewById(R.id.editText3)).setText("");
        ((EditText) findViewById(R.id.editText4)).setText("");
        ((EditText) findViewById(R.id.editText5)).setText("");
        ((EditText) findViewById(R.id.editText6)).setText("");
        ((EditText) findViewById(R.id.editText7)).setText("");
        ((EditText) findViewById(R.id.editText8)).setText("");
    }

    public void submitFunc(View v) {
        Intent intent = new Intent(this, DataDisplayActivity_A1_2013078.class);
        String name = ((EditText) findViewById(R.id.editText2)).getText().toString();
        String branch = ((EditText) findViewById(R.id.editText3)).getText().toString();
        String C3 = ((EditText) findViewById(R.id.editText4)).getText().toString();
        String C1 = ((EditText) findViewById(R.id.editText5)).getText().toString();
        String rollNum = ((EditText) findViewById(R.id.editText6)).getText().toString();
        String C4 = ((EditText) findViewById(R.id.editText7)).getText().toString();
        String C2 = ((EditText) findViewById(R.id.editText8)).getText().toString();

        intent.putExtra("NAME", name);
        intent.putExtra("ROLL", rollNum);
        intent.putExtra("BRANCH", branch);
        intent.putExtra("C1", C1);
        intent.putExtra("C2", C2);
        intent.putExtra("C3", C3);
        intent.putExtra("C4", C4);

        startActivity(intent);
    }

}