package edu.hw6.task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class DiskMap extends AbstractDelegatingMap {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Path filePath;


    public DiskMap(Map<String, String> delegate, Path filePath) {
        super(delegate);
        this.filePath = filePath;
        loadFromDisk();
    }

    @Override
    public @Nullable String put(String key, String value) {
        var result = super.put(key, value);
        writeToDisk();
        return result;
    }

    @Override
    public String remove(Object key) {
        var result = super.remove(key);
        writeToDisk();
        return result;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        super.putAll(m);
        writeToDisk();
    }

    @Override
    public Map<String, String> getDelegatingMap() {
        return super.getDelegatingMap();
    }

    public void loadFromDisk() {
        try (
            FileChannel channel = FileChannel.open(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.READ,
                StandardOpenOption.WRITE
            )
        ) {
            ByteBuffer buff = ByteBuffer.allocate((int) channel.size());
            channel.read(buff);

            if (buff.array().length == 0) {
                LOGGER.info("\"{}\" contains no data", filePath);
                super.clear();
                return;
            }

            Map<String, String> deserialized = SerializationUtils.deserializeObject(buff.array());

            LOGGER.info("Loaded {} bytes from \"{}\"", buff.array().length, filePath);

            super.clear();
            super.putAll(deserialized);

        } catch (Exception e) {
            LOGGER.error("Catch the exception during load data from disk", e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    public void writeToDisk() {
        try (
            FileChannel channel = FileChannel.open(
                filePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING
            )
        ) {
            if (getDelegatingMap().isEmpty()) {
                LOGGER.info("Truncated \"{}\" as delegating map is empty", filePath);
                return;
            }

            byte[] serialized = SerializationUtils.serealizeObject(getDelegatingMap());
            ByteBuffer buffer = ByteBuffer.wrap(serialized);
            channel.write(buffer);

            LOGGER.info("Wrote {} bytes to \"{}\"", buffer.array().length, filePath);

        } catch (IOException e) {
            LOGGER.error("Caught an exception during writing data to the disk", e);
            throw new IllegalStateException(e);
        }
    }
}
