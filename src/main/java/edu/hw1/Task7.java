package edu.hw1;

public class Task7 {
    public int rotateRight(int n, int shift){

        String binary = Integer.toBinaryString(n);
        int[] binaryDigits = new int[binary.length()];

        for (int i = 0; i < binary.length(); i++){
            binaryDigits[i] = Character.getNumericValue(binary.charAt(i));
        }

        int length = binaryDigits.length;
        int[] result = new int[length];

        shift %= length;

        for (int i = 0; i < length; i++) {
            int newIndex = (i + shift) % length;
            result[newIndex] = binaryDigits[i];
        }

        StringBuilder strBinary = new StringBuilder();

        for (int bin : result) {
            strBinary.append(bin);
        }

        int answer = Integer.parseInt(strBinary.toString(), 2);

        return answer;
    }

    public int rotateLeft(int n, int shift){

        String binary = Integer.toBinaryString(n);
        int[] binaryDigits = new int[binary.length()];

        for (int i = 0; i < binary.length(); i++){
            binaryDigits[i] = Character.getNumericValue(binary.charAt(i));
        }

        int length = binaryDigits.length;
        int[] result = new int[length];

        shift %= length;

        for (int i = 0; i < length; i++) {
            int newIndex = (i - shift + length) % length;
            result[newIndex] = binaryDigits[i];
        }

        StringBuilder strBinary = new StringBuilder();

        for (int bin : result) {
            strBinary.append(bin);
        }

        int answer = Integer.parseInt(strBinary.toString(), 2);

        return answer;

    }
}
