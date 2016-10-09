package edu.sjsu.mathquiz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.sjsu.mathquiz.R;
import edu.sjsu.mathquiz.fragment.QuestionFragment;

/**
 * Created by Kamlendra Singh Chauhan on 10/7/2016.
 */
public class QuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        int quizType = getIntent().getIntExtra(HomeActivity.QUIZ_TYPE, 1);
        Bundle bundle = new Bundle();
        bundle.putInt(HomeActivity.QUIZ_TYPE, quizType);

        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.add(R.id.fragment_container, questionFragment);
        // transaction.add(R.id.fragment_container1, numberPadFragment);
        transaction.commit();


    }


}
