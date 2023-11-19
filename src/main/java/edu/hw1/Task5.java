package edu.hw1;

public class Task5 {

    public boolean isPalindromeDescendant(int number) {
        String digits = String.valueOf(number);

        while (digits.length() > 1) {
            if (isPalindrome(digits)) {
                return true;
            }
            digits = conversionToPalindrome(Integer.parseInt(digits));
        }
        return false;
    }

    public String conversionToPalindrome(int number) {
        String digits = String.valueOf(number);
        StringBuilder newDigits = new StringBuilder(digits.length() / 2);
        for (int i = 0; i < digits.length(); i += 2) {
            if (i + 1 == digits.length()) {
                newDigits.append(digits.charAt(i));
                break;
            }
            int newValue = Character.getNumericValue(digits.charAt(i))
                + Character.getNumericValue(digits.charAt(i + 1));
            newDigits.append(newValue);
        }
        return newDigits.toString();
    }

    public static boolean isPalindrome(String str) {
        return str.equals(new StringBuilder(str).reverse().toString());
    }
}
