package edu.neu.coe.info6205.team7.NameBySyllabification;

import edu.neu.coe.info6205.team7.PureHuskySortWithHelper;
import java.util.ArrayList;
import java.util.List;


public class NameBySyllabification implements
    Comparable<NameBySyllabification>, CharSequence {

  private final String Name;
  private int[] IndexArr;
  private boolean isInit = false;

  public NameBySyllabification(String name) {
    Name = name;
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();
    IndexArr = processor.CharAt(Name);
  }

  public NameBySyllabification[] preProcess(){
    if(!isInit) {
      ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();
      IndexArr = processor.CharAt(Name);
      isInit = true;
    }
    return null;
  }

  public String getName() {
    return Name;
  }

  @Override
  public int length() {
    return Name.length();
  }

  @Override
  public char charAt(int index) {
    return Name.charAt(index);
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    return new NameBySyllabification(Name.substring(start, end));
  }

  @Override
  public String toString() {
    return Name;
  }

  @Override
  public int compareTo(NameBySyllabification o) {
    int compare_length = Math.min(Name.length(), o.Name.length());

    for (int i = 0; i < compare_length; i++) {
      for (int j = 0; j < 4; j++) {
        int value_1 = IndexArr[i * 4 + j];
        int value_2 = o.IndexArr[i * 4 + j];

        if (value_1 != value_2) {
          return Integer.compare(value_1, value_2);
        }
      }
    }

    return Integer.compare(this.Name.length(), o.getName().length());
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

    names.add("欧阳修");
    names.add("欧阳休");

    // Using each Chinese name to construct a name processor by Syllabification
    List<NameBySyllabification> namesProcessor = new ArrayList<>();
    for (String s : names) {
      NameBySyllabification name = new NameBySyllabification(s);
      namesProcessor.add(name);
    }

    //namesProcessor.forEach(o -> System.out.println(o.NameSplitPinyin));

    namesProcessor.stream()
        .sorted()
        .forEach(o -> System.out.println(o.Name));
  }

}
