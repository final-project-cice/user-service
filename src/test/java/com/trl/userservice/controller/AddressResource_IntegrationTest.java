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
public class AddressResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/createAddressBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create() throws Exception {

        final String createdAddressResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Cuenca\",\"street\":\"Calle_1\",\"houseNumber\":\"1B\",\"postcode\":1111,\"user\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null,\"bankData\":null,\"address\":null,\"birthday\":null}}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/create")
                        .content("{\"country\": \"Spain\", \"city\": \"Cuenca\", \"street\": \"Calle_1\", \"houseNumber\": \"1B\", \"postcode\": 1111, \"user\": {\"id\": 1}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(createdAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userWithIdNotExist() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/create")
                        .content("{\"country\": \"Spain\", \"city\": \"Cuenca\", \"street\": \"Calle_1\", \"houseNumber\": \"1B\", \"postcode\": 1111, \"user\": {\"id\": 2}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
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

        final String updateAddressResult = "{\"id\":1,\"country\":\"Updated Country\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/country/1")
                        .content("Updated Country")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateAddressResult)));
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

        final String updateAddressResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Updated City\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/city/1")
                        .content("Updated City")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateAddressResult)));
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

        final String updateAddressResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Updated Street\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/street/1")
                        .content("Updated Street")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateAddressResult)));
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

        final String updateAddressResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"Updated House Number\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/houseNumber/1")
                        .content("Updated House Number")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateAddressResult)));
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

        final String updateAddressResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":0,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/update/postcode/1")
                        .content("{\"value\": 0}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateAddressResult)));
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

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findById() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findById/1")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findById_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findById/30")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByCountry() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null},{\"id\":6,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"StreetBarcelona\",\"houseNumber\":\"6A\",\"postcode\":666666,\"user\":null},{\"id\":7,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByCountry")
                        .content("Spain")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByCountry_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByCountry")
                        .content("AAAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByCity() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null},{\"id\":7,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByCity")
                        .content("Madrid")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByCity_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByCity")
                        .content("AAAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByStreet() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null},{\"id\":7,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByStreet")
                        .content("StreetMadrid")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByStreet_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByCity")
                        .content("AAAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByHouseNumber() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null},{\"id\":7,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByHouseNumber")
                        .content("1A")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByHouseNumber_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByHouseNumber")
                        .content("AAAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByPostcode() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null},{\"id\":7,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":null}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByPostcode")
                        .content("{\"value\": 111111}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByPostcode_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByPostcode")
                        .content("{\"value\": 0}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByUser() throws Exception {

        final String findAddressResult = "[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"StreetMadrid\",\"houseNumber\":\"1A\",\"postcode\":111111,\"user\":{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}},{\"id\":2,\"country\":\"United Kingdom\",\"city\":\"London\",\"street\":\"StreetLondon\",\"houseNumber\":\"2A\",\"postcode\":222222,\"user\":{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}}]";

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByUser")
                        .content("{\"id\": 1}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(findAddressResult)));
    }

    @Sql(value = {"/createAddressBefore-findBy.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createAddressAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByUser_addressNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/address/findByUser")
                        .content("{\"id\": 100}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}