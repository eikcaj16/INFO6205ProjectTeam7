package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.huskySort.sort.SortWithHelper;

/**
 * An abstract class extends from SortWithHelper and provides APIs for Radix Sort
 *
 * @author Yiqing Jackie Huang
 */
public abstract class RadixSort extends SortWithHelper<String> {

  public RadixSort(Helper<String> helper) {
    super(helper);
  }

  /**
   * Sort from xs[lo] to xs[hi] (exclusive) xs is an array of strings using MsdRadixSort
   *
   * @param xs the array to be sorted
   * @param lo the low index
   * @param hi the high index (exclusive)
   */
  @Override
  public abstract void sort(String[] xs, int lo, int hi);
}
