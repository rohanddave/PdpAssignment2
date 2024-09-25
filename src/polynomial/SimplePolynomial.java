package polynomial;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class that represents a SimplePolynomial where each term is of type PolynomialTerm.
 * Provides functions to add, multiply, derivative, etc.
 * Example: -2x^2 + 3x^1 - 9
 */
public class SimplePolynomial implements Polynomial {
  private final List<PolynomialTerm> polynomial;
  private int degree;
  private final String variable;

  /**
   * Construct a new instance of a SimplePolynomial.
   * Initializes polynomial = 0.
   */
  public SimplePolynomial() {
    this.polynomial = new ArrayList<>();
    this.degree = 0;
    this.variable = "x";
  }

  @Override
  public Polynomial add(Polynomial other) {
    Polynomial sum = new SimplePolynomial();

    for (PolynomialTerm term : this.polynomial) {
      int power = term.getPower();
      int coefficient = term.getCoefficient();

      sum.addTerm(coefficient, power);
    }


    int otherPolynomialDegree = other.getDegree();

    for (int power = otherPolynomialDegree; power >= 0; power--) {
      int otherPolynomialCoefficientForPower = other.getCoefficient(power);
      sum.addTerm(otherPolynomialCoefficientForPower, power);
    }

    return sum;
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    Polynomial product = new SimplePolynomial();

    for (int otherPower = other.getDegree(); otherPower >= 0; otherPower--) {
      int otherCoefficient = other.getCoefficient(otherPower);

      if (otherCoefficient == 0) {
        continue;
      }

      for (PolynomialTerm term : this.polynomial) {
        int power = term.getPower();
        int coefficient = term.getCoefficient();

        int newPower = otherPower + power;
        int newCoefficient = otherCoefficient * coefficient;

        product.addTerm(newCoefficient, newPower);
      }
    }

    return product;
  }

  @Override
  public Polynomial derivative() {
    Polynomial derivative = new SimplePolynomial();

    for (PolynomialTerm term : this.polynomial) {
      int power = term.getPower();
      int coefficient = term.getCoefficient();

      if (power == 0) {
        continue;
      }

      derivative.addTerm(coefficient * power, power - 1);
    }

    return derivative;
  }

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power cannot be negative");
    }

    if (coefficient == 0) {
      return;
    }

    // Remove any existing term with the same power
    PolynomialTerm existingTerm = getTermForPower(power);
    this.polynomial.remove(existingTerm);

    int finalCoefficient = existingTerm.getCoefficient() + coefficient;

    if (finalCoefficient != 0) {
      this.polynomial.add(new PolynomialTerm(finalCoefficient, power));
    }

    if (power > this.degree) {
      this.degree = power;
    } else if (finalCoefficient == 0 && power == this.degree) {
      computeDegree();
    }
  }

  @Override
  public int getDegree() {
    return this.degree;
  }

  @Override
  public double evaluate(double x) {
    double sum = 0.0;

    for (PolynomialTerm term : this.polynomial) {
      int power = term.getPower();
      int coefficient = term.getCoefficient();
      sum += coefficient * Math.pow(x, power);
    }

    return sum;
  }

  @Override
  public int getCoefficient(int power) {
    for (PolynomialTerm term : this.polynomial) {
      if (term.getPower() == power) {
        return term.getCoefficient();
      }
    }

    return 0;
  }

  /**
   * Checks if other object is equal to this polynomial.
   *
   * @param other other object.
   * @return boolean representing if other object is equal to this polynomial.
   */
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    if (!(other instanceof SimplePolynomial)) {
      return false;
    }

    Polynomial p = (SimplePolynomial) other;

    return Objects.equals(p.toString(), this.toString());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.polynomial.toString());
  }

  /**
   * Method that returns the polynomial as a string.
   * Example: 5x^2+ x - 2
   *
   * @return String representation of polynomial.
   */
  public String toString() {
    if (this.getDegree() == 0 && this.getCoefficient(0) == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();

    for (int power = this.getDegree(); power >= 0; power--) {
      int coefficient = this.getCoefficient(power);

      if (coefficient == 0) {
        continue;
      }

      StringBuilder termStringBuilder = new StringBuilder();

      String sign = coefficient < 0 ? "-" : "+";

      if (sb.length() != 0) {
        // not the first term
        termStringBuilder
                .append(" ")
                .append(sign)
                .append(" ");
      } else if (coefficient < 0) {
        // negative first term
        termStringBuilder.append(sign);
      }

      termStringBuilder.append(Math.abs(coefficient));

      if (power != 0) {
        termStringBuilder
                .append(this.variable)
                .append("^")
                .append(power);
      }

      sb.append(termStringBuilder);
    }

    return sb.toString();
  }

  private PolynomialTerm getTermForPower(int power) {
    for (PolynomialTerm term : this.polynomial) {
      if (term.getPower() == power) {
        return term;
      }
    }

    return new PolynomialTerm(0, 0);
  }

  private void computeDegree() {
    this.degree = 0;
    for (PolynomialTerm term : this.polynomial) {
      if (term.getPower() > this.degree) {
        this.degree = term.getPower();
      }
    }
  }
}