package com.ninuxgithub.dataclientfeign;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataClientFeignApplication.class)
public class DataClientFeignApplicationTests {

    private MockMvc mvc;

    @Autowired
    WebApplicationContext wac;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void contextLoads() {
        findProductByID();
    }

    /**
     * 查询产品记录
     */
    private void findProductByID() {
        try {
            String result = mvc.perform(MockMvcRequestBuilders
                    .get("/api/findProductById")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .param("id", "8a81b3e06759b05a016759b207560000"))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
