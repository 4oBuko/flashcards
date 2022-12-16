package com.flashcardsapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LanguagesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllLanguagesSuccessful() throws Exception{
        String response = """
                [
                    {"id":1,"name":"English"},
                    {"id":2,"name":"Spanish"},
                    {"id":3,"name":"German"}
                ]
                """;
        this.mockMvc.perform(get("/languages"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}

