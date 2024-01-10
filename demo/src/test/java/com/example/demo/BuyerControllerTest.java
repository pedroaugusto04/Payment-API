package com.example.demo;

import com.google.gson.Gson;
import com.pedro.wirecard.domain.model.Buyer;
import com.pedro.wirecard.domain.service.IBuyerService;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class BuyerControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IBuyerService buyerService;
    
    private Buyer buyer;


    @Before
    public void setUp() {
        this.buyer = new Buyer("Pedro", "pedro123@gmail.com", "123456");
    }

    @Test
    public void testSaveBuyer() throws Exception {
        Gson gson = new Gson();
        when(buyerService.saveBuyer(buyer)).thenReturn(buyer);
        
        mockMvc.perform(post("/buyers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(buyer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buyerCpf", is(buyer.getCpf())))
                .andExpect(jsonPath("$.buyerName", is(buyer.getName())))
                .andExpect(jsonPath("$.buyerEmail", is(buyer.getEmail())));

        verify(buyerService, times(1)).saveBuyer(buyer);
    }
}
