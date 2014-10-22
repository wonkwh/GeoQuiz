package com.khwon.android.geoquiz;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private Button true_button_;
    private Button false_button_;
    private ImageButton next_button_;
    private ImageButton prev_button_;
    private TextView question_text_view_;

    private TrueFalse[] question_back_ = new TrueFalse[] {
        new TrueFalse(R.string.question_oceans, true),
        new TrueFalse(R.string.question_mideast, false),
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true),

    };

    private int current_index_ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //update question text view
        question_text_view_ = (TextView)findViewById(R.id.question_text_view);
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

        next_button_ = (ImageButton) findViewById(R.id.next_button);
        next_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index_ = (current_index_ + 1) % question_back_.length;
                update_Question();
            }
        });

        //challenge: add a prev button
        prev_button_ = (ImageButton) findViewById(R.id.prev_button);
        prev_button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_index_ = (current_index_ == 0) ? question_back_.length - 1 : current_index_ - 1;
                update_Question();
            }
        });
    }

    private void update_Question() {
        int question_red_id = question_back_[current_index_].getQuestion();
        question_text_view_.setText(question_red_id);
    }

    private void check_Answer(boolean user_pressed_true) {
        boolean answer_is_true = question_back_[current_index_].istrue();

        int message_res_id;
        if (user_pressed_true == answer_is_true) {
            message_res_id = R.string.correct_toast;
        } else {
            message_res_id = R.string.incorrect_toast;
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
