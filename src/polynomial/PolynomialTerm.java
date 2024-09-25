package polynomial;

import java.util.Objects;

/**
 * A class that represents a term of a polynomial.
 */
public class PolynomialTerm {
  private int coefficient;
  private int power;

  /**
   * Constructs a new instance of a polynomial term.
   * @param coefficient coefficient of the term.
   * @param power power of the term.
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

  @Override
  public int hashCode() {
    return Objects.hash(this.coefficient, this.power);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    if (!(other instanceof PolynomialTerm)) {
      return false;
    }

    PolynomialTerm term = (PolynomialTerm) other;

    boolean isPowerEqual = Objects.equals(term.getPower(), this.getPower());
    boolean isCoefficientEqual = Objects.equals(term.getCoefficient(), this.getCoefficient());

    return isPowerEqual && isCoefficientEqual;
  }
}
