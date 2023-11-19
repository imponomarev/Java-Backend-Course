package edu.project3;

import com.beust.jcommander.JCommander;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private Main() {}

    private static final Logger LOGGER = LogManager.getLogger();

    private static final int LIMIT = 5;

    public static void main(String[] args) {
        CommandLineParameters commandLineParameters = new CommandLineParameters();
        JCommander.newBuilder()
            .addObject(commandLineParameters)
            .build()
            .parse(args);


        Supplier<Stream<LogRecord>> logRecordsSupplier = () -> {

            try {

                return LogReader.readLog(commandLineParameters);

            } catch (IOException | InterruptedException e) {
                LOGGER.error("Error during read loggs", e);
                return null;
            }
        };

        long totalRequests = LogReport.countRequests(logRecordsSupplier.get());

        List<Map.Entry<String, Long>> mostFrequentSources = LogReport
            .findMostFrequentResources(logRecordsSupplier.get(), LIMIT);

        List<Map.Entry<Integer, Long>> mostFrequentCodes = LogReport.
            findMostFrequentStatusCode(logRecordsSupplier.get(), LIMIT);

        OptionalDouble averageResponseSize = LogReport
            .calculateAverageResponseSize(logRecordsSupplier.get());

        ReportPrinter.generateReport(commandLineParameters.getFilePaths(),
            logRecordsSupplier,
            totalRequests,
            averageResponseSize.orElse(0.0),
            mostFrequentSources,
            mostFrequentCodes,
            commandLineParameters);
    }
}
