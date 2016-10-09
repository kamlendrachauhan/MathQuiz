package edu.sjsu.mathquiz.util;

import java.util.Random;

import edu.sjsu.mathquiz.model.QuizDetail;

/**
 * Created by I074841 on 10/8/2016.
 */
public class RandomNumberGenerator {

    public static int getRandomNumber() {
        int rndNumber = 0;
        Random rndm = new Random();
        rndNumber = rndm.nextInt(10);
        return rndNumber;
    }

}