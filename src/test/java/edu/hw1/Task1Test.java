package edu.hw1;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class Task1Test {

    @ParameterizedTest
    @CsvSource({"'13:56', 836", "'01:00', 60", "'999:59', 59999", "'66:66', -1", "'00:00', 0", "'60:60', -1", "'-01:30', -1", "'05:-35', -1"})
    @DisplayName("Counting video length in seconds")
    void testMinutesToSeconds(String input, int expected){

        Task1 task1 = new Task1();

        //When
        int result = task1.minutesToSeconds(input);

        //Then
        Assertions.assertThat(result).isEqualTo(expected);

    }
}
