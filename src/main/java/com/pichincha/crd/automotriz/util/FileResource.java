package com.pichincha.crd.automotriz.util;


import com.pichincha.crd.automotriz.exceptions.ApplicationException;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class FileResource {
    public static String read(String path) throws ApplicationException{
        try {
            InputStream fis = FileResource.class.getClassLoader().getResourceAsStream(path);
            String file = IOUtils.toString(fis);
            return file;
        } catch (Exception exception) {
            throw new ApplicationException(exception);
        }
    }
}
