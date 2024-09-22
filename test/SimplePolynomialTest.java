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

  }

  @Test
  public void testAddTermWhenCoefficientZero() {

  }

  @Test
  public void testAddTermWhenCoefficientNonZero() {

  }

  @Test
  public void testEvaluateWhenEmpty() {

  }

  @Test
  public void testEvaluateWhenDegreeZero() {

  }

  @Test
  public void testEvaluateWhenDegreeNonZero() {

  }

  @Test
  public void testToStringWhenEmpty() {

  }

  @Test
  public void testToString() {

  }

  @Test
  public void testAddTwoEmptyPolynomials() {

  }

  @Test
  public void testAddWhenOtherPolynomialIsEmpty() {

  }

  @Test
  public void testAddWhenCurrentPolynomialIsEmpty() {

  }

  @Test
  public void testAddTwoNonEmptyPolynomials() {

  }

  @Test
  public void testDerivateWhenEmpty() {

  }

  @Test
  public void testDerivateWhenConstant() {

  }

  @Test
  public void testDerivate() {

  }
}
