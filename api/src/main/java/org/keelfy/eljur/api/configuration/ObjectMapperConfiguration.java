package org.keelfy.eljur.api.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.keelfy.eljur.api.configuration.serialization.BigDecimalToStringSerializer;
import org.keelfy.eljur.api.configuration.serialization.ZonedDateTimeDeserializer;
import org.keelfy.eljur.api.configuration.serialization.ZonedDateTimeSerializer;
import org.keelfy.eljur.api.util.DateTimeUtil;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Egor Kuzmin
 */
@Configuration
public class ObjectMapperConfiguration {

    private static final JsonDeserializer<Instant> INSTANT_DESERIALIZER = InstantDeserializer.INSTANT;

    private static final JsonSerializer<Instant> INSTANT_SERIALIZER = InstantSerializer.INSTANCE;

    @Bean
    Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializationInclusion(JsonInclude.Include.NON_NULL);
            builder.featuresToEnable(JsonParser.Feature.STRICT_DUPLICATE_DETECTION);
            builder.featuresToEnable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);
            builder.featuresToEnable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
            builder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            builder.modules(createUtilityModule(), createJavaTimeModule());
        };
    }

    private DateTimeFormatter createDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DateTimeUtil.ISO_INSTANT_DATE_PATTERN);
    }

    private ZoneId getIncomingZoneId() {
        return ZoneId.systemDefault();
    }

    private ZoneId getOutgoingZoneId() {
        return ZoneId.of("UTC");
    }

    private SimpleModule createJavaTimeModule() {
        final var formatter = createDateTimeFormatter();
        final var module = new JavaTimeModule();
        module.addDeserializer(Instant.class, INSTANT_DESERIALIZER);
        module.addSerializer(Instant.class, INSTANT_SERIALIZER);
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        module.addDeserializer(ZonedDateTime.class, new ZonedDateTimeDeserializer(formatter, getIncomingZoneId()));
        module.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer(formatter, getOutgoingZoneId()));
        return module;
    }

    private SimpleModule createUtilityModule() {
        final var module = new SimpleModule();
        module.addSerializer(BigDecimal.class, new BigDecimalToStringSerializer());
        return module;
    }

}
