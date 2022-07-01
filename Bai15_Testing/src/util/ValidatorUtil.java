package util;

import exception.InvalidContactNumberException;
import exception.InvalidEntryYearException;
import exception.InvalidScoreException;
import java.time.Year;

public class ValidatorUtil {

  private static final float MIN_SCORE = 0;
  private static final float MAX_SCORE = 100;
  private static final Year MIN_YEAR = Year.of(2003);
  private static final Year MAX_YEAR = Year.now();
  private static final String regexContactNumber = "^(090|098|091|031|035|038|093)\\d{7}$";

  public static boolean checkValidContactNumber(String contactNumber) {
    if (!contactNumber.matches(regexContactNumber)) {
      throw new InvalidContactNumberException(contactNumber);
    }
    return true;
  }

  public static boolean checkValidScore(float score) {
    if (score < MIN_SCORE || score > MAX_SCORE) {
      throw new InvalidScoreException(score);
    }
    return true;
  }

  public static boolean checkValidEntryYear(Year entryYear) throws InvalidEntryYearException {
    if (!entryYear.isBefore(MAX_YEAR) || !entryYear.isAfter(MIN_YEAR)) {
      throw new InvalidEntryYearException(entryYear);
    }
    return true;
  }
}
