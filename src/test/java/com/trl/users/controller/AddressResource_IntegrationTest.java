package com.trl.users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.yml")
public class AddressResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/createAddressBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Cuenca\",\"street\":\"Calle_1\",\"houseNumber\":\"1B\",\"postcode\":1111,\"user\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null,\"bankData\":null,\"address\":null,\"birthday\":null}}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/create")
                        .content("{\"country\": \"Spain\", \"city\": \"Cuenca\", \"street\": \"Calle_1\", \"houseNumber\": \"1B\", \"postcode\": 1111, \"user\": {\"id\": 1}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userIsNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/create")
                        .content("{\"country\": \"Spain\", \"city\": \"Cuenca\", \"street\": \"Calle_1\", \"houseNumber\": \"1B\", \"postcode\": 1111, \"user\": null}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Sql(value = {"/createAddressBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userIdIsNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/create")
                        .content("{\"country\": \"Spain\", \"city\": \"Cuenca\", \"street\": \"Calle_1\", \"houseNumber\": \"1B\", \"postcode\": 1111, \"user\": {\"id\": null}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }

}