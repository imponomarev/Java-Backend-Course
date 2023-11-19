package edu.project3;

import com.beust.jcommander.JCommander;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void testReadFromFile() throws Exception {

        String[] args = {"--path", "src/main/resources/logs1.txt"};
        Main.main(args);

        CommandLineParameters commandLineParameters = new CommandLineParameters();
        JCommander.newBuilder()
            .addObject(commandLineParameters)
            .build()
            .parse(args);


        Supplier<Stream<LogRecord>> logRecordsSupplier = () -> {
            try {
                return LogReader.readLog(commandLineParameters);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        };

        assertEquals(271, logRecordsSupplier.get().count());
    }

    @Test
    void testReadFromUrl() throws IOException, InterruptedException {

        String[] args = {"--path", "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"};
        Main.main(args);

        CommandLineParameters commandLineParameters = new CommandLineParameters();
        JCommander.newBuilder()
            .addObject(commandLineParameters)
            .build()
            .parse(args);


        Supplier<Stream<LogRecord>> logRecordsSupplier = () -> {
            try {
                return LogReader.readLog(commandLineParameters);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        };

        assertTrue( logRecordsSupplier.get().count() > 10000);
    }

    @Test
    void testReadFromDirectory() throws IOException, InterruptedException {

        String[] args = {"--path", "src/main/resources/logs-dir/"};
        Main.main(args);

        CommandLineParameters commandLineParameters = new CommandLineParameters();
        JCommander.newBuilder()
            .addObject(commandLineParameters)
            .build()
            .parse(args);


        Supplier<Stream<LogRecord>> logRecordsSupplier = () -> {
            try {
                return LogReader.readLog(commandLineParameters);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        };

        assertTrue( logRecordsSupplier.get().count() > 100);
    }

    @Test
    void testFindMostFrequentStatusCode() {

        Stream<LogRecord> logRecords = createSampleLogRecords();

        List<Map.Entry<Integer, Long>> mostFrequentStatusCodes = LogReport.findMostFrequentStatusCode(logRecords, 2);

        assertEquals(2, mostFrequentStatusCodes.size());

        assertEquals(200, mostFrequentStatusCodes.get(0).getKey());
        assertEquals(4L, mostFrequentStatusCodes.get(0).getValue());
        assertEquals(404, mostFrequentStatusCodes.get(1).getKey());
        assertEquals(1L, mostFrequentStatusCodes.get(1).getValue());
    }

    @Test
    void testCountRequests() {
        Stream<LogRecord> logRecords = createSampleLogRecords();

        long requestCount = LogReport.countRequests(logRecords);

        assertEquals(5L, requestCount);
    }

    @Test
    void testCalculateAverageResponseSize() {
        Stream<LogRecord> logRecords = createSampleLogRecords();

        OptionalDouble averageResponseSize = LogReport.calculateAverageResponseSize(logRecords);

        assertTrue(averageResponseSize.isPresent());
        assertEquals(200.0, averageResponseSize.getAsDouble());
    }




    private Stream<LogRecord> createSampleLogRecords() {
        LogRecord record1 = new LogRecord("ip1", "user1", OffsetDateTime.now(), "/resource1", 200, 100, "", "");
        LogRecord record2 = new LogRecord("ip2", "user2", OffsetDateTime.now(), "/resource2", 200, 200, "", "");
        LogRecord record3 = new LogRecord("ip3", "user3", OffsetDateTime.now(), "/resource1", 404, 150, "", "");
        LogRecord record4 = new LogRecord("ip4", "user4", OffsetDateTime.now(), "/resource2", 200, 250, "", "");
        LogRecord record5 = new LogRecord("ip5", "user5", OffsetDateTime.now(), "/resource2", 200, 300, "", "");

        return Stream.of(record1, record2, record3, record4, record5);
    }

    @Test
    void reportPrinterTest() {

        String[] args = {"--path",
            "src/main/resources/logs2.txt"};
        Main.main(args);

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

        List<Map.Entry<String, Long>> mostFrequentSources =
            LogReport.findMostFrequentResources(logRecordsSupplier.get(), 5);

        List<Map.Entry<Integer, Long>> mostFrequentCodes =
            LogReport.findMostFrequentStatusCode(logRecordsSupplier.get(), 5);

        OptionalDouble averageResponseSize = LogReport.calculateAverageResponseSize(logRecordsSupplier.get());

        assertDoesNotThrow(() -> ReportPrinter.generateReport(
            commandLineParameters.getFilePaths(),
            logRecordsSupplier,
            totalRequests,
            averageResponseSize.orElse(0.0),
            mostFrequentSources,
            mostFrequentCodes,
            commandLineParameters
        ));
    }
}



