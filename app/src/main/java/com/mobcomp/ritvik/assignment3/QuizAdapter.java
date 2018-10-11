package com.mobcomp.ritvik.assignment3;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizHolder> {

    private Context mContext;
    private Cursor mCursor;
    private static RecyclerViewClickListener itemListener;

    public QuizAdapter(Context context, Cursor cursor, quesListFragment quesListFragment){

        mContext = context;
        mCursor = cursor;
        this.itemListener = quesListFragment;

    }

    public class QuizHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView QuestionText;
        public QuizHolder( View itemView) {
            super(itemView);
            QuestionText = itemView.findViewById(R.id.questionTextView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
            //String item = itemListener.;
            Toast.makeText(mContext, "An item was clicked", Toast.LENGTH_SHORT).show();

            // get position
            int pos = getAdapterPosition();

            // check if item still exists
            if(mCursor.moveToFirst() )
            {

                String clickedQuestion = mCursor.getString(mCursor.getColumnIndex(QuestionList.question.COl_QUES));
                String clickedAnswer = mCursor.getString(mCursor.getColumnIndex(QuestionList.question.COL_CorrAns));
                //String clickedDataItem = mCursor.getString(mCursor.getColumnIndex(QuestionList.question.COl_QUES));
                //Toast.makeText(v.getContext(), "You clicked " + clickedDataItem, Toast.LENGTH_SHORT).show();


                //getSupportFragmentManager().beginTransaction().add(R.id.mainFrame, frag, "FragTag").disallowAddToBackStack().commit();

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                quesDetailFragment frag = new quesDetailFragment();

                Bundle mBundle = new Bundle();
                mBundle.putString("qText", clickedQuestion);
                mBundle.putString("qAnswer", clickedAnswer);
                frag.setArguments(mBundle);

                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainFrame, frag, "DetailFragTag").addToBackStack(null).commit();

            }

        }
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.question_layout, parent, false);
        return new QuizHolder(view);
    }

    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        if (!mCursor.move(position)){
            return;
        }

        String ques = mCursor.getString(mCursor.getColumnIndex(QuestionList.question.COl_QUES));

        try {
            holder.QuestionText.setText(ques);
        }
        catch (Exception e) {
            Log.d("NEW", "onBindViewHolder: New code didn't cork");
        }

        //String ques = mCursor.getString(mCursor.getColumnIndex(QuestionList.question.COl_QUES));

        //((QuizHolder)holder).QuestionText.setText(ques);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

}
