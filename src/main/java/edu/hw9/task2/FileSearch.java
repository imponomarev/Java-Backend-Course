package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FileSearch {

    private FileSearch() {

    }

    private final static int LIMITATION = 100;


    public static List<File> findDirectoriesWithMoreThan1000Files(File root) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        DirectorySearchTask task = new DirectorySearchTask(root);

        return forkJoinPool.invoke(task);
    }

    public static List<File> findFilesMatchingPredicate(File root, String extension, long sizeThreshold) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FileSearchTask task = new FileSearchTask(root, extension, sizeThreshold);

        return forkJoinPool.invoke(task);
    }


    static class DirectorySearchTask extends RecursiveTask<List<File>> {

        private final File directory;

        DirectorySearchTask(File directory) {
            this.directory = directory;
        }

        @Override
        protected List<File> compute() {

            List<File> result = new ArrayList<>();

            File[] files = directory.listFiles();

            if (files != null) {
                List<DirectorySearchTask> subTasks = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        DirectorySearchTask subTask = new DirectorySearchTask(file);
                        subTask.fork();
                        subTasks.add(subTask);
                    }
                }

                for (DirectorySearchTask subTask : subTasks) {
                    result.addAll(subTask.join());
                }

                if (files.length > LIMITATION) {
                    result.add(directory);
                }
            }

            return result;
        }

    }

    static class FileSearchTask extends RecursiveTask<List<File>> {
        private final File directory;
        private final String extension;
        private final long sizeThreshold;

        FileSearchTask(File directory, String extension, long sizeThreshold) {
            this.directory = directory;
            this.extension = extension;
            this.sizeThreshold = sizeThreshold;
        }

        @Override
        protected List<File> compute() {

            List<File> result = new ArrayList<>();

            File[] files = directory.listFiles();

            if (files != null) {
                List<FileSearchTask> subTasks = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        FileSearchTask subTask = new FileSearchTask(file, extension, sizeThreshold);
                        subTask.fork();
                        subTasks.add(subTask);
                    } else if (file.isFile() && file.getName().endsWith(extension) && file.length() > sizeThreshold) {
                        result.add(file);
                    }
                }

                for (FileSearchTask subTask : subTasks) {
                    result.addAll(subTask.join());
                }
            }

            return result;
        }
    }
}
