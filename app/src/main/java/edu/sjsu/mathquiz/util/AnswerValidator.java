package edu.sjsu.mathquiz.util;

/**
 * Created by I074841 on 10/9/2016.
 */
public class AnswerValidator {

    public static boolean validateAnswer(int operandOne, int operandTwo, int answerInserted, String operator) {
        return (answerInserted == calculateAnswer(operandOne, operandTwo, operator)) ? true : false;
    }

    public static int calculateAnswer(int operandOne, int operandTwo, String operator) {
        int result = 0;
        if (operator.equals("+")) {
            result = (operandOne + operandTwo);
        } else if (operator.equals("-")) {
            result = (operandOne - operandTwo);
        } else {
            result = (operandOne * operandTwo);
        }
        return result;
    }
}
