package com.trl.user.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserResource_IntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = {"/createUserBefore-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void create() throws Exception {

        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":null,\"address\":null,\"birthday\":\"12.06.2019\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/create")
                        .content("{\"firstName\": \"Tsyupryk\", \"lastName\": \"Roman\", \"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\", \"password\":\"strong_password\", \"birthday\": \"12.06.2019\"}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));

    }

    @Sql(value = {"/createUserBefore-create_userWithThisEmailExist.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void create_userWithThisEmailExist() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/create")
                        .content("{\"id\":2,\"firstName\": \"Tsyupryk\", \"lastName\": \"Roman\", \"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\", \"password\":\"strong_password\", \"bankData\":[{\"bankAccountNumber\": \"1111111111111111111\", \"dateOfExpiry\": \"12.06.2019\", \"cvi\": \"111\"}, {\"bankAccountNumber\": \"2222222222222222222\", \"dateOfExpiry\": \"12.06.2019\", \"cvi\": \"222\"}], \"address\":[{\"country\": \"Spain\", \"city\": \"Madrid\", \"street\": \"Calle Madrid\", \"houseNumber\": \"1A\", \"postCode\": 111111}, {\"country\": \"Spain\", \"city\": \"Barcelona\", \"street\": \"Calle Barcelona\", \"houseNumber\": \"2A\", \"postCode\": 222222}], \"birthday\": \"12.06.2019\"}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }


    @Sql(value = {"/createUserBefore-updateFirstName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateFirstName() throws Exception {

        final String updatedUserResult = "{\"id\":1,\"firstName\":\"First Name Updated\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/firstName/1")
                        .content("First Name Updated")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-updateFirstName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateFirstName_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/firstName/2")
                        .content("First Name Updated")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-updateLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateLastName() throws Exception {

        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Last Name Updated\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/lastName/1")
                        .content("Last Name Updated")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-updateLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateLastName_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/lastName/2")
                        .content("Last Name Updated")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-updateEmail.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateEmail() throws Exception {
        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"updated.email@email.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/email/1")
                        .content("updated.email@email.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-updateEmail.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateEmail_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/email/2")
                        .content("updated.email@email.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-updateEmail.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateEmail_userWithEmailExist() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/email/1")
                        .content("tsyupryk.roman.lyubomyrovych@gmail.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is(422));
    }

    @Sql(value = {"/createUserBefore-updatePassword.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updatePassword() throws Exception {
        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"updatedPassword\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/password/1")
                        .content("updatedPassword")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-updatePassword.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updatePassword_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/password/2")
                        .content("updated.email@email.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-updateBirthday.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateBirthday() throws Exception {
        final String updatedUserResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"01.01.1970\"}";

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/birthday/1")
                        .param("birthday", "01.01.1970")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUserResult)));
    }

    @Sql(value = {"/createUserBefore-updateBirthday.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateBirthday_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(
                post("http://localhost:8081/user/update/birthday/2")
                        .param("birthday", "01.01.1970")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-delete.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void delete() throws Exception {
        final String userWithIdResult = "true";

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userWithIdResult)));
    }

    @Sql(value = {"/createUserBefore-delete-userNotHaveBankData.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void delete_UserNotHaveBankData() throws Exception {
        final String userWithIdResult = "true";

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userWithIdResult)));
    }

    @Sql(value = {"/createUserBefore-delete-userNotHaveAddress.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void delete_UserNotHaveAddress() throws Exception {
        final String userWithIdResult = "true";

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userWithIdResult)));
    }

    @Sql(value = {"/createUserBefore-delete.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void delete_verifyIfUserDeleted() throws Exception {
        final String userWithIdResult = "true";

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userWithIdResult)));

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-delete.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void delete_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete("http://localhost:8081/user/delete/4"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findById.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findById() throws Exception {
        final String userWithIdResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(get("http://localhost:8081/user/findById/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(userWithIdResult)));
    }

    @Sql(value = {"/createUserBefore-findById.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findById_userWithIdNotFound() throws Exception {

        this.mockMvc.perform(get("http://localhost:8081/user/findById/4"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findByFirstName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByFirstName() throws Exception {
        final String allUsersWithFirstNameResult = "[{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"},{\"id\":3,\"firstName\":\"Tsyupryk\",\"lastName\":\"Last Name User_3\",\"email\":\"user_3@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"02.01.1970\"}]";

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByFirstName")
                        .content("Tsyupryk")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allUsersWithFirstNameResult)));
    }

    @Sql(value = {"/createUserBefore-findByFirstName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByFirstName_usersWithFirstNameNotFound() throws Exception {

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByFirstName")
                        .content("AAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findByLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByLastName() throws Exception {
        final String allUsersWithLastNameResult = "[{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"},{\"id\":3,\"firstName\":\"First Name User_3\",\"lastName\":\"Roman\",\"email\":\"user_3@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"02.01.1970\"}]";

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByLastName")
                        .content("Roman")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allUsersWithLastNameResult)));
    }

    @Sql(value = {"/createUserBefore-findByLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByLastName_usersWithLastNameNotFound() throws Exception {

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByLastName")
                        .content("AAAAAAAAA")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findByEmail.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByEmail() throws Exception {
        final String usersWithEmailResult = "{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}";

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByEmail")
                        .content("tsyupryk.roman.lyubomyrovych@gmail.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(usersWithEmailResult)));
    }

    @Sql(value = {"/createUserBefore-findByEmail.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByEmail_userWithEmailNotFound() throws Exception {

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByEmail")
                        .content("aaaaaaaa@email.com")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findByFirstNameAndLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByFirstNameAndLastName() throws Exception {
        final String allUsersWithFirstNameAndLastNameResult = "[{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"},{\"id\":3,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"user_3@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"02.01.1970\"}]";

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByFirstNameAndLastName")
                        .content("{\"firstValue\": \"Tsyupryk\", \"secondValue\": \"Roman\"}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allUsersWithFirstNameAndLastNameResult)));
    }

    @Sql(value = {"/createUserBefore-findByFirstNameAndLastName.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByFirstNameAndLastName_usersWithFirstNameAndLastNameNotFound() throws Exception {

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByFirstNameAndLastName")
                        .content("{\"firstValue\": \"AAAAAAAAAA\", \"secondValue\": \"AAAAAAAAAA\"}")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findByBirthday.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByBirthday() throws Exception {
        final String allUsersWithBirthdayResult = "[{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"},{\"id\":3,\"firstName\":\"First Name User_3\",\"lastName\":\"Last Name User_3\",\"email\":\"user_3@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"}]";

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByBirthday")
                        .param("birthday", "26.06.1988")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allUsersWithBirthdayResult)));
    }

    @Sql(value = {"/createUserBefore-findByBirthday.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findByBirthday_usersWithBirthdayNotFound() throws Exception {

        this.mockMvc.perform(
                get("http://localhost:8081/user/findByBirthday")
                        .param("birthday", "01.01.1979")
                        .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Sql(value = {"/createUserBefore-findAll.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findAll() throws Exception {
        final String allUsersResult = "[{\"id\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman.lyubomyrovych@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"26.06.1988\"},{\"id\":2,\"firstName\":\"First Name User_2\",\"lastName\":\"Last Name User_2\",\"email\":\"user_2@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"01.01.1970\"},{\"id\":3,\"firstName\":\"First Name User_3\",\"lastName\":\"Last Name User_3\",\"email\":\"user_3@gmail.com\",\"password\":\"strong_password\",\"bankData\":[],\"address\":[],\"birthday\":\"02.01.1970\"}]";

        this.mockMvc.perform(get("http://localhost:8081/user/findAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(allUsersResult)));
    }

    @Sql(value = {"/createUserBefore-findAll_usersNotFound.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/createUserAfter.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void findAll_usersNotFound() throws Exception {

        this.mockMvc.perform(get("http://localhost:8081/user/findAll"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}