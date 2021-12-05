package edu.neu.coe.info6205.team7;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import java.util.Arrays;
import org.junit.Test;

public class NameBySyllabificationTest {

  @Test
  public void testCompare1() {
    NameBySyllabification name1 = new NameBySyllabification("李白");
    NameBySyllabification name2 = new NameBySyllabification("李太白");
    NameBySyllabification name3 = new NameBySyllabification("杜甫");

    assertEquals(name1.compareTo(name2), -1);
    assertEquals(name1.compareTo(name3), 1);
    assertEquals(name2.compareTo(name3), 1);
  }

  @Test
  public void testCompare2() {
    NameBySyllabification name1 = new NameBySyllabification("白虎兵");
    NameBySyllabification name2 = new NameBySyllabification("白华林");
    NameBySyllabification name3 = new NameBySyllabification("窦子昂");

    assertEquals(name1.compareTo(name2), -1);
    assertEquals(name1.compareTo(name3), -1);
    assertEquals(name2.compareTo(name3), -1);
  }

  @Test
  public void testCompare3() {
    NameBySyllabification[] SyllaNames = new NameBySyllabification[14];
    int i = 0;
    SyllaNames[i++] = new NameBySyllabification("李白");
    SyllaNames[i++] = new NameBySyllabification("李白白");
    SyllaNames[i++] = new NameBySyllabification("杜甫");
    SyllaNames[i++] = new NameBySyllabification("杜芙");
    SyllaNames[i++] = new NameBySyllabification("白居易");
    SyllaNames[i++] = new NameBySyllabification("白居一");
    SyllaNames[i++] = new NameBySyllabification("白居疑");
    SyllaNames[i++] = new NameBySyllabification("苏东坡");
    SyllaNames[i++] = new NameBySyllabification("苏东");
    SyllaNames[i++] = new NameBySyllabification("欧阳修");
    SyllaNames[i++] = new NameBySyllabification("欧阳休");
    SyllaNames[i++] = new NameBySyllabification("字");
    SyllaNames[i++] = new NameBySyllabification("至");
    SyllaNames[i] = new NameBySyllabification("咋");

    String[] expectRes = {"白居一", "白居疑", "白居易", "杜芙", "杜甫", "李白", "李白白",
        "欧阳修", "欧阳休", "苏东", "苏东坡", "咋", "字", "至"};

    Arrays.sort(SyllaNames);
    String[] sortRes = new String[SyllaNames.length];
    for (int j = 0; j < sortRes.length; j++) {
      sortRes[j] = SyllaNames[j].getName();
    }

    assertArrayEquals(expectRes, sortRes);
  }

}
