package com.dbs.test.file.controller;

import com.dbs.test.file.controller.data.ContentDetails;
import com.dbs.test.file.service.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dir")
public class DirectoryController {

    private static final Logger logger = LoggerFactory.getLogger(DirectoryController.class);
    private final DirectoryService directoryService;

    /**
     * Arg-Constructor
     * @param directoryService the {@link DirectoryService} to set.
     */
    @Autowired
    public DirectoryController(final DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    /**
     * Endpoint for retrieving details of the directory for the given path.
     * @param path the {@link String} path to directory for which details are requested..
     * @return the populate {@link ContentDetails} with directory details.
     */
    @GetMapping
    public ResponseEntity<ContentDetails> getDirectoryContents(@RequestParam final  String path) {

        logger.info("Directory details requested for {}", path);
        final ContentDetails directoryDetails = directoryService.getDirectoryDetails(path);
        return ResponseEntity.ok(directoryDetails);
    }
}
