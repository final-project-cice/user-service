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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.yml")
public class BankDataResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Sql(value = {"/createBankDataBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createBankDataAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create() throws Exception {

        final String updateBankDataResult = "{\"id\":1,\"bankAccountNumber\":\"999999999999999999999999\",\"dateOfExpiry\":\"09.09.2019\",\"cvi\":999,\"userId\":{\"id\":1,\"firstName\":null,\"lastName\":null,\"email\":null,\"password\":null,\"bankData\":null,\"address\":null,\"birthday\":null}}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/bankData/create")
                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"userId\": {\"id\": 1}}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updateBankDataResult)));
    }


    /*

    .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"userId\": {\"id\": 0}}")
//                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"userId\": {\"id\": null}}")
//                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999, \"userId\": null}")

comprobar metodo create

    */






























    // TODO: Terminar este test.
    // TODO: Pensar como resolver la situacion si hace falta borrar datos de banco o cambiar
    // TODO : Hau que hacer un methodo ....
    // TDOD: Pensar en estas situaciones que ocurren con varios values

    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {
        // TODO: Terminar este test.
//        final String updatedUserResult = "????????";
//
//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/add/oneValue/1")
//                        .content("{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Test
    public void add_userWithIdNotFound() {
        // TODO: Terminar este test.
    }

    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateOneValue() throws Exception {
//        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"id\":1,\"bankAccountNumber\":\"999999999999999999999999\",\"dateOfExpiry\":\"09.09.2019\",\"cvi\":999},{\"id\":2,\"bankAccountNumber\":\"2222222222222222222\",\"dateOfExpiry\":\"01.01.1970\",\"cvi\":222}],\"address\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postCode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2A\",\"postCode\":222222}],\"birthday\":\"26.06.1988\"}";
//
//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/update/oneValue/1")
//                        .content("{\"firstValue\": \"1\", \"secondValue\": {\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateOneValue_userWithIdUserNotFound() throws Exception {

//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/update/oneValue/2")
//                        .content("{\"firstValue\": \"1\", \"secondValue\": {\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateOneValue_bankDataWithIdBankDataNotFound() throws Exception {

//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/update/oneValue/1")
//                        .content("{\"firstValue\": \"3\", \"secondValue\": {\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateAllValues() throws Exception {

        // TODO: Si arrancar solo este test, base de datos devuelve los object con id que estan en JSON de abajo. No lo se porque occure esto. De momento dejo asi.
//        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"id\":1,\"bankAccountNumber\":\"999999999999999999999999\",\"dateOfExpiry\":\"09.09.2019\",\"cvi\":999},{\"id\":3,\"bankAccountNumber\":\"8888888888888888888888888\",\"dateOfExpiry\":\"08.08.2018\",\"cvi\":888},{\"id\":2,\"bankAccountNumber\":\"777777777777777777777777\",\"dateOfExpiry\":\"07.07.2017\",\"cvi\":777}],\"address\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postCode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2A\",\"postCode\":222222}],\"birthday\":\"26.06.1988\"}";
        // TODO: Si arrancar todos los test, base de datos devuelve los object con id que estan en JSON de abajo. No lo se porque occure esto. De momento dejo asi.
//        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"id\":11,\"bankAccountNumber\":\"999999999999999999999999\",\"dateOfExpiry\":\"09.09.2019\",\"cvi\":999},{\"id\":13,\"bankAccountNumber\":\"8888888888888888888888888\",\"dateOfExpiry\":\"08.08.2018\",\"cvi\":888},{\"id\":12,\"bankAccountNumber\":\"777777777777777777777777\",\"dateOfExpiry\":\"07.07.2017\",\"cvi\":777}],\"address\":[{\"id\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postCode\":111111},{\"id\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2A\",\"postCode\":222222}],\"birthday\":\"26.06.1988\"}";

//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/update/allValues/1")
//                        .content("{\"value\": [{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}, {\"bankAccountNumber\": \"8888888888888888888888888\", \"dateOfExpiry\": \"08.08.2018\", \"cvi\": 888}, {\"bankAccountNumber\": \"777777777777777777777777\", \"dateOfExpiry\": \"07.07.2017\", \"cvi\": 777}]}")
//                        .content("{\"value\": [{\"id\": \"1\",\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}, {\"id\": \"2\",\"bankAccountNumber\": \"8888888888888888888888888\", \"dateOfExpiry\": \"08.08.2018\", \"cvi\": 888}, {\"id\": \"3\",\"bankAccountNumber\": \"777777777777777777777777\", \"dateOfExpiry\": \"07.07.2017\", \"cvi\": 777}]}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(updatedUserResult)));
    }


    @Sql(value = {"/createUserBefore-bankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateAllValues_userWithIdUserNotFound() throws Exception {

//        this.mockMvc.perform(
//                post("http://localhost:8081/user/bankData/update/allValues/2")
//                        .content("{\"value\": [{\"bankAccountNumber\": \"999999999999999999999999\", \"dateOfExpiry\": \"09.09.2019\", \"cvi\": 999}, {\"bankAccountNumber\": \"8888888888888888888888888\", \"dateOfExpiry\": \"08.08.2018\", \"cvi\": 888}, {\"bankAccountNumber\": \"777777777777777777777777\", \"dateOfExpiry\": \"07.07.2017\", \"cvi\": 777}]}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteOnValue() {
        // TODO: Terminar este test.
    }

    @Test
    public void deleteOnValue_userWithIdNotFound() {
        // TODO: Terminar este test.
    }

    @Test
    public void deleteOnValue_bankDataDataWithIdNotFound() {
        // TODO: Terminar este test.
    }

    @Test
    public void deleteAllValues() {
        // TODO: Terminar este test.
    }

    @Test
    public void deleteAllValues_userWithIdNotFound() {
        // TODO: Terminar este test.
    }

}