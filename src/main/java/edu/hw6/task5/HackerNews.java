package edu.hw6.task5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String TOP_STORIES_URL = "https://hacker-news.firebaseio.com/v0/topstories.json";

    private static final String ITEM_URL_TEMPLATE = "https://hacker-news.firebaseio.com/v0/item/%d.json";

    public static long[] hackerNewsTopStories() {

        try (
            HttpClient httpClient = HttpClient.newHttpClient();
        ) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TOP_STORIES_URL))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String listOfNews = response.body();

            StringBuilder stringBuilder = new StringBuilder(listOfNews);

            stringBuilder.deleteCharAt(0);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);

            String[] listOfNewsArray = stringBuilder.toString().split(",");

            long[] parsedJSON = new long[listOfNewsArray.length];

            for (int i = 0; i < listOfNewsArray.length; i++) {
                parsedJSON[i] = Long.parseLong(listOfNewsArray[i].trim());
            }

            return parsedJSON;

        } catch (IOException | InterruptedException e) {
            LOGGER.error("Error while parsing json", e);
            return new long[0];
        }
    }

    public static String news(long id) {

        String itemUrl = String.format(ITEM_URL_TEMPLATE, id);

        try (
            HttpClient httpClient = HttpClient.newHttpClient();
        ) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(itemUrl))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            String news = response.body();

            String regex = "\"title\":\"(.*?)\"";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(news);

            if (matcher.find()) {

                return matcher.group(1);

            } else {
                LOGGER.error("Item not found");
                return null;
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Error while searching for the news title", e);
            return null;
        }
    }
}
