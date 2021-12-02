package edu.neu.coe.info6205.team7;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.info6205.team7.Benchmark.HelperWIthTesting;
import edu.neu.coe.info6205.team7.NameBySyllabification.ChsCharToIdxArrBySylla;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MsdPinyinSyllabification extends MsdRadixSort {

  public MsdPinyinSyllabification(Helper<String> helper) {
    super(helper);
  }

  @Override
  public void sort(String[] xs, int from, int to) {
    aux = new byte[xs.length][];
    sort(0, xs.length, 0, 0);
  }

  @Override
  public String[] preProcess(String[] xs) {
    convertStrArrToByteArr(xs);
    return null;
  }

  @Override
  public void postProcess(String[] xs) {
    convertByteArrToStrArr(xs);
  }

  private void convertByteArrToStrArr(String[] wordArr) {
    for (int k = 0; k < wordArr.length; k++) {
      byte[] bA = compArr[k];
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bA.length; i += 8) {
        int j = 4;
        while (bA[i + j] == 0x00) {
          j++;
        }
        sb.append(
            new String(Arrays.copyOfRange(bA, i + j, i + 8), StandardCharsets.UTF_8));
      }
      wordArr[k] = sb.toString();
    }
  }

  private static void convertStrArrToByteArr(String[] wordArr) {
    final ChsCharToIdxArrBySylla cctiabl = new ChsCharToIdxArrBySylla();
    compArr = new byte[wordArr.length][];

    for (int k = 0; k < wordArr.length; k++) {
      String word = wordArr[k];
      // Use 4 bytes to represent for pinyin index and 4 bytes for the original character
      compArr[k] = new byte[word.length() * 8];
      int byteShift = 0;
      int[] pinyinIdxArr = cctiabl.CharAt(word);

      for (int i = 0; i < word.length(); i++) {
        // Since the greatest number of the index is less than 64 and the sign bit is useless in our case,
        // when casting to byte, the missing first byte of the int does not affect the result

        // 1st idx
        compArr[k][byteShift + 1] |= (byte) (pinyinIdxArr[i * 4] - 1);
        // 2nd idx
        compArr[k][byteShift + 2] |= (byte) pinyinIdxArr[i * 4 + 1];
        // 3rd idx
        compArr[k][byteShift + 3] |= ((byte) (pinyinIdxArr[i * 4 + 2] << 3));
        // 4th idx
        compArr[k][byteShift + 3] |= (byte) (pinyinIdxArr[i * 4 + 3] - 1);
        // original word
        byte[] bWord = word.substring(i, i + 1).getBytes(StandardCharsets.UTF_8);
        for (int j = 0; j < bWord.length; j++) {
          compArr[k][byteShift + 7 - j] = bWord[bWord.length - j - 1];
        }
        byteShift += 8;
      }
    }
  }

  @Override
  protected void sort(int lo, int hi, int d, int cirIdx) {
    if (hi <= lo) {
    } else {
      int[] count = new int[radix[cirIdx] + 2];
      if (d % 8 == 0) {
        d++;
      }
      for (int i = lo; i < hi; i++) {
        int a = charAt(compArr[i], d, cirIdx);
        int tempIdx = a + 2;
        count[tempIdx]++;
      }

      for (int r = 0; r < radix[cirIdx] + 1; r++)      // Transform counts to indices.
      {
        count[r + 1] += count[r];
      }

      for (int i = lo; i < hi; i++) {
        aux[count[charAt(compArr[i], d, cirIdx) + 1]++] = compArr[i];
      }

      // Copy back
      if (hi - lo >= 0) {
        System.arraycopy(aux, 0, compArr, lo, hi - lo);
      }

      int step;
      switch (cirIdx) {
        case 0:
        case 1:
          step = 1;
          break;
        case 3:
          step = 4;
          break;
        default:
          step = 0;
          break;
      }

      for (int r = 0; r < radix[cirIdx]; r++) {
        sort(lo + count[r], lo + count[r + 1], d + step, (cirIdx + 1) % 4);
      }
    }
  }

  @Override
  protected int charAt(byte[] bArr, int d, int cirIdx) {
    if (d < bArr.length) {
      int loc = (d / 8) * 8;
      switch (cirIdx) {
        case 0:
          loc += 1;
          return bArr[loc];
        case 1:
          loc += 2;
          return bArr[loc];
        case 2:
          loc += 3;
          return ((int) bArr[loc] >> 3) * -1;
        case 3:
          loc += 3;
          return (int) bArr[loc] & 0x7;
      }
    }
    return -1;
  }

  private static final int[] radix = {35, 23, 23, 5};

  public static void main(String[] args) {
    String[] sa = {"老伙计", "救济", "做作", "经济", "坐下", "啊"};
    MsdRadixSort mrs = new MsdPinyinSyllabification(new HelperWIthTesting<>("MSD Radix Sort"));
    mrs.preProcess(sa);
    mrs.sort(sa, 0, sa.length);
    mrs.postProcess(sa);
    Arrays.stream(sa).forEach(System.out::println);
  }
}
