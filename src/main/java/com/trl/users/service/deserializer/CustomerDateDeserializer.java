package com.trl.users.service.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
@Slf4j
public class CustomerDateDeserializer extends JsonDeserializer<Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyy");

    /**
     * @param paramJsonParser
     * @param paramDeserializationContext
     * @return
     */
    @Override
    public Date deserialize(JsonParser paramJsonParser, DeserializationContext paramDeserializationContext) {

        String str = null;

        try {
            str = paramJsonParser.getText().trim();
            return dateFormat.parse(str);
        } catch (IOException | ParseException exception) {
            log.error("************ CustomerDateDeserializer.deserialize() ", exception);
        }

        return paramDeserializationContext.parseDate(str);
    }

}
