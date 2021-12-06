package edu.neu.coe.info6205.team7.util;

import edu.neu.coe.info6205.team7.NameByLetter.NameByLetter;
import edu.neu.coe.info6205.team7.NameBySyllabification.NameBySyllabification;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

  private static final String output_path = "./output/";

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

  public static void writeArrayToFile(String[] result, String filename) {
    try {

      FileWriter fr = new FileWriter(output_path + filename);
      for (String name : result) {
        fr.write(name + "\n");
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void writeArrayToFile(NameByLetter[] result, String filename) {
    try {
      FileWriter fr = new FileWriter(output_path + "LetterSortOrder/" + filename);
      for (NameByLetter name : result) {
        fr.write(name.getName() + "\n");
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void writeArrayToFile(NameBySyllabification[] result, String filename) {
    try {
      FileWriter fr = new FileWriter(output_path + "SyllableSortOrder/" + filename);
      for (NameBySyllabification name : result) {
        fr.write(name.getName() + "\n");
      }
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}