package com.chornobuk.flashcardsapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlashcardsSetControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(value = "test")
    public void getSetByIdNonExistedSet() throws Exception {
        this.mockMvc.perform(get("/sets/set/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    //    todo: add flashcards to the test set
    @Test
    @WithMockUser(value = "test")
    public void getSetByIdSuccessful() throws Exception {
        String response = """
                {
                "id":1,
                "name":"numbers",
                "questionLanguageId":1,
                "answerLanguageId": 3,
                "description":"English numbers",
                "flashcards":[],
                "tags":[1],
                "public":false,
                "userId":1
                }
                """;
        this.mockMvc.perform(get("/sets/set/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    @WithMockUser(value = "test")
    public void getUserSetsNonExistedUser() throws Exception {
        this.mockMvc.perform(get("/sets/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }


    //        todo: add flashcards to the test sets
    @Test
    @WithMockUser(value = "test")
    public void getUserSetsSuccessful() throws Exception {
        String response = """
                [
                {
                    "id":3,
                    "name":"solar system",
                    "description":"solar system planets",
                    "flashcards":[],
                    "public":false,
                    "tags":[3,4],
                    "userId":1,
                    "questionLanguageId":1,
                    "answerLanguageId":1
                },
                {
                    "id":1,
                    "name":"numbers",
                    "description":"English numbers",
                    "flashcards":[],
                    "public":false,
                    "tags":[1],
                    "userId":1,
                    "questionLanguageId":1,
                    "answerLanguageId":3
                },
                {
                    "id":2,
                    "name":"integrals",
                    "description":"algebra",
                    "flashcards":[],
                    "public":true,
                    "tags":[2,4],
                    "userId":1,
                    "questionLanguageId":1,
                    "answerLanguageId":1
                }
                ]
                """;
        this.mockMvc.perform(get("/sets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}

