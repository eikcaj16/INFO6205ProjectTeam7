package edu.neu.coe.info6205.team7.NameBySyllabification;

import edu.neu.coe.info6205.team7.IChsCharToIdxArr;
import java.util.LinkedList;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;

public class ChsCharToIdxArrBySylla implements IChsCharToIdxArr {

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
  public static int[] CharAt(char c){
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



}
