package edu.neu.coe.info6205.team7.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
  public static String[] readArrayFromFile(String srcPath) {
    return readArrayFromFile(srcPath, -1);
  }

  public static String[] readArrayFromFile(String srcPath, int size) {
    InputStream absolutePath = FileUtil.class.getResourceAsStream(srcPath);
    List<String> resList = new ArrayList<>();
    try {
      assert absolutePath != null;
      BufferedReader reader = new BufferedReader(new InputStreamReader(absolutePath));
      String s;
      while ((s = reader.readLine()) != null) {
        resList.add(s);
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (resList.size() < size || size == -1) {
      size = resList.size();
    }
    String[] res = new String[size];
    for (int i = 0; i < size; i++) {
      res[i] = resList.get(i);
    }
    return res;
  }
}