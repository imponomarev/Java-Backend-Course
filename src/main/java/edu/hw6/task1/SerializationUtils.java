package edu.hw6.task1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public final class SerializationUtils {

    private SerializationUtils() {
    }

    public static byte[] serealizeObject(Map<String, String> object) throws IOException {
        try (
            var baos = new ByteArrayOutputStream();
            var objectOutputStream = new ObjectOutputStream(baos);
            ) {
            objectOutputStream.writeObject(object);
            return baos.toByteArray();
        }
    }

    public static <T extends Serializable> T deserializeObject(byte[] bytes)
    throws IOException, ClassNotFoundException, ClassCastException {

        try (var objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return (T) objectInputStream.readObject();
        }
    }
}
