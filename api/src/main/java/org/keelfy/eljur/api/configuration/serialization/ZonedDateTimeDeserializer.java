package org.keelfy.eljur.api.configuration.serialization;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author Egor Kuzmin
 */
public class ZonedDateTimeDeserializer extends InstantDeserializer<ZonedDateTime> {

    private static final long serialVersionUID = -6970652093161975546L;

    public ZonedDateTimeDeserializer(DateTimeFormatter formatter, ZoneId incomingZoneId) {
        this(ZonedDateTime.class,
                formatter,
                temporal -> {
                    final var localDateTime = LocalDateTime.from(temporal);
                    return ZonedDateTime.of(localDateTime, incomingZoneId);
                },
                a -> ZonedDateTime.ofInstant(Instant.ofEpochMilli(a.value), a.zoneId),
                a -> ZonedDateTime.ofInstant(Instant.ofEpochSecond(a.integer, a.fraction), a.zoneId),
                ZonedDateTime::withZoneSameInstant,
                false);
    }

    protected ZonedDateTimeDeserializer(Class<ZonedDateTime> supportedType,
                                        DateTimeFormatter formatter,
                                        Function<TemporalAccessor, ZonedDateTime> parsedToValue,
                                        Function<FromIntegerArguments, ZonedDateTime> fromMilliseconds,
                                        Function<FromDecimalArguments, ZonedDateTime> fromNanoseconds,
                                        BiFunction<ZonedDateTime, ZoneId, ZonedDateTime> adjust,
                                        boolean replaceZeroOffsetAsZ) {

        super(supportedType, formatter, parsedToValue, fromMilliseconds, fromNanoseconds, adjust, replaceZeroOffsetAsZ);
    }

}
