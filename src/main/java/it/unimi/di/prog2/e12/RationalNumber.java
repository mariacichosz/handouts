/*

Copyright 2025 Massimo Santini

This file is part of "Programmazione 2 @ UniMI" teaching material.

This is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This material is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this file.  If not, see <https://www.gnu.org/licenses/>.

*/

package it.unimi.di.prog2.e12;

import java.util.Arrays;

/**
 * A rational number is an immutable number that can be expressed as the quotient or fraction \( p/q
 * \) of two {@code int}s, a numerator \( p \) and a non-zero denominator \( q \).
 */
public class RationalNumber {

  // EXERCISE: complete following the specification (with particular attention
  // to the eventual exceptions) and provide an implementation (including the
  // equals, hashCode, and toString methods); add methods that are adequate to
  // the specification. Provide also the RI and AF.

  /** The numerator valure */
  private final int numerator;
  
  /** The denominator value, always positive */
  private final int denominator;

  /*
   * RI:
   * 
   *  - gcd of numerator and denominator is always 1.
   *  - denominator > 0
   * 
   * AF:
   * 
   *  - numerator represents \(p \).
   *  - denominator represents \(q \).
   * 
   */

  /**
   * Creates a new rational number.
   *
   * @param numerator the numerator.
   * @param denominator the denominator.
   * @return a simplified {@link RationalNumber} where {@code denominator} is always positive
   * @throws IllegalArgumentException if {@code denominator} is zero
   */
  public RationalNumber(int numerator, int denominator) {
    if (denominator == 0) throw new IllegalArgumentException("denominator cannot be zero u idiot\n");
    int gcd = GCD(numerator, denominator);
    this.numerator = (numerator * denominator >= 0 ? 1 : -1) * abs(numerator / gcd);
    this.denominator = abs(denominator / gcd);
  }

  /**
   * Calculates absolute value of an integer.
   * 
   * @param a integer.
   * @return absolute value of {@code a}.
   */
  private int abs(int a){
    return (a < 0 ? -1 : 1) * a;
  }

  /**
   * Calculates GCD of two integers.
   * 
   * @param a integer.
   * @param b integer.
   * @return the absoulte valuse of the greatest common divisor of {@code a} and {@code b}.
   */
  private int GCD(int a, int b){
    if (b == 0) return abs(a);
    return GCD(b, a % b);
  }

  /**
   * Calculates LCM of two integers.
   * 
   * @param a integer
   * @param b integer.
   * @return the least common multiple of {@code a} and {@code b}.
   */
  private int LCM(int a, int b){
    return a / GCD(a, b) * b;
  }

  /**
   * Returns the sum of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the sum of this rational number and {@code other}.
   */
  public RationalNumber add(RationalNumber other) {
    int nDenominator = LCM(this.denominator, other.denominator);
    int nNumerator = this.numerator * (nDenominator / this.denominator) + other.numerator * (nDenominator / other.denominator);
    return new RationalNumber(nNumerator, nDenominator);
  }

  /**
   * Returns the product of this rational number and another one.
   *
   * @param other the other rational number.
   * @return the product of this rational number and {@code other}.
   */
  public RationalNumber mul(RationalNumber other) {
    return new RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator);
  }

  @Override
  public boolean equals (Object obj){
    if (this == obj) return true;
    if (!(obj instanceof RationalNumber other)) return false;
    return (this.numerator == other.numerator && this.denominator == other.denominator);
  }

  @Override
  public int hashCode(){
    int[] nb = new int[]{this.numerator, this.denominator};
    return Arrays.hashCode(nb);
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder(this.numerator);
    if (this.denominator != 1) sb.append("/" + this.denominator);
    sb.append("\n");
    return sb.toString();
  }
}
