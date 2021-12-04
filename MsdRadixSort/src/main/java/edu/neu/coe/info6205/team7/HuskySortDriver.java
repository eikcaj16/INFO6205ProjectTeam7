package edu.neu.coe.info6205.team7;

import edu.neu.coe.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.huskySort.sort.huskySortUtils.BaseHuskySequenceCoder;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskySequenceCoder;
import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuskySortDriver {

  private static final HuskySequenceCoder<NameByLetter> nameCoderByLetter =
      new BaseHuskySequenceCoder<NameByLetter>("Name", 2) {
        @Override
        public long huskyEncode(NameByLetter nameByLetter) {
          return HuskyCoderFactory.utf8Coder.huskyEncode((nameByLetter.getName()));
        }
      };

  private static final HuskySequenceCoder<NameBySyllabification> nameCoderBySylla =
      new BaseHuskySequenceCoder<NameBySyllabification>("Name", 2) {
        @Override
        public long huskyEncode(NameBySyllabification nameBySyllabification) {
          return HuskyCoderFactory.utf8Coder.huskyEncode((nameBySyllabification.getName()));
        }
      };

  public static void testSylla(List<String> names_txt) {
    NameBySyllabification[] names = new NameBySyllabification[names_txt.size()];
    for (int i = 0; i < names_txt.size(); i++) {
      names[i] = new NameBySyllabification(names_txt.get(i));
    }

    Collections.shuffle(Arrays.asList(names));
    final int sort_times = 10;

//    long normal_count = 0;
//    for (int i = 0; i < sort_times; i++) {
//      NameBySyllabification[] temp = names.clone();
//      long t1 = System.nanoTime();
//      Arrays.sort(temp);
//      long t2 = System.nanoTime();
//      normal_count += (t2 - t1);
//    }
//    System.out.println("System sort: " + normal_count / 1e6 / sort_times + "ms");

    long husky_count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameBySyllabification[] temp = names.clone();
      long t1 = System.nanoTime();
      PureHuskySort<NameBySyllabification> sorter =
          new PureHuskySort<>(nameCoderBySylla,
              false,
              false);
      sorter.sort(temp);
      long t2 = System.nanoTime();
      husky_count += (t2 - t1);
    }
    System.out.println("Husky sort: " + husky_count / 1e6 / sort_times + "ms");
  }

  public static void testLetter(List<String> names_txt) {
    NameByLetter[] names = new NameByLetter[names_txt.size()];
    for (int i = 0; i < names_txt.size(); i++) {
      names[i] = new NameByLetter(names_txt.get(i));
    }

    Collections.shuffle(Arrays.asList(names));
    final int sort_times = 10;

//    long normal_count = 0;
//    for (int i = 0; i < sort_times; i++) {
//      NameByLetter[] temp = names.clone();
//      long t1 = System.nanoTime();
//      Arrays.sort(temp);
//      long t2 = System.nanoTime();
//      normal_count += (t2 - t1);
//    }
//    System.out.println("System sort: " + normal_count / 1e6 / sort_times + "ms");

    long husky_count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameByLetter[] temp = names.clone();
      long t1 = System.nanoTime();
      PureHuskySort<NameByLetter> sorter =
          new PureHuskySort<>(nameCoderByLetter,
              false,
              false);
      sorter.sort(temp);
      long t2 = System.nanoTime();
      husky_count += (t2 - t1);
    }
    System.out.println("Husky sort: " + husky_count / 1e6 / sort_times + "ms");
  }

  public static void demo() {
    List<String> names_txt = new ArrayList<>();
    try {
      FileReader fr = new FileReader("./sortedChinese.txt");
      BufferedReader br = new BufferedReader(fr);
      System.out.println("\n======== Create file reader success! ========");

      while (br.ready()) {
        names_txt.add(br.readLine());
      }

      System.out.println("======== Data have been read! ========");

      br.close();
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("\nTesting name sort by letter:");
    testLetter(names_txt);

    System.out.println("\nTesting name sort by syllabification:");
    testSylla(names_txt);

//    try {
//      FileWriter fr = new FileWriter("./sortedResult.txt");
//      for (NameBySyllabification name : names) {
//        fr.write(name.getName() + "\n");
//      }
//      fr.close();
//    }
//    catch (Exception e){
//      e.printStackTrace();
//    }
  }
}
