package edu.neu.coe.info6205.team7.NameBySyllabification;

import edu.neu.coe.info6205.team7.AbstractChsCharToIdxArr;
import java.util.LinkedList;
import java.util.List;

public class ChsCharToIdxArrBySylla extends AbstractChsCharToIdxArr {
  /**
   *
   * @param pinyin A string represents pinyin a single Chinese character
   * @return A list of string, which is split result of the pinyin
   */
  public static List<String> SpiltBySyllabification(String pinyin){
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
    return slice;
  }

  /**
   *
   * @param c A single Chinese character
   * @return An index array of each syllabification, which size is 4
   */
  @Override
  public int[] CharAt(char c){
    String pinyin = TransferChineseToPinyin(c);
    if(pinyin == null){
      System.exit(-1);
    }

    List<String> slice = SpiltBySyllabification(pinyin);

    int[] index_arr = new int[4];
    index_arr[0] = LetterMap.FirstIndex.get(slice.get(0));
    index_arr[1] = LetterMap.NextIndex.get(slice.get(1));
    index_arr[2] = LetterMap.NextIndex.get(slice.get(2));
    index_arr[3] = Integer.parseInt(slice.get(3));

    return index_arr;
  }

  public int[] CharAt(String s){
    int len = s.length();
    int[] index_arr = new int[len * 4];

    for(int i = 0; i < len; i++){
      String pinyin = TransferChineseToPinyin(s.charAt(i));
      if(pinyin == null){
        System.exit(-1);
      }

      List<String> slice = SpiltBySyllabification(pinyin);

      int i4 = i * 4;
      index_arr[i4] = LetterMap.FirstIndex.get(slice.get(0));
      index_arr[i4 + 1] = LetterMap.NextIndex.get(slice.get(1));
      index_arr[i4 + 2] = LetterMap.NextIndex.get(slice.get(2));
      index_arr[i4 + 3] = Integer.parseInt(slice.get(3));
    }

    return index_arr;
  }


}
