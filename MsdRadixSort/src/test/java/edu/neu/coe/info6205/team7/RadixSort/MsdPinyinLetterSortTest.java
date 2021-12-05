package edu.neu.coe.info6205.team7.RadixSort;

import static org.junit.Assert.assertEquals;

import edu.neu.coe.huskySort.sort.SortWithHelper;
import edu.neu.coe.huskySort.sort.simple.TimSort;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import edu.neu.coe.info6205.team7.util.FileUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MsdPinyinLetterSortTest {

  @Test
  public void basicTest1() {
    String[] xs = new String[]{"吗", "啦", "啥", "吧", "啊", "噶"};
    RadixSort stringSort = new MsdPinyinLetterSort(
        new HelperWIthTesting<>("MSD Pinyin Letter Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void basicTest2() {
    String[] xs = new String[]{"老伙计", "救济", "做作", "经济", "坐下", "啊"};
    RadixSort stringSort = new MsdPinyinLetterSort(
        new HelperWIthTesting<>("MSD Pinyin Letter Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void advancedTest() {
    String[] xs = FileUtil.readArrayFromFile("/shuffledChinese.txt", 100000);
    RadixSort stringSort = new MsdPinyinLetterSort(
        new HelperWIthTesting<>("MSD Pinyin Letter Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  static List<String> expectSortRes(String[] xs) {
    NameByLetter[] nameByLetters = constructNameByLetter(xs);
    SortWithHelper<NameByLetter> timSort = new TimSort<NameByLetter>(
        new HelperWIthTesting<>("TimSort with NameByLetter"));
    timSort.sort(nameByLetters, 0, xs.length);

    List<String> expect = new ArrayList<>();
    for (NameByLetter n : nameByLetters) {
      expect.add(n.getName());
    }
    return expect;
  }

  static NameByLetter[] constructNameByLetter(String[] src) {
    List<NameByLetter> res = new ArrayList<>();
    for (String s : src) {
      res.add(new NameByLetter(s));
    }
    return res.toArray(new NameByLetter[0]);
  }
}
