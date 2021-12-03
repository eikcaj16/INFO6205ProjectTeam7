package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameByLetter.ChsCharToIdxArrByLetter;
import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LsdPinyinLetterSort extends PinyinSort {

  public LsdPinyinLetterSort(Helper<String> helper) {
    super(helper);
    radix = new int[]{25, 10, 8, 8, 6, 2, 6};
  }

  @Override
  /**
   * Sort from xs[lo] to xs[hi] (exclusive) xs is an array of strings using MsdRadixSort.
   *
   * ===IMPORTANT===
   * Call MsdPinyinSort.preProcess before calling MsdPinyinSort.sort
   * Call MsdPinyin.Sort.postProcess after sorting to get the result in a string array
   *
   * @param xs the array to be sorted
   * @param lo the low index
   * @param hi the high index (exclusive)
   */
  public void sort(String[] xs, int lo, int hi) {
    aux = new byte[xs.length][];
    int maxLen = findMaxLength(xs) * 8;
    sort(lo, hi, maxLen, 0);
  }

  @Override
  void sort(int lo, int hi, int maxLen, int cirIdx) {
    int period = radix.length;
    int step;
    for (int i = maxLen - 1; i >= 0; i -= step) {
      int[] count = new int[radix[period - 1 - cirIdx] + 2];

      for (int j = lo; j < hi; j++) {
        count[charAt(compArr[j], i, period - 1 - cirIdx) + 2]++;
      }

      for (int r = 0; r < radix[period - 1 - cirIdx] + 1; r++)      // Transform counts to indices.
      {
        count[r + 1] += count[r];
      }

      for (int j = lo; j < hi; j++) {
        aux[count[charAt(compArr[j], i, period - 1 - cirIdx) + 1]++] = compArr[j];
      }

      if (hi - lo >= 0) {
        System.arraycopy(aux, 0, compArr, lo, hi - lo);
      }

      step = getStep(cirIdx);
      cirIdx = (cirIdx + 1) % period;
    }
  }

  /**
   * findMaxLength method returns maximum length of all available strings in an array
   *
   * @param strArr It contains an array of String from which maximum length needs to be found
   * @return int Returns maximum length value
   */
  private int findMaxLength(String[] strArr) {
    int maxLength = strArr[0].length();
    for (String str : strArr) {
      maxLength = Math.max(maxLength, str.length());
    }
    return maxLength;
  }

  @Override
  void convertStrArrToByteArr(String[] wordArr) {
    final ChsCharToIdxArrByLetter cctiabl = new ChsCharToIdxArrByLetter();

    for (int k = 0; k < wordArr.length; k++) {
      String word = wordArr[k];
      int byteShift = 0;
      int[] pinyinIdxArr = cctiabl.CharAt(word);

      for (int i = 0; i < word.length(); i++) {
        // Since the greatest number of the index is less than 64 and the sign bit is useless in our case,
        // when casting to byte, the missing first byte of the int does not affect the result

        // 1st idx
        compArr[k][byteShift] |= (byte) (pinyinIdxArr[i * 7]);
        // 2nd idx
        compArr[k][byteShift + 1] |= (byte) pinyinIdxArr[i * 7 + 1];
        // 3rd idx
        compArr[k][byteShift + 2] |= (byte) (pinyinIdxArr[i * 7 + 2] << 3);
        // 4th idx
        compArr[k][byteShift + 2] |= (byte) pinyinIdxArr[i * 7 + 3];
        // 5th idx
        compArr[k][byteShift + 3] |= (byte) (pinyinIdxArr[i * 7 + 4] << 4);
        // 6th idx
        compArr[k][byteShift + 3] |= (byte) (pinyinIdxArr[i * 7 + 5] << 3);
        // 7th idx
        compArr[k][byteShift + 3] |= (byte) (pinyinIdxArr[i * 7 + 6]);
        // original word
        copyWordToByteArr(word, i, k, byteShift);
        byteShift += 8;
      }
    }
  }

  @Override
  int getStep(int cirIdx) {
    switch (cirIdx) {
      case 0:
      case 1:
      case 3:
        return 1;
      case 6:
        return 4;
      default:
        return 0;
    }
  }

  @Override
  int charAt(byte[] bArr, int d, int cirIdx) {
    if (d < bArr.length) {
      int loc = (d / 8) * 8;
      switch (cirIdx) {
        case 0:
          return bArr[loc];
        case 1:
          return bArr[loc + 1];
        case 2:
          return (int) bArr[loc + 2] >> 3;
        case 3:
          return (int) bArr[loc + 2] & 0x7;
        case 4:
          return (int) bArr[loc + 3] >> 4;
        case 5:
          return bArr[loc + 3] & 0b100;
        case 6:
          return (int) bArr[loc + 3] & 0x7;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
//    String[] sa = {"老伙计", "救济", "做作", "经济", "坐下", "啊"};
//    RadixSort lrs = new LsdPinyinSyllabificationSort(new HelperWIthTesting<>("MSD Radix Sort"));
//    lrs.preProcess(sa);
//    lrs.sort(sa, 0, sa.length);
//    lrs.postProcess(sa);
//    Arrays.stream(sa).forEach(System.out::println);

    List<String> names_txt = new ArrayList<>();
    try {
      FileReader fr = new FileReader("shuffledChinese1.txt");
      BufferedReader br = new BufferedReader(fr);
      System.out.println("\n======== Create file reader success! ========");

      while (br.ready()) {
        String a = br.readLine();
        if (a.contains("吕")) {
          continue;
        }
        names_txt.add(a);
      }

      System.out.println("======== Data have been read! ========");

      br.close();
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    String[] ssa = new String[names_txt.size()];

    System.out.println(ssa.length);
    for (int i = 0; i < ssa.length; i++) {
      ssa[i] = names_txt.get(i);
    }

    RadixSort mrs = new LsdPinyinSyllabificationSort(new HelperWIthTesting<>("MSD Radix Sort"));
    mrs.preProcess(ssa);
    mrs.sort(ssa, 0, ssa.length);
    mrs.postProcess(ssa);
    Arrays.stream(ssa).forEach(System.out::println);
  }
}
