package edu.project3;

import java.time.LocalDate;
import java.util.List;
import com.beust.jcommander.Parameter;

public class CommandLineParameters {


    @Parameter(names = "--path")
    private List<String> filePaths;

    @Parameter(names = "--from", converter = LocalDateTimeConverter.class)
    private LocalDate fromDate;

    @Parameter(names = "--to", converter = LocalDateTimeConverter.class)
    private LocalDate toDate;

    @Parameter(names = "--format")
    private String format;

    public List<String> getFilePaths() {
        return filePaths;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public String getFormat() {
        return format;
    }

    public void setFilePaths(List<String> filePath) {
        this.filePaths = filePath;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
