package com.mobcomp.ritvik.assignment3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class quesDetailFragment extends Fragment {

    public quesDetailFragment() {
        // Required empty public constructor
    }

    private RadioGroup radioAnsGrp;
    private RadioButton radioAnsBut;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.fragment_ques_detail, container, false);

        String qText = "";
        String qAns = "";

        Bundle bundle = this.getArguments();
        if(bundle != null){
            qText = bundle.get("qText").toString();
            qAns = bundle.get("qAnswer").toString();
        }

        TextView t = (TextView) view.findViewById(R.id.questionText);
        t.setText(qText + "    " + qAns);

        radioAnsGrp = (RadioGroup) view.findViewById(R.id.ansGrp) ;
        Button saveBut = (Button) view.findViewById(R.id.savBut);

        final String finalQText = qText;
        final String finalQAns = qAns;
        saveBut.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {

               int selectedId = radioAnsGrp.getCheckedRadioButtonId();
               radioAnsBut = (RadioButton) view.findViewById(selectedId);

               Toast.makeText(view.getContext(), radioAnsBut.getText(), Toast.LENGTH_SHORT).show();
//               AppCompatActivity activity = (AppCompatActivity) view.getContext();
//               quesListFragment frag = new quesListFragment();
//
//               Bundle mBundle = new Bundle();
//               mBundle.putString("qText", finalQText);
//               mBundle.putString("qAnswer", finalQAns);
//               mBundle.putString("UserAns", (String) radioAnsBut.getText());
//
//               frag.setArguments(mBundle);
//
//               activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, frag, "DetailFragTag").addToBackStack(null).commit();

           }

       });

        // Inflate the layout for this fragment
        return view;
    }
}
