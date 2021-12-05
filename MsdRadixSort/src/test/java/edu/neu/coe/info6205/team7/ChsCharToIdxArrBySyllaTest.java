package edu.neu.coe.info6205.team7;

import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import edu.neu.coe.info6205.team7.NameBySyllabification.LetterMap;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ChsCharToIdxArrBySyllaTest {

  @Test
  public void testTransfer1() {
    String pinyin;
    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('李');
    assertEquals(pinyin, "li3");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('白');
    assertEquals(pinyin, "bai2");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('杜');
    assertEquals(pinyin, "du4");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('甫');
    assertEquals(pinyin, "fu3");
  }

  @Test
  public void testTransfer2() {
    String pinyin;
    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('王');
    assertEquals(pinyin, "wang2");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('昌');
    assertEquals(pinyin, "chang1");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('龄');
    assertEquals(pinyin, "ling2");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('欧');
    assertEquals(pinyin, "ou1");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('阳');
    assertEquals(pinyin, "yang2");

    pinyin = ChsCharToIdxArrBySylla.TransferChineseToPinyin('修');
    assertEquals(pinyin, "xiu1");
  }

  @Test
  public void testSpilt1() {
    String name1 = "白居易";
    String pinyin11 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(0));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin11),
        Arrays.asList("b", "ai", "", "2"));
    String pinyin12 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(1));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin12),
        Arrays.asList("j", "u", "", "1"));
    String pinyin13 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(2));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin13),
        Arrays.asList("y", "i", "", "4"));

    String name2 = "苏东坡";
    String pinyin21 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name2.charAt(0));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin21),
        Arrays.asList("s", "u", "", "1"));
    String pinyin22 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name2.charAt(1));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin22),
        Arrays.asList("d", "ong", "", "1"));
    String pinyin23 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name2.charAt(2));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin23),
        Arrays.asList("p", "o", "", "1"));
  }

  @Test
  public void testSpilt2() {
    String name1 = "祝无双";
    String pinyin11 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(0));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin11),
        Arrays.asList("zh", "u", "", "4"));
    String pinyin12 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(1));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin12),
        Arrays.asList("w", "u", "", "2"));
    String pinyin13 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name1.charAt(2));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin13),
        Arrays.asList("sh", "u", "ang", "1"));

    String name2 = "庄周";
    String pinyin21 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name2.charAt(0));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin21),
        Arrays.asList("zh", "u", "ang", "1"));
    String pinyin22 = ChsCharToIdxArrBySylla.TransferChineseToPinyin(name2.charAt(1));
    assertEquals(ChsCharToIdxArrBySylla.SpiltBySyllabification(pinyin22),
        Arrays.asList("zh", "ou", "", "1"));
  }

  @Test
  public void testCharAt1() {
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();

    String name1 = "祝无双";
    int[] index11 = processor.CharAt(name1.charAt(0));
    int[] index12 = processor.CharAt(name1.charAt(1));
    int[] index13 = processor.CharAt(name1.charAt(2));

    assertEquals(Arrays.stream(index11).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get(""),
            4));
    assertEquals(Arrays.stream(index12).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("w"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get(""),
            2));
    assertEquals(Arrays.stream(index13).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("sh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get("ang"),
            1));

    String name2 = "庄周";
    int[] index21 = processor.CharAt(name2.charAt(0));
    int[] index22 = processor.CharAt(name2.charAt(1));

    assertEquals(Arrays.stream(index21).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get("ang"),
            1));
    assertEquals(Arrays.stream(index22).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("ou"),
            LetterMap.NextIndex.get(""),
            1));
  }

  @Test
  public void testCharAt2() {
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();

    String name1 = "杨洋";
    int[] index11 = processor.CharAt(name1.charAt(0));
    int[] index12 = processor.CharAt(name1.charAt(1));

    assertEquals(Arrays.stream(index11).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("y"),
            LetterMap.NextIndex.get("ang"),
            LetterMap.NextIndex.get(""),
            2));
    assertEquals(Arrays.stream(index12).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("y"),
            LetterMap.NextIndex.get("ang"),
            LetterMap.NextIndex.get(""),
            2));

    String name2 = "倪妮";
    int[] index21 = processor.CharAt(name2.charAt(0));
    int[] index22 = processor.CharAt(name2.charAt(1));

    assertEquals(Arrays.stream(index21).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("n"),
            LetterMap.NextIndex.get("i"),
            LetterMap.NextIndex.get(""),
            2));
    assertEquals(Arrays.stream(index22).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("n"),
            LetterMap.NextIndex.get("i"),
            LetterMap.NextIndex.get(""),
            1));
  }

  @Test
  public void testCharAtString1() {
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();

    String name1 = "祝无双";
    int[] index11 = processor.CharAt(name1);

    assertEquals(Arrays.stream(index11).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get(""),
            4,
            LetterMap.FirstIndex.get("w"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get(""),
            2,
            LetterMap.FirstIndex.get("sh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get("ang"),
            1));

    String name2 = "庄周";
    int[] index21 = processor.CharAt(name2);

    assertEquals(Arrays.stream(index21).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("u"),
            LetterMap.NextIndex.get("ang"),
            1,
            LetterMap.FirstIndex.get("zh"),
            LetterMap.NextIndex.get("ou"),
            LetterMap.NextIndex.get(""),
            1));
  }

  @Test
  public void testCharAtString2() {
    ChsCharToIdxArrBySylla processor = new ChsCharToIdxArrBySylla();

    String name1 = "杨洋";
    int[] index11 = processor.CharAt(name1);

    assertEquals(Arrays.stream(index11).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("y"),
            LetterMap.NextIndex.get("ang"),
            LetterMap.NextIndex.get(""),
            2,
            LetterMap.FirstIndex.get("y"),
            LetterMap.NextIndex.get("ang"),
            LetterMap.NextIndex.get(""),
            2));

    String name2 = "倪妮";
    int[] index21 = processor.CharAt(name2);

    assertEquals(Arrays.stream(index21).boxed().collect(Collectors.toList()),
        Arrays.asList(
            LetterMap.FirstIndex.get("n"),
            LetterMap.NextIndex.get("i"),
            LetterMap.NextIndex.get(""),
            2,
            LetterMap.FirstIndex.get("n"),
            LetterMap.NextIndex.get("i"),
            LetterMap.NextIndex.get(""),
            1));
  }

}
