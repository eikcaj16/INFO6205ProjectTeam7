package edu.neu.coe.info6205.team7.NameByLetter;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class NameByLetter implements Comparable<NameByLetter> {

  private final String name;
  private final List<String> pinyin;
  private final List<Integer> tone;
  HanyuPinyinOutputFormat pinyinOutputFormat = new HanyuPinyinOutputFormat();

  public List<String> getPinyin() {
    return pinyin;
  }

  public List<Integer> getTone() {
    return tone;
  }

  public NameByLetter(String name) {
    this.name = name;
    pinyin = new ArrayList<>();
    tone = new ArrayList<>();
    pinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    pinyinOutputFormat.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
    pinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    chineseToPinyin();
  }

  public void chineseToPinyin() {
    for (Character c : name.toCharArray()) {
      String[] temp = new String[0];
      try {
        temp = PinyinHelper.toHanyuPinyinStringArray(c, pinyinOutputFormat);
      } catch (BadHanyuPinyinOutputFormatCombination e) {
        e.printStackTrace();
      }
      if (temp.length > 0) {
        pinyin.add(temp[0].substring(0, temp[0].length() - 1));
        int thisTone = temp[0].charAt(temp[0].length() - 1) - '0';
        tone.add(thisTone == 5 ? 0 : thisTone);
      }
    }
  }

  @Override
  public int compareTo(NameByLetter o) {
    int length = Math.min(pinyin.size(), o.pinyin.size());
    for (int i = 0; i < length; i++) {
      String pinyin1 = pinyin.get(i);
      String pinyin2 = o.pinyin.get(i);
      int value1 = 0;
      int value2 = 0;
      int length1 = pinyin1.length();
      int length2 = pinyin2.length();

      int index = 0;
      while (index < Math.min(length1, length2)) {
        switch (index) {
          case 0:
            value1 = LetterMap.index0.get(pinyin1.charAt(0));
            value2 = LetterMap.index0.get(pinyin2.charAt(0));
            break;
          case 1:
            value1 = LetterMap.index1.get(pinyin1.charAt(1));
            value2 = LetterMap.index1.get(pinyin2.charAt(1));
            break;
          case 2:
            value1 = LetterMap.index2.get(pinyin1.charAt(2));
            value2 = LetterMap.index2.get(pinyin2.charAt(2));
            break;
          case 3:
            value1 = LetterMap.index3.get(pinyin1.charAt(3));
            value2 = LetterMap.index3.get(pinyin2.charAt(3));
            break;
          case 4:
            value1 = LetterMap.index4.get(pinyin1.charAt(4));
            value2 = LetterMap.index4.get(pinyin2.charAt(4));
            break;
          case 5:
            value1 = LetterMap.index5.get(pinyin1.charAt(5));
            value2 = LetterMap.index5.get(pinyin2.charAt(5));
            break;
        }
        if (value1 != value2) {
          return Integer.compare(value1, value2);
        }
        if (length1 < index + 2 || length2 < index + 2) {
          if (length1 == length2) {
            if (tone.get(i).equals(o.tone.get(i))) {
              // Two characters have the same pinyin. Stop Comparing and jump to the next character.
              index = 6;
              continue;
            }
            return tone.get(i).compareTo(o.tone.get(i));
          } else {
            return Integer.compare(length1, length2);
          }
        }
        index++;
      }
    }
    return Integer.compare(pinyin.size(), o.pinyin.size());
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
