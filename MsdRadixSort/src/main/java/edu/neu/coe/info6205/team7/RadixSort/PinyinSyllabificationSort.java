package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;

/**
 * Radix Sort for Pinyin characters in syllabification order The class extends PinyinSort and
 * implements some APIs for Chinese sort algorithm
 *
 * @author Yiqing Jackie Huang
 */
class PinyinSyllabificationSort extends PinyinSort {

  public PinyinSyllabificationSort(Helper<String> helper) {
    super(helper);
  }

  @Override
  void convertStrArrToByteArr(String[] wordArr) {
  }

  @Override
  int getStep(int cirIdx) {
    switch (cirIdx) {
      case 0:
      case 1:
      case 2:
        return 1;
      case 3:
        return 5;
      default:
        return 0;
    }
  }

  @Override
  int charAt(byte[] bArr, int d, int cirIdx) {
    if (d < bArr.length) {
      int loc = (d / 8) * 8;
      switch (cirIdx) {
        case 0:
          return bArr[loc];
        case 1:
          return bArr[loc + 1];
        case 2:
          return bArr[loc + 2];
        case 3:
          return bArr[loc + 3];
      }
    }
    return -1;
  }
}
