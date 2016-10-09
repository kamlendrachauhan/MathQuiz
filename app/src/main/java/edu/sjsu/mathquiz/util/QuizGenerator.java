package edu.sjsu.mathquiz.util;

import edu.sjsu.mathquiz.model.QuestionAnswer;
import edu.sjsu.mathquiz.model.QuizDetail;

/**
 * Created by I074841 on 10/9/2016.
 */
public class QuizGenerator {

    public static QuestionAnswer getQuestionObj(String operator) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        int operand1 = RandomNumberGenerator.getRandomNumber();
        int operand2 = RandomNumberGenerator.getRandomNumber();
        if(operator.equals("-")){
            if(operand1 < operand2){
                int temp = operand1;
                operand1 = operand2;
                operand2 = temp;
            }
        }
        questionAnswer.setOperandOne(operand1);
        questionAnswer.setOperandTwo(operand2);
        questionAnswer.setCorrectAnswer(AnswerValidator.calculateAnswer(operand1,operand2,operator));
        return questionAnswer;
    }

    public static QuizDetail getNextQuizObj(QuizDetail quizDetail, boolean isCorrect) {
        int currentQuizNumber = quizDetail.getCurrentQuestionNumber();
        quizDetail.setCurrentQuestionNumber(++currentQuizNumber);
        if (isCorrect) {
            int currentScore = quizDetail.getScore();
            quizDetail.setScore(++currentScore);
        }
        return quizDetail;
    }
}
