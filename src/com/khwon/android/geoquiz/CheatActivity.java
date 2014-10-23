package com.khwon.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by wonkwh on 2014. 10. 23..
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.khwon.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.khwon.android.geoquiz.answer_shown";


    private boolean answer_is_true_;

    private TextView answer_text_view_;
    private Button show_answer_;

    private void set_Answer_Shown_Result(boolean is_answer_shown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, is_answer_shown);
        setResult(RESULT_OK, data);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        set_Answer_Shown_Result(false);

        answer_is_true_ = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answer_text_view_ = (TextView) findViewById(R.id.showAnswerButton);
        show_answer_ = (Button) findViewById(R.id.showAnswerButton);
        show_answer_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answer_is_true_) {
                    answer_text_view_.setText(R.string.true_button);
                } else {
                    answer_text_view_.setText(R.string.false_button);
                }

                set_Answer_Shown_Result(true);
            }
        });
    }
}