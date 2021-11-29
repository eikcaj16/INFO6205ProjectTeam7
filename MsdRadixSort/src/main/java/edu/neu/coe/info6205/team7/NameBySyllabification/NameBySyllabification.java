package edu.neu.coe.info6205.team7.NameBySyllabification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;


public class NameBySyllabification implements Comparable<NameBySyllabification> {

  private final String Name;
  private final List<String> NamePinyin;
  private final List<List<String>> NameSplitPinyin;

  public NameBySyllabification(String name){
    Name = name;
    NamePinyin = new ArrayList<>();
    NameSplitPinyin = new ArrayList<>();

//    TransferChineseToPinyin();
//    SpiltBySyllabification();
  }

  public String getName() {
    return Name;
  }

  public List<String> getNamePinyin() {
    return NamePinyin;
  }

  public List<List<String>> getNameSplitPinyin() {
    return NameSplitPinyin;
  }

  public void TransferChineseToPinyin(){
    for(int i = 0; i < Name.length(); i++){
      String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(Name.charAt(i));
      if(pinyin.length > 0){
        if(pinyin[0].contains(":")){
          pinyin[0] = pinyin[0].replace(":", "");
        }
        NamePinyin.add(pinyin[0]);
      }
    }
  }

  public void SpiltBySyllabification(){
    for(String pinyin: NamePinyin){
      //Go through all Chinese character in the name
      List<String> slice = new LinkedList<>();

      int length = pinyin.length();
      String tone = Character.toString(pinyin.charAt(length - 1));
      pinyin = pinyin.substring(0, length - 1);

      for(int i = 0; i < pinyin.length(); ){
        int old_i = i;
        for(int step = 3; step >= 1; step--){
          //Go through pinyin with different step, try to find Chinese pinyin combo

          if(i + step > pinyin.length()){
            continue;
          }

          String sub = pinyin.substring(i, i + step);

          if(i < 1 && LetterMap.FirstIndex.containsKey(sub)) {
            slice.add(sub);
            i += step;
            break;
          }
          else if(LetterMap.NextIndex.containsKey(sub)) {
            slice.add(sub);
            i += step;
            break;
          }
        }
        if(i == old_i){
          System.out.println("Matching pinyin Error! Can't match: " + pinyin);
          System.exit(-1);
        }
      }

      if(slice.size() == 0){
        System.out.println("Matching pinyin Error! Can't match: " + pinyin);
        System.exit(-1);
      }

      while (slice.size() < 3){
        slice.add("");
      }

      slice.add(tone);
      NameSplitPinyin.add(slice);
    }
  }

  @Override
  public int compareTo(NameBySyllabification o) {
    int compare_length = Math.min(this.NameSplitPinyin.size(), o.NameSplitPinyin.size());

    for(int i = 0; i < compare_length; i++){
      List<String> pinyin_1 = this.NameSplitPinyin.get(i);
      List<String> pinyin_2 = o.NameSplitPinyin.get(i);
      for (int j = 0; j < 4; j++) {
        int value_1, value_2;
//        System.out.println(this.Name + " " + o.Name + " " + pinyin_1.get(j) + " " + pinyin_2.get(j));

        if(pinyin_1.get(j).length() > 0 && pinyin_2.get(j).length() > 0) {
          // Take each syllable from pinyin to compare
          if (j == 0) {
            value_1 = LetterMap.FirstIndex.get(pinyin_1.get(j));
            value_2 = LetterMap.FirstIndex.get(pinyin_2.get(j));
          } else if (j < 3) {
            value_1 = LetterMap.NextIndex.get(pinyin_1.get(j));
            value_2 = LetterMap.NextIndex.get(pinyin_2.get(j));
          } else {
            value_1 = Integer.parseInt(pinyin_1.get(j));
            value_2 = Integer.parseInt(pinyin_2.get(j));
          }

          if (value_1 < value_2) {
            return -1;
          } else if (value_1 > value_2) {
            return 1;
          } else if (i == compare_length - 1 && j == 3) {
            return Integer.compare(this.NameSplitPinyin.size(), o.NameSplitPinyin.size());
          }
        }
      }
    }

    return 0;
  }

  public static void demo(){
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

    names.add("欧阳修");
    names.add("欧阳休");

    // Using each Chinese name to construct a name processor by Syllabification
    List<NameBySyllabification> namesProcessor = new ArrayList<>();
    for(String s: names){
      NameBySyllabification name = new NameBySyllabification(s);
      name.TransferChineseToPinyin();
      name.SpiltBySyllabification();
      namesProcessor.add(name);
    }

    namesProcessor.forEach(o -> System.out.println(o.NameSplitPinyin));

    namesProcessor.stream()
        .sorted()
        .forEach(o -> System.out.println(o.Name));
  }

}
