package com.rovio.plushmarket.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Utility class for file operations.
 */
public class FileUtils {

    private FileUtils() {
    }
    
    public static <T> T parseString(final String path, final Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(path, cls);
    }

    public static String readFile(final String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        return Files.readString(filePath);
    }
}
