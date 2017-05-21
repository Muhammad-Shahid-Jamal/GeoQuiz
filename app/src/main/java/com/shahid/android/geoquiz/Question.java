package com.shahid.android.geoquiz;

/**
 * Created by shahid on 4/22/2017.
 */

public class Question {
    private int mTextResId;
    private boolean mAnsTrue;

    public Question(int mTextResId,boolean mAnsTrue){
        this.mTextResId = mTextResId;
        this.mAnsTrue = mAnsTrue;
    }

    public int getmTextResId() {
        return this.mTextResId;
    }

    public boolean ismAnsTrue() {
        return this.mAnsTrue;
    }
}
