package com.flashcardsapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    public void getUserTagNonExistedUser() throws Exception {
        this.mockMvc.perform(get("/tags/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @WithMockUser(value = "test")
    @Test
    public void getUserTagsSuccessful() throws Exception {
        String response = """
                [
                    {
                        "id": 1,
                        "name": "english",
                        "colorId": 1,
                        "userId": 1
                    },
                    {
                        "id": 2,
                        "name": "math",
                        "colorId": 2,
                        "userId": 1
                    },
                    {
                        "id": 3,
                        "name": "space",
                        "colorId": 1,
                        "userId": 1
                    },
                    {
                        "id": 4,
                        "name": "science",
                        "colorId": 2,
                        "userId": 1
                    }
                ]""";
        this.mockMvc.perform(get("/tags/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @WithMockUser(value = "test")
    @Test
    public void getTagByIdNonExistedTag() throws Exception {
        this.mockMvc.perform(get("/tags/tag/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @WithMockUser(value = "test")
    @Test
    public void getTagByIdSuccessful() throws Exception {
        String response = """
                {
                    "id":1,
                    "name":"english",
                    "colorId":1,
                    "userId":1
                }
                """;
        this.mockMvc.perform(get("/tags/tag/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @WithMockUser(value = "test")
    @Test
    public void deleteTag() throws Exception {
        this.mockMvc.perform(delete("/tags/5"))
                .andExpect(status().isOk());
        this.mockMvc.perform(get("/tags/tag/5"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @WithMockUser(value = "test")
    @Test
    public void addNewTagWrongColorId() throws Exception {
        String body = """
                {
                    "name" : "test",
                    "colorId" : 0
                }
                """;
        this.mockMvc.perform(post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(status().isBadRequest());
    }

    @WithMockUser(value = "test")
    @Test
    public void addNewTagEmptyName() throws Exception {
        String body = """
                {
                    "colorId" : 1
                }
                """;
        this.mockMvc.perform(post("/tags")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(status().isBadRequest());
    }
//    todo: this how to do test with jwt
//      because it's impossible to test successful case when request doesn't have jwt


    @WithMockUser(value = "test")
    @Test
    public void updateTag() throws Exception {
        String updatedTag = """
                 {
                         "id": 4,
                         "name": "rockets",
                         "colorId": 2,
                         "userId": 1
                 }
                 """;
        this.mockMvc.perform(put("/tags")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTag)
                ).agrndExpect(status().isOk())
                .andExpect(content().json(updatedTag));
    }

    @WithMockUser(value = "test")
    @Test
    public void testNestedEntityChanges() {

    }
}