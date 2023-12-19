package edu.project3;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogReader {

    private LogReader() {}

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int REMOTE_USER = 3;

    private static final int TIMESTAMP = 4;

    private static final int REQUEST = 5;

    private static final int STATUS_CODE = 6;

    private static final int BYTES_SENT = 7;

    private static final int REFERER = 8;

    private static final int USER_AGENT = 9;

    private static final DateTimeFormatter LOG_DATE_TIME_FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss XXXX", Locale.ENGLISH);

    public static Stream<LogRecord> readLog(CommandLineParameters commandLineParameters)
        throws IOException, InterruptedException {

        List<String> paths = commandLineParameters.getFilePaths();

        LocalDate fromDate = commandLineParameters.getFromDate();
        LocalDate toDate = commandLineParameters.getToDate();

        for (String path : paths) {

            if (isUrl(path)) {
               return readFromUrl(path, fromDate, toDate);
            } else {
                File directory = new File(path);
                if (directory.isDirectory()) {
                    return readFromDirectory(directory, fromDate, toDate);
                } else {
                    return readFromFile(path, fromDate, toDate);
                }

            }
        }
        return Stream.empty();
    }

    private static Stream<LogRecord> readFromUrl(String url, LocalDate fromDate, LocalDate toDate)
        throws IOException, InterruptedException {

        try (
            HttpClient client = HttpClient.newHttpClient();
        ) {

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                    url))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Stream<String> logLines = response.body().lines();

            Stream<LogRecord> logRecords = parseLogRecords(logLines);

            if (fromDate != null && toDate != null) {

                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(fromDate) >= 0
                        && logRecord.timestamp().toLocalDate().compareTo(toDate) <= 0);

            } else if (fromDate != null) {

                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(fromDate) >= 0);

            } else if (toDate != null) {
                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(toDate) <= 0);
            }


            return logRecords;

        }
    }

    private static boolean isUrl(String path) {
        try {
            new URL(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static Stream<LogRecord> readFromFile(String filePath, LocalDate fromDate, LocalDate toDate) {

        try {

            Stream<String> lines = Files.lines(Paths.get(filePath));

            Stream<LogRecord> logRecords = parseLogRecords(lines);

            if (fromDate != null && toDate != null) {

                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(fromDate) >= 0
                        && logRecord.timestamp().toLocalDate().compareTo(toDate) <= 0);

            } else if (fromDate != null) {

                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(fromDate) >= 0);

            } else if (toDate != null) {
                logRecords = logRecords.filter(logRecord ->
                    logRecord.timestamp().toLocalDate().compareTo(toDate) <= 0);
            }




            return logRecords;

        } catch (IOException e) {
            LOGGER.error("Error duting read loggs from file", e);
            return Stream.empty();
        }
    }

    private static Stream<LogRecord> readFromDirectory(File directory, LocalDate fromDate, LocalDate toDate) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    return readFromFile(file.getPath(), fromDate, toDate);
                }
            }
        }
        return Stream.empty();
    }



    private static Stream<LogRecord> parseLogRecords(Stream<String> logLines) {

        Pattern pattern =
            Pattern.compile("^(\\S+) (\\S+) (\\S+) \\[(.+?)] \"(.+?)\" (\\d+) (\\d+) \"([^\"]*)\" \"([^\"]*)\"$");

        return logLines.map(line -> {

            Matcher matcher = pattern.matcher(line);

            if (matcher.matches()) {
                String ipAddress = matcher.group(1);
                String remoteUser = matcher.group(REMOTE_USER);
                OffsetDateTime timestamp = OffsetDateTime.parse(matcher
                    .group(TIMESTAMP), LOG_DATE_TIME_FORMATTER);
                String request = matcher.group(REQUEST);
                int statusCode = Integer.parseInt(matcher.group(STATUS_CODE));
                long bytesSent = Long.parseLong(matcher.group(BYTES_SENT));
                String referer = matcher.group(REFERER);
                String userAgent = matcher.group(USER_AGENT);

                return new LogRecord(ipAddress, remoteUser, timestamp,
                    request, statusCode, bytesSent, referer, userAgent);
            } else {
                LOGGER.error("Error during parsing logs");
                return null;
            }
        }).filter(Objects::nonNull);
    }
}
