package edu.neu.coe.info6205.team7;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.huskySort.sort.SortWithHelper;

public abstract class MsdRadixSort extends SortWithHelper<String> {

  public MsdRadixSort(Helper<String> helper) {
    super(helper);
  }

  /**
   * Sort an array of Strings using MsdRadixSort.
   *
   * @param xs the array to be sorted.
   */
  @Override
  public abstract void sort(String[] xs, int from, int to);

  /**
   * Sort from a[lo] to a[hi] (exclusive), ignoring the first d characters of each String. This
   * method is recursive.
   *
   * @param lo     the low index.
   * @param hi     the high index (one above the highest actually processed).
   * @param d      the number of characters in each String to be skipped.
   * @param cirIdx circle index {0, 1, 2, 3}
   */
  protected abstract void sort(int lo, int hi, int d, int cirIdx);

  protected abstract int charAt(byte[] bArr, int d, int cirIdx);

  protected static byte[][] aux;
  protected static byte[][] compArr;
}
