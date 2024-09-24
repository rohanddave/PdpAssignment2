package polynomial;

/**
 * A class that represents a term of a polynomial.
 */
public class PolynomialTerm {
  private int coefficient;
  private int power;

  /**
   * Constructs a new instance of a polynomial term.
   * @param coefficient
   * @param power
   */
  PolynomialTerm(int coefficient, int power) {
    this.coefficient = coefficient;
    this.power = power;
  }

  /**
   * Getter function for coefficient.
   * @return integer representing coefficient.
   */
  public int getCoefficient() {
    return this.coefficient;
  }

  /**
   * Getter function for power.
   * @return integer representing the power.
   */
  public int getPower() {
    return this.power;
  }
}
