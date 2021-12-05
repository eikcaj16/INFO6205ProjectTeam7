package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import java.util.Arrays;

/**
 * A class extends from PinyinSyllabificationSort to implement MSD Radix Sort for Chinese characters
 * in Pinyin Syllabification order
 *
 * @author Yiqing Jackie Huang
 */
public final class MsdPinyinSyllabificationSort extends PinyinSyllabificationSort {

  public MsdPinyinSyllabificationSort(Helper<String> helper) {
    super(helper);
    radix = new int[]{36, 24, 24, 5};
    cutoff = 6;
  }

  @Override
  void convertStrArrToByteArr(String[] wordArr) {
    final ChsCharToIdxArrBySylla cctiabs = new ChsCharToIdxArrBySylla();

    for (int k = 0; k < wordArr.length; k++) {
      String word = wordArr[k];
      int byteShift = 0;
      int[] pinyinIdxArr = cctiabs.CharAt(word);

      for (int i = 0; i < word.length(); i++) {
        // Since the greatest number of the index is less than 64 and the sign bit is useless in our case,
        // when casting to byte, the missing first byte of the int does not affect the result

        // 1st idx
        compArr[k][byteShift] |= (byte) (pinyinIdxArr[i * 4] - 1);
        // 2nd idx
        compArr[k][byteShift + 1] |= (byte) pinyinIdxArr[i * 4 + 1];
        // 3rd idx
        compArr[k][byteShift + 2] |= (byte) pinyinIdxArr[i * 4 + 2];
        // 4th idx
        compArr[k][byteShift + 3] |= (byte) (pinyinIdxArr[i * 4 + 3] - 1);
        // original word
        copyWordToByteArr(word, i, k, byteShift);
        byteShift += 8;
      }
    }
  }

  public static void main(String[] args) {
    String[] sa = {"老伙计", "救济", "做作", "经济", "坐下", "啊"};
    RadixSort mrs = new MsdPinyinSyllabificationSort(new HelperWIthTesting<>("MSD Radix Sort"));
    mrs.preProcess(sa);
    mrs.sort(sa, 0, sa.length);
    mrs.postProcess(sa);
    Arrays.stream(sa).forEach(System.out::println);
  }
}
