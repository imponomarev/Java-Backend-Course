package edu.hw6;

import edu.hw6.task5.HackerNews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class Task5Test {

    @Test
    void testHackerNewsTopStories() throws IOException, InterruptedException {

        long[] topStories = HackerNews.hackerNewsTopStories();

        Assertions.assertNotNull(topStories);
        Assertions.assertNotEquals(0, topStories.length);

        for (long id : topStories) {
            Assertions.assertTrue(id > 0);
        }
    }

    @Test
    void testNews() throws IOException, InterruptedException {


        String result = HackerNews.news(37570037);
        String expected = "JDK 21 Release Notes";

        Assertions.assertNotNull(result);
        Assertions.assertNotEquals("", result);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testInvalidNewsId() throws IOException, InterruptedException {


        String newsTitle = HackerNews.news(-12345678);

        Assertions.assertNull(newsTitle);
    }



}
