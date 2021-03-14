package com.apzumi.rest.user;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static javafx.beans.binding.Bindings.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostController postController;


    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(postController).isNotNull();
    }

}