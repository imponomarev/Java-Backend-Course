package edu.hw1;

public class Task4 {
    public String fixString(String str) {
        char tmp;
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            tmp = charArray[i];
            if (i + 1 == charArray.length){
                break;
            }
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tmp;
            i++;

        }
        str = String.valueOf(charArray);
        return str;
    }
}
