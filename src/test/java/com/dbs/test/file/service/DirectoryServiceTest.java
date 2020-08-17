package com.dbs.test.file.service;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.controller.data.FileAttributesType;
import com.dbs.test.file.exception.FileDirectoryNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class DirectoryServiceTest {
    private final static String CURRENT_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    DirectoryService directoryService = new DirectoryService();

    @Test
    public void shouldReturnErrorIfPathNotFound() {
        Assertions.assertThrows(FileDirectoryNotFoundException.class, () -> {
            directoryService.getDirectoryDetails(CURRENT_PATH + "xyz");
        });
    }

    @Test
    public void shouldReturnErrorIfPathIsNotADirectory() {
        Assertions.assertThrows(FileDirectoryNotFoundException.class, () -> {
            directoryService.getDirectoryDetails(CURRENT_PATH + "/pom.xml");
        });
    }

    @Test
    public void shouldReturnDirectoryDetailsRecursively() {
        ContentDetails details = directoryService.getDirectoryDetails(CURRENT_PATH + "/src");

        Assertions.assertNotNull(details);
        Assertions.assertEquals(details.getPath(), CURRENT_PATH + "/src");
        Assertions.assertTrue(details.isDirectory());
        Assertions.assertEquals(details.getContents().size(), 2);
        Assertions.assertTrue(details.getContents().stream().anyMatch(contentDetails -> contentDetails.getPath().equals(CURRENT_PATH + "/src/main")));
        Assertions.assertTrue(details.getContents().stream().anyMatch(contentDetails -> contentDetails.getPath().equals(CURRENT_PATH + "/src/test")));
    }
}
