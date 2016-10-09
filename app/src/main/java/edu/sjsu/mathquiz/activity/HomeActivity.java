package edu.sjsu.mathquiz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import edu.sjsu.mathquiz.R;
import edu.sjsu.mathquiz.fragment.QuestionFragment;
import edu.sjsu.mathquiz.model.QuizDetail;

public class HomeActivity extends AppCompatActivity {

    public static final String QUIZ_TYPE = "QuizType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startQuiz(View view) {
        Log.d("HomeActivity", "Quiz Started");
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.rgQuiz);
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbAddition:
                Log.d("HomeActivity.startQuiz", "Addition Quiz has been selected");

                break;
            case R.id.rbSubtraction:
                Log.d("HomeActivity.startQuiz", "Subtraction Quiz has been selected");
                break;
            case R.id.rbMultiplication:
                Log.d("HomeActivity.startQuiz", "Multiplication Quiz has been selected");
                break;
        }

        Intent quizIntent = new Intent(this, QuizActivity.class);

        QuizDetail quizDetail = new QuizDetail();
        quizDetail.setQuizType(radioGroup.getCheckedRadioButtonId());

        quizIntent.putExtra(QUIZ_TYPE, radioGroup.getCheckedRadioButtonId());

        startActivity(quizIntent);
    }
}
