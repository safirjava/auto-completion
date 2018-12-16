package com.safir.autocompletion.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class WordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testInsertWord()  throws Exception {
        mockMvc.perform (post ("/add_word")
                .content ("kolkata")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));

    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testSuggestionReturnsEmptyBody() throws Exception{
        mockMvc.perform(get("/suggest_cities")
                .param("start", "ko")
                .param("atmost", "10")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testSuggestionReturnsCaseInsensitiveSearch() throws Exception{
        mockMvc.perform (post ("/add_word")
                .content ("kolkata")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));

        mockMvc.perform(get("/suggest_cities")
                .param("start", "KO")
                .param("atmost", "5")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect (content ().contentType ("text/plain;charset=UTF-8"))
                .andExpect(content().string("kolkata\n"));

        mockMvc.perform(get("/suggest_cities")
                .param("start", "ko")
                .param("atmost", "5")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect (content ().contentType ("text/plain;charset=UTF-8"))
                .andExpect(content().string("kolkata\n"));

    }


    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testSuggestionReturnsWithLimit() throws Exception{
        mockMvc.perform (post ("/add_word")
                .content ("kolkata")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));
        mockMvc.perform (post ("/add_word")
                .content ("kota")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));


        mockMvc.perform(get("/suggest_cities")
                .param("start", "ko")
                .param("atmost", "1")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect (content ().contentType ("text/plain;charset=UTF-8"))
                .andExpect(content().string("kota\n"));

    }

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    public void testSuggestionReturnsWithLimitZero() throws Exception{
        mockMvc.perform (post ("/add_word")
                .content ("kolkata")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));
        mockMvc.perform (post ("/add_word")
                .content ("kota")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect (status().isOk())
                .andExpect (content().string ("true"));


        mockMvc.perform(get("/suggest_cities")
                .param("start", "ko")
                .param("atmost", "0")
                .contentType (MediaType.TEXT_PLAIN_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }
}
