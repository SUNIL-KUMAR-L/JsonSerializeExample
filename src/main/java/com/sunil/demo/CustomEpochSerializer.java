package com.sunil.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class CustomEpochSerializer extends StdSerializer<Long> {
    public CustomEpochSerializer() {
        this(null);
    }
    public CustomEpochSerializer(Class<Long> t) {
        super(t);
    }

    @Override
    public void serialize (Long value, JsonGenerator jsonGenerator, SerializerProvider arg)
            throws IOException, JsonProcessingException {
        Instant instant = Instant.ofEpochSecond(value);
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
        jsonGenerator.writeString(zdt.toString());
    }
}
