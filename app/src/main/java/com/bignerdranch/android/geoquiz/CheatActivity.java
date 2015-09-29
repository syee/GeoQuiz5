package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    private static final String KEY_INDEX_CHEAT = "Cheat";
//    private static final String KEY_LIST_CHEAT = "CheatList";
    private static final String TAG = "CheatActivity";
    private ArrayList<Integer> cheatList = new ArrayList<Integer>();
    private static final String EXTRA_QUESTION_NUMBER = "com.bignerdranch.android.geoquiz.question_number";
    private boolean mAnswerIsTrue;
    private boolean mAnswerShown = false;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            if(savedInstanceState.getBoolean(KEY_INDEX_CHEAT, false)){
                setAnswerShownResult(true);
                mAnswerShown = true;
            };
//            if((savedInstanceState.getIntegerArrayList(KEY_LIST_CHEAT)!= null)){
//                cheatList = savedInstanceState.getIntegerArrayList(KEY_LIST_CHEAT);
//            };
        }

        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                mAnswerShown = true;
                setAnswerShownResult(true);
//                cheatList.add(getIntent().getIntExtra(EXTRA_QUESTION_NUMBER, -1));
            }
        });


    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cheat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstance State");
        savedInstanceState.putBoolean(KEY_INDEX_CHEAT, mAnswerShown);
//        savedInstanceState.putIntegerArrayList(KEY_LIST_CHEAT, cheatList);
    }

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, int question){
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        i.putExtra(EXTRA_QUESTION_NUMBER, question);
        return i;
    }
}
