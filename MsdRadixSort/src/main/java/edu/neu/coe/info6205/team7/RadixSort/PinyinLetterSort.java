package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;

/**
 * Radix Sort for Pinyin characters in natural order The class extends PinyinSort and implements
 * some APIs for Chinese sort algorithm
 *
 * @author Yiqing Jackie Huang
 */
class PinyinLetterSort extends PinyinSort {

  public PinyinLetterSort(Helper<String> helper) {
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
      case 3:
        return 1;
      case 6:
        return 4;
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
          return (int) bArr[loc + 2] >> 3;
        case 3:
          return (int) bArr[loc + 2] & 0x7;
        case 4:
          return (int) bArr[loc + 3] >> 4;
        case 5:
          return bArr[loc + 3] >> 3 & 0x1;
        case 6:
          return (int) bArr[loc + 3] & 0x7;
      }
    }
    return -1;
  }
}
