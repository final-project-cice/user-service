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
public class BankData_IntegrationTest {

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

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"bankDataId\":4,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/add",
                        pathParameters(
                                parameterWithName("userId").description("The user id to which bankData will be added.")
                        ),
                        requestFields(
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user.")
                        ),
                        responseFields(
                                fieldWithPath("bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user."),
                                fieldWithPath("_links.self.href").description("Link to self(add) the bankData resource."),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get the bank data resource by bank data id."),
                                fieldWithPath("_links.getByBankDataId.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data resource by user id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data resource by user id."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update the bank data resource by bank data id."),
                                fieldWithPath("_links.updateByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete the bank data resource by bank data id."),
                                fieldWithPath("_links.deleteByBankDataId.templated").ignored(),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all the bank data resource by user id.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(add) the bankData resource."),
                                linkWithRel("getByBankDataId").description("Link to get the bank data resource by bank data id."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data resource by user id."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data resource by user id."),
                                linkWithRel("updateByBankDataId").description("Link to update the bank data resource by bank data id."),
                                linkWithRel("deleteByBankDataId").description("Link to delete the bank data resource by bank data id."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all the bank data resource by user id.")
                        )
                ));

        // Check if bankData is add correctly.
        final String responseBodyContent_2 = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":333},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":111},{\"bankDataId\":4,\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";
        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void add_IllegalUserId_ValueNull() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_IllegalUserId_ValueZero() throws Exception {

        final Integer USER_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_ValueNull() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParameterBankData_IllegalField_BankAccountNumber_NullValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'bankAccountNumber' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_IllegalField_BankAccountNumber_EmptyValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'bankAccountNumber' is empty, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_IllegalField_DataOfExpiry_NullValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'dateOfExpiry' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_IllegalField_CVI_NullValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' not be equals to null, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\"}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_IllegalField_CVI_ZeroValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' must be greater that zero, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":0}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterBankData_IllegalField_CVI_NegativeValue() throws Exception {

        final Integer USER_ID = 1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"Field 'cvi' must be greater that zero, check the field that it has the 'bankData' parameter.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":-1}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100/bankData\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/bankData", USER_ID)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByBankDataId() throws Exception {

        final Integer BANK_DATA_ID = 1;

        final String responseBodyContent = "{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":333,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/getByBankDataId",
                        pathParameters(
                                parameterWithName("bankDataId").description("Get bankData by this bankDataId")
                        ),
                        responseFields(
                                fieldWithPath("bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user."),
                                fieldWithPath("_links.self.href").description("Link to self(getByBankDataId) the bankData resource."),
                                fieldWithPath("_links.add.href").description("Link to add the bank data resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data resource by user id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data resource by user id."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update the bank data resource by bank data id."),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete the bank data resource by bank data id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all the bank data resource by user id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(getByBankDataId) the bankData resource."),
                                linkWithRel("add").description("Link to add the bank data resource by user id."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data resource by user id."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data resource by user id."),
                                linkWithRel("updateByBankDataId").description("Link to update the bank data resource by bank data id."),
                                linkWithRel("deleteByBankDataId").description("Link to delete the bank data resource by bank data id."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all the bank data resource by user id.")
                        )
                ));
    }

    @Test
    public void getByBankDataId_IllegalBankDataId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getByBankDataId_IllegalBankDataId_ZeroValue() throws Exception {

        final Integer BANK_DATA_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getByBankDataId_IllegalBankDataId_NegativeValue() throws Exception {

        final Integer BANK_DATA_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByBankDataId_BankDataById_NotExist() throws Exception {

        final Integer BANK_DATA_ID = 33;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"BankData with this bankDataId = 33 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/33\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfBankDataByUserId() throws Exception {

        final Integer USER_ID = 1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "        ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/getPageOfBankDataByUserId",
                        pathParameters(
                                parameterWithName("userId").description("The user id to which bankData will be added."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")
                        ),
                        responseFields(
                                fieldWithPath("content.[].bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("content.[].bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("content.[].dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("content.[].cvi").description("The cvi number of bank account of the user."),
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

    @Test
    public void getPageOfBankDataByUserId_IllegalUserId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfBankDataByUserId_IllegalUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/bankData/0/3\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfBankDataByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1/bankData/0/3\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfBankDataByUserId_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100/bankData/0/3\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfBankDataByUserId_NotFoundBankData_ByUserId() throws Exception {

        final Integer USER_ID = 2;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"BankData with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/2/bankData/0/3\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedBankDataByUserId() throws Exception {

        final Integer USER_ID = 1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;
        final String SORT_ORDER = "cvi";

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "        ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/getPageOfSortedBankDataByUserId",
                        pathParameters(
                                parameterWithName("userId").description("The user id to which bankData will be added."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0"),
                                parameterWithName("sortOrder").description("The value by which the sorted bank data will be.")
                        ),
                        responseFields(
                                fieldWithPath("content.[].bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("content.[].bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("content.[].dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("content.[].cvi").description("The cvi number of bank account of the user."),
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

    @Test
    public void getPageOfSortedBankDataByUserId_IllegalUserId_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void getPageOfSortedBankDataByUserId_IllegalUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;
        final String SORT_ORDER = "cvi";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/bankData/0/3/cvi\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSortedBankDataByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;
        final String SORT_ORDER = "cvi";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1/bankData/0/3/cvi\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}/{orderSize}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedBankDataByUserId_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;
        final String SORT_ORDER = "cvi";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100/bankData/0/3/cvi\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}/{orderSize}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedBankDataByUserId_NotFoundBankData_ByUserId() throws Exception {

        final Integer USER_ID = 2;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 3;
        final String SORT_ORDER = "cvi";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"BankData with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/2/bankData/0/3/cvi\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}/{orderSize}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void update_docs() throws Exception {

        final Integer BANK_DATA_ID = 1;

        final String responseBodyContent = "{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":333,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true}}}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"bankAccountNumber\":\"0123456789\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/update",
                        pathParameters(
                                parameterWithName("bankDataId").description("The bank data id to which bankData will be updated.")
                        ),
                        requestFields(
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user.")
                        ),
                        responseFields(
                                fieldWithPath("bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user."),
                                fieldWithPath("_links.self.href").description("Link to self(updateByBankDataId) the bankData resource."),
                                fieldWithPath("_links.add.href").description("Link to add the bank data resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get the bank data resource by bank data id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data resource by user id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data resource by user id."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.deleteByBankDataId.href").description("Link to delete the bank data resource by bank data id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all the bank data resource by user id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(updateByBankDataId) the bankData resource."),
                                linkWithRel("add").description("Link to add the bank data resource by user id."),
                                linkWithRel("getByBankDataId").description("Link to get the bank data resource by bank data id."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data resource by user id."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data resource by user id."),
                                linkWithRel("deleteByBankDataId").description("Link to delete the bank data resource by bank data id."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all the bank data resource by user id.")
                        )
                ));
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByBankDataId() throws Exception {

        final Integer BANK_DATA_ID = 1;

        final String responseBodyContent = "{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":333,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/1\"},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/bankData\",\"templated\":true}}}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/deleteByBankDataId",
                        pathParameters(
                                parameterWithName("bankDataId").description("The bank data id to which bankData will be deleted.")
                        ),
                        responseFields(
                                fieldWithPath("bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("cvi").description("The cvi number of bank account of the user."),
                                fieldWithPath("_links.self.href").description("Link to self(deleteByBanDataId) the bankData resource."),
                                fieldWithPath("_links.add.href").description("Link to add the bank data resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByBankDataId.href").description("Link to get the bank data resource by bank data id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.href").description("Link to get page of bank data resource by user id."),
                                fieldWithPath("_links.getPageOfBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.href").description("Link to get page of sorted bank data resource by user id."),
                                fieldWithPath("_links.getPageOfSortedBankDataByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByBankDataId.href").description("Link to update the bank data resource by bank data id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.href").description("Link to delete all the bank data resource by user id."),
                                fieldWithPath("_links.deleteAllBankDataByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(deleteByBankDataId) the bankData resource."),
                                linkWithRel("add").description("Link to add the bank data resource by user id."),
                                linkWithRel("getByBankDataId").description("Link to get the bank data resource by bank data id."),
                                linkWithRel("getPageOfBankDataByUserId").description("Link to get page of bank data resource by user id."),
                                linkWithRel("getPageOfSortedBankDataByUserId").description("Link to get page of sorted bank data resource by user id."),
                                linkWithRel("updateByBankDataId").description("Link to update the bank data resource by bank data id."),
                                linkWithRel("deleteAllBankDataByUserId").description("Link to delete all the bank data resource by user id.")
                        )
                ));

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"BankData with this bankDataId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteByBankDataId_IllegalBankDataId_NullVale() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteByBankDataId_IllegalBankDataId_ZeroVale() throws Exception {

        final Integer BANK_DATA_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteByBankDataId_IllegalBankDataId_NegativeVale() throws Exception {

        final Integer BANK_DATA_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/-1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByBankDataId_BankDataById_NotExist() throws Exception {

        final Integer BANK_DATA_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"BankData with this bankDataId = 100 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/bankData/100\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/bankData/{bankDataId}", BANK_DATA_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllBankDataByUserId() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":333,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":111,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/bankData\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedBankDataByUserId\",\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByBankDataId\",\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]}]";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/bankData", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("bankData/deleteAllBankDataByUserId",
                        pathParameters(
                                parameterWithName("userId").description("The user id to which bankData will be added.")
                        ),
                        responseFields(
                                fieldWithPath("[].bankDataId").description("The id of bank account of the user."),
                                fieldWithPath("[].bankAccountNumber").description("The number of bank account of the user."),
                                fieldWithPath("[].dateOfExpiry").description("The date of expiry of bank account of the user."),
                                fieldWithPath("[].cvi").description("The cvi number of bank account of the user."),
                                fieldWithPath("[].links[].rel").ignored(),
                                fieldWithPath("[].links[].href").ignored(),
                                fieldWithPath("[].links[].hreflang").ignored(),
                                fieldWithPath("[].links[].media").ignored(),
                                fieldWithPath("[].links[].title").ignored(),
                                fieldWithPath("[].links[].type").ignored(),
                                fieldWithPath("[].links[].deprecation").ignored()
                        )
                ));

        // Check if subComments is deleted correctly.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent_2 = "{\"errorMessage\":\"BankData with this userId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/1/bankData/0/10\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/bankData/{startPage}/{pageSize}", 1, 0, 10))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteAllBankDataByUserId_IllegalUserId_ValueNull() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void deleteAllBankDataByUserId_IllegalUserId_ValueZero() throws Exception {

        final Integer USER_ID = 0;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/0/bankData\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/bankData", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllBankDataByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/-1/bankData\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/bankData", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllBankDataByUserId_UserById_NotExist() throws Exception {

        final Integer USER_ID = 100;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/100/bankData\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/bankData", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/BankData_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/BankData_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllBankDataByUserId_BankDataByUserId_NotExist() throws Exception {

        final Integer USER_ID = 2;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm"));
        final String responseBodyContent = "{\"errorMessage\":\"BankData with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"timestamp\":\"" + timestamp + "\",\"description\":\"uri=/users/2/bankData\"";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/bankData", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}