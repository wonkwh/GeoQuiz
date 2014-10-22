package com.khwon.android.geoquiz;

/**
 * Created by wonkwh on 2014. 10. 22..
 */

public class TrueFalse {
    private int question_;
    private boolean is_true_;

    public TrueFalse(int question, boolean is_true) {
        this.question_ = question;
        this.is_true_ = is_true;
    }

    public int getQuestion() {
        return question_;
    }

    public void setQuestion(int question) {
        this.question_ = question;
    }

    public boolean istrue() {
        return is_true_;
    }

    public void setTrue(boolean is_true) {
        this.is_true_ = is_true;
    }
}
