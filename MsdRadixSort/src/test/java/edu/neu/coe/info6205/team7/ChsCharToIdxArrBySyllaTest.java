package edu.neu.coe.info6205.team7;
import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import org.junit.Test;
import org.w3c.dom.ls.LSInput;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ChsCharToIdxArrBySyllaTest {
    @Test
    public void testTransfer1(){
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
    public void testTransfer2(){
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
    public void testSpilt1(){
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
    public void testSpilt2(){
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
    public void testCharAt1(){
        String name1 = "祝无双";
        int[] index11 = ChsCharToIdxArrBySylla.CharAt(name1.charAt(0));
        int[] index12 = ChsCharToIdxArrBySylla.CharAt(name1.charAt(1));
        int[] index13 = ChsCharToIdxArrBySylla.CharAt(name1.charAt(2));

        String name2 = "庄周";
        int[] index21 = ChsCharToIdxArrBySylla.CharAt(name2.charAt(0));
        int[] index22 = ChsCharToIdxArrBySylla.CharAt(name2.charAt(1));

        System.out.println(Arrays.toString(index11));
        System.out.println(Arrays.toString(index12));
        System.out.println(Arrays.toString(index13));
        System.out.println(Arrays.toString(index21));
        System.out.println(Arrays.toString(index22));
    }

    @Test
    public void testCharAt2(){

    }
}
