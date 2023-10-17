package edu.hw1;

public class Task4 {
    public String fixString(String str) {
        char tmp;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length - 1; i += 2) {
            tmp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tmp;

        }
        String modifiedStr = String.valueOf(charArray);
        return modifiedStr;
    }
}
