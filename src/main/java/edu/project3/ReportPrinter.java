package edu.project3;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ReportPrinter {

    private ReportPrinter() {}

    private static final Logger LOGGER = LogManager.getLogger();

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final String WALL_OF_TABLE = "|===";

    public static void generateReport(List<String> filePaths, Supplier<Stream<LogRecord>> logRecordsSupplier,
        long requestCount, double averageResponseSize,
        List<Map.Entry<String, Long>> mostFrequentSources, List<Map.Entry<Integer, Long>> mostFrequentCodes,
        CommandLineParameters commandLineParameters) {

        var format = commandLineParameters.getFormat();

        if (format == null || format.isEmpty() || format.equals("md")) {

            generateMarkdownReport(filePaths, logRecordsSupplier, requestCount,
                averageResponseSize, mostFrequentSources, mostFrequentCodes);

        } else if (format.equals("adoc")) {

            generateAdocReport(filePaths, logRecordsSupplier, requestCount,
                averageResponseSize, mostFrequentSources, mostFrequentCodes);

        } else {
            LOGGER.error("Unsupported format: " + format);
            throw new IllegalArgumentException();
        }
    }

    public static void generateMarkdownReport(List<String> filePaths, Supplier<Stream<LogRecord>> logRecordsSupplier,
        long requestCount, double averageResponseSize,
        List<Map.Entry<String, Long>> mostFrequentSources, List<Map.Entry<Integer, Long>> mostFrequentCodes) {

        Path outputPath = Paths.get("report.md");

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputPath.toFile()))) {

            Optional<LogRecord> minRecord = logRecordsSupplier.get().min(Comparator.comparing(LogRecord::timestamp));
            Optional<LogRecord> maxRecord = logRecordsSupplier.get().max(Comparator.comparing(LogRecord::timestamp));



            // Header1
            writer.println("#### Общая информация");
            writer.println();
            writer.println("| Метрика | Значение |");
            writer.println("|:--------|---------:|");

            // Files
            writer.printf("| Файл(-ы) | `%s` |%n", String.join(", ", filePaths));

            // Dates
            writer.printf("| Начальная дата | %s |%n", minRecord
                .map(r -> r.timestamp().toLocalDate()
                    .format(DATE_PATTERN)).orElse(""));

            writer.printf("| Конечная дата | %s |%n", maxRecord
                .map(r -> r.timestamp().toLocalDate()
                    .format(DATE_PATTERN)).orElse(""));

            // Request count
            writer.printf("| Количество запросов | %,d |%n", requestCount);


            // Average response size
            writer.printf("| Средний размер ответа | %.2f б |%n", averageResponseSize);

            writer.println();

            // Header2
            writer.println("#### Запрашиваемые ресурсы");
            writer.println();
            writer.println("| Ресурс | Количество |");
            writer.println("|:-------|-----------:|");

            // Most frequent sources
            for (Map.Entry<String, Long> entry : mostFrequentSources) {
                writer.printf("| `%s` | %,d |%n", entry.getKey(), entry.getValue());
            }

            writer.println();

            writer.println("#### Коды ответа");
            writer.println();
            writer.println("| Код |          Имя          | Количество |");
            writer.println("|:---:|:---------------------:|-----------:|");

            // Most frequent codes
            for (Map.Entry<Integer, Long> entry : mostFrequentCodes) {
                writer.printf("| %d | %s | %,d |%n", entry.getKey(),
                    getStatusCodeName(entry.getKey()), entry.getValue());
            }

            writer.println();

            writer.flush();

        } catch (IOException e) {
            LOGGER.error("Error during create .md report", e);
            throw new RuntimeException();
        }
    }

    public static void generateAdocReport(List<String> filePaths, Supplier<Stream<LogRecord>> logRecordsSupplier,
        long requestCount, double averageResponseSize,
        List<Map.Entry<String, Long>> mostFrequentSources, List<Map.Entry<Integer, Long>> mostFrequentCodes) {


        Path outputPath = Paths.get("report.adoc");

        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
            new FileOutputStream(outputPath.toFile()), StandardCharsets.UTF_8))) {

            Optional<LogRecord> minRecord = logRecordsSupplier.get().min(Comparator.comparing(LogRecord::timestamp));
            Optional<LogRecord> maxRecord = logRecordsSupplier.get().max(Comparator.comparing(LogRecord::timestamp));

            // Header1
            writer.println("==== Общая информация");
            writer.println();
            writer.println(WALL_OF_TABLE);
            writer.println("| Метрика | Значение ");

            // File(s)
            writer.printf("| Файл(-ы) | `%s`%n", String.join(", ", filePaths));

            // Dates
            writer.printf("| Начальная дата | `%s`%n", minRecord
                .map(r -> r.timestamp().toLocalDate()
                    .format(DATE_PATTERN)).orElse(""));

            writer.printf("| Конечная дата | `%s`%n", maxRecord
                .map(r -> r.timestamp().toLocalDate()
                    .format(DATE_PATTERN)).orElse(""));

            // Request count
            writer.printf("| Количество запросов | %,d%n", requestCount);

            // Average response size
            writer.printf("| Средний размер ответа | %.2f б%n", averageResponseSize);

            writer.println(WALL_OF_TABLE);

            // Header2
            writer.println("==== Запрашиваемые ресурсы");
            writer.println();
            writer.println(WALL_OF_TABLE);
            writer.println("| Ресурс | Количество");

            // Most frequent sources
            for (Map.Entry<String, Long> entry : mostFrequentSources) {
                writer.printf("| `%s` | %,d%n", entry.getKey(), entry.getValue());
            }

            writer.println(WALL_OF_TABLE);

            writer.println("==== Коды ответа");
            writer.println();
            writer.println(WALL_OF_TABLE);
            writer.println("| Код | Имя | Количество");

            // Most frequent codes
            for (Map.Entry<Integer, Long> entry : mostFrequentCodes) {
                writer.printf("| %d | %s | %,d%n", entry.getKey(), getStatusCodeName(entry.getKey()), entry.getValue());
            }

            writer.println(WALL_OF_TABLE);

            writer.flush();


        } catch (IOException e) {
            LOGGER.error("Error during create .adoc report", e);
            throw new RuntimeException();
        }
    }



    private static String getStatusCodeName(int statusCode) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/statuc_codes.txt"));
            ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" {5}");
                if (parts.length == 2) {
                    int code = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    if (code == statusCode) {
                        return name;
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error during get status code", e);
        }

        return "";
    }
}
