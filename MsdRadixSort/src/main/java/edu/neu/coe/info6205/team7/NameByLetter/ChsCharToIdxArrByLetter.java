package edu.neu.coe.info6205.team7.NameByLetter;

import edu.neu.coe.info6205.team7.AbstractChsCharToIdxArr;

public class ChsCharToIdxArrByLetter extends AbstractChsCharToIdxArr {

  @Override
  public int[] CharAt(char c) {
    int[] res = new int[7];
    String wholePinyin = TransferChineseToPinyin(c);
    int tone = wholePinyin.charAt(wholePinyin.length() - 1) - '0';
    String pinyin = wholePinyin.substring(0, wholePinyin.length() - 1);

    int index = 0;
    while (index < pinyin.length()) {
      res[index] = mapIndex(index, pinyin);
      index++;
    }
    res[6] = tone;
    return res;
  }

  @Override
  public int[] CharAt(String s) {
    int[] res = new int[s.length() * 7];
    for (int i = 0; i < s.length(); i++) {
      String wholePinyin = TransferChineseToPinyin(s.charAt(i));
      int tone = wholePinyin.charAt(wholePinyin.length() - 1) - '0';
      String pinyin = wholePinyin.substring(0, wholePinyin.length() - 1);
      int index = 0;
      while (index < pinyin.length()) {
        res[index + i * 7] = mapIndex(index, pinyin);
        index++;
      }
      res[i * 7 + 6] = tone;
    }
    return res;
  }

  public int mapIndex(int index, String pinyin) {
    switch (index) {
      case 0:
        return LetterMap.index0.get(pinyin.charAt(0));
      case 1:
        return LetterMap.index1.get(pinyin.charAt(1));
      case 2:
        return LetterMap.index2.get(pinyin.charAt(2));
      case 3:
        return LetterMap.index3.get(pinyin.charAt(3));
      case 4:
        return LetterMap.index4.get(pinyin.charAt(4));
      case 5:
        return LetterMap.index5.get(pinyin.charAt(5));
      default:
        return -1;
    }
  }
}
