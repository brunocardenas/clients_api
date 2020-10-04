package com.clients.api.integration.controller;

import com.clients.api.MongoDbContainer;
import com.clients.api.mock.Mocks;
import com.clients.api.repository.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {ClientControllerIT.MongoDbInitializer.class})
@AutoConfigureMockMvc
public class ClientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientRepository clientRepository;

    private static MongoDbContainer mongoDbContainer;

    private MockRestServiceServer restServiceServer;

    @BeforeEach
    public void startRestServiceServer() {
        restServiceServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
    }

    @BeforeAll
    public static void startContainerAndPublicPortIsAvailable() {
        mongoDbContainer = new MongoDbContainer();
        mongoDbContainer.start();
    }

    @Test
    public void createClientOkTest() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/v1/clients")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(Mocks.getTestClients().get(0))))
                .andExpect(status().isCreated());

        assertEquals(1, clientRepository.findAll().size());
        assertEquals("Lionel", clientRepository.findAll().get(0).getName());
    }

    @Test
    public void getClientsKpiOkTest() throws Exception {
        clientRepository.saveAll(Mocks.getTestClients());

        mockMvc.perform(get("http://localhost:8080/api/v1/clientskpi")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.averageAge").value("46.0"))
                .andExpect(jsonPath("$.standardDeviation").value("13.0"));
    }

    @Test
    public void getClientsListWithProbablyDeathDateOk() throws Exception {
        clientRepository.saveAll(Mocks.getTestClients());

        Mocks.happyPathExpectedRequests(restServiceServer);

        mockMvc.perform(get("http://localhost:8080/api/v1/clientslist")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"123xyz456\",\"name\":\"Lionel\",\"surname\":\"Messi\",\"age\":33,\"birthDate\":\"1987-06-24T03:00:00.000+00:00\",\"probablyDeathDate\":\"2063-06-24T03:00:00.000+00:00\"},{\"id\":\"456xyz789\",\"name\":\"Diego\",\"surname\":\"Maradona\",\"age\":59,\"birthDate\":\"1960-10-30T03:00:00.000+00:00\",\"probablyDeathDate\":\"2040-10-30T03:00:00.000+00:00\"}]"));
    }


    public static class MongoDbInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

            TestPropertyValues values = TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongoDbContainer.getContainerIpAddress(),
                    "spring.data.mongodb.port=" + mongoDbContainer.getPort()

            );
            values.applyTo(configurableApplicationContext);
        }
    }
}
