package com.anst.sd.telegram.fw;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        SimpleModule localDateTimeSerialization = new SimpleModule();
        localDateTimeSerialization.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        localDateTimeSerialization.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(localDateTimeSerialization);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT);

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(fmt));
        }

    }

    static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DATE_FORMAT);

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
            return LocalDateTime.parse(p.getValueAsString(), fmt);
        }
    }
}