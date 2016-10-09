package edu.sjsu.mathquiz.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import edu.sjsu.mathquiz.R;
import edu.sjsu.mathquiz.activity.HomeActivity;
import edu.sjsu.mathquiz.model.QuestionAnswer;
import edu.sjsu.mathquiz.model.QuizDetail;
import edu.sjsu.mathquiz.util.AnswerValidator;
import edu.sjsu.mathquiz.util.QuizGenerator;

public class QuestionFragment extends Fragment implements View.OnClickListener, SummaryFragment.SummaryFragmentListener {
    private static final String QUESTION_NUMBER_QUESTION_TXT = "Question ";
    private static final String QUESTION_NUMBER_OUT_OF_TXT = " of 10";
    public static QuestionAnswer currentQuestionAnswer;
    public static QuizDetail currentQuizState;
    private static View view;
    private static int numberOfQuestionsAnswered;
    private static Timer timer;
    private String operatorType;
    private EditText editText;
    private int numberOfSecond = 5;
    private TextView timerTextView;
    private QuizTimerTask timerTask = new QuizTimerTask();
    private Activity currentActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int quizType = bundle.getInt(HomeActivity.QUIZ_TYPE);
            switch (quizType) {
                case R.id.rbAddition:
                    operatorType = "+";
                    break;
                case R.id.rbSubtraction:
                    operatorType = "-";
                    break;
                case R.id.rbMultiplication:
                    operatorType = "*";
                    break;
            }
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_question, container, false);

        TextView txtOperatorView = (TextView) view.findViewById(R.id.txtOperator);
        txtOperatorView.setText(operatorType);

        currentQuestionAnswer = QuizGenerator.getQuestionObj(operatorType);
        TextView txtFirstOperand = (TextView) view.findViewById(R.id.firstOperand);
        TextView txtSecondOperand = (TextView) view.findViewById(R.id.secondOperand);
        txtFirstOperand.setText(String.valueOf(currentQuestionAnswer.getOperandOne()));
        txtSecondOperand.setText(String.valueOf(currentQuestionAnswer.getOperandTwo()));

        currentQuizState = new QuizDetail();
        TextView txtQuestionNumber = (TextView) view.findViewById(R.id.txtQuestionNumber);
        txtQuestionNumber.setText(QUESTION_NUMBER_QUESTION_TXT + currentQuizState.getCurrentQuestionNumber() + QUESTION_NUMBER_OUT_OF_TXT);

        ((Button) view.findViewById(R.id.btnNumber1)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber2)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber3)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber4)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber5)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber6)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber7)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber8)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber9)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNumber0)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btnNext)).setOnClickListener(this);
        numberOfQuestionsAnswered = 1;
        timerTextView = (TextView) view.findViewById(R.id.txtTimer);
        startTimer();
        currentActivity = getActivity();
        return view;
    }

    @Override
    public void onClick(View v) {
        editText = (EditText) view.findViewById(R.id.answer);

        switch (v.getId()) {
            case R.id.btnNumber1:
                Log.d("QuestionFragment", "Number 1 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(1));
                performNumberOperation();
                break;
            case R.id.btnNumber2:
                Log.d("QuestionFragment", "Number 2 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(2));
                performNumberOperation();
                break;
            case R.id.btnNumber3:
                Log.d("QuestionFragment", "Number 3 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(3));
                performNumberOperation();
                break;
            case R.id.btnNumber4:
                Log.d("QuestionFragment", "Number 4 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(4));
                performNumberOperation();
                break;
            case R.id.btnNumber5:
                Log.d("QuestionFragment", "Number 5 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(5));
                performNumberOperation();
                break;
            case R.id.btnNumber6:
                Log.d("QuestionFragment", "Number 6 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(6));
                performNumberOperation();
                break;
            case R.id.btnNumber7:
                Log.d("QuestionFragment", "Number 7 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(7));
                performNumberOperation();
                break;
            case R.id.btnNumber8:
                Log.d("QuestionFragment", "Number 8 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(8));
                performNumberOperation();
                break;
            case R.id.btnNumber9:
                Log.d("QuestionFragment", "Number 9 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(9));
                performNumberOperation();
                break;
            case R.id.btnNumber0:
                Log.d("QuestionFragment", "Number 0 Pressed");
                editText.setText(editText.getText() + "" + String.valueOf(0));
                performNumberOperation();
                break;
            case R.id.btnNext:
                Log.d("QuestionFragment", "Next Button");
                //Skip the question and go to next one
                //show toast as skipped
                //call getNextQuizObj
                Toast toast = Toast.makeText(getActivity(), "Question Skipped", Toast.LENGTH_SHORT);
                toast.show();
                timer.cancel();
                numberOfSecond = 5;
                checkToShowNextQuestion(false, false);
                startTimer();
                break;
        }
    }

    private void performNumberOperation() {
        String currentAnswerText = editText.getText() == null ? "0" : editText.getText().toString();
        boolean result = AnswerValidator.validateAnswer(currentQuestionAnswer.getOperandOne(), currentQuestionAnswer.getOperandTwo(), Integer.parseInt(currentAnswerText), operatorType);
        Log.d("QuestionFragment", "Result : " + result);
        if ((String.valueOf(currentQuestionAnswer.getCorrectAnswer()).length() == currentAnswerText.length()) && checkToShowNextQuestion(result, true)) {
            if (result) {
                Toast toast = Toast.makeText(getActivity(), "Correct Answer", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getActivity(), "Incorrect Answer", Toast.LENGTH_SHORT);
                toast.show();
            }
            if (!QuestionFragment.currentQuizState.isQuizOver())
                restartTimerAndMoveToNextQuestion(true);
        }
    }

    private void loadNextQuestion() {
        TextView txtFirstOperand = (TextView) view.findViewById(R.id.firstOperand);
        TextView txtSecondOperand = (TextView) view.findViewById(R.id.secondOperand);
        if (editText == null)
            editText = (EditText) view.findViewById(R.id.answer);
        editText.setText("");

        currentQuestionAnswer = QuizGenerator.getQuestionObj(operatorType);
        txtFirstOperand.setText(String.valueOf(currentQuestionAnswer.getOperandOne()));
        txtSecondOperand.setText(String.valueOf(currentQuestionAnswer.getOperandTwo()));

        TextView txtQuestionNumber = (TextView) view.findViewById(R.id.txtQuestionNumber);
        txtQuestionNumber.setText(QUESTION_NUMBER_QUESTION_TXT + currentQuizState.getCurrentQuestionNumber() + QUESTION_NUMBER_OUT_OF_TXT);
    }

    private synchronized boolean checkToShowNextQuestion(boolean answerResult, boolean isNumberPressed) {
        Log.d("QuestionFragment", "Inside checkToShowNextQuestion " + currentQuizState.toString());
        Log.d("QuestionFragment", "Inside checkToShowNextQuestion " + currentQuestionAnswer.toString());

        if (numberOfQuestionsAnswered >= 10) {
            //Quiz over show the popup with ok option
           // if (isNumberPressed)
            //    performNumberOperation();
            if(isNumberPressed)
                QuizGenerator.getNextQuizObj(QuestionFragment.currentQuizState, answerResult);
            QuestionFragment.currentQuizState.setQuizOver(true);
            Log.d("QuestionFragment", "Inside checkToShowNextQuestion if condition" + currentQuizState.toString());
            Log.d("QuestionFragment", "Inside checkToShowNextQuestion if condition" + currentQuestionAnswer.toString());

            SummaryFragment summaryFragment = new SummaryFragment();
            summaryFragment.show(getActivity().getSupportFragmentManager(), "SummaryFragment");


        } else {
            QuizGenerator.getNextQuizObj(QuestionFragment.currentQuizState, answerResult);
            loadNextQuestion();
            ++numberOfQuestionsAnswered;
            return true;

        }
        return false;
    }

    private void startTimer() {
        if (numberOfQuestionsAnswered <= 10 && !QuestionFragment.currentQuizState.isQuizOver()) {
            timer = new Timer();
            timerTask = new QuizTimerTask();
            timer.scheduleAtFixedRate(timerTask, 0, 1000);
        } else {
            QuestionFragment.currentQuizState.setQuizOver(true);
            // getActivity().finish();
        }
    }

    private synchronized void restartTimerAndMoveToNextQuestion(boolean isAnswerPressed) {

        timer.cancel();
        numberOfSecond = 5;
        if (!isAnswerPressed && numberOfQuestionsAnswered <= 10) {
            Toast toast = Toast.makeText(currentActivity, "Time Out : Question Skipped", Toast.LENGTH_SHORT);
            toast.show();
            checkToShowNextQuestion(false, false);
        }
        startTimer();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("QuestionFragment", "Quiz Over");
        getActivity().finish();
    }

    class QuizTimerTask extends TimerTask {
        @Override
        public void run() {

            if (getActivity() == null)
                return;

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (numberOfSecond >= 0) {
                        timerTextView.setText(Integer.toString(numberOfSecond));
                        numberOfSecond--;
                    } else {
                        if (!QuestionFragment.currentQuizState.isQuizOver()) {
                            restartTimerAndMoveToNextQuestion(false);
                            numberOfSecond = 5;
                        }
                    }
                }

            });

        }
    }
}
