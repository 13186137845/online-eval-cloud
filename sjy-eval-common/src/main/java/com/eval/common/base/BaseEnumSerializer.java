package com.eval.common.base;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @Description:
 * @Author: youxun
 * @Version: 1.0
 **/
public class BaseEnumSerializer extends StdSerializer<BaseEnum> {
    public BaseEnumSerializer() {
        super(BaseEnum.class);
    }
    public BaseEnumSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(BaseEnum value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("status");
        gen.writeNumber(value.status);
        gen.writeFieldName("msg");
        gen.writeString(value.msg);
        gen.writeEndObject();
    }
}
