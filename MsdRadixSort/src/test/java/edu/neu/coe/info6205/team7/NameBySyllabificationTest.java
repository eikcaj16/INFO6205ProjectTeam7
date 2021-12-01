package edu.neu.coe.info6205.team7;

import static org.junit.Assert.assertEquals;

import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import java.util.Arrays;
import org.junit.Test;

public class NameBySyllabificationTest {
  @Test
  public void testTransfer1(){
    NameBySyllabification name1 = new NameBySyllabification("李白");
    assertEquals(name1.getNamePinyin().get(0), "li3");
    assertEquals(name1.getNamePinyin().get(1), "bai2");

    NameBySyllabification name2 = new NameBySyllabification("杜甫");
    assertEquals(name2.getNamePinyin().get(0), "du4");
    assertEquals(name2.getNamePinyin().get(1), "fu3");
  }

  @Test
  public void testTransfer2(){
    NameBySyllabification name1 = new NameBySyllabification("王昌龄");
    assertEquals(name1.getNamePinyin().get(0), "wang2");
    assertEquals(name1.getNamePinyin().get(1), "chang1");
    assertEquals(name1.getNamePinyin().get(2), "ling2");

    NameBySyllabification name2 = new NameBySyllabification("欧阳修");
    assertEquals(name2.getNamePinyin().get(0), "ou1");
    assertEquals(name2.getNamePinyin().get(1), "yang2");
    assertEquals(name2.getNamePinyin().get(2), "xiu1");
  }

  @Test
  public void testSpilt1(){
    NameBySyllabification name1 = new NameBySyllabification("窦子昂");
    assertEquals(name1.getNameSplitPinyin().get(0), Arrays.asList("d", "ou", "", "4"));
    assertEquals(name1.getNameSplitPinyin().get(1), Arrays.asList("z", "i", "", "5"));
    assertEquals(name1.getNameSplitPinyin().get(2), Arrays.asList("ang", "", "", "2"));

    NameBySyllabification name2 = new NameBySyllabification("苏东坡");
    assertEquals(name2.getNameSplitPinyin().get(0), Arrays.asList("s", "u", "", "1"));
    assertEquals(name2.getNameSplitPinyin().get(1), Arrays.asList("d", "ong", "", "1"));
    assertEquals(name2.getNameSplitPinyin().get(2), Arrays.asList("p", "o", "", "1"));
  }

  @Test
  public void testSpilt2(){
    NameBySyllabification name1 = new NameBySyllabification("祝无双");
    assertEquals(name1.getNameSplitPinyin().get(0), Arrays.asList("zh", "u", "", "4"));
    assertEquals(name1.getNameSplitPinyin().get(1), Arrays.asList("w", "u", "", "2"));
    assertEquals(name1.getNameSplitPinyin().get(2), Arrays.asList("sh", "u", "ang", "1"));

    NameBySyllabification name2 = new NameBySyllabification("庄周");
    name2.TransferChineseToPinyin();
    name2.SpiltBySyllabification();
    assertEquals(name2.getNameSplitPinyin().get(0), Arrays.asList("zh", "u", "ang", "1"));
    assertEquals(name2.getNameSplitPinyin().get(1), Arrays.asList("zh", "ou", "", "1"));
  }

  @Test
  public void testCompare1(){
    NameBySyllabification name1 = new NameBySyllabification("李白");
    NameBySyllabification name2 = new NameBySyllabification("李太白");
    NameBySyllabification name3 = new NameBySyllabification("杜甫");

    assertEquals(name1.compareTo(name2), -1);
    assertEquals(name1.compareTo(name3), 1);
    assertEquals(name2.compareTo(name3), 1);
  }

  @Test
  public void testCompare2(){
    NameBySyllabification name1 = new NameBySyllabification("白虎兵");
    NameBySyllabification name2 = new NameBySyllabification("白华林");
    NameBySyllabification name3 = new NameBySyllabification("窦子昂");

    assertEquals(name1.compareTo(name2), -1);
    assertEquals(name1.compareTo(name3), -1);
    assertEquals(name2.compareTo(name3), -1);
  }

}
