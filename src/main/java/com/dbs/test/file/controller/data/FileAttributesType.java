package com.dbs.test.file.controller.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum  FileAttributesType {
    READ,
    WRITE,
    EXECUTE;

    public static List<FileAttributesType> supportedAttributes(final File file) {
        final List<FileAttributesType> attributesTypes = new ArrayList<>();

        if (file.canExecute())  {
            attributesTypes.add(FileAttributesType.EXECUTE);
        }

        if (file.canRead()) {
            attributesTypes.add(FileAttributesType.READ);
        }

        if (file.canWrite()) {
            attributesTypes.add(FileAttributesType.WRITE);
        }

        return attributesTypes;
    }
}
