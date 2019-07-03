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

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateCountry() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Updated Country\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/country/1")
                        .content("Updated Country")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateCountry_addressIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/country/3")
                        .content("Updated Country")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateCity() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Updated City\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/city/1")
                        .content("Updated City")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateCity_addressIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/city/3")
                        .content("Updated City")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateStreet() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Updated Street\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/street/1")
                        .content("Updated Street")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateStreet_addressIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/street/3")
                        .content("Updated Street")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateHouseNumber() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"Updated House Number\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/houseNumber/1")
                        .content("Updated House Number")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateHouseNumber_addressIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/houseNumber/3")
                        .content("Updated House Number")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updatePostcode() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":0,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/postcode/1")
                        .content("{\"value\": 0}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-update.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updatePostcode_addressIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/houseNumber/3")
                        .content("{\"value\": 0}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}














































