package com.ApiClima.controller;

import com.ApiClima.audit.repository.AuditoriaRepository;
import com.ApiClima.security.jwt.TokenUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ConsultasControllerTest {

    int i = 0;
    public static final String BASE_URL = "/consultas";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    String bearerToken;
    private TokenUtils tokenUtils;
    @MockBean
    private AuditoriaRepository auditoriaRepository;

    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        bearerToken = tokenUtils.createToken("prueba", "usuario@mail.com");
        i++;
        System.out.println("i = " + i);
    }

    //PRUEBAS ENDPOINT "/Clima"
    @Test
    @Order(1)
            @WithMockUser
    void getClimaTestOK() throws Exception { //OK
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(2)
    void getClimaTestNoOK() throws Exception { //BAD REQUEST
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(7)
    void getClimaToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 10; i++) {
            result = this.mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/clima/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }

    // PRUEBAS ENDPOINT "/pronostico5dias"
    @Test
    @Order(3)
    void getPronostico5DiasTestOK() throws Exception { //OK
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(4)
    void getPronostico5DiasNoOK() throws Exception { //BAD REQUEST
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(8)
    void getPronostico5DiasToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 10; i++) {
            result = this.mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/pronostico5dias/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }

    // PRUEBAS ENDPOINT "/contaminacion"
    @Test
    @Order(5)
    void getContaminacionTestOK() throws Exception { //OK
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("bogota"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    @Order(6)
    void getContaminacionTestNoOK() throws Exception { //BAD REQUEST
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("jajajaj"))
                        .header("Authorization", "Bearer " + bearerToken)
        ).andReturn();
        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    @Order(9)
    void getContaminacionTestToMannyRequest() throws Exception { //TO MANNY REQUEST
        MvcResult result = null;
        for (int i = 0; i < 10; i++) {
            result = this.mockMvc.perform(
                    MockMvcRequestBuilders.get(BASE_URL.concat("/contaminacion/").concat("redbank"))
                            .header("Authorization", "Bearer " + bearerToken)
            ).andReturn();
        }
        assertEquals(429, result.getResponse().getStatus());
    }

}
