package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.util.FileUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MsdStringSortTest {

  @Test
  public void basicTest1() {
    String[] xs = new String[]{"c", "a", "b", "z", "d", "b"};
    RadixSort stringSort = new MsdStringSort(new HelperWIthTesting<>("MSD String Sort"));
    stringSort.sort(xs, 0, xs.length);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void basicTest2() {
    String[] xs = new String[]{"cz", "a", "b", "cd", "db", "d"};
    RadixSort stringSort = new MsdStringSort(new HelperWIthTesting<>("MSD String Sort"));
    stringSort.sort(xs, 0, xs.length);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  @Test
  public void advancedTest() {
    String[] xs = FileUtil.readArrayFromFile("/3000-common-words.txt");
    RadixSort stringSort = new MsdStringSort(new HelperWIthTesting<>("MSD String Sort"));
    stringSort.sort(xs, 0, xs.length);
    assertEquals(expectSortRes(xs), Arrays.asList(xs));
  }

  static List<String> expectSortRes(String[] xs) {
    String[] expect = Arrays.copyOfRange(xs, 0, xs.length);
    Arrays.sort(expect);
    return Arrays.asList(expect);
  }
}
