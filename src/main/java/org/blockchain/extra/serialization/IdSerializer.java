package org.blockchain.extra.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.blockchain.entity.BaseEntity;

import java.io.IOException;

public class IdSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        BaseEntity entity = (BaseEntity) o;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(entity.getId()));
        jsonGenerator.writeEndObject();
    }
}
