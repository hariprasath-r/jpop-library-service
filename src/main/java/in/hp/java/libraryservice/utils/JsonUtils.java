package in.hp.java.libraryservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonUtils {

    private static ObjectMapper mapper;

    private JsonUtils() {
    }

    public static ObjectMapper getMapper() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
        }
        return mapper;
    }
}
