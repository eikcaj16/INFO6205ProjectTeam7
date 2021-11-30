package edu.neu.coe.info6205.team7;

import edu.neu.coe.info6205.team7.NameByLetter.ChsCharToIdxArrByLetter;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ChasCharToIdxArrByLetterTest {
    @Test
    public void testIdx1(){
        ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
        int[] idx1 = chsCharToIdxArrByLetter.CharAt('张');
        assertEquals(Arrays.asList(23, 3, 1, 5, 1, 1, 0),Arrays.stream(idx1).boxed().collect(Collectors.toList()));

        int[] idx2 = chsCharToIdxArrByLetter.CharAt('李');
        assertEquals(Arrays.asList(11, 4, 3, 0, 0, 0, 0),Arrays.stream(idx2).boxed().collect(Collectors.toList()));
    }

    @Test
    public void testIdx2(){
        ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
        int[] idx1 = chsCharToIdxArrByLetter.CharAt('祝');
        assertEquals(Arrays.asList(23, 3, 7, 4, 0, 0, 0),Arrays.stream(idx1).boxed().collect(Collectors.toList()));

        int[] idx2 = chsCharToIdxArrByLetter.CharAt('兴');
        assertEquals(Arrays.asList(21, 4, 5, 3, 1, 0, 0),Arrays.stream(idx2).boxed().collect(Collectors.toList()));
    }
    @Test
    public void testIdx3(){
        ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
        int[] idx1 = chsCharToIdxArrByLetter.CharAt('窗');
        assertEquals(Arrays.asList(3, 3, 7, 1, 3, 1, 1),Arrays.stream(idx1).boxed().collect(Collectors.toList()));

        int[] idx2 = chsCharToIdxArrByLetter.CharAt('装');
        assertEquals(Arrays.asList(23, 3, 7, 1, 3, 1, 1),Arrays.stream(idx2).boxed().collect(Collectors.toList()));
    }
    @Test
    public void testStringIdx1(){
        ChsCharToIdxArrByLetter chsCharToIdxArrByLetter = new ChsCharToIdxArrByLetter();
        int[] idx1 = chsCharToIdxArrByLetter.CharAt("陈然");
        assertEquals(Arrays.asList(3,3,2,5,2,0,0,17,1,5,2,0,0,0),Arrays.stream(idx1).boxed().collect(Collectors.toList()));
        int[] idx2 = chsCharToIdxArrByLetter.CharAt("李白");
        assertEquals(Arrays.asList(11,4,3,0,0,0,0,2,1,4,2,0,0,0),Arrays.stream(idx2).boxed().collect(Collectors.toList()));
        int[] idx3 = chsCharToIdxArrByLetter.CharAt("杜甫");
        assertEquals(Arrays.asList(4,8,4,0,0,0,0,6,8,3,0,0,0,0),Arrays.stream(idx3).boxed().collect(Collectors.toList()));
    }
}
