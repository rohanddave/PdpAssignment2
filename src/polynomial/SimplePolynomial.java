package polynomial;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A class that represents a SimplePolynomial.
 */
public class SimplePolynomial implements Polynomial {
  private final HashMap<Integer, Integer> polynomial;
  private int degree;
  private final String variable;

  /**
   * Construct a new instance of a SimplePolynomial.
   * Initializes polynomial = 0.
   */
  public SimplePolynomial() {
    this.polynomial = new HashMap<>();
    this.degree = 0;
    this.variable = "x";
  }

  @Override
  public Polynomial add(Polynomial other) {
    Polynomial sum = new SimplePolynomial();

    for (Map.Entry<Integer, Integer> entrySet : this.polynomial.entrySet()) {
      int power = entrySet.getKey();
      int coefficient = entrySet.getValue();

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

      for (Map.Entry<Integer, Integer> entry : this.polynomial.entrySet()) {
        int power = entry.getKey();
        int coefficient = entry.getValue();

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

    for (Map.Entry<Integer, Integer> entry : this.polynomial.entrySet()) {
      int power = entry.getKey();
      int coefficient = entry.getValue();

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

    int finalCoefficient = this.polynomial.getOrDefault(power, 0) + coefficient;
    if (finalCoefficient == 0) {
      this.polynomial.remove(power);
      if (power == this.degree) {
        this.degree = this.polynomial.keySet().stream().max(Integer::compare).orElse(0);
      }
    } else {
      this.polynomial.put(power, finalCoefficient);
    }

    this.degree = Math.max(this.degree, power);
  }

  @Override
  public int getDegree() {
    return this.degree;
  }

  @Override
  public double evaluate(double x) {
    double sum = 0.0;

    for (Map.Entry<Integer, Integer> entry : polynomial.entrySet()) {
      int power = entry.getKey();
      int coefficient = entry.getValue();
      sum += coefficient * Math.pow(x, power);
    }

    return sum;
  }

  @Override
  public int getCoefficient(int power) {
    return this.polynomial.getOrDefault(power, 0);
  }

  /**
   * Checks if other object is equal to this polynomial.
   *
   * @param other other object.
   * @return boolean representing if other object is equal to this polynomial.
   */
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }

    // TODO: instanceof Polynomial or SimplePolynomial
    if (!(other instanceof SimplePolynomial)) {
      return false;
    }

    Polynomial p = (SimplePolynomial) other;

    return Objects.equals(p.toString(), this.toString());
  }

  /**
   * Method that returns the polynomial as a string.
   * Example: 5x^2+ x - 2
   *
   * @return String representation of polynomial.
   */
  public String toString() {
    if (this.getDegree() == 0 && this.polynomial.getOrDefault(0, 0) == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();

    for (int power = this.getDegree(); power >= 0; power--) {
      int coefficient = this.polynomial.getOrDefault(power, 0);

      if (coefficient == 0) {
        continue;
      }

      StringBuilder termStringBuilder = new StringBuilder();

      String sign = coefficient < 0 ? "-" : "+";

      if (!sb.isEmpty()) {
        // not the first term
        termStringBuilder
                .append(" ")
                .append(sign)
                .append(" ");
      } else if (coefficient < 0) {
        // negative first term
        termStringBuilder.append(sign);
      }

      termStringBuilder.append(Integer.toString(Math.abs(coefficient)));

      if (power != 0) {
        termStringBuilder
                .append(this.variable)
                .append("^")
                .append(Integer.toString(power));
      }

      sb.append(termStringBuilder.toString());
    }

    return sb.toString();
  }
}