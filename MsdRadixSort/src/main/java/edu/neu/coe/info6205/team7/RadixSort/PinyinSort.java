package edu.neu.coe.info6205.team7.RadixSort;

import edu.neu.coe.huskySort.sort.Helper;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Radix Sort for Chinese characters The class extends RadixSort and encapsulates some APIs for
 * Chinese sort algorithm
 *
 * @author Yiqing Jackie Huang
 */
abstract class PinyinSort extends RadixSort {

  public PinyinSort(Helper<String> helper) {
    super(helper);
  }

  @Override
  /**
   * Sort from xs[lo] to xs[hi] (exclusive) xs is an array of strings using MsdRadixSort.
   *
   * ===IMPORTANT===
   * Call PinyinSort.preProcess before calling PinyinSort.sort
   * Call PinyinSort.postProcess after sorting to get the result in a string array
   *
   * @param xs the array to be sorted
   * @param lo the low index
   * @param hi the high index (exclusive)
   */
  public void sort(String[] xs, int lo, int hi) {
    aux = new byte[xs.length][];
    sort(lo, hi, 0, 0);
  }

  @Override
  /* Borrow the API to enable more flexibility when doing benchmark */
  public String[] preProcess(String[] xs) {
    compArr = new byte[xs.length][];
    // Use 4 bytes to represent for pinyin index and 4 bytes for the original character
    // Using this way to initialize all byte arrays is a temporary solution to prevent the resources
    // from being released. Those arrays may be redundant for most of the input, but insufficient
    // for those long words.
    for (int i = 0; i < compArr.length; i++) {
      compArr[i] = new byte[32];
    }
    convertStrArrToByteArr(xs);
    return null;
  }

  @Override
  /* Borrow the API to enable more flexibility when doing benchmark */
  public void postProcess(String[] xs) {
    convertByteArrToStrArr(xs);
  }

  void sort(int lo, int hi, int d, int cirIdx) {
    if (hi <= lo) {
    } else {
      int[] count = new int[radix[cirIdx] + 2];
      for (int i = lo; i < hi; i++) {
        count[charAt(compArr[i], d, cirIdx) + 2]++;
      }

      for (int r = 0; r < radix[cirIdx] + 1; r++)      // Transform counts to indices.
      {
        count[r + 1] += count[r];
      }

      for (int i = lo; i < hi; i++) {
        aux[count[charAt(compArr[i], d, cirIdx) + 1]++] = compArr[i];
      }

      // Copy back
      System.arraycopy(aux, 0, compArr, lo, hi - lo);

      int step = getStep(cirIdx);
      int period = radix.length;

      for (int r = 0; r < radix[cirIdx]; r++) {
        sort(lo + count[r], lo + count[r + 1], d + step, (cirIdx + 1) % period);
      }
    }
  }

  /**
   * Convert the input words in a string array in to an internal byte array with all the words'
   * pinyin and tone information
   *
   * @param wordArr the input words in a string array
   */
  abstract void convertStrArrToByteArr(String[] wordArr);

  /**
   * Convert the internal byte array to a string array
   *
   * @param wordArr an out parameter to get the result of the string array
   */
  void convertByteArrToStrArr(String[] wordArr) {
    for (int k = 0; k < wordArr.length; k++) {
      byte[] bA = compArr[k];
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bA.length; i += 8) {
        int j;
        for (j = 4; j < 8; j++) {
          if (bA[i + j] != 0x00) {
            break;
          }
        }
        // Stop when there is no remaining character
        if (j == 7) {
          break;
        }
        sb.append(
            new String(Arrays.copyOfRange(bA, i + j, i + 8), StandardCharsets.UTF_8));
      }
      wordArr[k] = sb.toString();
    }
  }

  /**
   * Get the forward step according to the current circle index. Unlike the original algorithm where
   * radix is a fixed value, in this optimized algorithm, the forward step varies periodically
   *
   * @param cirIdx the current circle index
   * @return the value of forward step
   */
  abstract int getStep(int cirIdx);

  abstract int charAt(byte[] bArr, int d, int cirIdx);

  void copyWordToByteArr(String word, int i, int k, int byteShift) {
    byte[] bWord = word.substring(i, i + 1).getBytes(StandardCharsets.UTF_8);
    for (int j = 0; j < bWord.length; j++) {
      compArr[k][byteShift + 7 - j] = bWord[bWord.length - j - 1];
    }
  }

  static int[] radix;
  static byte[][] compArr;
  static byte[][] aux;
}
