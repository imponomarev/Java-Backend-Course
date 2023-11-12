package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {

    public boolean binaryMatch(String input) {

        if (input == null) {
            return false;
        }

        String regex = "[01][01]0[01]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch2(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(0|1)[01]*\\1$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch3(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^[01]{1,3}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

}
