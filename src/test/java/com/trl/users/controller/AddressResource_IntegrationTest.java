package com.trl.users.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.yml")
public class AddressResource_IntegrationTest {


    @Test
    public void updateAddress() {

        // TODO: Terminar este test.
        // TODO: Pensar como resolver la situacion si hace falta borrar datos de address o cambiar
        // TODO : Hau que hacer un methodo ....
        // TDOD: Pensar en estas situaciones que ocurren con varios values

    }

    @Test
    public void updateAddress_userWithIdNotFound() {

        // TODO: Terminar este test.

    }

    @Test
    public void findByAddress() throws Exception {

        String allUsersWithAddressResult = "1";

        // TODO: Resolver esta Exception.
//        this.mockMvc.perform(
//                get("http://localhost:8081/user/findByAddress")
//                        .content("{\"country\": \"Spain\", \"city\": \"Madrid\", \"street\": \"Calle Madrid\", \"houseNumber\": \"1A\", \"postCode\": 111111}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString(user)));
    }

    @Test
    public void findByAddress_usersWithAddressNotFound() throws Exception {

        // TODO: Resolver esta Exception.
//        this.mockMvc.perform(
//                get("http://localhost:8081/user/findByAddress")
//                        .content("{\"country\": \"AAA\", \"city\": \"BBB\", \"street\": \"CCC\", \"houseNumber\": \"1Q1Q\", \"postCode\": 100000000}")
//                        .contentType("application/json;charset=UTF-8"))
//                .andDo(print())
//                .andExpect(status().isNotFound());
    }

}