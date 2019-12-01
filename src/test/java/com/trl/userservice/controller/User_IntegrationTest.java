package com.trl.userservice.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Sql(value = {"/User_Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/User_After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void getById() throws Exception {

        final Integer USER_ID = 1;

        final String responseBodyContent = "{\"userId\":1,\"firstName\":\"Tsyupryk\",\"lastName\":\"Roman\",\"userName\":\"TRL\",\"email\":\"tsyupryk.roman@gmail.com\",\"password\":\"strong_password\",\"bankData\":[{\"bankDataId\":1,\"bankAccountNumber\":\"1212121212121212\",\"dateOfExpiry\":\"01.01.2000\",\"cvi\":111},{\"bankDataId\":2,\"bankAccountNumber\":\"343434343434343\",\"dateOfExpiry\":\"02.01.2000\",\"cvi\":222},{\"bankDataId\":3,\"bankAccountNumber\":\"565656565656565\",\"dateOfExpiry\":\"03.01.2000\",\"cvi\":333}],\"address\":[{\"addressId\":1,\"country\":\"Spain\",\"city\":\"Madrid\",\"street\":\"Calle Madrid\",\"houseNumber\":\"1A\",\"postcode\":1111},{\"addressId\":2,\"country\":\"Spain\",\"city\":\"Barcelona\",\"street\":\"Calle Barcelona\",\"houseNumber\":\"2B\",\"postcode\":22222},{\"addressId\":3,\"country\":\"Spain\",\"city\":\"Toledo\",\"street\":\"Calle Toledo\",\"houseNumber\":\"3C\",\"postcode\":33333}],\"birthday\":\"26.06.1988\",\"_links\":{\"self\":{\"href\":\"http://localhost:8080/users/1\"}}}";

        this.mockMvc.perform(
                get(BASE_URL + "/users/{userId}", USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(content().string(containsString(responseBodyContent)))
                .andDo(print())
                .andDo(document("user/getById",
                        pathParameters(
                                parameterWithName("userId").description("This id will be use to search the user.")
                        )
//                        ,
//                        responseFields(
//                                fieldWithPath("commentId").description("The id of Comment"),
//                                fieldWithPath("userId").description("The user id of the user who created this comment."),
//                                fieldWithPath("text").description("Text of the comment."),
//                                fieldWithPath("date").description("Date of the comment."),
//                                fieldWithPath("subComments.[]").description("The array of sub comments by the comment."),
//                                fieldWithPath("subComments.[].subCommentId").description("The id of sub comment."),
//                                fieldWithPath("subComments.[].userId").description("The user id of the user who created this sub comment."),
//                                fieldWithPath("subComments.[].text").description("Text of the sub comment."),
//                                fieldWithPath("subComments.[].date").description("Date of the sub comment."),
//                                fieldWithPath("_links.self.href").description("Link to self(getBYCommentId) the comment resource by id."),
//                                fieldWithPath("_links.add.href").description("Link to add the comment resource."),
//                                fieldWithPath("_links.add.templated").ignored(),
//                                fieldWithPath("_links.getPageOfCommentsByBookId.href").description("Link to get the page of comments resource."),
//                                fieldWithPath("_links.getPageOfCommentsByBookId.templated").ignored(),
//                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.href").description("Link to get the page of sorted comments resource."),
//                                fieldWithPath("_links.getPageOfSortedCommentsByBookId.templated").ignored(),
//                                fieldWithPath("_links.updateByCommentId.href").description("Link to upgrade comment resource by comment id."),
//                                fieldWithPath("_links.deleteByCommentId.href").description("Link to delete comment resource by comment id."),
//                                fieldWithPath("_links.deleteAllCommentsByBookId.href").description("Link to delete comments resource by book id."),
//                                fieldWithPath("_links.deleteAllCommentsByBookId.templated").ignored(),
//                                fieldWithPath("_links.addSubComment.href").description("Link to add sub comments to the comment resource."),
//                                fieldWithPath("_links.getBySubCommentId.href").description("Link to get sub comments from the comment resource."),
//                                fieldWithPath("_links.getBySubCommentId.templated").ignored(),
//                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.href").description("Link to get page of sub comments from the comment resource."),
//                                fieldWithPath("_links.getPageOfSubCommentsByCommentId.templated").ignored(),
//                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.href").description("Link to get page of sorted sub comments from the comment resource."),
//                                fieldWithPath("_links.getPageOfSortedSubCommentsByCommentId.templated").ignored(),
//                                fieldWithPath("_links.updateBySubCommentId.href").description("Link to update sub comments from the comment resource."),
//                                fieldWithPath("_links.updateBySubCommentId.templated").ignored(),
//                                fieldWithPath("_links.deleteBySubCommentId.href").description("Link to delete sub comments from the comment resource."),
//                                fieldWithPath("_links.deleteBySubCommentId.templated").ignored(),
//                                fieldWithPath("_links.deleteAllSubCommentsByCommentId.href").description("Link to delete all sub comments from the comment resource.")
//                        ),
//                        links(
//                                linkWithRel("self").description("Link to self(getBYCommentId) the comment resource by id."),
//                                linkWithRel("add").description("Link to add the comment resource."),
//                                linkWithRel("getPageOfCommentsByBookId").description("Link to get the page of comments resource."),
//                                linkWithRel("getPageOfSortedCommentsByBookId").description("Link to get the page of sorted comments resource."),
//                                linkWithRel("updateByCommentId").description("Link to upgrade comment resource by comment id."),
//                                linkWithRel("deleteByCommentId").description("Link to delete comment resource by comment id."),
//                                linkWithRel("deleteAllCommentsByBookId").description("Link to delete comments resource by book id."),
//                                linkWithRel("addSubComment").description("Link to add sub comments to the comment resource."),
//                                linkWithRel("getBySubCommentId").description("Link to get sub comments from the comment resource."),
//                                linkWithRel("getPageOfSubCommentsByCommentId").description("Link to get page of sub comments from the comment resource."),
//                                linkWithRel("getPageOfSortedSubCommentsByCommentId").description("Link to get page of sorted sub comments from the comment resource."),
//                                linkWithRel("updateBySubCommentId").description("Link to update sub comments from the comment resource."),
//                                linkWithRel("deleteBySubCommentId").description("Link to delete sub comments from the comment resource."),
//                                linkWithRel("deleteAllSubCommentsByCommentId").description("Link to delete all sub comments from the comment resource.")
//                        )
                ));
    }
}
