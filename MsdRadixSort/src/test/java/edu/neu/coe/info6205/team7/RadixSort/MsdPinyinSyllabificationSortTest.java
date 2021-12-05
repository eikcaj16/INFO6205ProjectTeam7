package edu.neu.coe.info6205.team7.RadixSort;

import static org.junit.Assert.assertEquals;

import edu.neu.coe.huskySort.sort.SortWithHelper;
import edu.neu.coe.huskySort.sort.simple.TimSort;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import edu.neu.coe.info6205.team7.util.FileUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MsdPinyinSyllabificationSortTest {

  @Test
  public void basicTest1() {
    String[] xs = new String[]{"吗", "啦", "啥", "吧", "啊", "噶"};
    RadixSort stringSort = new MsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("MSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void basicTest2() {
    String[] xs = new String[]{"老伙计", "救济", "做作", "经济", "坐下", "啊"};
    RadixSort stringSort = new MsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("MSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void advancedTest() {
    String[] xs = FileUtil.readArrayFromFile("/shuffledChinese.txt", 100000);
    RadixSort stringSort = new MsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("MSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  static List<String> expectSortRes(String[] xs) {
    NameBySyllabification[] nameBySyllabifications = constructNameNameBySyllabification(xs);
    SortWithHelper<NameBySyllabification> timSort = new TimSort<NameBySyllabification>(
        new HelperWIthTesting<>("TimSort with NameByLetter"));
    timSort.sort(nameBySyllabifications, 0, xs.length);

    List<String> expect = new ArrayList<>();
    for (NameBySyllabification n : nameBySyllabifications) {
      expect.add(n.getName());
    }
    return expect;
  }

  static NameBySyllabification[] constructNameNameBySyllabification(String[] src) {
    List<NameBySyllabification> res = new ArrayList<>();
    for (String s : src) {
      res.add(new NameBySyllabification(s));
    }
    return res.toArray(new NameBySyllabification[0]);
  }
}
