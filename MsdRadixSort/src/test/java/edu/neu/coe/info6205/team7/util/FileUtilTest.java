package edu.neu.coe.info6205.team7.util;

import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class FileUtilTest {

  @Test
  public void test1() {
    String[] res = FileUtil.readArrayFromFile("/10-words.txt");
    String[] expect = new String[]{"a", "bite", "abandon", "ability", "able", "abortion", "about",
        "above", "abroad", "absence"};
    Assert.assertEquals(Arrays.asList(expect), Arrays.asList(res));
  }

  @Test
  public void test2() {
    String[] res = FileUtil.readArrayFromFile("/10-words.txt", 5);
    String[] expect = new String[]{"a", "bite", "abandon", "ability", "able"};
    Assert.assertEquals(Arrays.asList(expect), Arrays.asList(res));
  }

  @Test
  public void test3() {
    String[] res = FileUtil.readArrayFromFile("/10-words.txt", 11);
    String[] expect = new String[]{"a", "bite", "abandon", "ability", "able", "abortion", "about",
        "above", "abroad", "absence"};
    Assert.assertEquals(Arrays.asList(expect), Arrays.asList(res));
  }
}
