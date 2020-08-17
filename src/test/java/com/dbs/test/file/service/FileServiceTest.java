package com.dbs.test.file.service;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.controller.data.FileAttributesType;
import com.dbs.test.file.exception.FileDirectoryNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class FileServiceTest {
    private final static String CURRENT_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    FileService fileService = new FileService();

    @Test
    public void shouldReturnErrorIfFilePathNotFound() {
        Assertions.assertThrows(FileDirectoryNotFoundException.class, () -> {
            fileService.getFileDetails(CURRENT_PATH + "xyz");
        });
    }

    @Test
    public void shouldReturnErrorIfPathIsNotAFile() {
        Assertions.assertThrows(FileDirectoryNotFoundException.class, () -> {
            fileService.getFileDetails(CURRENT_PATH);
        });
    }

    @Test
    public void shouldReturnFileDetails() {
        ContentDetails details = fileService.getFileDetails(CURRENT_PATH + "/pom.xml");

        Assertions.assertNotNull(details);
        Assertions.assertEquals(details.getPath(), CURRENT_PATH + "/pom.xml");
        Assertions.assertFalse(details.isDirectory());
        Assertions.assertEquals(details.getAttributes().size(), 2);
        Assertions.assertTrue(details.getAttributes().contains(FileAttributesType.READ));
        Assertions.assertTrue(details.getAttributes().contains(FileAttributesType.WRITE));
    }
}
