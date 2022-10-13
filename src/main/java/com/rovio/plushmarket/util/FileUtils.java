package com.rovio.plushmarket.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Utility class for file operations. 
 */
public class FileUtils {
    
    public static <T> T parseFile(final String path, final Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), cls);
    }

    public static String readFile(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        String content = Files.readString(filePath);
        return content;
    }
}
