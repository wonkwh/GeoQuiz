package com.khwon.android.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button true_button_;
    private Button false_button_;
    private ImageButton next_button_;
    private ImageButton prev_button_;
    private Button cheat_button_;

    private TextView question_text_view_;

    private TrueFalse[] question_back_ = new TrueFalse[] {
        new TrueFalse(R.string.question_oceans, true),
        new TrueFalse(R.string.question_mideast, false),
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true)

    };

    private int current_index_ = 0;
    private boolean is_cheater_;

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy called");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        //update question text view
        question_text_view_ = (TextView)findViewById(R.id.question_text_view);

        //restore key index when rotate
        if (savedInstanceState != null) {
            current_index_ = savedInstanceState.getInt(KEY_INDEX);
        }
        update_Question();


        //challenge::add a listener to the TextView
        question_text_view_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index_ = (current_index_ + 1) % question_back_.length;
                update_Question();
            }
        });

        true_button_ = (Button)findViewById(R.id.true_button);
        true_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(true);
            }
        });

        false_button_ = (Button)findViewById(R.id.false_button);
        false_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_Answer(false);
            }
        });

        cheat_button_ = (Button)findViewById(R.id.cheat_button);
        cheat_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean answer_is_true = question_back_[current_index_].istrue();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answer_is_true);
                //startActivity(i);
                startActivityForResult(i, 0);
            }
        });

        next_button_ = (ImageButton) findViewById(R.id.next_button);
        next_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index_ = (current_index_ + 1) % question_back_.length;
                is_cheater_ = false;
                update_Question();
            }
        });

        //challenge: add a prev button
        prev_button_ = (ImageButton) findViewById(R.id.prev_button);
        prev_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index_ = (current_index_ == 0) ? question_back_.length - 1 : current_index_ - 1;
                is_cheater_ = false;
                update_Question();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        is_cheater_ = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    //save data across rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstaceState");
        outState.putInt(KEY_INDEX, current_index_);
    }

    private void update_Question() {
        int question_red_id = question_back_[current_index_].getQuestion();
        question_text_view_.setText(question_red_id);
    }

    private void check_Answer(boolean user_pressed_true) {
        boolean answer_is_true = question_back_[current_index_].istrue();

        int message_res_id;

        if (is_cheater_) {
            message_res_id = R.string.judgment_toast;
        } else {

            if (user_pressed_true == answer_is_true) {
                message_res_id = R.string.correct_toast;
            } else {
                message_res_id = R.string.incorrect_toast;
            }
        }


        Toast.makeText( this,
                message_res_id,
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.quiz, menu);
        return true;
    }

}
