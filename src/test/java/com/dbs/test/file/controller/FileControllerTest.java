package com.dbs.test.file.controller;

import com.dbs.test.file.service.FileService;
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
@ContextConfiguration(classes = {FileController.class, FileService.class})
@WebMvcTest
class FileControllerTest {
    private final static String CURRENT_PATH = Paths.get(".").toAbsolutePath().normalize().toString();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileController fileController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(fileController)
                .setControllerAdvice(new DBSFileControllerAdvice())
                .build();
    }

    @Test
    public void shouldGetFileDetails() throws Exception {
        String filePath = CURRENT_PATH + "/pom.xml";
        String apiPath = "/v1/file?path=" + filePath;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(apiPath)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.path").value(filePath))
                .andExpect(MockMvcResultMatchers.jsonPath("$.directory").value(false))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);
    }

    @Test
    public void shouldReturnErrorIfFilePathNotProvided() throws Exception {
        String invalidFilePath = CURRENT_PATH ;
        String apiPath = "/v1/file?path=" + invalidFilePath;
        String expectedMessage = "Requested path does not exists or is not a valid file.";
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
