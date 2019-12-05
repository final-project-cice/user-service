package com.trl.userservice.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class User_IntegrationTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-docs");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private static final String BASE_URL = "http://localhost:8081";

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults()
                        .withResponseDefaults(prettyPrint()))
                .build();
    }


    @Sql(value = {"/User_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users\"},\"getById\":{\"href\":\"http://localhost:8080/users/1\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/add",
                        requestFields(
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User.")
                        ),
                        responseFields(
                                fieldWithPath("userId").description("The id of the User."),
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User."),
                                fieldWithPath("_links.self.href").description("Link to add USER."),
                                fieldWithPath("_links.getById.href").description("Link to get the USER by ID."),
                                fieldWithPath("_links.getPageOfUsers.href").description("Link to get page of USERS."),
                                fieldWithPath("_links.getPageOfUsers.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedUsers.href").description("Link to get page of sorted USERS."),
                                fieldWithPath("_links.getPageOfSortedUsers.templated").ignored(),
                                fieldWithPath("_links.updateById.href").description("Link to update the USER by ID."),
                                fieldWithPath("_links.deleteById.href").description("Link to delete the USER by ID."),
                                fieldWithPath("_links.addAddress.href").description("Link to add address to the USER."),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get address of the USER."),
                                fieldWithPath("_links.getByAddressId.templated").ignored(),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get page of addresses of the USER."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get page of sorted addresses of the USER."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to update address of the USER."),
                                fieldWithPath("_links.updateByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address of the USER."),
                                fieldWithPath("_links.deleteByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete all addresses of the USER."),
                                fieldWithPath("_links.addBankData.href").description("Link to add bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data of the USER."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data of the USER."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update bank data of the USER."),
                                fieldWithPath("_links.updateByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete one bank data of the USER."),
                                fieldWithPath("_links.deleteByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all bank data of the USER.")
                        ),
                        links(
                                linkWithRel("self").description("Link to add USER."),
                                linkWithRel("getById").description("Link to get the USER by ID."),
                                linkWithRel("getPageOfUsers").description("Link to get page of USERS."),
                                linkWithRel("getPageOfSortedUsers").description("Link to get page of sorted USERS."),
                                linkWithRel("updateById").description("Link to update the USER by ID."),
                                linkWithRel("deleteById").description("Link to delete the USER by ID."),
                                linkWithRel("addAddress").description("Link to add address to the USER."),
                                linkWithRel("getByAddressId").description("Link to get address of the USER."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get page of addresses of the USER."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get page of sorted addresses of the USER."),
                                linkWithRel("updateByAddressId").description("Link to update address of the USER."),
                                linkWithRel("deleteByAddressId").description("Link to delete address of the USER."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete all addresses of the USER."),
                                linkWithRel("addBankData").description("Link to add bank data of the USER."),
                                linkWithRel("getByBankDataId").description("Link to get bank data of the USER."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data of the USER."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data of the USER."),
                                linkWithRel("updateByBankDataId").description("Link to update bank data of the USER."),
                                linkWithRel("deleteByBankDataId").description("Link to delete one bank data of the USER."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all bank data of the USER.")
                        )
                ));

        // Check if bankData is add correctly.
        final Integer USER_ID = 1;
        final String responseBodyContent_2 = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";
        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void add_IllegalParameterUser_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParameterFirstName_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'firstName' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"id\":null,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterFirstName_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'firstName' is empty, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"id\":null,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"id\":null,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterLastName_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'lastName' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterLastName_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'lastName' is empty, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterUserName_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'userName' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterUserName_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'userName' is empty, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterEmail_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'email' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterEmail_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'email' is empty, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterPassword_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'password' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterPassword_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'password' is empty, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_ParameterBankData_Empty() throws Exception {

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users\"},\"getById\":{\"href\":\"http://localhost:8080/users/1\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankAccountNumber_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'bankAccountNumber' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankAccountNumber_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'bankAccountNumber' is empty, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterDataOfExpiry_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'dateOfExpiry' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCVI_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\"}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCVI_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' must be greater that zero, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":0}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCVI_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' must be greater that zero, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":-1}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_ParameterAddress_Empty() throws Exception {

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users\"},\"getById\":{\"href\":\"http://localhost:8080/users/1\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCounty_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'country' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCountry_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'country' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCity_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'city' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterCity_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'city' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterStreet_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'street' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterStreet_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'street' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterHouseNumber_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'houseNumber' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterHouseNumber_EmptyValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'houseNumber' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterPostcode_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'postcode' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\"}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterPostcode_ZeroValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'postcode' must be greater that zero, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":0}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterPostcode_NegativeValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'postcode' must be greater that zero, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":-1}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBirthday_NullValue() throws Exception {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'birthday' not be equals to null, check the field that it has the 'user' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users")
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}]}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":333}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/getById",
                        pathParameters(
                                parameterWithName("userId").description("This id will be use to search the user.")
                        ),
                        responseFields(
                                fieldWithPath("userId").description("The id of the User."),
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User."),
                                fieldWithPath("_links.self.href").description("Link to get USER by ID."),
                                fieldWithPath("_links.add.href").description("Link to add the USER."),
                                fieldWithPath("_links.getPageOfUsers.href").description("Link to get page of USERS."),
                                fieldWithPath("_links.getPageOfUsers.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedUsers.href").description("Link to get page of sorted USERS."),
                                fieldWithPath("_links.getPageOfSortedUsers.templated").ignored(),
                                fieldWithPath("_links.updateById.href").description("Link to update the USER by ID."),
                                fieldWithPath("_links.deleteById.href").description("Link to delete the USER by ID."),
                                fieldWithPath("_links.addAddress.href").description("Link to add address to the USER."),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get address of the USER."),
                                fieldWithPath("_links.getByAddressId.templated").ignored(),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get page of addresses of the USER."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get page of sorted addresses of the USER."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to update address of the USER."),
                                fieldWithPath("_links.updateByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address of the USER."),
                                fieldWithPath("_links.deleteByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete all addresses of the USER."),
                                fieldWithPath("_links.addBankData.href").description("Link to add bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data of the USER."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data of the USER."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update bank data of the USER."),
                                fieldWithPath("_links.updateByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete one bank data of the USER."),
                                fieldWithPath("_links.deleteByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all bank data of the USER.")
                        ),
                        links(
                                linkWithRel("self").description("Link to get USER by ID."),
                                linkWithRel("add").description("Link to add the USER."),
                                linkWithRel("getPageOfUsers").description("Link to get page of USERS."),
                                linkWithRel("getPageOfSortedUsers").description("Link to get page of sorted USERS."),
                                linkWithRel("updateById").description("Link to update the USER by ID."),
                                linkWithRel("deleteById").description("Link to delete the USER by ID."),
                                linkWithRel("addAddress").description("Link to add address to the USER."),
                                linkWithRel("getByAddressId").description("Link to get address of the USER."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get page of addresses of the USER."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get page of sorted addresses of the USER."),
                                linkWithRel("updateByAddressId").description("Link to update address of the USER."),
                                linkWithRel("deleteByAddressId").description("Link to delete address of the USER."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete all addresses of the USER."),
                                linkWithRel("addBankData").description("Link to add bank data of the USER."),
                                linkWithRel("getByBankDataId").description("Link to get bank data of the USER."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data of the USER."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data of the USER."),
                                linkWithRel("updateByBankDataId").description("Link to update bank data of the USER."),
                                linkWithRel("deleteByBankDataId").description("Link to delete one bank data of the USER."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all bank data of the USER.")
                        )
                ));
    }

    @Test
    public void getById_IllegalUserId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getById_IllegalUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getById_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this userId = 100 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfUsers() throws Exception {

        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 10;

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "    ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{startPage}/{pageSize}", START_PAGE, PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/getPageOfUsers",
                        pathParameters(
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")
                        ),
                        responseFields(
                                fieldWithPath("content.[].userId").description("The id of the User."),
                                fieldWithPath("content.[].firstName").description("The firstName of the User."),
                                fieldWithPath("content.[].lastName").description("The lastName of the User."),
                                fieldWithPath("content.[].userName").description("The userName of the User."),
                                fieldWithPath("content.[].email").description("The email of the User."),
                                fieldWithPath("content.[].password").description("The password of the User."),
                                fieldWithPath("content.[].bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("content.[].bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].links.[]").ignored(),
                                fieldWithPath("content.[].address.[]").description("The array of addresses of the User."),
                                fieldWithPath("content.[].address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("content.[].address.[].country").description("The country of address of the User."),
                                fieldWithPath("content.[].address.[].city").description("The city of address of the User."),
                                fieldWithPath("content.[].address.[].street").description("The street of address of the User."),
                                fieldWithPath("content.[].address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("content.[].address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("content.[].address.[].links.[]").ignored(),
                                fieldWithPath("content.[].birthday").description("The birthday of the User."),
                                fieldWithPath("content.[].links[].href").ignored(),
                                fieldWithPath("content.[].links[].rel").ignored(),
                                fieldWithPath("content.[].links[].hreflang").ignored(),
                                fieldWithPath("content.[].links[].media").ignored(),
                                fieldWithPath("content.[].links[].title").ignored(),
                                fieldWithPath("content.[].links[].type").ignored(),
                                fieldWithPath("content.[].links[].deprecation").ignored(),
                                fieldWithPath("pageable.sort.sorted").ignored(),
                                fieldWithPath("pageable.sort.unsorted").ignored(),
                                fieldWithPath("pageable.sort.empty").ignored(),
                                fieldWithPath("pageable.pageSize").ignored(),
                                fieldWithPath("pageable.pageNumber").ignored(),
                                fieldWithPath("pageable.offset").ignored(),
                                fieldWithPath("pageable.paged").ignored(),
                                fieldWithPath("pageable.unpaged").ignored(),
                                fieldWithPath("totalPages").ignored(),
                                fieldWithPath("totalElements").ignored(),
                                fieldWithPath("last").ignored(),
                                fieldWithPath("first").ignored(),
                                fieldWithPath("sort.sorted").ignored(),
                                fieldWithPath("sort.unsorted").ignored(),
                                fieldWithPath("sort.empty").ignored(),
                                fieldWithPath("number").ignored(),
                                fieldWithPath("numberOfElements").ignored(),
                                fieldWithPath("size").ignored(),
                                fieldWithPath("empty").ignored()
                        ),
                        links(
                                halLinks()
                        )
                ));
    }

    @Sql(value = {"/User_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getPageOfUsers_Users_NotFound() throws Exception {

        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 10;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Users not exists.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/10\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{startPage}/{pageSize}", START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedUsers() throws Exception {

        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 10;
        final String SORT_ORDER = "firstName";

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "    ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{startPage}/{pageSize}/{sortOrder}", START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/getPageOfSortedUsers",
                        pathParameters(
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0"),
                                parameterWithName("sortOrder").description("The value by which the sorted comments will be.")
                        ),
                        responseFields(
                                fieldWithPath("content.[].userId").description("The id of the User."),
                                fieldWithPath("content.[].firstName").description("The firstName of the User."),
                                fieldWithPath("content.[].lastName").description("The lastName of the User."),
                                fieldWithPath("content.[].userName").description("The userName of the User."),
                                fieldWithPath("content.[].email").description("The email of the User."),
                                fieldWithPath("content.[].password").description("The password of the User."),
                                fieldWithPath("content.[].bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("content.[].bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("content.[].bankData.[].links.[]").ignored(),
                                fieldWithPath("content.[].address.[]").description("The array of addresses of the User."),
                                fieldWithPath("content.[].address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("content.[].address.[].country").description("The country of address of the User."),
                                fieldWithPath("content.[].address.[].city").description("The city of address of the User."),
                                fieldWithPath("content.[].address.[].street").description("The street of address of the User."),
                                fieldWithPath("content.[].address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("content.[].address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("content.[].address.[].links.[]").ignored(),
                                fieldWithPath("content.[].birthday").description("The birthday of the User."),
                                fieldWithPath("content.[].links[].href").ignored(),
                                fieldWithPath("content.[].links[].rel").ignored(),
                                fieldWithPath("content.[].links[].hreflang").ignored(),
                                fieldWithPath("content.[].links[].media").ignored(),
                                fieldWithPath("content.[].links[].title").ignored(),
                                fieldWithPath("content.[].links[].type").ignored(),
                                fieldWithPath("content.[].links[].deprecation").ignored(),
                                fieldWithPath("pageable.sort.sorted").ignored(),
                                fieldWithPath("pageable.sort.unsorted").ignored(),
                                fieldWithPath("pageable.sort.empty").ignored(),
                                fieldWithPath("pageable.pageSize").ignored(),
                                fieldWithPath("pageable.pageNumber").ignored(),
                                fieldWithPath("pageable.offset").ignored(),
                                fieldWithPath("pageable.paged").ignored(),
                                fieldWithPath("pageable.unpaged").ignored(),
                                fieldWithPath("totalPages").ignored(),
                                fieldWithPath("totalElements").ignored(),
                                fieldWithPath("last").ignored(),
                                fieldWithPath("first").ignored(),
                                fieldWithPath("sort.sorted").ignored(),
                                fieldWithPath("sort.unsorted").ignored(),
                                fieldWithPath("sort.empty").ignored(),
                                fieldWithPath("number").ignored(),
                                fieldWithPath("numberOfElements").ignored(),
                                fieldWithPath("size").ignored(),
                                fieldWithPath("empty").ignored()
                        ),
                        links(
                                halLinks()
                        )
                ));
    }

    @Sql(value = {"/User_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getPageOfSortedUsers_Users_NotFound() throws Exception {

        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 10;
        final String SORT_ORDER = "firstName";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Users not exists.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/10/firstName\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{startPage}/{pageSize}/{sortOrder}", START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateById() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":333}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getById\":{\"href\":\"http://localhost:8080/users/1\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/{userId}", USER_ID)
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/updateById",
                        pathParameters(
                                parameterWithName("userId").description("This id will be use to update the user.")
                        ),
                        requestFields(
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User.")
                        ),
                        responseFields(
                                fieldWithPath("userId").description("The id of the User."),
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User."),
                                fieldWithPath("_links.self.href").description("Link to update USER by ID."),
                                fieldWithPath("_links.add.href").description("Link to add the USER."),
                                fieldWithPath("_links.getById.href").description("Link to get the USER by ID."),
                                fieldWithPath("_links.getPageOfUsers.href").description("Link to get page of USERS."),
                                fieldWithPath("_links.getPageOfUsers.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedUsers.href").description("Link to get page of sorted USERS."),
                                fieldWithPath("_links.getPageOfSortedUsers.templated").ignored(),
                                fieldWithPath("_links.deleteById.href").description("Link to delete the USER by ID."),
                                fieldWithPath("_links.addAddress.href").description("Link to add address to the USER."),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get address of the USER."),
                                fieldWithPath("_links.getByAddressId.templated").ignored(),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get page of addresses of the USER."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get page of sorted addresses of the USER."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to update address of the USER."),
                                fieldWithPath("_links.updateByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address of the USER."),
                                fieldWithPath("_links.deleteByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete all addresses of the USER."),
                                fieldWithPath("_links.addBankData.href").description("Link to add bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data of the USER."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data of the USER."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update bank data of the USER."),
                                fieldWithPath("_links.updateByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete one bank data of the USER."),
                                fieldWithPath("_links.deleteByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all bank data of the USER.")
                        ),
                        links(
                                linkWithRel("self").description("Link to update USER by ID."),
                                linkWithRel("add").description("Link to add the USER."),
                                linkWithRel("getById").description("Link to get the USER by ID."),
                                linkWithRel("getPageOfUsers").description("Link to get page of USERS."),
                                linkWithRel("getPageOfSortedUsers").description("Link to get page of sorted USERS."),
                                linkWithRel("deleteById").description("Link to delete the USER by ID."),
                                linkWithRel("addAddress").description("Link to add address to the USER."),
                                linkWithRel("getByAddressId").description("Link to get address of the USER."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get page of addresses of the USER."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get page of sorted addresses of the USER."),
                                linkWithRel("updateByAddressId").description("Link to update address of the USER."),
                                linkWithRel("deleteByAddressId").description("Link to delete address of the USER."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete all addresses of the USER."),
                                linkWithRel("addBankData").description("Link to add bank data of the USER."),
                                linkWithRel("getByBankDataId").description("Link to get bank data of the USER."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data of the USER."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data of the USER."),
                                linkWithRel("updateByBankDataId").description("Link to update bank data of the USER."),
                                linkWithRel("deleteByBankDataId").description("Link to delete one bank data of the USER."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all bank data of the USER.")
                        )
                ));
    }

    @Test
    public void updateById_IllegalParameterUserId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void updateById_IllegalParameterUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0\"}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/{userId}", USER_ID)
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void updateById_IllegalParameterUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1\"}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/{userId}", USER_ID)
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void updateById_IllegalParameterUser_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void updateById_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100\"}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/{userId}", USER_ID)
                        .content("{\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong password\",\"bankData\":[{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle\",\"houseNumber\":\"1\",\"postcode\":111111}],\"birthday\":\"26.06.1988\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":333}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getById\":{\"href\":\"http://localhost:8080/users/1\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/delete",
                        pathParameters(
                                parameterWithName("userId").description("This id will be use to delete the user.")
                        ),
                        responseFields(
                                fieldWithPath("userId").description("The id of the User."),
                                fieldWithPath("firstName").description("The firstName of the User."),
                                fieldWithPath("lastName").description("The lastName of the User."),
                                fieldWithPath("userName").description("The userName of the User."),
                                fieldWithPath("email").description("The email of the User."),
                                fieldWithPath("password").description("The password of the User."),
                                fieldWithPath("bankData.[]").description("The array of bank data of the User."),
                                fieldWithPath("bankData.[].bankDataId").description("The id of bank account of the User."),
                                fieldWithPath("bankData.[].bankAccountNumber").description("The bank account number of bank account of the User."),
                                fieldWithPath("bankData.[].dateOfExpiry").description("The date of expiry of bank account of the User."),
                                fieldWithPath("bankData.[].cvi").description("The cvi number of bank account of the User."),
                                fieldWithPath("address.[]").description("The array of addresses of the User."),
                                fieldWithPath("address.[].addressId").description("The id of address of the User."),
                                fieldWithPath("address.[].country").description("The country of address of the User."),
                                fieldWithPath("address.[].city").description("The city of address of the User."),
                                fieldWithPath("address.[].street").description("The street of address of the User."),
                                fieldWithPath("address.[].houseNumber").description("The house number of address of the User."),
                                fieldWithPath("address.[].postcode").description("The postcode of address of the User."),
                                fieldWithPath("birthday").description("The birthday of the User."),
                                fieldWithPath("_links.self.href").description("Link to delete USER by ID."),
                                fieldWithPath("_links.add.href").description("Link to add the USER."),
                                fieldWithPath("_links.getById.href").description("Link to get the USER by ID."),
                                fieldWithPath("_links.getPageOfUsers.href").description("Link to get page of USERS."),
                                fieldWithPath("_links.getPageOfUsers.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedUsers.href").description("Link to get page of sorted USERS."),
                                fieldWithPath("_links.getPageOfSortedUsers.templated").ignored(),
                                fieldWithPath("_links.updateById.href").description("Link to update the USER by ID."),
                                fieldWithPath("_links.addAddress.href").description("Link to add address to the USER."),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get address of the USER."),
                                fieldWithPath("_links.getByAddressId.templated").ignored(),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get page of addresses of the USER."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get page of sorted addresses of the USER."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to update address of the USER."),
                                fieldWithPath("_links.updateByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address of the USER."),
                                fieldWithPath("_links.deleteByAddressId.templated").ignored(),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete all addresses of the USER."),
                                fieldWithPath("_links.addBankData.href").description("Link to add bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get bank data of the USER."),
                                fieldWithPath("_links.getByBankDataId.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data of the USER."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data of the USER."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update bank data of the USER."),
                                fieldWithPath("_links.updateByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete one bank data of the USER."),
                                fieldWithPath("_links.deleteByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all bank data of the USER.")
                        ),
                        links(
                                linkWithRel("self").description("Link to delete USER by ID."),
                                linkWithRel("add").description("Link to add the USER."),
                                linkWithRel("getById").description("Link to get the USER by ID."),
                                linkWithRel("getPageOfUsers").description("Link to get page of USERS."),
                                linkWithRel("getPageOfSortedUsers").description("Link to get page of sorted USERS."),
                                linkWithRel("updateById").description("Link to update the USER by ID."),
                                linkWithRel("addAddress").description("Link to add address to the USER."),
                                linkWithRel("getByAddressId").description("Link to get address of the USER."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get page of addresses of the USER."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get page of sorted addresses of the USER."),
                                linkWithRel("updateByAddressId").description("Link to update address of the USER."),
                                linkWithRel("deleteByAddressId").description("Link to delete address of the USER."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete all addresses of the USER."),
                                linkWithRel("addBankData").description("Link to add bank data of the USER."),
                                linkWithRel("getByBankDataId").description("Link to get bank data of the USER."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data of the USER."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data of the USER."),
                                linkWithRel("updateByBankDataId").description("Link to update bank data of the USER."),
                                linkWithRel("deleteByBankDataId").description("Link to delete one bank data of the USER."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all bank data of the USER.")
                        )
                ));
    }

    @Test
    public void deleteById_IllegalParameterUserId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteById_IllegalParameterUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteById_IllegalParameterUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1\"}";


        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteById_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100\"}";


        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}