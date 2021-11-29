package edu.neu.coe.info6205.team7.NameBySyllabification;

import java.util.HashMap;
import java.util.Map;

public class LetterMap {

  public final static Map<String, Integer> FirstIndex = new HashMap<>();
  public final static Map<String, Integer> NextIndex = new HashMap<>();

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
    NextIndex.put("", index++);
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

}
