package exception;

import java.time.Year;

public class InvalidEntryYearException extends RuntimeException {

  public InvalidEntryYearException(Year entryYear) {
    super("Invalid entry year range (must be from 2003 to now) -> current year: " + entryYear);
  }
}
