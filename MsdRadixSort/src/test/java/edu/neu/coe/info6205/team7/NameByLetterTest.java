package edu.neu.coe.info6205.team7;

import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NameByLetterTest {
    @Test
    public void testPinyin1(){
        NameByLetter name1 = new NameByLetter("祝福你");
        assertEquals(name1.getPinyin().get(0),"zhu");
        assertEquals(name1.getTone().get(0),(Integer) 4);
        assertEquals(name1.getPinyin().get(1),"fu");
        assertEquals(name1.getTone().get(1),(Integer) 2);
        assertEquals(name1.getPinyin().get(2),"ni");
        assertEquals(name1.getTone().get(2),(Integer) 3);

        NameByLetter name2 = new NameByLetter("吕布");
        assertEquals(name2.getPinyin().get(0),"lv");
        assertEquals(name2.getTone().get(0),(Integer) 3);
        assertEquals(name2.getPinyin().get(1),"bu");
        assertEquals(name2.getTone().get(1),(Integer) 4);

        NameByLetter name3 = new NameByLetter("愈合");
        assertEquals(name3.getPinyin().get(0),"yu");
        assertEquals(name3.getTone().get(0),(Integer) 4);
        assertEquals(name3.getPinyin().get(1),"he");
        assertEquals(name3.getTone().get(1),(Integer) 2);
    }
    @Test
    public void testPinyin2(){
        NameByLetter name1 = new NameByLetter("窗户门");
        assertEquals(name1.getPinyin().get(0),"chuang");
        assertEquals(name1.getTone().get(0),(Integer) 1);
        assertEquals(name1.getPinyin().get(1),"hu");
        assertEquals(name1.getTone().get(1),(Integer) 4);
        assertEquals(name1.getPinyin().get(2),"men");
        assertEquals(name1.getTone().get(2),(Integer) 2);


        NameByLetter name2 = new NameByLetter("艾草");
        assertEquals(name2.getPinyin().get(0),"ai");
        assertEquals(name2.getTone().get(0),(Integer) 4);
        assertEquals(name2.getPinyin().get(1),"cao");
        assertEquals(name2.getTone().get(1),(Integer) 3);

        NameByLetter name3 = new NameByLetter("阿华");
        assertEquals(name3.getPinyin().get(0),"a");
        assertEquals(name3.getTone().get(0),(Integer) 1);
        assertEquals(name3.getPinyin().get(1),"hua");
        assertEquals(name3.getTone().get(1),(Integer) 2);

        NameByLetter name4 = new NameByLetter("啊");
        assertEquals(name4.getPinyin().get(0),"a");
        assertEquals(name4.getTone().get(0),(Integer) 0);

    }
    @Test
    public void testCompare1(){
        NameByLetter name1 = new NameByLetter("吕布");
        NameByLetter name2 = new NameByLetter("卢布");

        assertEquals(name1.compareTo(name2),1);

        NameByLetter name3 = new NameByLetter("冗余");
        NameByLetter name4 = new NameByLetter("冗鱼");
        assertEquals(name3.compareTo(name4),0);

        NameByLetter name5 = new NameByLetter("章鱼");
        NameByLetter name6 = new NameByLetter("占于");
        assertEquals(name5.compareTo(name6),1);
    }
    @Test
    public void testCompare2(){
        NameByLetter name1 = new NameByLetter("陈思");
        NameByLetter name2 = new NameByLetter("陈思思");

        assertEquals(name1.compareTo(name2),-1);

        NameByLetter name3 = new NameByLetter("然后");
        NameByLetter name4 = new NameByLetter("让后");
        assertEquals(name3.compareTo(name4),-1);

        NameByLetter name5 = new NameByLetter("啊");
        NameByLetter name6 = new NameByLetter("阿");
        assertEquals(name5.compareTo(name6),-1);
    }
}
