package edu.neu.coe.info6205.team7;

import net.sourceforge.pinyin4j.PinyinHelper;

public abstract class AbstractChsCharToIdxArr {
  public abstract int[] CharAt(char c);

  /**
   *
   * @param c A single Chinese character
   * @return A string represents pinyin of the Chinese character, including tone
   */
  public static String TransferChineseToPinyin(char c){
    String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c);
    if(pinyin.length > 0){
      if(pinyin[0].contains(":")){
        pinyin[0] = pinyin[0].replace(":", "");
      }
      return pinyin[0];
    }
    else{
      System.exit(-1);
    }
    return " ";
  }
}
