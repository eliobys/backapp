package com.example.demo.services;

import com.example.demo.DemoApplication;
import com.example.demo.model.Consulta;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class ServiceHacedorTest {
    @Autowired
    private MockMvc mvc;
    ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, false);
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

    @Test
    public void whenAskingforUserItShouldReturnInfo() throws Exception {
        Consulta req = new Consulta(
                null,
                null,
                "1",
                null,
                null,
                null,
                "cristain@23gmail.com",
                null,
                null,
                null,
                null,
                null,
                null,
                null );
        mvc.perform(post("/hacedor/consultar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ow.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id", is(1)));
    }
}
