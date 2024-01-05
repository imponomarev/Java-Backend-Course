package edu.hw6;

import edu.hw6.task1.DiskMap;
import edu.hw6.task1.SerializationUtils;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertAll;

class Task1Test implements WithAssertions {

    private Path filePath;

    private DiskMap diskMap;

    @BeforeEach
    void setUp() throws IOException {

        filePath = Files.createTempFile(null, null);

        diskMap = new DiskMap(new HashMap<>() , filePath);

    }


    @Test
    void shouldPutToDelegateAndWriteToDisk() {

        String key = "key";

        String value = "value";

        diskMap.put(key, value);

        assertAll(
            () -> assertThat(diskMap.getDelegatingMap()).containsEntry(key, value),
            () -> assertFileContains(Map.of(key, value))
        );
    }

    @Test
    void shouldRemoveFromDelegateAndWriteToDisk() {

        String key = "key";

        String value = "value";

        diskMap.put(key, value);

        diskMap.remove(key);

        assertAll(
            () -> assertThat(diskMap.getDelegatingMap()).doesNotContainKey(key),
            () -> assertFileIsEmpty()
        );
    }

    @Test
    void shouldPutAllToDelegateAndFlushToDisk() {

        Map<String, String> map = Map.of("key1", "value1", "key2", "value2");

        diskMap.putAll(map);

        assertAll(
            () -> assertThat(diskMap.getDelegatingMap()).containsAllEntriesOf(map),
            () -> assertFileContains(map)
        );


    }

    @Test
    void shouldThrowExceptionWhenFileDataIsIllegal() throws IOException {
        Files.writeString(filePath, "Hello, world!");

        assertThatThrownBy(() -> diskMap.loadFromDisk())
            .isInstanceOf(RuntimeException.class);
    }

    private void assertFileContains(Map<String, String> expectedContent) {
        try {

            byte[] bytes = Files.readAllBytes(filePath);

            Map<String, String> deserializedMap = SerializationUtils.deserializeObject(bytes);

            for (Map.Entry<String, String> entry : expectedContent.entrySet()) {
                assertThat(deserializedMap).contains(entry);
            }

        } catch (Exception e) {
            fail("Failed to read file content", e);
        }
    }

    private void assertFileIsEmpty() {
        try {

            byte[] bytes = Files.readAllBytes(filePath);

            assertThat(bytes).isEmpty();

        } catch (Exception e) {
            fail("Failed to read file content", e);
        }
    }
}
