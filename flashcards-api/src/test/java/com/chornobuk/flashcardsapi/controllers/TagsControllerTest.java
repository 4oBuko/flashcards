package com.chornobuk.flashcardsapi.controllers;

import com.chornobuk.flashcardsapi.entities.Tag;
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
public class TagsControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @WithMockUser(value = "test")
    @Test
    public void getTagByIdWithNonExistedUser() throws Exception {
        this.mockMvc.perform(get("/tags/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @WithMockUser(value = "test")
    @Test
    public void getTagByIdSuccessful() throws Exception {
        String response = """
                [
                    {
                        "id": 1,
                        "user": 1,
                        "name": "english",
                        "sets": [],
                        "color": null
                    },
                    {
                        "id": 2,
                        "user": 1,
                        "name": "math",
                        "sets": [],
                        "color": null
                    },
                    {
                        "id": 3,
                        "user": 1,
                        "name": "space",
                        "sets": [],
                        "color": null
                    }
                ]""";
        this.mockMvc.perform(get("/tags/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
