package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {

    public boolean binaryMatch1(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^([01]{2})*[01]$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch2(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(0([01]{2})*|1([01]{2})*[01])$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch3(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^((1*01*){3})*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch4(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(?!11$|111$)[01]*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch5(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(1([01]|$))+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch6(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(([01]?0+0+)|(0+[01]?0+)|(0+0+[01]?))$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }

    public boolean binaryMatch7(String input) {

        if (input == null) {
            return false;
        }

        String regex = "^(0+|^)(1?(0+|$))+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        return matcher.matches();
    }
}
