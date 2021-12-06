package edu.neu.coe.info6205.team7.NameByLetter;

import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class NameByLetter implements
    Comparable<NameByLetter>, CharSequence {

  private final String name;
  private int[] IndexArr;
  private boolean isInit = false;

  public NameByLetter(String name) {
    this.name = name;

    ChsCharToIdxArrByLetter processor = new ChsCharToIdxArrByLetter();
    IndexArr = processor.CharAt(name);
  }

  public NameByLetter[] preProcess(){
    if(!isInit) {
      ChsCharToIdxArrByLetter processor = new ChsCharToIdxArrByLetter();
      IndexArr = processor.CharAt(name);
      isInit = true;
    }
    return null;
  }

  public String getName() {
    return name;
  }

  @Override
  public int length() {
    return name.length();
  }

  @Override
  public char charAt(int index) {
    return name.charAt(index);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return name.substring(start, end);
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public int compareTo(NameByLetter o) {
    int length = Math.min(name.length(), o.name.length());

    for (int i = 0; i < length; i++) {
      for (int j = 0; j < 7; j++) {
        int value_1 = IndexArr[i * 7 + j];
        int value_2 = o.IndexArr[i * 7 + j];

        if (value_1 != value_2) {
          return Integer.compare(value_1, value_2);
        }
      }
    }
    return Integer.compare(name.length(), o.name.length());
  }

}
