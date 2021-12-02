package edu.neu.coe.info6205.team7.Benchmark;

import edu.neu.coe.huskySort.sort.huskySortUtils.BaseHuskySequenceCoder;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskyCoderFactory;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskySequenceCoder;
import edu.neu.coe.huskySort.sort.simple.QuickSort_DualPivot;
import edu.neu.coe.huskySort.sort.simple.TimSort;
import edu.neu.coe.huskySort.util.SorterBenchmark;
import edu.neu.coe.huskySort.util.TimeLogger;
import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import edu.neu.coe.info6205.team7.PureHuskySortWithHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkChineseSort {

  int nRuns = 5;
  int[] datasetSize = {250000,500000,1000000,2000000,4000000};
  public final static TimeLogger[] timeLoggersLinearithmic = {
      new TimeLogger("Raw time per run (mSec): ", (time, n) -> time),
  };

  public static void main(String[] args) {
    BenchmarkChineseSort benchmark = new BenchmarkChineseSort();
    benchmark.runBenchmark();
  }
  private void runBenchmark(){
    // Read words from file
    List<String> toSort4M = readChineseArrayFromFile("/shuffledChinese.txt");
    toSort4M.addAll(toSort4M);
    toSort4M.addAll(toSort4M);
    for (int size: datasetSize){
      sortChinese(toSort4M.subList(0,size).toArray(new String[0]),constructNameByLetter(toSort4M,size).toArray(new NameByLetter[0]),
              constructNameNameBySyllabification(toSort4M,size).toArray(new NameBySyllabification[0]));
    }
  }

  private void sortChinese(String[] toSort, NameByLetter[] nameByLetters, NameBySyllabification[] nameBySyllabification) {

    int nWords = toSort.length;
    // Tim Sort
    new SorterBenchmark<>(NameByLetter.class, null,
        new TimSort<NameByLetter>(new HelperWIthTesting<>("TimSort", nWords)), nameByLetters, nRuns,
        timeLoggersLinearithmic).run(nWords);
    new SorterBenchmark<>(NameBySyllabification.class, null,
        new TimSort<NameBySyllabification>(new HelperWIthTesting<>("TimSort", nWords)),
        nameBySyllabification, nRuns, timeLoggersLinearithmic).run(nWords);

    // Dual Pivot Quick Sort
    new SorterBenchmark<>(NameByLetter.class, null, new QuickSort_DualPivot<NameByLetter>(
        new HelperWIthTesting<>("QuickSort_DualPivot with NameByLetter", nWords)), nameByLetters,
        nRuns, timeLoggersLinearithmic).run(nWords);
    new SorterBenchmark<NameBySyllabification>(NameBySyllabification.class, null,
        new QuickSort_DualPivot(new HelperWIthTesting<>("QuickSort_DualPivot",
            nWords)), nameBySyllabification, nRuns,
        timeLoggersLinearithmic).run(nWords);

    // Husky Sort
    new SorterBenchmark<>(NameByLetter.class, null, new PureHuskySortWithHelper<>(nameCoderByLetter,
        new HelperWIthTesting<>("HuskySort with NameByLetter", nWords)), nameByLetters, nRuns,
        timeLoggersLinearithmic).run(nWords);
    new SorterBenchmark<>(NameBySyllabification.class, null,
        new PureHuskySortWithHelper<>(nameCoderBySylla,
            new HelperWIthTesting<>("HuskySort with NameBySyllabification", nWords)),
        nameBySyllabification, nRuns, timeLoggersLinearithmic).run(nWords);
  }

  private List<NameByLetter> constructNameByLetter(List<String> src,int size) {
    List<NameByLetter> res = new ArrayList<>();
    for (int i = 0;i < size;i++) {
      res.add(new NameByLetter(src.get(i)));
    }
    return res;
  }

  private List<NameBySyllabification> constructNameNameBySyllabification(List<String> src,int size) {
    List<NameBySyllabification> res = new ArrayList<>();
    for (int i = 0;i < size;i++) {
      res.add(new NameBySyllabification(src.get(i)));
    }
    return res;
  }

  private List<String> readChineseArrayFromFile(String resource) {
    InputStream path = getClass().getResourceAsStream(resource);
    List<String> res = new ArrayList<>();
    try {
      assert path != null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(path));
      String s;
      while ((s = reader.readLine()) != null) {
        res.add(s);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
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
