package newbank.server;

public class Currency {

  private final char symbol = '£';
  private int pence;

  public Currency(String input) throws NumberFormatException {
    double asDouble = Float.parseFloat(input);
    this.pence = (int)Math.floor(asDouble * 100.0);
  }

  public Currency(Currency currency) {
    pence = currency.getValue();
  }

  public Currency(int amount) {
    pence = amount * 100;
  }

  public Currency(double amount) {
    pence = (int)Math.floor(amount * 100);
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
