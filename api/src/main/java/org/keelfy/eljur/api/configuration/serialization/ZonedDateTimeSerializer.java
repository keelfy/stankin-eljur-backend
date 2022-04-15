package org.keelfy.eljur.api.configuration.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.Getter;

import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Getter
public class ZonedDateTimeSerializer extends com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer {

    private static final long serialVersionUID = 6759452053540357618L;

    private final ZoneId zoneId;

    public ZonedDateTimeSerializer(DateTimeFormatter formatter, ZoneId zoneId) {
        super(formatter);
        this.zoneId = zoneId;
    }

    @Override
    public void serialize(ZonedDateTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
        if (!useTimestamp(provider)) {
            final var localDateTime = value.toLocalDateTime();
            g.writeString(_formatter.format(ZonedDateTime.of(localDateTime, zoneId)));
            return;
        }
        super.serialize(value, g, provider);
    }

}
