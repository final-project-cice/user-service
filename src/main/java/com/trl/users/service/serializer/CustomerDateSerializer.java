package com.trl.users.service.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Slf4j
public class CustomerDateSerializer extends StdSerializer<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public CustomerDateSerializer() {
        this(null);
    }

    public CustomerDateSerializer(Class<Date> dateClass) {
        super(dateClass);
    }

    /**
     * @param value
     * @param gen
     * @param arg2
     */
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) {

        try {
            gen.writeString(dateFormat.format(value));
        } catch (IOException exception) {
            log.error("************ CustomerDateSerializer.serialize() ", exception);
        }
    }

}
