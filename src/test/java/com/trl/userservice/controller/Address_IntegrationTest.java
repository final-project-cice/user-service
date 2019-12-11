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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class Address_IntegrationTest {

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

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void add() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"addressId\":4,\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/4\"},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/4\"},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/4\"},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"}}}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/add",
                        pathParameters(
                                parameterWithName("userId").description("The user id to which address will be added.")
                        ),
                        requestFields(
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user.")
                        ),
                        responseFields(
                                fieldWithPath("addressId").description("The id of Address"),
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user."),
                                fieldWithPath("_links.self.href").description("Link to self(add) the address resource."),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get the address resource by address id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get the page of addresses resource by user id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get the page of sorted addresses resource by user id."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to upgrade address resource by address id."),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address resource by address id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete addresses resource by user id.")
                        ),
                        links(
                                linkWithRel("self").description("Link to self(add) the address resource."),
                                linkWithRel("getByAddressId").description("Link to get the address resource by address id."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get the page of addresses resource by user id."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get the page of sorted addresses resource by user id."),
                                linkWithRel("updateByAddressId").description("Link to upgrade address resource by address id."),
                                linkWithRel("deleteByAddressId").description("Link to delete address resource by address id."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete addresses resource by user id.")
                        )
                ));

        // Check if comment is add correctly.
        final String responseBodyContent_2 = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":333}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333},{\"addressId\":4,\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"},\"add\":{\"href\":\"http://localhost:8080/users\"},\"getPageOfUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedUsers\":{\"href\":\"http://localhost:8080/users/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateById\":{\"href\":\"http://localhost:8080/users/1\"},\"deleteById\":{\"href\":\"http://localhost:8080/users/1\"},\"addAddress\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"templated\":true},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/1/addresses\"},\"addBankData\":{\"href\":\"http://localhost:8080/users/1/bankData\"},\"getByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"getPageOfBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteByBankDataId\":{\"href\":\"http://localhost:8080/users/bankData/{bankDataId}\",\"templated\":true},\"deleteAllBankDataByUserId\":{\"href\":\"http://localhost:8080/users/1/bankData\"}}}";

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
    public void add_IllegalUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/0/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/-1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_ValueNull() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParameterAddress_IllegalField_Country_NullValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'country' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_Country_EmptyValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'country' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_City_NullValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'city' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_City_EmptyValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'city' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_Street_NullValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'street' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_Street_EmptyValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'street' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_HouseNumber_NullValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'houseNumber' not be equals to null, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_HouseNumber_EmptyValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'houseNumber' is empty, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_Postcode_NullValue() throws Exception {
        // TODO: Busar la informacion como se pede pasar valor null, y terminar ese test.
    }

    @Test
    public void add_ParameterAddress_IllegalField_Postcode_ZeroValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'postcode' must be greater that zero, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":0}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void add_ParameterAddress_IllegalField_Postcode_NegativeValue() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Field 'postcode' must be greater that zero, check the field that it has the 'address' parameter.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":-1}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void add_UserWithId_NotExist() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 1 not exist.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/1/addresses\"}";

        this.mockMvc.perform(
                post(BASE_URL + "/users/{userId}/addresses", USER_ID)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }


    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getByAddressId() throws Exception {

        final Integer ADDRESS_ID = 1;

        final String responseBodyContent = "{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/getByAddressId",
                        pathParameters(
                                parameterWithName("addressId").description("Get address by the addressId")
                        ),
                        responseFields(
                                fieldWithPath("addressId").description("The id of Address"),
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user."),
                                fieldWithPath("_links.self.href").description("Link to self(getByAddressId) the address resource."),
                                fieldWithPath("_links.add.href").description("Link to add the address resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get the page of addresses resource by user id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get the page of sorted addresses resource by user id."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to upgrade address resource by address id."),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address resource by address id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete addresses resource by user id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(getByAddressId) the address resource."),
                                linkWithRel("add").description("Link to add the address resource by user id."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get the page of addresses resource by user id."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get the page of sorted addresses resource by user id."),
                                linkWithRel("updateByAddressId").description("Link to upgrade address resource by address id."),
                                linkWithRel("deleteByAddressId").description("Link to delete address resource by address id."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete addresses resource by user id.")
                        )
                ));
    }

    @Test
    public void getByAddressId_IllegalAddressId_ZeroValue() throws Exception {

        final Integer ADDRESS_ID = 0;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/addresses/0\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getByAddressId_IllegalAddressId_NegativeValue() throws Exception {

        final Integer ADDRESS_ID = -1;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/addresses/-1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Empty_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Test
    public void getByAddressId_AddressByThisIdNotFound() throws Exception {

        final Integer ADDRESS_ID = 1;

        final String responseBodyContent = "{\"errorMessage\":\"Address with this addressId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/addresses/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfAddressesByUserId() throws Exception {

        final Integer USER_ID = 1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "        ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/getPageOfAddressesByUserId",
                        pathParameters(
                                parameterWithName("userId").description("The userId used to search for a addresses."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0")
                        ),
                        responseFields(
                                fieldWithPath("content.[].addressId").description("The id of Address"),
                                fieldWithPath("content.[].country").description("The name of country where lives user."),
                                fieldWithPath("content.[].city").description("The name of city where lives user."),
                                fieldWithPath("content.[].street").description("The name of street where lives user."),
                                fieldWithPath("content.[].houseNumber").description("The number of house where lives user."),
                                fieldWithPath("content.[].postcode").description("The postcode of house where lives user."),
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
    public void getPageOfAddressesByUserId_IllegalUserId_ValueZero() throws Exception {

        final Integer USER_ID = 0;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/0/addresses/0/2\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfAddressesByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/-1/addresses/0/2\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfAddressesByUserId_NotExistUser_ByUserId() throws Exception {

        final Integer USER_ID = 100;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;

        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/100/addresses/0/2\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfAddressesByUserId_NotFoundAddresses_ByUserId() throws Exception {

        final Integer USER_ID = 2;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;

        final String responseBodyContent = "{\"errorMessage\":\"Addresses with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/2/addresses/0/2\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedAddressesByUserId() throws Exception {

        final Integer USER_ID = 1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;
        final String SORT_ORDER = "city";

        // TODO: Search information how to test pageable 'responseBodyContent'.
        // TODO: Each time it returns a different 'responseBodyContent'.
        final String responseBodyContent = "        ";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
//                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/getPageOfSortedAddressesByUserId",
                        pathParameters(
                                parameterWithName("userId").description("The userId used to search for a addresses."),
                                parameterWithName("startPage").description("zero-based page index, must not be negative"),
                                parameterWithName("pageSize").description("the size of the page to be returned, must be greater than 0"),
                                parameterWithName("sortOrder").description("The value by which the sorted comments will be.")
                        ),
                        responseFields(
                                fieldWithPath("content.[].addressId").description("The id of Address"),
                                fieldWithPath("content.[].country").description("The name of country where lives user."),
                                fieldWithPath("content.[].city").description("The name of city where lives user."),
                                fieldWithPath("content.[].street").description("The name of street where lives user."),
                                fieldWithPath("content.[].houseNumber").description("The number of house where lives user."),
                                fieldWithPath("content.[].postcode").description("The postcode of house where lives user."),
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
    public void getPageOfSortedAddressesByUserId_IllegalUserId_ValueZero() throws Exception {

        final Integer USER_ID = 0;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;
        final String SORT_ORDER = "city";

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/0/addresses/0/2/city\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSortedAddressesByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;
        final String SORT_ORDER = "city";

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/-1/addresses/0/2/city\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void getPageOfSortedAddressesByUserId_NotExistUser_ByUserId() throws Exception {

        final Integer USER_ID = 100;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;
        final String SORT_ORDER = "city";

        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/100/addresses/0/2/city\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getPageOfSortedAddressesByUserId_NotFoundAddresses_ByUserId() throws Exception {

        final Integer USER_ID = 2;
        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 2;
        final String SORT_ORDER = "city";

        final String responseBodyContent = "{\"errorMessage\":\"Addresses with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/2/addresses/0/2/city\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}", USER_ID, START_PAGE, PAGE_SIZE, SORT_ORDER))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void update_docs() throws Exception {

        final Integer ADDRESS_ID = 1;

        final String responseBodyContent = "{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"deleteByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true}}}";

        this.mockMvc.perform(
                patch(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"country\":\"TestDataNameCountry\",\"city\":\"TestDataNameCity\",\"street\":\"TestDataNameStreet\",\"houseNumber\":\"TestDataNameHouseNumber\",\"postcode\":1111}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/update",
                        pathParameters(
                                parameterWithName("addressId").description("The address id to which address will be updated.")
                        ),
                        requestFields(
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user.")
                        ),
                        responseFields(
                                fieldWithPath("addressId").description("The id of Address"),
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user."),
                                fieldWithPath("_links.self.href").description("Link to self(updateByAddressId) the address resource."),
                                fieldWithPath("_links.add.href").description("Link to add address resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get the address resource by address id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get the page of addresses resource by user id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get the page of sorted addresses resource by user id."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.deleteByAddressId.href").description("Link to delete address resource by address id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete addresses resource by user id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(updateByAddressId) the address resource."),
                                linkWithRel("add").description("Link to add address resource by user id."),
                                linkWithRel("getByAddressId").description("Link to get the address resource by address id."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get the page of addresses resource by user id."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get the page of sorted addresses resource by user id."),
                                linkWithRel("deleteByAddressId").description("Link to delete address resource by address id."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete addresses resource by user id.")
                        )
                ));
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteByAddressId() throws Exception {

        final Integer ADDRESS_ID = 1;

        final String responseBodyContent = "{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111,\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"add\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true},\"getByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"getPageOfAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}\",\"templated\":true},\"getPageOfSortedAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses/{startPage}/{pageSize}/{sortOrder}\",\"templated\":true},\"updateByAddressId\":{\"href\":\"http://localhost:8080/users/addresses/1\"},\"deleteAllAddressesByUserId\":{\"href\":\"http://localhost:8080/users/{userId}/addresses\",\"templated\":true}}}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/deleteByAddressId",
                        pathParameters(
                                parameterWithName("addressId").description("The address with this addressId to be deleted.")
                        ),
                        responseFields(
                                fieldWithPath("addressId").description("The id of Address"),
                                fieldWithPath("country").description("The name of country where lives user."),
                                fieldWithPath("city").description("The name of city where lives user."),
                                fieldWithPath("street").description("The name of street where lives user."),
                                fieldWithPath("houseNumber").description("The number of house where lives user."),
                                fieldWithPath("postcode").description("The postcode of house where lives user."),
                                fieldWithPath("_links.self.href").description("Link to self(deleteByAddressId) the address resource."),
                                fieldWithPath("_links.add.href").description("Link to add the address resource by user id."),
                                fieldWithPath("_links.add.templated").ignored(),
                                fieldWithPath("_links.getByAddressId.href").description("Link to get address resource by address id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.href").description("Link to get the page of addresses resource by user id."),
                                fieldWithPath("_links.getPageOfAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.href").description("Link to get the page of sorted addresses resource by user id."),
                                fieldWithPath("_links.getPageOfSortedAddressesByUserId.templated").ignored(),
                                fieldWithPath("_links.updateByAddressId.href").description("Link to upgrade address resource by address id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.href").description("Link to delete addresses resource by user id."),
                                fieldWithPath("_links.deleteAllAddressesByUserId.templated").ignored()
                        ),
                        links(
                                linkWithRel("self").description("Link to self(deleteByAddressId) the address resource."),
                                linkWithRel("add").description("Link to add the address resource by user id."),
                                linkWithRel("getByAddressId").description("Link to get the address resource by address id."),
                                linkWithRel("getPageOfAddressesByUserId").description("Link to get the page of addresses resource by user id."),
                                linkWithRel("getPageOfSortedAddressesByUserId").description("Link to get the page of sorted addresses resource by user id."),
                                linkWithRel("updateByAddressId").description("Link to upgrade address resource by address id."),
                                linkWithRel("deleteAllAddressesByUserId").description("Link to delete addresses resource by user id.")
                        )
                ));

        final String responseBodyContent_2 = "{\"errorMessage\":\"Address with this addressId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/addresses/1\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteByAddressId_IllegalAddressId_ValueZero() throws Exception {

        final Integer ADDRESS_ID = 0;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/addresses/0\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteByAddressId_IllegalAddressId_NegativeValue() throws Exception {

        final Integer ADDRESS_ID = -1;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/addresses/-1\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteByAddressId_NotFoundAddressById() throws Exception {

        final Integer ADDRESS_ID = 100;

        final String responseBodyContent = "{\"errorMessage\":\"Address with this addressId = 100 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/addresses/100\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/addresses/{addressId}", ADDRESS_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllAddressesByUserId() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333,\"links\":[{\"rel\":\"self\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"add\",\"href\":\"http://localhost:8080/users/1/addresses\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"getPageOfSortedAddressesByUserId\",\"href\":\"http://localhost:8080/users/1/addresses/{startPage}/{pageSize}/{sortOrder}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"updateByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null},{\"rel\":\"deleteByAddressId\",\"href\":\"http://localhost:8080/users/addresses/{addressId}\",\"hreflang\":null,\"media\":null,\"title\":null,\"type\":null,\"deprecation\":null}]}]";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/addresses", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("address/deleteAllAddressesByUserId",
                        pathParameters(
                                parameterWithName("userId").description("These addresses with this userId to be deleted.")
                        ),
                        responseFields(
                                fieldWithPath("[].addressId").description("The id of Address"),
                                fieldWithPath("[].country").description("The name of country where lives user."),
                                fieldWithPath("[].city").description("The name of city where lives user."),
                                fieldWithPath("[].street").description("The name of street where lives user."),
                                fieldWithPath("[].houseNumber").description("The number of house where lives user."),
                                fieldWithPath("[].postcode").description("The postcode of house where lives user."),
                                fieldWithPath("[].links[].rel").ignored(),
                                fieldWithPath("[].links[].href").ignored(),
                                fieldWithPath("[].links[].hreflang").ignored(),
                                fieldWithPath("[].links[].media").ignored(),
                                fieldWithPath("[].links[].title").ignored(),
                                fieldWithPath("[].links[].type").ignored(),
                                fieldWithPath("[].links[].deprecation").ignored()
                        )
                ));

        final Integer START_PAGE = 0;
        final Integer PAGE_SIZE = 10;

        final String responseBodyContent_2 = "{\"errorMessage\":\"Addresses with this userId = 1 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/1/addresses/0/10\"}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}/addresses/{startPage}/{pageSize}", USER_ID, START_PAGE, PAGE_SIZE))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent_2)))
                .andDo(print());
    }

    @Test
    public void deleteAllAddressesByUserId_IllegalUserId_ZeroValue() throws Exception {

        final Integer USER_ID = 0;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/0/addresses\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/addresses", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllAddressesByUserId_IllegalUserId_NegativeValue() throws Exception {

        final Integer USER_ID = -1;

        final String responseBodyContent = "{\"errorMessage\":\"One of parameters is illegal. Parameters must be not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/-1/addresses\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/addresses", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Test
    public void deleteAllAddressesByUserId_UserByIdNotExist() throws Exception {

        final Integer USER_ID = 100;

        final String responseBodyContent = "{\"errorMessage\":\"User with this id = 100 not exist.\",\"errorCode\":400,\"documentation\":null,\"description\":\"uri=/users/100/addresses\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/addresses", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }

    @Sql(value = {"/Address_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/Address_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void deleteAllAddressesByUserId_AddressesByUserId_NotExist() throws Exception {

        final Integer USER_ID = 2;

        final String responseBodyContent = "{\"errorMessage\":\"Addresses with this userId = 2 not exist.\",\"errorCode\":404,\"documentation\":null,\"description\":\"uri=/users/2/addresses\"}";

        this.mockMvc.perform(
                delete(BASE_URL + "/users/{userId}/addresses", USER_ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print());
    }
}