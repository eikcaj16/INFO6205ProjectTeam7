package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import java.util.Arrays;

/**
 * LSD Radix Sort for latin characters Adapted from <a href="INFO6205
 * repository">https://github.com/rchillyard/INFO6205</a>
 *
 * @author Prof. Robin Hillyard
 */
public final class LsdStringSort extends RadixSort {

  public LsdStringSort(Helper<String> helper) {
    super(helper);
  }

  @Override
  public void sort(String[] xs, int lo, int hi) {
    int maxLength = findMaxLength(xs);
    for (int i = maxLength - 1; i >= 0; i--) {
      charSort(xs, i, lo, hi);
    }
  }

  /**
   * findMaxLength method returns maximum length of all available strings in an array
   *
   * @param strArr It contains an array of String from which maximum length needs to be found
   * @return int Returns maximum length value
   */
  private int findMaxLength(String[] strArr) {
    int maxLength = strArr[0].length();
    for (String str : strArr) {
      maxLength = Math.max(maxLength, str.length());
    }
    return maxLength;
  }

  /**
   * charAsciiVal method returns ASCII value of particular character in a String.
   *
   * @param str          String input for which ASCII Value need to be found
   * @param charPosition Character position of which ASCII value needs to be found. If character
   *                     doesn't exist then ASCII value of null i.e. 0 is returned
   * @return int Returns ASCII value
   */
  private int charAsciiVal(String str, int charPosition) {
    if (charPosition >= str.length()) {
      return 0;
    }
    return str.charAt(charPosition);
  }

  /**
   * charSort method is implementation of LSD sort algorithm at particular character.
   *
   * @param strArr       It contains an array of String on which LSD char sort needs to be
   *                     performed
   * @param charPosition This is the character position on which sort would be performed
   * @param from         This is the starting index from which sorting operation will begin
   * @param to           This is the ending index up until which sorting operation will be
   *                     continued
   */
  private void charSort(String[] strArr, int charPosition, int from, int to) {
    int[] count = new int[ASCII_RANGE + 2];
    String[] result = new String[strArr.length];

    for (int i = from; i < to; i++) {
      int c = charAsciiVal(strArr[i], charPosition);
      count[c + 2]++;
    }

    // transform counts to indices
    for (int r = 1; r < ASCII_RANGE + 2; r++) {
      count[r] += count[r - 1];
    }

    // distribute
    for (int i = from; i < to; i++) {
      int c = charAsciiVal(strArr[i], charPosition);
      result[count[c + 1]++] = strArr[i];
    }

    // copy back
    if (to - from >= 0) {
      System.arraycopy(result, 0, strArr, from, to - from);
    }
  }

  private final int ASCII_RANGE = 256;

  public static void main(String[] args) {
    String[] aa = {"dab", "db", "dac", "bac", "ba"};
    RadixSort lss = new LsdStringSort(new HelperWIthTesting<>("MSD Radix Sort"));
    lss.sort(aa, 0, aa.length);
    Arrays.stream(aa).forEach(System.out::println);
  }
}
