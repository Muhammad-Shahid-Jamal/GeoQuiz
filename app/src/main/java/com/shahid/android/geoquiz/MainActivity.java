package com.shahid.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private TextView mTextView;
    private Button mNextBtn;
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
        setContentView(R.layout.activity_main);
        mTrueBtn = (Button) findViewById(R.id.btn_true);
        mFalseBtn = (Button) findViewById(R.id.btn_false);
        mNextBtn = (Button) findViewById(R.id.btn_next);
        mTextView = (TextView) findViewById(R.id.question_text_view);
        this.upDateQuestion();
        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.correct_toast,Toast.LENGTH_LONG).show();
            }
        });

        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,R.string.incorrect_toast,Toast.LENGTH_LONG).show();
            }
        });

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("before Module :" , ""+mQuestionBank.length);
                indexOfQuestion = (indexOfQuestion + 1) % mQuestionBank.length;
                String checkResult = "= " + indexOfQuestion;
                Log.d("that is the index of : ",checkResult);
                upDateQuestion();
            }
        });
    }

    private void upDateQuestion(){
        int questionOfTextId = mQuestionBank[indexOfQuestion].getmTextResId();
        mTextView.setText(questionOfTextId);
    }
}
