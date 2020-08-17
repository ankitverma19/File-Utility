package com.dbs.test.file.exception;

public class FileDirectoryNotFoundException extends  RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileDirectoryNotFoundException(String message) {
        super(message);
    }
}
