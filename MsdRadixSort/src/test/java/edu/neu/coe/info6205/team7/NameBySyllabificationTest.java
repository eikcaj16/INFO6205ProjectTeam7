package edu.neu.coe.info6205.team7;

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

}
