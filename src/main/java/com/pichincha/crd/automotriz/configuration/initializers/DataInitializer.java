package com.pichincha.crd.automotriz.configuration.initializers;

import com.pichincha.crd.automotriz.exceptions.ApplicationException;
import com.pichincha.crd.automotriz.util.FileResource;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataInitializer {
    public <T> List<T> getCsvToBean(String fileName, Class<T> clazz) throws Exception {
        List<T> list = new ArrayList<>(0);
        Integer count = 0;
        Field[] headers = clazz.getDeclaredFields();
        String content = FileResource.read("data/" + fileName);
        for (String line : content.split("\r\n")){
            if (count > 0) {
                T instance = clazz.getConstructor().newInstance();
                String array[] = line.split(",");
                if(array.length < headers.length) {
                    throw new ApplicationException("The File don't have the correct number of headers, the correct is:"
                            + headers.length);
                }
                for (int i = 0; i < array.length; i++) {
                    try {
                        if (headers[i].getType().getTypeName().equals("java.lang.Long"))
                            FieldUtils.writeField(instance, headers[i].getName(), Long.valueOf(array[i]), true);
                        else
                            FieldUtils.writeField(instance, headers[i].getName(), array[i], true);
                    }catch (Exception exception) {
                        throw new ApplicationException(exception);
                    }
                }

                list.add(instance);
            }
            count++;
        }

        return list;
    }
    public void validateDuplicates(List<?> list, String fieldName) throws ApplicationException {
        Set<String> identificationSet = new HashSet<>();
        boolean hasDuplicates = list.stream()
                .map(entity -> {
                    try {
                        Field field = entity.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        return field.get(entity);
                    }catch (Exception exception) {
                        return null;
                    }
                }).anyMatch(id -> !identificationSet.add(String.valueOf(id)));
        if (hasDuplicates){
            throw new ApplicationException("Error, exists duplicates registers for class::" + list.get(0).getClass());
        }
    }
}
