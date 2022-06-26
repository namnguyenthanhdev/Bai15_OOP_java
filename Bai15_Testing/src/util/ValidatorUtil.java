package util;

import exception.InvalidScoreException;

public class ValidatorUtil {

  private static final float MIN_SCORE = 0;

  private static final float MAX_SCORE = 10;


  public static boolean checkValidScore(float score) {
    if (score < MIN_SCORE || score > MAX_SCORE) {
      throw new InvalidScoreException(score);
    }
    return true;
  }

}
