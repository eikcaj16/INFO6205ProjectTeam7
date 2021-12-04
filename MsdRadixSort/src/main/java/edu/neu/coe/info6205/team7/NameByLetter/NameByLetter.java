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
  //  private final List<String> pinyin;
//  private final List<Integer> tone;
  HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();

  private final int[] IndexArr;
  private final int pinyin_length;

  public NameByLetter(String name) {
    this.name = name;

    ChsCharToIdxArrByLetter processor = new ChsCharToIdxArrByLetter();
    IndexArr = processor.CharAt(name);
    pinyin_length = IndexArr.length / 7;
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

  public static void demo() {
    List<String> names = new ArrayList<>();
    names.add("李白");
    names.add("李白白");

    names.add("杜甫");
    names.add("杜芙");

    names.add("白居易");
    names.add("白居一");
    names.add("白居亦");
    names.add("白居疑");

    names.add("苏东坡");
    names.add("苏东");

    names.add("欧阳秀");
    names.add("欧阳修");
    names.add("女布");
    names.add("吕布");
    names.add("鲁豫");

    names.add("张床户");
    names.add("章床户");
    names.add("张窗活");

    names.add("爱林一");
    names.add("艾零");
    List<NameByLetter> namesProcessor = new ArrayList<>();
    for (String s : names) {
      namesProcessor.add(new NameByLetter(s));
    }
    namesProcessor.stream()
        .sorted()
        .forEach(o -> System.out.println(o.name));
  }

}
