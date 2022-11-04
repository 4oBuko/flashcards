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
                "flashcards":[
                    {"id":9,"question":"1","answer":"one"},
                    {"id":10,"question":"2","answer":"one"},
                    {"id":11,"question":"3","answer":"three"},
                    {"id":12,"question":"4","answer":"four"},
                    {"id":13,"question":"5","answer":"five"}
                    ],
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


    @Test
    @WithMockUser(value = "test")
    public void getUserSetsSuccessful() throws Exception {
        String response = """
                [
                {
                    "id":3,
                    "name":"solar system",
                    "description":"solar system planets",
                    "flashcards":[
                    {"id":1,"question":"1st planet","answer":"Mercury"},
                    {"id":2,"question":"2nd planet","answer":"Venus"},
                    {"id":3,"question":"3rd planet","answer":"Earth"},
                    {"id":4,"question":"4th planet","answer":"Mars"},
                    {"id":5,"question":"5th planet","answer":"Jupiter"},
                    {"id":6,"question":"6th planet","answer":"Saturn"},
                    {"id":7,"question":"7th planet","answer":"Uranus"},
                    {"id":8,"question":"8th planet","answer":"Neptune"}
                    ],
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
                    "flashcards":[
                    {"id":9,"question":"1","answer":"one"},
                    {"id":10,"question":"2","answer":"one"},
                    {"id":11,"question":"3","answer":"three"},
                    {"id":12,"question":"4","answer":"four"},
                    {"id":13,"question":"5","answer":"five"}
                    ],
                    "public":false,
                    "tags":[1],
                    "userId":1,
                    "questionLanguageId":1,
                    "answerLanguageId":3
                },
                {
                    "id":2,
                    "name":"cubes",
                    "description":"algebra",
                    "flashcards":[{"id":14,"question":"3^3","answer":"27"},{"id":15,"question":"4^3","answer":"64"},{"id":16,"question":"5^3","answer":"125"}],
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

