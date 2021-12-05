package edu.neu.coe.info6205.team7.InsertionSort;

import java.nio.charset.StandardCharsets;

/**
 * Insertion sort for the encoded Chinese byte array
 *
 * @author Yiqing Jackie Huang
 */
public class InsertionSortPinyin {

  public static void sort(byte[][] a, int lo, int hi, int d) {
    // An aux array of String that represent for the remaining part of each byte array that needs
    // to be sorted. Meanwhile, we filter the unnecessary part in each byte array which represents
    // for the original chinese character
    String[] aux = new String[a.length];
    int loc = (d / 8) * 8;
    for (int i = 0; i < a.length; i++) {
      // For each 8 bytes, there are 4 bytes that represents for the original chinese character
      // that we do not need
      byte[] tempByteArr = new byte[(a[i].length - loc) / 2];
      int idx = 0;
      for (int j = loc; j < a[i].length; j += 8) {
        tempByteArr[idx] = a[i][j];
        tempByteArr[idx + 1] = a[i][j + 1];
        tempByteArr[idx + 2] = a[i][j + 2];
        tempByteArr[idx + 3] = a[i][j + 3];
        idx += 4;
      }
      // Since the highest digit of each 8 bytes is 0, each 8 bytes can be converted to a character
      // when doing UTF-8 string convert. Never can a case such that 16, 24, or 32 bytes would be
      // converted into one string character happen. Thus, the original information can be converted
      // into String type in a lossless way
      aux[i] = new String(tempByteArr, StandardCharsets.UTF_8);
    }

    for (int i = lo; i < hi; i++) {
      for (int j = i; j > lo && less(aux[j], aux[j - 1]); j--) {
        swap(a, aux, j, j - 1);
      }
    }
  }

  private static boolean less(String v, String w) {
    return v.compareTo(w) < 0;
  }

  private static void swap(byte[][] a, String[] aux, int j, int i) {
    byte[] temp = a[j];
    a[j] = a[i];
    a[i] = temp;
    String tempStr = aux[j];
    aux[j] = aux[i];
    aux[i] = tempStr;
  }
}
