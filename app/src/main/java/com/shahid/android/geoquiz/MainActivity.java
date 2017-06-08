package com.shahid.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_FOR_RES = "result";
    private static final int REQUEST_CODE_CHEAT = 0;
    private Button mTrueBtn;
    private Button mFalseBtn;
    private TextView mTextView;
    private Button mCheatBtn;
    private Button mNextBtn;
    private  boolean mIsCheater;
    //array of question class
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_america,true),
            new Question(R.string.question_asia,true)
    };

    private int indexOfQuestion = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            indexOfQuestion = savedInstanceState.getInt(KEY_INDEX,0);
            this.mIsCheater = savedInstanceState.getBoolean(KEY_FOR_RES);
        }else {
            this.mIsCheater = false;
        }
        setContentView(R.layout.activity_main);
        mTrueBtn = (Button) findViewById(R.id.btn_true);
        mFalseBtn = (Button) findViewById(R.id.btn_false);
        mNextBtn = (Button) findViewById(R.id.btn_next);
        mCheatBtn = (Button) findViewById(R.id.cht_btn);
        mTextView = (TextView) findViewById(R.id.question_text_view);
        this.upDateQuestion();
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indexOfQuestion = (indexOfQuestion + 1) % mQuestionBank.length;
                upDateQuestion();
            }
        });
        mCheatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start a new activity
                boolean mAnsIsTrue = mQuestionBank[indexOfQuestion].ismAnsTrue();
                Intent i = CheatActivity.newIntent(MainActivity.this,mAnsIsTrue);
                startActivityForResult(i,REQUEST_CODE_CHEAT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnsIsShown(data);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        //assigning int value with key value pair style and retrive in onCreate method like $_POST[] in web PHP
        saveInstanceState.putInt(KEY_INDEX,indexOfQuestion);
        saveInstanceState.putBoolean(KEY_FOR_RES,mIsCheater);
    }

    private void upDateQuestion(){
        int questionOfTextId = mQuestionBank[indexOfQuestion].getmTextResId();
        mTextView.setText(questionOfTextId);
    }

    private void checkAnswer(boolean ans){
        boolean ansTrue = mQuestionBank[indexOfQuestion].ismAnsTrue();
        int massageRsId = 0;
        if(mIsCheater){
            massageRsId = R.string.judgment_toast;
        }else {
            if(ans == ansTrue){
                massageRsId = R.string.correct_toast;
            }else {
                massageRsId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this,massageRsId,Toast.LENGTH_LONG).show();
    }

    //this is activity life cycle learning
//    @Override
//    protected void onStart(){
//        super.onStart();
//        Log.d(TAG,"onStart() Called");
//    }
//
//    @Override
//    protected void onPause(){
//        super.onPause();
//        Log.d(TAG,"onPause() Called");
//    }
//
//    @Override
//    protected void onResume(){
//        super.onResume();
//        Log.d(TAG,"onResume() Called");
//    }
//
//    @Override
//    protected void onStop(){
//        super.onStop();
//        Log.d(TAG,"onStop() Called");
//    }
//
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        Log.d(TAG,"onDestroy() Called");
//    }
}
