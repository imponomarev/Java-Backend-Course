package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

    public boolean validatePassword (String password) {

        if (password == null) {
            return false;
        }

        String regex = "[~!@#$%^&*|]";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
