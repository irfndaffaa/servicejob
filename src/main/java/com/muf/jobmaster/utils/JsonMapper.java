package com.muf.jobmaster.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonMapper {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectWriter WRITER = new ObjectMapper().writer();
    public static final ObjectReader READER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .reader();
}
