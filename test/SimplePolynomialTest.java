import org.junit.Test;

import polynomial.Polynomial;
import polynomial.SimplePolynomial;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit4 class that tests implementation of SimplePolynomial class.
 */
public class SimplePolynomialTest {
  @Test
  public void testCreation() {
    // arrange and act
    Polynomial p = new SimplePolynomial();

    // assert
    assertEquals(0, p.getDegree());
  }

  @Test
  public void testGetCoefficientWhenEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();

    int coeff = p.getCoefficient(1);

    // assert
    assertEquals(0, coeff);
  }

  @Test
  public void testGetCoefficientForNonExistentPower() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(1, 0);

    // act
    int coeff = p.getCoefficient(1);

    // assert
    assertEquals(0, coeff);
  }

  @Test
  public void testGetCoefficientForExistentPower() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(1, 0);

    // act
    int coeff = p.getCoefficient(0);

    // assert
    assertEquals(1, coeff);
  }

  @Test
  public void testAddTermWhenPowerZero() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    p.addTerm(10, 0);

    // assert
    assertEquals(0, p.getDegree());
  }

  @Test
  public void testAddTermWhenPowerNonZero() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    p.addTerm(1, 10);

    // assert
    assertEquals("1x^10", p.toString());
  }

  @Test
  public void testAddTermWhenCoefficientZeroAndOnlyTerm() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    p.addTerm(0, 10);

    // assert
    assertEquals("0", p.toString());
  }

  @Test
  public void testAddTermWhenCoefficientZeroAndNotOnlyTerm() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    p.addTerm(1, 12);
    p.addTerm(0, 10);

    // assert
    assertEquals("1x^12", p.toString());
  }

  @Test
  public void testAddTermWhenCoefficientNonZero() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    p.addTerm(1, 12);

    // assert
    assertEquals("1x^12", p.toString());
  }

  @Test
  public void testEvaluateWhenEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // assert
    assertEquals(0.0, p.evaluate(12), 0);
  }

  @Test
  public void testEvaluateWhenDegreeZero() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);

    // assert
    assertEquals(10, p.evaluate(12), 0);
  }

  @Test
  public void testEvaluateWhenDegreeNonZeroAndOnlyTerm() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 1);

    // assert
    assertEquals(100, p.evaluate(10), 0);
  }

  @Test
  public void testEvaluateWhenDegreeNonZeroAndNotOnlyTerm() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);
    p.addTerm(10, 1);

    // assert
    assertEquals(110, p.evaluate(10), 0);
  }


  @Test
  public void testToStringWhenEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // assert
    assertEquals("0", p.toString());
  }

  @Test
  public void testToStringWhenNonEmptyAndOnlyConstant() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);

    // assert
    assertEquals("10", p.toString());
  }

  @Test
  public void testToStringWhenNonEmptyAndMultipleTerms() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);
    p.addTerm(12, 1);

    // assert
    assertEquals("12x^1 + 10", p.toString());
  }

  @Test
  public void testAddTwoEmptyPolynomials() {
    // arrange
    Polynomial p = new SimplePolynomial();
    Polynomial other = new SimplePolynomial();

    // act
    Polynomial sum = p.add(other);

    // assert
    assertEquals("0", sum.toString());
  }

  @Test
  public void testAddWhenOtherPolynomialIsEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 1);
    Polynomial other = new SimplePolynomial();

    // act
    Polynomial sum = p.add(other);

    // assert
    assertEquals("10x^1", sum.toString());
  }

  @Test
  public void testAddWhenCurrentPolynomialIsEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();
    Polynomial other = new SimplePolynomial();
    other.addTerm(10, 1);

    // act
    Polynomial sum = p.add(other);

    // assert
    assertEquals("10x^1", sum.toString());
  }

  @Test
  public void testAddTwoNonEmptyPolynomialsAndOneOverlappingTerm() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(20, 1);
    Polynomial other = new SimplePolynomial();
    other.addTerm(10, 1);

    // act
    Polynomial sum = p.add(other);

    // assert
    assertEquals("30x^1", sum.toString());
  }

  @Test
  public void testDerivativeWhenEmpty() {
    // arrange
    Polynomial p = new SimplePolynomial();

    // act
    Polynomial derivative = p.derivative();

    // assert
    assertEquals("0", derivative.toString());
  }

  @Test
  public void testDerivativeWhenConstant() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);

    // act
    Polynomial derivative = p.derivative();

    // assert
    assertEquals("0", derivative.toString());
  }

  @Test
  public void testDerivative() {
    // arrange
    Polynomial p = new SimplePolynomial();
    p.addTerm(10, 0);
    p.addTerm(2, 1);
    p.addTerm(-5, 2);

    // act
    Polynomial derivative = p.derivative();

    // assert
    assertEquals("-10x^1 + 2", derivative.toString());
  }

  @Test
  public void testMultiplication() {
    // arrange
    Polynomial a = new SimplePolynomial();
    a.addTerm(5, 2);
    a.addTerm(4, 1);
    a.addTerm(-2, 0);

    Polynomial b = new SimplePolynomial();
    b.addTerm(4, 2);
    b.addTerm(4, 1);
    b.addTerm(0, 0);

    // act
    Polynomial product = a.multiply(b);

    // arrange
    assertEquals("20x^4 + 36x^3 + 8x^2 - 8x^1", product.toString());
  }

  @Test
  public void testMultiplicationByZero() {
    // arrange
    Polynomial zero = new SimplePolynomial();
    Polynomial p = new SimplePolynomial();
    p.addTerm(1, 0);
    p.addTerm(2, 2);

    // act
    Polynomial product = p.multiply(zero);

    // assert
    assertEquals("0", product.toString());
  }

  @Test
  public void testMultiplicationByOne() {
    // arrange
    Polynomial one = new SimplePolynomial();
    one.addTerm(1, 0);

    Polynomial p = new SimplePolynomial();
    p.addTerm(1, 0);
    p.addTerm(2, 2);

    // act
    Polynomial product = p.multiply(one);

    // assert
    assertEquals("2x^2 + 1", product.toString());
  }

  @Test
  public void testMultiplicationOfTwoConstants() {
    // arrange
    Polynomial c1 = new SimplePolynomial();
    c1.addTerm(3, 0);

    Polynomial c2 = new SimplePolynomial();
    c2.addTerm(2, 0);

    // act
    Polynomial product = c1.multiply(c2);

    // assert
    assertEquals("6", product.toString());
  }

  @Test
  public void testMultiplicationOfTwoPolynomialsWithGapInPower() {
    // arrange
    Polynomial p1 = new SimplePolynomial();
    p1.addTerm(4, 3);
    p1.addTerm(1, 1);

    Polynomial p2 = new SimplePolynomial();
    p2.addTerm(3, 2);
    p2.addTerm(5, 0);

    // act
    Polynomial product = p1.multiply(p2);

    // assert
    assertEquals("12x^5 + 23x^3 + 5x^1", product.toString());
  }
}
