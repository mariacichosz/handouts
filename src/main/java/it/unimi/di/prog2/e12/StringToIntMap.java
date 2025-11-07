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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * A map from {@link String} to {@link Integer}.
 *
 * <p>A <em>map</em> is a collection that associates keys to values. In this case, the keys are
 * strings and the values are integers. The map cannot contain duplicate keys, which means that each
 * key can be associated to at most one value.
 */
public class StringToIntMap {

  // EXERCISE: provide an implementation (including the equals, hashCode, and toString
  // methods). Provide also the RI and AF.

  // Note: do not use the Map in Java Collections, the point is to implement it from scratch!

  /** A list of values rembered by the keys */
  private ArrayList<Integer> values;

  /** A list of keys present in the map */
  private ArrayList<String> keys;

  /** The curent number of keys in the map */
  int size;

  /*
   * RI:
   * 
   *  - keys is not null and does not contain nulls
   *  - keys does not contain duplicates
   *  - values is not null
   * 
   * AF:
   * 
   *  - keys contains the keys present int the map
   *  - values contains values associated to the keys
   *  - size specifies the current amount of (key, value) pairs present in the map
   *  - for each 0 <= i < size keys[i] corrisponds to values[i]
   * 
   */

  /** Creates a new empty map. */
  public StringToIntMap() {
    values = new ArrayList<>();
    keys = new ArrayList<>();
    size = 0;
  }

  /**
   * Returns the size of this map.
   *
   * @return the number of key-value mappings in this map.
   */
  public int size() {
    return size;
  }

  /**
   * Returns if this map is empty.
   *
   * @return {@code true} iff this map contains no key-value mappings.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Finds the position of the key in the list.
   * 
   * @param key the key to search for.
   * @return the index of the position of the {@code key} in the list if it's present; -1 if it's not.
   */
  private int getIndexK(String key){
    for (int i = 0; i < size; i ++){
      if (keys.get(i).equals(key)) return i;
    }
    return -1;
  }

  /**
   * Finds the position of the value in the list.
   * 
   * @param value the value to search for.
   * @return the index of the position of the {@code value} in the list if it's present; -1 if it's not.
   */
  private int getIndexV(int value){
    for (int i = 0; i < size; i ++){
      if (values.get(i) == value) return i;
    }
    return -1;
  }

  /**
   * Returns if this map contains the specified key.
   *
   * @param key the key to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code key}.
   */
  public boolean containsKey(String key) {
    return getIndexK(key) != -1;
  }

  /**
   * Returns if this map contains the specified value.
   *
   * @param value the value to search for.
   * @return {@code true} iff this map contains a key-value mappings with the given {@code value}.
   */
  public boolean containsValue(int value) {
    return getIndexV(value) != -1;
  }

  /**
   * Returns the value to which the specified key is mapped.
   *
   * @param key the key whose associated value is to be returned.
   * @return the value to which the specified key is mapped.
   * @throws NoSuchElementException if this map contains no mapping for the key, or the key is
   *     {@code null}.
   */
  public int get(String key) throws NoSuchElementException {
    Objects.requireNonNull(key);
    if (!containsKey(key)) throw new NoSuchElementException();
    return values.get(getIndexK(key));
  }

  /**
   * Associates in this map the new key with the specified value.
   *
   * @param key the key with which the specified value is to be associated.
   * @param value the value to be associated with the specified key.
   * @throws IllegalArgumentException if the map already contain a mapping for the key.
   * @throws NullPointerException if the key is {@code null}.
   */
  public void put(String key, int value) {
    Objects.requireNonNull(key);
    if (containsKey(key)) throw new IllegalArgumentException("this key already exists\n");
    keys.add(key);
    values.add(value);
    size ++;
  }

  /**
   * Removes the mapping for a key from this map if it is present.
   *
   * @param key the key whose mapping is to be removed from the map.
   * @return {@code true} iff this map contained a mapping for the specified key, and hence is
   *     modified by this operation.
   */
  public boolean remove(String key) {
    Objects.requireNonNull(key);
    if (!containsKey(key)) return false;
    int i = getIndexK(key);
    keys.remove(i);
    values.remove(i);
    size --;
    return true;
  }

  /** Removes all of the mappings from this map. */
  public void clear() {
    keys.clear();
    values.clear();
    size = 0;
  }

  @Override
  public boolean equals(Object obj){
    if (this == obj) return true;
    if (!(obj instanceof StringToIntMap other)) return false;
    if (this.size != other.size) return false;
    for (String k: this.keys){
      if (!other.containsKey(k)) return false;
      if (this.get(k) != other.get(k)) return false;
    }
    return true;
  }

  @Override
  public int hashCode(){
    List<String> cp = keys.subList(0, size);
    cp.sort(null);
    for (int i = 0; i < size; i ++){
      cp.add(String.valueOf(get(cp.get(i))));
    }
    return cp.hashCode();
  }

  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    for (String key: keys){
      sb.append("[" + key + " : ");
      sb.append(get(key));
      sb.append("] ");
    }
    sb.append("\n");
    return sb.toString();
  }
}
