package com.dbs.test.file.service;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.controller.data.FileAttributesType;
import com.dbs.test.file.exception.FileDirectoryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    /**
     * Parses the file at the Given path and returns the full description of that file.
     * The description must include a full path and all of the attributes available.
     *
     * @param path the {@link String} path to the given file
     * @return the {@link ContentDetails} populated with given file information.
     */
    public final ContentDetails getFileDetails(final String path) {

        final File requestedFile = new File(path);
        if (!requestedFile.exists() || !requestedFile.isFile()) {
            log.error("Requested path {} either does not exists or is not a file", path);
            throw new FileDirectoryNotFoundException("Requested path does not exists or is not a valid file.");
        }

        final List<FileAttributesType> attributes = FileAttributesType.supportedAttributes(requestedFile);
        final ContentDetails fileContent = new ContentDetails(requestedFile.getPath(), false, requestedFile.length(), attributes);
        return fileContent;
    }
}
