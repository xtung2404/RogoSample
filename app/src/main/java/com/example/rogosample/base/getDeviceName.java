package com.example.rogosample.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import rogo.iot.module.rogocore.basesdk.define.IoTManufacturer;

public class getDeviceName {
    public static HashMap<String, Integer> getClassInfo() throws ClassNotFoundException, IllegalAccessException {
        HashMap<String, Integer> fieldMap = new HashMap<>();
        Class<?> aClazz = Class.forName("rogo.iot.module.rogocore.basesdk.define.IoTManufacturer");
        Field[] fields = aClazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            IoTManufacturer manufacturer = new IoTManufacturer();
            Integer fieldValue = (Integer) field.get(manufacturer);

            fieldMap.put(field.getName(), fieldValue);
        }
        return fieldMap;
    }
}
