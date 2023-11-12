package edu.hw5;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private static final List<Character> COMMON_MATECHARACTERS = Arrays.asList(
        '^', '{', '}', '+', '<', '[', ']', '*', '(', ')',
        '>', '.', '|', '$', '\\', '?'
    );

    public boolean isSubsequence(String s, String t) {

        if (s == null || t == null) {
            return false;
        }

        StringBuilder regex = new StringBuilder();

        for (char c : s.toCharArray()) {

            if (COMMON_MATECHARACTERS.contains(c)){
                throw new RuntimeException();
            }
            regex.append(".*").append(Pattern.quote(Character.toString(c)));
        }

        regex.append(".*");

        String regexStr = regex.toString();

        Pattern pattern = Pattern.compile(regexStr);
        Matcher matcher = pattern.matcher(t);

        return matcher.matches();
    }
}
