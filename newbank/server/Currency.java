package newbank.server;

public class Currency {

  private final char symbol = '£';
  private int pence;

  public Currency(String input) throws NumberFormatException {
    double asDouble = Float.valueOf(input);
    this.pence = (int)Math.floor(asDouble * 100.0);
  }

  public int getValue() {
    return pence;
  }

  public int getBigUnit() {
    return pence / 100;
  }

  public int getSmallUnit() {
    return pence % 100;
  }

  public char getSymbol() {
    return symbol;
  }

  public String toString() {
    return String.format("%c%n%20n", symbol, getBigUnit(), getSmallUnit());
  }

  public boolean isNegative() {
    return pence < 0;
  }

  public boolean isPositive() {
    return pence > 0;
  }

}
