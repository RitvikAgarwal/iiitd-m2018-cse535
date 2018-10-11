package com.mobcomp.ritvik.assignment3;

import android.provider.BaseColumns;

public class QuestionList {

    private QuestionList() {}

    public static final class question implements BaseColumns {
        public static final String TABLE_NAME = "QuizQnA";
        public static final String COl_QUES = "question";
        public static final String COL_CorrAns = "answer";
        public static final String COL_UserAns = "userAns";
    }


}
