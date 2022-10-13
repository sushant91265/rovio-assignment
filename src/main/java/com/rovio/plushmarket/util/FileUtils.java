package com.rovio.plushmarket.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Utility class for file operations. 
 */
public class FileUtils {
    
    public static <T> T parseFile(final String path, final Class<T> cls) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), cls);
    }
}
