package com.trl.userservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
public class BankDataResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/createBankDataBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"bankAccountNumber\":\"999999999999999999999999\",\"dateOfExpiry\":\"09.09.2019\",\"cvi\":999,\"user\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null,\"bankData\":null,\"address\":null,\"birthday\":null}}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/create")
                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"user\": {\"id\": 1}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createBankDataBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userIsNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/create")
                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"user\": null}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Sql(value = {"/createBankDataBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userIdIsNull() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/create")
                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"user\": {\"id\": null}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Sql(value = {"/createBankDataBefore-findByUser.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByUser() throws Exception {

        final String updateBankDataResult = "[{\"id\":1,\"bankAccountNumber\":\"1111111111111111111\",\"dateOfExpiry\":\"01.01.1970\",\"cvi\":111,\"user\":null},{\"id\":2,\"bankAccountNumber\":\"2222222222222222222\",\"dateOfExpiry\":\"01.01.1970\",\"cvi\":222,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/findByUser")
                        .content("{\"id\": 1}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createBankDataBefore-findByUser.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByUser_bankDataNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/findByUser")
                        .content("{\"id\": 2}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}