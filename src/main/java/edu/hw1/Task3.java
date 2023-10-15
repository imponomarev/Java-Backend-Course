package edu.hw1;

import java.util.Arrays;

public class Task3 {

    public boolean isNestable(int[] a1, int[] a2){
        Arrays.sort(a1);
        Arrays.sort(a2);

        if ((a1[0] > a2[0]) && (a1[a1.length - 1] < a2[a2.length - 1])){
            return true;
        }
        else{
            return false;
        }
    }
}
