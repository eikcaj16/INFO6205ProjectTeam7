package edu.neu.coe.info6205.team7;

import edu.neu.coe.huskySort.sort.Helper;
import edu.neu.coe.huskySort.sort.SortWithHelper;
import edu.neu.coe.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.huskySort.sort.huskySortUtils.HuskyCoder;

public class PureHuskySortWithHelper<X extends Comparable<X>> extends SortWithHelper<X> {
  PureHuskySort<X> pureHuskySort;
  public PureHuskySortWithHelper(HuskyCoder<X> code,Helper<X> helper) {
    super(helper);
    pureHuskySort = new PureHuskySort<>(code,false,false);
  }
  @Override
  public void sort(X[] xs, int from, int to) {
    if (pureHuskySort == null){
      throw new HuskySortException("No HuskyCode");
    }
    pureHuskySort.sort(xs);
  }
  public static class HuskySortException extends RuntimeException {

    public HuskySortException(String message) {
      super(message);
    }
  }
}
