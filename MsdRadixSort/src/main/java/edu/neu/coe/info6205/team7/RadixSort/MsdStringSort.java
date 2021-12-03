package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import java.util.Arrays;

/**
 * MSD Radix Sort for latin characters
 * Adapted from <a href="INFO6205 repository">https://github.com/rchillyard/INFO6205</a>
 *
 * @author Prof. Robin Hillyard
 */
public final class MsdStringSort extends RadixSort {

  public MsdStringSort(Helper<String> helper) {
    super(helper);
  }

  @Override
  public void sort(String[] xs, int lo, int hi) {
    aux = new String[xs.length];
    sort(xs, lo, hi, 0);
  }

  /**
   * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String. This
   * method is recursive
   *
   * @param a  the array to be sorted
   * @param lo the low index
   * @param hi the high index (one above the highest actually processed)
   * @param d  the number of characters in each String to be skipped
   */
  private void sort(String[] a, int lo, int hi, int d) {
    if (hi <= lo) {
    } else {
      int[] count = new int[radix + 2];        // Compute frequency counts.
      for (int i = lo; i < hi; i++) {
        count[charAt(a[i], d) + 2]++;
      }
      for (int r = 0; r < radix + 1; r++)      // Transform counts to indices.
      {
        count[r + 1] += count[r];
      }
      for (int i = lo; i < hi; i++)     // Distribute.
      {
        aux[count[charAt(a[i], d) + 1]++] = a[i];
      }
      // Copy back.
      System.arraycopy(aux, 0, a, lo, hi - lo);
      // Recursively sort for each character value.
      for (int r = 0; r < radix; r++) {
        sort(a, lo + count[r], lo + count[r + 1], d + 1);
      }
    }
  }

  private int charAt(String s, int d) {
    return d < s.length() ? s.charAt(d) : -1;
  }

  private static final int radix = 256;
  private static String[] aux;

  public static void main(String[] args) {
    String[] sa = {"dab", "dac", "abc"};
    RadixSort mrs = new MsdStringSort(new HelperWIthTesting<>("MSD Radix Sort"));
    mrs.sort(sa, 0, sa.length);
    Arrays.stream(sa).forEach(System.out::println);
  }
}
