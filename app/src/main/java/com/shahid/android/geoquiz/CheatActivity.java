package com.shahid.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANS_IS_TRUE = "com.shahid.android.geoquiz.ans_is_true";
    private boolean ansCheckIsTrue;

    private TextView mAnsTextView;
    private Button mShowAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        ansCheckIsTrue = getIntent().getBooleanExtra(EXTRA_ANS_IS_TRUE,false);
        mAnsTextView = (TextView)findViewById(R.id.ansTextView);
        mShowAns = (Button) findViewById(R.id.showAnsBtn);
        mShowAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ansCheckIsTrue){
                    mAnsTextView.setText(R.string.btn_true);
                }else {
                    mAnsTextView.setText(R.string.btn_false);
                }
            }
        });
    }

    public static Intent newIntent (Context packageContext,boolean mAnsIsTrue){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANS_IS_TRUE,mAnsIsTrue);
        return i;
    }
}
