package edu.neu.coe.info6205.team7.NameBySyllabification;

import java.util.ArrayList;
import java.util.List;


public class NameBySyllabification implements
    Comparable<NameBySyllabification>, CharSequence {

  private final String Name;
  private final int[] IndexArr;

  public NameBySyllabification(String name) {
    Name = name;
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();
    IndexArr = processor.CharAt(Name);
  }

  public String getName() {
    return Name;
  }

  @Override
  public int length() {
    return Name.length();
  }

  @Override
  public char charAt(int index) {
    return Name.charAt(index);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return new NameBySyllabification(Name.substring(start, end));
  }

  @Override
  public String toString() {
    return Name;
  }

  @Override
  public int compareTo(NameBySyllabification o) {
    int compare_length = Math.min(Name.length(), o.Name.length());

    for (int i = 0; i < compare_length; i++) {
      for (int j = 0; j < 4; j++) {
        int value_1 = IndexArr[i * 4 + j];
        int value_2 = o.IndexArr[i * 4 + j];

        if (value_1 != value_2) {
          return Integer.compare(value_1, value_2);
        }
      }
    }

    return Integer.compare(this.Name.length(), o.getName().length());
  }

}
