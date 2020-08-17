package com.dbs.test.file.controller;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FileService fileService;

    /**
     * Arg-Constructor
     * @param fileService the {@link FileService} to set.
     */
    @Autowired
    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Endpoint for retrieving details of the file for the given path.
     * @param path the {@link String} path to file for which details are requested.
     * @return the populate {@link ContentDetails} with file details.
     */
    @GetMapping
    public ResponseEntity<ContentDetails> getFileDetails(@RequestParam final  String path) {

        logger.info("File details requested for {}", path);
        final ContentDetails fileDetails = fileService.getFileDetails(path);
        return ResponseEntity.ok(fileDetails);
    }
}
