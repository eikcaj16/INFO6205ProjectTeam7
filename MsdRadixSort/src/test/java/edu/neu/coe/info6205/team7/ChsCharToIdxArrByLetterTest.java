package edu.neu.coe.info6205.team7;

import edu.neu.coe.info6205.team7.NameByLetter.ChsCharToIdxArrByLetter;
import edu.neu.coe.info6205.team7.NameByLetter.LetterMap;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ChsCharToIdxArrByLetterTest {

  @Test
  public void testIdx1() {
    ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
    int[] idx1 = chsCharToIdxArrByLetter.CharAt('张');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('z'),
            LetterMap.index1.get('h'),
            LetterMap.index2.get('a'),
            LetterMap.index3.get('n'),
            LetterMap.index4.get('g'),
            0,
            1),
        Arrays.stream(idx1).boxed().collect(Collectors.toList()));

    int[] idx2 = chsCharToIdxArrByLetter.CharAt('李');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('l'),
            LetterMap.index1.get('i'),
            0,
            0,
            0,
            0,
            3),
        Arrays.stream(idx2).boxed().collect(Collectors.toList()));
  }

  @Test
  public void testIdx2() {
    ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
    int[] idx1 = chsCharToIdxArrByLetter.CharAt('祝');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('z'),
            LetterMap.index1.get('h'),
            LetterMap.index2.get('u'),
            0,
            0,
            0,
            4),
        Arrays.stream(idx1).boxed().collect(Collectors.toList()));

    int[] idx2 = chsCharToIdxArrByLetter.CharAt('兴');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('x'),
            LetterMap.index1.get('i'),
            LetterMap.index2.get('n'),
            LetterMap.index3.get('g'),
            0,
            0,
            1),
        Arrays.stream(idx2).boxed().collect(Collectors.toList()));
  }

  @Test
  public void testIdx3() {
    ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
    int[] idx1 = chsCharToIdxArrByLetter.CharAt('窗');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('c'),
            LetterMap.index1.get('h'),
            LetterMap.index2.get('u'),
            LetterMap.index3.get('a'),
            LetterMap.index4.get('n'),
            LetterMap.index5.get('g'),
            1),
        Arrays.stream(idx1).boxed().collect(Collectors.toList()));

    int[] idx2 = chsCharToIdxArrByLetter.CharAt('装');
    assertEquals(Arrays.asList(
            LetterMap.index0.get('z'),
            LetterMap.index1.get('h'),
            LetterMap.index2.get('u'),
            LetterMap.index3.get('a'),
            LetterMap.index4.get('n'),
            LetterMap.index5.get('g'),
            1),
        Arrays.stream(idx2).boxed().collect(Collectors.toList()));
  }

  @Test
  public void testStringIdx1() {
    ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
    int[] idx1 = chsCharToIdxArrByLetter.CharAt("陈然");
    assertEquals(Arrays.asList(
            LetterMap.index0.get('c'),
            LetterMap.index1.get('h'),
            LetterMap.index2.get('e'),
            LetterMap.index3.get('n'),
            0,
            0,
            2,
            LetterMap.index0.get('r'),
            LetterMap.index1.get('a'),
            LetterMap.index2.get('n'),
            0,
            0,
            0,
            2),
        Arrays.stream(idx1).boxed().collect(Collectors.toList()));

    int[] idx2 = chsCharToIdxArrByLetter.CharAt("李白");
    assertEquals(Arrays.asList(
            LetterMap.index0.get('l'),
            LetterMap.index1.get('i'),
            0,
            0,
            0,
            0,
            3,
            LetterMap.index0.get('b'),
            LetterMap.index1.get('a'),
            LetterMap.index2.get('i'),
            0,
            0,
            0,
            2),
        Arrays.stream(idx2).boxed().collect(Collectors.toList()));
    int[] idx3 = chsCharToIdxArrByLetter.CharAt("杜甫");
    assertEquals(Arrays.asList(
            LetterMap.index0.get('d'),
            LetterMap.index1.get('u'),
            0,
            0,
            0,
            0,
            4,
            LetterMap.index0.get('f'),
            LetterMap.index1.get('u'),
            0,
            0,
            0,
            0,
            3),
        Arrays.stream(idx3).boxed().collect(Collectors.toList()));
  }
}
