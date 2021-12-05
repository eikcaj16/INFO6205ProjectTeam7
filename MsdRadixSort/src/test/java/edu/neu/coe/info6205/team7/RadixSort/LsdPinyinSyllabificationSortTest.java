package edu.neu.coe.info6205.team7.RadixSort;

import static org.junit.Assert.assertEquals;

import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.util.FileUtil;
import java.util.Arrays;
import org.junit.Test;

public class LsdPinyinSyllabificationSortTest {

  @Test
  public void basicTest1() {
    String[] xs = new String[]{"吗", "啦", "啥", "吧", "啊", "噶"};
    RadixSort stringSort = new LsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("LSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(MsdPinyinSyllabificationSortTest.expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void basicTest2() {
    String[] xs = new String[]{"老伙计", "救济", "做作", "经济", "坐下", "啊"};
    RadixSort stringSort = new LsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("LSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(MsdPinyinSyllabificationSortTest.expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void advancedTest() {
    String[] xs = FileUtil.readArrayFromFile("/shuffledChinese.txt", 100000);
    RadixSort stringSort = new LsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("LSD Pinyin Syllabification Sort"));
    stringSort.preProcess(xs);
    stringSort.sort(xs, 0, xs.length);
    stringSort.postProcess(xs);
    assertEquals(MsdPinyinSyllabificationSortTest.expectSortRes(xs), Arrays.asList(xs));
  }
}
