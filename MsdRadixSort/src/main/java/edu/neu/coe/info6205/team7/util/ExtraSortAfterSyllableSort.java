package edu.neu.coe.info6205.team7.util;

import edu.neu.coe.huskySort.sort.huskySortUtils.BaseHuskySequenceCoder;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskySequenceCoder;
import edu.neu.coe.huskySort.sort.simple.QuickSort_DualPivot;
import edu.neu.coe.huskySort.sort.simple.TimSort;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import edu.neu.coe.info6205.team7.PureHuskySortWithHelper;
import edu.neu.coe.info6205.team7.RadixSort.LsdPinyinLetterSort;
import edu.neu.coe.info6205.team7.RadixSort.LsdPinyinSyllabificationSort;
import edu.neu.coe.info6205.team7.RadixSort.MsdPinyinLetterSort;
import edu.neu.coe.info6205.team7.RadixSort.MsdPinyinSyllabificationSort;
import edu.neu.coe.info6205.team7.RadixSort.RadixSort;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ExtraSortAfterSyllableSort {
  int[] datasetSize = {125000, 250000, 500000, 1000000, 2000000, 4000000};
  int sort_times = 100;

  public static void main(String[] args) {
    ExtraSortAfterSyllableSort output = new ExtraSortAfterSyllableSort();
    output.runOutput();
  }

  public void runOutput() {
    // Read words from file
    String[] data = FileUtil.readArrayFromFile("/shuffledChinese.txt");
//    List<String> data = readChineseArrayFromFile("/shuffledChinese.txt");
    List<String> toSort = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      Collections.addAll(toSort, data);
    }

    for (int size : datasetSize) {
      System.out.println("------------data size: " + size);
      sortChinese(toSort.subList(0, size).toArray(new String[0]),
          constructNameByLetter(toSort, size).toArray(new NameByLetter[0]),
          constructNameNameBySyllabification(toSort, size).toArray(new NameBySyllabification[0]));
    }
  }

  private void TestTimsort(NameByLetter[] nameByLetters,
      NameBySyllabification[] nameBySyllabification, int nWords){

    TimSort<NameByLetter> sorter = new TimSort<NameByLetter>(
        new HelperWIthTesting<>("TimSort", nWords));

    TimSort<NameBySyllabification> sorter_2 = new TimSort<NameBySyllabification>(
        new HelperWIthTesting<>("TimSort", nWords));

    long count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameByLetter[] temp = nameByLetters.clone();
      long t1 = System.nanoTime();
      sorter.sort(temp);
      long t2 = System.nanoTime();

      count += ((t2 - t1));
    }
    System.out.println("\t\tTim sort: " + count / 1e6 / sort_times + "ms");

    count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameBySyllabification[] temp = nameBySyllabification.clone();
      long t1 = System.nanoTime();
      temp = sorter_2.sort(temp);
      long t2 = System.nanoTime();

      NameByLetter[] names_final = new NameByLetter[nameBySyllabification.length];
      for (int j = 0; j < temp.length; j++) {
        names_final[j] = new NameByLetter(temp[j].getName());
      }

      long t3 = System.nanoTime();
      sorter.sort(names_final);
      long t4 = System.nanoTime();

      count += ((t2 - t1) + (t4 - t3));
    }
    System.out.println("\t\tExtra Tim sort: " + count / 1e6 / sort_times + "ms");
  }

  private void TestQuicksort(NameByLetter[] nameByLetters,
      NameBySyllabification[] nameBySyllabification, int nWords){
    QuickSort_DualPivot<NameByLetter> sorter = new QuickSort_DualPivot<NameByLetter>(
        new HelperWIthTesting<>("QuickSort_DualPivot with NameByLetter", nWords));

    QuickSort_DualPivot<NameBySyllabification> sorter_2 = new QuickSort_DualPivot<NameBySyllabification>(
        new HelperWIthTesting<>("QuickSort_DualPivot", nWords));

    long count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameByLetter[] temp = nameByLetters.clone();
      long t1 = System.nanoTime();
      sorter.sort(temp);
      long t2 = System.nanoTime();

      count += ((t2 - t1));
    }
    System.out.println("\t\tQuick sort: " + count / 1e6 / sort_times + "ms");

    count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameBySyllabification[] temp = nameBySyllabification.clone();
      long t1 = System.nanoTime();
      temp = sorter_2.sort(temp);
      long t2 = System.nanoTime();

      NameByLetter[] names_final = new NameByLetter[nameBySyllabification.length];
      for (int j = 0; j < temp.length; j++) {
        names_final[j] = new NameByLetter(temp[j].getName());
      }

      long t3 = System.nanoTime();
      Arrays.sort(names_final);
      long t4 = System.nanoTime();

      count += ((t2 - t1) + (t4 - t3));
    }
    System.out.println("\t\tExtra Quick sort: " + count / 1e6 / sort_times + "ms");
  }

  private void TestHuskysort(NameByLetter[] nameByLetters,
      NameBySyllabification[] nameBySyllabification, int nWords){

    PureHuskySortWithHelper<NameByLetter> sorter =
        new PureHuskySortWithHelper<>(nameCoderByLetter,
            new HelperWIthTesting<>("HuskySort with NameByLetter", nWords));

    PureHuskySortWithHelper<NameBySyllabification> sorter_2 =
        new PureHuskySortWithHelper<>(nameCoderBySylla,
            new HelperWIthTesting<>("HuskySort with NameByLetter", nWords));

    long count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameByLetter[] temp = nameByLetters.clone();
      long t1 = System.nanoTime();
      sorter.sort(temp);
      long t2 = System.nanoTime();

      count += ((t2 - t1));
    }
    System.out.println("\t\tHusky sort: " + count / 1e6 / sort_times + "ms");

    count = 0;
    for (int i = 0; i < sort_times; i++) {
      NameBySyllabification[] temp = nameBySyllabification.clone();
      long t1 = System.nanoTime();
      temp = sorter_2.sort(temp);
      long t2 = System.nanoTime();

      NameByLetter[] names_final = new NameByLetter[nameBySyllabification.length];
      for (int j = 0; j < temp.length; j++) {
        names_final[j] = new NameByLetter(temp[j].getName());
      }

      long t3 = System.nanoTime();
      Arrays.sort(names_final);
      long t4 = System.nanoTime();

      count += ((t2 - t1) + (t4 - t3));
    }
    System.out.println("\t\tExtra Husky sort: " + count / 1e6 / sort_times + "ms");
  }

  private void TestMsdsort(String[] toSort, int nWords){
    long count = 0;
    for (int i = 0; i < sort_times; i++) {
      RadixSort sorter = new MsdPinyinLetterSort(
          new HelperWIthTesting<>("MSD sort with Letter", nWords), 3);
      String[] temp = toSort.clone();
      sorter.preProcess(temp);
      long t1 = System.nanoTime();
      temp = sorter.sort(temp);
      long t2 = System.nanoTime();
      sorter.postProcess(temp);

      count += ((t2 - t1));
    }
    System.out.println("\t\tMsd sort: " + count / 1e6 / sort_times + "ms");

    count = 0;

    for (int i = 0; i < sort_times; i++) {
      RadixSort sorter_2 = new MsdPinyinSyllabificationSort(
          new HelperWIthTesting<>("MSD sort with Syllabification", nWords), 3);
      String[] temp = toSort.clone();
      sorter_2.preProcess(toSort);
      long t1 = System.nanoTime();
      temp = sorter_2.sort(temp);
      long t2 = System.nanoTime();
      sorter_2.postProcess(temp);

      NameByLetter[] names_final = new NameByLetter[toSort.length];
      for (int j = 0; j < temp.length; j++) {
        names_final[j] = new NameByLetter(temp[j]);
      }

      long t3 = System.nanoTime();
      Arrays.sort(names_final);
      long t4 = System.nanoTime();

      count += ((t2 - t1) + (t4 - t3));
    }
    System.out.println("\t\tExtra Msd sort: " + count / 1e6 / sort_times + "ms");
  }

  private void TestLsdsort(String[] toSort, int nWords){
    long count = 0;

    for (int i = 0; i < sort_times; i++) {
      RadixSort sorter = new LsdPinyinLetterSort(
          new HelperWIthTesting<>("LSD sort with Letter", nWords));
      String[] temp = toSort.clone();
      sorter.preProcess(toSort);
      long t1 = System.nanoTime();
      temp = sorter.sort(temp);
      long t2 = System.nanoTime();
      sorter.postProcess(temp);

      count += ((t2 - t1));
    }
    System.out.println("\t\tLsd sort: " + count / 1e6 / sort_times + "ms");

    count = 0;
    for (int i = 0; i < sort_times; i++) {
      RadixSort sorter_2 = new LsdPinyinSyllabificationSort(
          new HelperWIthTesting<>("LSD sort with Syllabification", nWords));

      String[] temp = toSort.clone();
      sorter_2.preProcess(toSort);
      long t1 = System.nanoTime();
      temp = sorter_2.sort(temp);
      long t2 = System.nanoTime();
      sorter_2.postProcess(temp);

      NameByLetter[] names_final = new NameByLetter[toSort.length];
      for (int j = 0; j < temp.length; j++) {
        names_final[j] = new NameByLetter(temp[j]);
      }

      long t3 = System.nanoTime();
      Arrays.sort(names_final);
      long t4 = System.nanoTime();

      count += ((t2 - t1) + (t4 - t3));
    }
    System.out.println("\t\tExtra Lsd sort: " + count / 1e6 / sort_times + "ms");
  }


  private void sortChinese(String[] toSort, NameByLetter[] nameByLetters,
      NameBySyllabification[] nameBySyllabification) {

    int nWords = toSort.length;
    TestTimsort(nameByLetters, nameBySyllabification, nWords);
    TestQuicksort(nameByLetters, nameBySyllabification, nWords);
    TestHuskysort(nameByLetters, nameBySyllabification, nWords);
    TestMsdsort(toSort, nWords);
    TestLsdsort(toSort, nWords);
  }

  private List<NameByLetter> constructNameByLetter(List<String> src, int size) {
    List<NameByLetter> res = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      res.add(new NameByLetter(src.get(i)));
    }
    return res;
  }

  private List<NameBySyllabification> constructNameNameBySyllabification(List<String> src,
      int size) {
    List<NameBySyllabification> res = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      res.add(new NameBySyllabification(src.get(i)));
    }
    return res;
  }

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
}
