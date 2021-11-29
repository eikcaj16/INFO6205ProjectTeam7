package edu.neu.coe.info6205.team7.NameByLetter;

import edu.neu.coe.info6205.team7.AbstractChsCharToIdxArr;

public class ChsCharToIdxArrByLetter extends AbstractChsCharToIdxArr {
    @Override
    public int[] CharAt(char c) {
        int[] res = new int[7];
        String wholePinyin = TransferChineseToPinyin(c);
        int tone = wholePinyin.charAt(wholePinyin.length() - 1) - '0';
        String pinyin = wholePinyin.substring(0,wholePinyin.length() - 1);

        int index = 0;
        while (index < pinyin.length()) {
            switch (index) {
                case 0:
                    res[index] = LetterMap.index0.get(pinyin.charAt(0));
                    break;
                case 1:
                    res[index] = LetterMap.index1.get(pinyin.charAt(1));
                    break;
                case 2:
                    res[index] = LetterMap.index2.get(pinyin.charAt(2));
                    break;
                case 3:
                    res[index] = LetterMap.index3.get(pinyin.charAt(3));
                    break;
                case 4:
                    res[index] = LetterMap.index4.get(pinyin.charAt(4));
                    break;
                case 5:
                    res[index] = LetterMap.index5.get(pinyin.charAt(5));
                    break;
            }
            index++;
        }
        res[index] = tone;
        return res;
    }
}
