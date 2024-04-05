package com.ll.shop.mock;

import com.ll.shop.controller.TestController;
import com.ll.shop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestController testController;

    @MockBean
    private MemberService memberService;

    @Test
    public void 테스트() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("This will return posts's URI."));
    }
}
