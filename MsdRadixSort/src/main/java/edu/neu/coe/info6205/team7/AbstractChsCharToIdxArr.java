package edu.neu.coe.info6205.team7;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public abstract class AbstractChsCharToIdxArr {
  public abstract int[] CharAt(char c);

  static HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();
  static {
    pinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
    pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
  }
  /**
   *
   * @param c A single Chinese character
   * @return A string represents pinyin of the Chinese character, including tone
   */
  public static String TransferChineseToPinyin(char c){
    String[] pinyin = new String[0];
    try {
      pinyin = PinyinHelper.toHanyuPinyinStringArray(c, pinyinOutputFormat);
    } catch (BadHanyuPinyinOutputFormatCombination e) {
      e.printStackTrace();
    }
    if(pinyin.length > 0){
      if(pinyin[0].contains(":")){
        pinyin[0] = pinyin[0].replace(":", "");
      }
      return pinyin[0];
    }
    else{
      System.exit(-1);
    }
    return "";
  }
}
