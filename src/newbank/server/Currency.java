package newbank.server;

public class Currency {

  private final char symbol = '£'; // Could use this to add support for different currencies
  private int pence; // For example £5.50 would be stored as 550 pence

  /**
   * Copy constructor
   *
   * @param currency object to be copied
   */
  public Currency(Currency currency) {
    pence = currency.getValue();
  }

  /**
   * Constructor from string
   *
   * Note: this is private and accessed by calling FromString i.e.
   *
   * Currency balance = Currency.FromString(userInput);
   *
   * This pattern suggested in Clean Code, Robert Martin Chapter 2
   * This method starts with a capital as it is shadowing a constructor
   *
   * @param input amount in the format 5.05 which is £5.05
   * @throws NumberFormatException if the input was not recognised as an amount
   */
  private Currency(String input) throws NumberFormatException {
    double asDouble = Float.parseFloat(input);
    this.pence = (int)Math.floor(asDouble * 100.0);
  }

  /**
   * Creates a new Currency object from an input string
   *
   * @param amount input string
   * @return new Currency object
   * @throws NumberFormatException if the input was not recognised as an amount
   */
  public static Currency FromString(String amount) throws NumberFormatException {
    return new Currency(amount);
  }

  /**
   * Constructor from integer
   *
   * Note: this is private and accessed by calling FromInteger i.e.
   *
   * Currency balance = Currency.FromInteger(5);
   *
   * This pattern suggested in Clean Code, Robert Martin Chapter 2
   * This method starts with a capital as it is shadowing a constructor
   *
   * @param amount e.g. 5 which is £5.00
   */
  private Currency(int amount) {
    pence = amount * 100;
  }

  /**
   * Creates a Currency object from a integer
   *
   * @param amount the amount in pounds e.g. 5 is £5.00
   * @return new Currency object
   */
  public static Currency FromInteger(int amount) {
    return new Currency(amount);
  }

  /**
   * Constructor from double
   *
   * Note: this is private and accessed by calling FromDouble i.e.
   *
   * Currency balance = Currency.FromDouble(5.12);
   *
   * This pattern suggested in Clean Code, Robert Martin Chapter 2
   * This method starts with a capital as it is shadowing a constructor
   *
   * @param amount e.g. 5.12 which is £5.12
   * @throws NumberFormatException if there are fractions of a penny e.g. 5.125
   */
  private Currency(double amount) throws NumberFormatException {
    double fractionOfPennies = Math.floor(amount * 100) - amount * 100;
    if (fractionOfPennies != 0) {
      throw new NumberFormatException("Cannot have fractions of a penny" + amount);
    }
    pence = (int)Math.floor(amount * 100);
  }

  /**
   * Creates a Currency object from a double
   *
   * @param amount e.g. 5.12 which is £5.12
   * @return new Currency object
   * @throws NumberFormatException if there are fractions of a penny e.g. 5.125
   */
  public static Currency FromDouble(double amount) {
    return new Currency(amount);
  }

  public int getValue() {
    return pence;
  }

  public void setValue(int amount) {
    pence = amount;
  }

  public int getPounds() {
    return pence / 100;
  }

  public int getPence() {
    return pence % 100;
  }

  public char getSymbol() {
    return symbol;
  }

  public int positiveInt(int negative) {
    return negative*-1;
  }

  public String toString() {
    if (isNegative()) {
      return String.format("-%c%d.%02d", symbol, positiveInt(getPounds()), getPence());
    } else {
      return String.format("%c%d.%02d", symbol, getPounds(), getPence());
    }
  }

  public boolean isNegative() {
    return pence < 0;
  }

  public boolean isPositive() {
    return pence > 0;
  }

  public void add(Currency amount) {
    pence += amount.getValue();
  }

  public void subtract(Currency amount) {
    pence -= amount.getValue();

  }

  public boolean greaterThanOrEqual(Currency amount) {
    return pence > amount.getValue();
  }

}
