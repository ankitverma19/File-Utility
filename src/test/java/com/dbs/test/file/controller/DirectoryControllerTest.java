package com.dbs.test.file.controller;

import com.dbs.test.file.service.DirectoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {DirectoryController.class, DirectoryService.class})
@WebMvcTest
class DirectoryControllerTest {
    private final static String CURRENT_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DirectoryController directoryController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(directoryController)
                .setControllerAdvice(new DBSFileControllerAdvice())
                .build();
    }

    @Test
    public void shouldGetDirectoryDetails() throws Exception {
        String dirPath = CURRENT_PATH + "/src";
        String apiPath = "/v1/dir?path=" + dirPath;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(apiPath)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value(dirPath))
                .andExpect(MockMvcResultMatchers.jsonPath("$.directory").value(true))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    public void shouldReturnErrorIfFilePathNotProvided() throws Exception {
        String invalidDirPath = CURRENT_PATH + "/src/pom.xml";
        String apiPath = "/v1/dir?path=" + invalidDirPath;
        String expectedMessage = "Requested path does not exists or is not a directory.";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(apiPath)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(expectedMessage))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);
    }
}
