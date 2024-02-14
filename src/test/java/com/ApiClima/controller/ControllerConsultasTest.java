package com.ApiClima.controller;

import com.ApiClima.security.jwt.TokenUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
public class ControllerConsultasTest {

    public static final String BASE_URL = "/consultas";
    MockMvc mockMvc;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    private WebApplicationContext webApplicationContext;
    String bearerToken;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        bearerToken = tokenUtils.createToken("prueba", "usuario@mail.com");
    }

    //PRUEBAS ENDPOINT "/Clima"
    
    @Test
    void getClimaTestOK() throws Exception { //OK
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void getClimaTestNoOK() throws Exception { //BAD REQUEST
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void getClimaToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 6; i++) {
            result = mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }
    
    // PRUEBAS ENDPOINT "/pronostico5dias"
    @Test
    void getPronostico5DiasTestOK() throws Exception { //OK
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void getPronostico5DiasNoOK() throws Exception { //BAD REQUEST
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void getPronostico5DiasToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 6; i++) {
            result = mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }
    
    // PRUEBAS ENDPOINT "/contaminacion"
    @Test
    void getContaminacionTestOK() throws Exception { //OK
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    void getContaminacionTestNoOK() throws Exception { //BAD REQUEST
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void getContaminacionTestToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 6; i++) {
            result = mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }
    
}
