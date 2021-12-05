package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import java.util.Arrays;

/**
 * A class extends from RadixSort to combine Msd Pinyin Sort in Syllabification order and Msd Pinyin
 * Sort in natural order, which both guarantee the result is in Pinyin natural order in Pinyin and
 * promise a better performance than only using Msd Pinyin Sort in natural order
 *
 * @author Yiqing Jackie Huang
 */
public class AdvancedMsdPinyinLetterSort extends RadixSort {

  public AdvancedMsdPinyinLetterSort(Helper<String> helper) {
    super(helper);
    this.helper = helper;
    msdPinyinSyllabificationSort = new MsdPinyinSyllabificationSort(helper);
  }

  public AdvancedMsdPinyinLetterSort(Helper<String> helper, int maxWordLength) {
    super(helper);
    this.helper = helper;
    maxWordLen = maxWordLength;
    msdPinyinSyllabificationSort = new MsdPinyinSyllabificationSort(helper, maxWordLength);
  }

  @Override
  /**
   * Sort from xs[lo] to xs[hi] (exclusive) xs is an array of strings.
   *
   * ===IMPORTANT===
   * Call AdvancedMsdPinyinLetterSort.preProcess before calling AdvancedMsdPinyinLetterSort.sort
   * Call AdvancedMsdPinyinLetterSort.postProcess after sorting to get the result in a string array
   *
   * @param xs the array to be sorted
   * @param lo the low index
   * @param hi the high index (exclusive)
   */
  public void sort(String[] xs, int lo, int hi) {
    msdPinyinSyllabificationSort.sort(xs, lo, hi);
    msdPinyinSyllabificationSort.postProcess(xs);
    RadixSort msdPinyinLetterSort = new MsdPinyinLetterSort(helper, maxWordLen);
    msdPinyinLetterSort.preProcess(xs);
    msdPinyinLetterSort.sort(xs, lo, hi);
  }

  @Override
  public void postProcess(String[] xs) {
    msdPinyinSyllabificationSort.postProcess(xs);
  }

  @Override
  public String[] preProcess(String[] xs) {
    return msdPinyinSyllabificationSort.preProcess(xs);
  }

  private final RadixSort msdPinyinSyllabificationSort;
  private final Helper<String> helper;
  private int maxWordLen = 4;

  public static void main(String[] args) {
    String[] sa = {"老伙计", "救济", "做作", "经济", "坐下", "啊", "砸", "子", "值"};
    RadixSort mrs = new AdvancedMsdPinyinLetterSort(new HelperWIthTesting<>("MSD Radix Sort"));
    mrs.preProcess(sa);
    mrs.sort(sa, 0, sa.length);
    mrs.postProcess(sa);
    Arrays.stream(sa).forEach(System.out::println);
  }
}
