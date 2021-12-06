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
import java.util.List;

public class OutputSortingResult {

  int[] datasetSize = {500};

  public static void main(String[] args) {
    OutputSortingResult output = new OutputSortingResult();
    output.runOutput();
  }

  public void runOutput() {
    // Read words from file
    String[] data = FileUtil.readArrayFromFile("/shuffledChinese.txt");
    List<String> toSort = Arrays.asList(data);

    for (int size : datasetSize) {
      sortChinese(toSort.subList(0, size).toArray(new String[0]),
          constructNameByLetter(toSort, size).toArray(new NameByLetter[0]),
          constructNameNameBySyllabification(toSort, size).toArray(new NameBySyllabification[0]));
    }
  }

  private void sortChinese(String[] toSort, NameByLetter[] nameByLetters,
      NameBySyllabification[] nameBySyllabification) {

    int nWords = toSort.length;
    NameByLetter[] tempForLetter;
    NameBySyllabification[] tempForSylla;
    String[] tempForString;

    //================================== With Letters ==================================
    System.out.println("------------------------Sort with Letters-----------------");
    // Tim Sort
    TimSort<NameByLetter> tim_sorter = new TimSort<NameByLetter>(
        new HelperWIthTesting<>("TimSort", nWords));
    tempForLetter = nameByLetters.clone();
    tempForLetter = tim_sorter.sort(tempForLetter);
    FileUtil.writeArrayToFile(tempForLetter, "timsort_result.txt");

    // Dual Pivot Quick Sort
    QuickSort_DualPivot<NameByLetter> quick_sorter = new QuickSort_DualPivot<NameByLetter>(
        new HelperWIthTesting<>("QuickSort_DualPivot with NameByLetter", nWords));
    tempForLetter = nameByLetters.clone();
    tempForLetter = quick_sorter.sort(tempForLetter);
    FileUtil.writeArrayToFile(tempForLetter, "quicksort_result.txt");

    // Husky Sort
    PureHuskySortWithHelper<NameByLetter> husky_sorter =
        new PureHuskySortWithHelper<>(nameCoderByLetter,
            new HelperWIthTesting<>("HuskySort with NameByLetter", nWords));
    tempForLetter = nameByLetters.clone();
    tempForLetter = husky_sorter.sort(tempForLetter);
    FileUtil.writeArrayToFile(tempForLetter, "huskysort_result.txt");

    // MSD Sort
    RadixSort MsdByLetter = new MsdPinyinLetterSort(
        new HelperWIthTesting<>("MSD sort with Letter", nWords), 3);
    tempForString = toSort.clone();
    MsdByLetter.preProcess(tempForString);
    MsdByLetter.sort(tempForString);
    MsdByLetter.postProcess(tempForString);
    FileUtil.writeArrayToFile(tempForString, "LetterSortOrder/msdsort_result.txt");

    // LSD Sort
    RadixSort LsdByLetter = new LsdPinyinLetterSort(
        new HelperWIthTesting<>("LSD sort with Letter", nWords));
    tempForString = toSort.clone();
    LsdByLetter.preProcess(tempForString);
    LsdByLetter.sort(tempForString);
    LsdByLetter.postProcess(tempForString);
    FileUtil.writeArrayToFile(tempForString, "LetterSortOrder/lsdsort_result.txt");

    //================================== With Syllabification ==================================
    System.out.println("------------------------Sort with Syllabification-----------------");
    // Tim Sort
    TimSort<NameBySyllabification> tim_sorter_2 = new TimSort<NameBySyllabification>(
        new HelperWIthTesting<>("TimSort", nWords));
    tempForSylla = nameBySyllabification.clone();
    tempForSylla = tim_sorter_2.sort(tempForSylla);
    FileUtil.writeArrayToFile(tempForSylla, "timsort_result.txt");

    // Dual Pivot Quick Sort
    QuickSort_DualPivot<NameBySyllabification> quick_sorter_2 = new QuickSort_DualPivot<NameBySyllabification>(
        new HelperWIthTesting<>("QuickSort_DualPivot", nWords));
    tempForSylla = nameBySyllabification.clone();
    tempForSylla = quick_sorter_2.sort(tempForSylla);
    FileUtil.writeArrayToFile(tempForSylla, "quicksort_result.txt");

    // Husky Sort
    PureHuskySortWithHelper<NameBySyllabification> husky_sorter_2 =
        new PureHuskySortWithHelper<>(nameCoderBySylla,
            new HelperWIthTesting<>("HuskySort with NameByLetter", nWords));
    tempForSylla = nameBySyllabification.clone();
    tempForSylla = husky_sorter_2.sort(tempForSylla);
    FileUtil.writeArrayToFile(tempForSylla, "huskysort_result.txt");

    // MSD Sort
    RadixSort MsdBySyllabification = new MsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("MSD sort with Syllabification", nWords), 3);
    tempForString = toSort.clone();
    MsdBySyllabification.preProcess(tempForString);
    MsdBySyllabification.sort(tempForString);
    MsdBySyllabification.postProcess(tempForString);
    FileUtil.writeArrayToFile(tempForString, "SyllableSortOrder/msdsort_result.txt");

    // LSD Sort
    RadixSort LsdBySyllabification = new LsdPinyinSyllabificationSort(
        new HelperWIthTesting<>("LSD sort with Syllabification", nWords));
    tempForString = toSort.clone();
    LsdBySyllabification.preProcess(tempForString);
    LsdBySyllabification.sort(tempForString);
    LsdBySyllabification.postProcess(tempForString);
    FileUtil.writeArrayToFile(tempForString, "SyllableSortOrder/lsdsort_result.txt");
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
