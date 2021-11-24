package edu.neu.coe.info6205.team7;

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

  private final static Map<String, Integer> FirstIndex = new HashMap<>();
  private final static Map<String, Integer> NextIndex = new HashMap<>();

  static{
    int index = 0;
    FirstIndex.put("a", index++);
    FirstIndex.put("ai", index++);
    FirstIndex.put("ao", index++);
    FirstIndex.put("ang", index++);
    FirstIndex.put("b", index++);
    FirstIndex.put("c", index++);
    FirstIndex.put("ch", index++);
    FirstIndex.put("d", index++);
    FirstIndex.put("e", index++);
    FirstIndex.put("ei", index++);
    FirstIndex.put("en", index++);
    FirstIndex.put("eng", index++);
    FirstIndex.put("er", index++);
    FirstIndex.put("f", index++);
    FirstIndex.put("g", index++);
    FirstIndex.put("h", index++);
    FirstIndex.put("i", index++);
    FirstIndex.put("j", index++);
    FirstIndex.put("k", index++);
    FirstIndex.put("l", index++);
    FirstIndex.put("m", index++);
    FirstIndex.put("n", index++);
    FirstIndex.put("o", index++);
    FirstIndex.put("ou", index++);
    FirstIndex.put("p", index++);
    FirstIndex.put("q", index++);
    FirstIndex.put("r", index++);
    FirstIndex.put("s", index++);
    FirstIndex.put("sh", index++);
    FirstIndex.put("t", index++);
    FirstIndex.put("w", index++);
    FirstIndex.put("x", index++);
    FirstIndex.put("y", index++);
    FirstIndex.put("z", index++);
    FirstIndex.put("zh", index);

    index = 0;
    NextIndex.put("a", index++);
    NextIndex.put("ai", index++);
    NextIndex.put("ao", index++);
    NextIndex.put("an", index++);
    NextIndex.put("ang", index++);
    NextIndex.put("e", index++);
    NextIndex.put("ei", index++);
    NextIndex.put("en", index++);
    NextIndex.put("eng", index++);
    NextIndex.put("er", index++);
    NextIndex.put("i", index++);
    NextIndex.put("ie", index++);
    NextIndex.put("in", index++);
    NextIndex.put("ing", index++);
    NextIndex.put("iu", index++);
    NextIndex.put("o", index++);
    NextIndex.put("ong", index++);
    NextIndex.put("ou", index++);
    NextIndex.put("u", index++);
    NextIndex.put("ue", index++);
    NextIndex.put("ui", index++);
    NextIndex.put("un", index);
  }

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

          if(i < 1 && FirstIndex.containsKey(sub)) {
            slice.add(sub);
            i += step;
            break;
          }
          else if(NextIndex.containsKey(sub)) {
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
            value_1 = FirstIndex.get(pinyin_1.get(j));
            value_2 = FirstIndex.get(pinyin_2.get(j));
          } else if (j < 3) {
            value_1 = NextIndex.get(pinyin_1.get(j));
            value_2 = NextIndex.get(pinyin_2.get(j));
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
