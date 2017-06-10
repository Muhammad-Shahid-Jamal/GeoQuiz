package com.shahid.android.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANS_IS_TRUE = "com.shahid.android.geoquiz.ans_is_true";
    private static final String EXTRA_ANS_SHOW = "com.shahid.android.geoquiz.ans_shown";
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
                setAnsShownResult(true);

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    int cx = mShowAns.getWidth()/2;
                    int cy = mShowAns.getHeight()/2;
                    float radius = mShowAns.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mShowAns,cx,cy,radius,0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mAnsTextView.setVisibility(View.VISIBLE);
                            mShowAns.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }else {
                    mAnsTextView.setVisibility(View.VISIBLE);
                    mShowAns.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public static Intent newIntent (Context packageContext,boolean mAnsIsTrue){
        Intent i = new Intent(packageContext,CheatActivity.class);
        i.putExtra(EXTRA_ANS_IS_TRUE,mAnsIsTrue);
        return i;
    }

    private void setAnsShownResult(boolean ansIsTrue){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANS_SHOW,ansIsTrue);
        setResult(RESULT_OK,data);
    }

    public static boolean wasAnsIsShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANS_SHOW,false);
    }
}
