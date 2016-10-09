package edu.sjsu.mathquiz.model;

/**
 * Created by I074841 on 10/9/2016.
 */
public class QuestionAnswer {
    private int operandOne;
    private int operandTwo;
    private int correctAnswer;

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getOperandOne() {
        return operandOne;
    }

    public void setOperandOne(int operandOne) {
        this.operandOne = operandOne;
    }

    public int getOperandTwo() {
        return operandTwo;
    }

    public void setOperandTwo(int operandTwo) {
        this.operandTwo = operandTwo;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "operandOne=" + operandOne +
                ", operandTwo=" + operandTwo +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
