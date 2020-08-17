package com.dbs.test.file.service;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.exception.FileDirectoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DirectoryService {

    private static final Logger log = LoggerFactory.getLogger(DirectoryService.class);

    /**
     * Parses the directory at the given path and returns data related to the directory and sub-directory.
     *
     * @param path the {@link String} path to the given file
     * @return the {@link ContentDetails} populated with given file information.
     */
    public final ContentDetails getDirectoryDetails(final String path) {

        final File requestedDirectory = new File(path);
        if (!requestedDirectory.exists() || !requestedDirectory.isDirectory()) {
            log.error("Requested path {} either does not exists or is not a directory", path);
            throw new FileDirectoryNotFoundException("Requested path does not exists or is not a directory.");
        }

        final ContentDetails rootContent = new ContentDetails(requestedDirectory.getPath(), true, requestedDirectory.length(), null, null);
        this.parseContents(requestedDirectory.listFiles(), rootContent);
        return rootContent;
    }

    /**
     * Recursive method to call check directory contents and contents of any sub-directories.
     * @param directoryContents the {@Array} current directory contents.
     * @param directoryDetails the {@link ContentDetails} current directory details.
     */
    private void parseContents(final File[] directoryContents,
                              final ContentDetails directoryDetails) {

        if (directoryContents == null || directoryContents.length <= 0) {
            log.info("Directory {} is empty", directoryDetails.getPath());
            return;
        }
        for (final File content : directoryContents) {
            if (content.isFile()) {
                final ContentDetails fileContent = new ContentDetails(content.getPath(), false, content.length(), null);
                directoryDetails.addContent(fileContent);
            } else {
                final ContentDetails folderContent = new ContentDetails(content.getPath(), true, content.length(), null, null);
                parseContents(content.listFiles(), folderContent);
                directoryDetails.addContent(folderContent);
            }
        }
    }
}
