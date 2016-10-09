package edu.sjsu.mathquiz.model;

/**
 * Created by I074841 on 10/8/2016.
 */
public class QuizDetail {
    private int quizType;
    private int currentQuestionNumber = 1;
    private int score = 0;
    private boolean isQuizOver;

    public int getQuizType() {
        return quizType;
    }

    public void setQuizType(int quizType) {
        this.quizType = quizType;
    }

    @Override
    public String toString() {
        return "QuizDetail{" +
                "quizType=" + quizType +
                ", currentQuestionNumber=" + currentQuestionNumber +
                ", score=" + score +
                ", isQuizOver=" + isQuizOver +
                '}';
    }

    public boolean isQuizOver() {
        return isQuizOver;
    }

    public void setQuizOver(boolean quizOver) {
        isQuizOver = quizOver;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionNumber;

    }

    public void setCurrentQuestionNumber(int currentQuestionNumber) {
        this.currentQuestionNumber = currentQuestionNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
