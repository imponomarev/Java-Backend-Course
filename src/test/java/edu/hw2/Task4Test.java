package edu.hw2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {

    @Test
    @DisplayName("callingInfo test")
    void callingInfoTest1() {

        //When
        Task4.CallingInfo callingInfo = Task4.callingInfo();

        //Then
        Assertions.assertEquals("edu.hw2.Task4Test", callingInfo.className());
        Assertions.assertEquals("callingInfoTest1", callingInfo.methodName());

    }

    @Test
    @DisplayName("callingInfo test")
    void callingInfoTest2() {

        //When
        Task4.CallingInfo callingInfo = Task4.callingInfo();

        //Then
        Assertions.assertEquals("edu.hw2.Task4Test", callingInfo.className());
        Assertions.assertEquals("callingInfoTest2", callingInfo.methodName());

    }
}
