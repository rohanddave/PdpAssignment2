package polynomial;

import java.util.HashMap;
import java.util.Iterator;
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
    this.polynomial.put(0, 0);
    this.degree = 0;
    this.variable = "x";
  }

  /**
   * Constructs a new instance of SimplePolynomial using the given hashmap.
   *
   * @param p hashmap where key is power and value is it's coefficient.
   */
  private SimplePolynomial(HashMap<Integer, Integer> p) {
    this.polynomial = p;
    this.variable = "x";
  }

  @Override
  public Polynomial add(Polynomial other) {
    HashMap<Integer, Integer> newPolynomial = new HashMap<>();

    int otherPolynomialDegree = other.getDegree();
    for (int i = otherPolynomialDegree; i >= 0; i--) {
      int otherPolynomialCoefficientForPower = other.getCoefficient(i);
      newPolynomial.put(i, otherPolynomialCoefficientForPower + this.getCoefficient(i));
    }

    return new SimplePolynomial(newPolynomial);
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    return null;
  }

  @Override
  public Polynomial derivative() {
    HashMap<Integer, Integer> newPolynomial = new HashMap<>();

    Iterator<Map.Entry<Integer, Integer>> iterator = this.polynomial.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<Integer, Integer> entry = iterator.next();
      int power = entry.getKey();
      int coefficient = entry.getValue();

      if (power == 0) {
        continue;
      }

      newPolynomial.put(power - 1, coefficient * power);
    }

    return new SimplePolynomial(newPolynomial);
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
    this.polynomial.put(power, finalCoefficient);
  }

  @Override
  public int getDegree() {
    return this.degree;
  }

  @Override
  public double evaluate(double x) {
    double sum = 0.0;

    Iterator<Map.Entry<Integer, Integer>> iterator = polynomial.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<Integer, Integer> entry = iterator.next();
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
    StringBuilder sb = new StringBuilder();

    Iterator<Map.Entry<Integer, Integer>> iterator = polynomial.entrySet().iterator();

    while (iterator.hasNext()) {
      Map.Entry<Integer, Integer> entry = iterator.next();
      int power = entry.getKey();
      int coefficient = entry.getValue();

      StringBuilder termStringBuilder = new StringBuilder();
      if (coefficient < 0) {
        termStringBuilder.append("-");
      } else if (!sb.isEmpty()) {
        termStringBuilder.append("+");
      }

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