package dom.lot.backend.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading and writing JSON data to file using Jackson.
 * Designed for generic lists of any model type.
 */
public class JsonDataAccess {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    public static <T> List<T> loadData(String filePath, TypeReference<List<T>> typeReference) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load data from file: " + filePath, e);
        }

    }

    public static <T> void saveData(String filePath, List<T> data) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save data to file: " + filePath, e);
        }
    }
}
