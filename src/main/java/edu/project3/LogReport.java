package edu.project3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogReport {

    private LogReport() {}

    public static long countRequests(Stream<LogRecord> logRecords) {

        return logRecords.count();

    }

    public static List<Map.Entry<String, Long>> findMostFrequentResources(Stream<LogRecord> logRecords, int limit) {

        Map<String, Long> resourceCounts = logRecords
            .map(logRecord -> getResourceFromRequest(logRecord.request()))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return resourceCounts.entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(limit)
            .toList();

    }

    private static String getResourceFromRequest(String request) {

        String[] parts = request.split(" ");
        if (parts.length >= 2) {
            return parts[1];
        }
        return "";
    }


    public static List<Map.Entry<Integer, Long>> findMostFrequentStatusCode(Stream<LogRecord> logRecords, int limit) {

        Map<Integer, Long> statusCodeCounts = logRecords
            .collect(Collectors.groupingBy(LogRecord::statusCode, Collectors.counting()));

        return statusCodeCounts.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(limit)
            .toList();
    }

    public static OptionalDouble calculateAverageResponseSize(Stream<LogRecord> logRecords) {

        return logRecords
            .mapToLong(LogRecord::bytesSent)
            .average();
    }
}
