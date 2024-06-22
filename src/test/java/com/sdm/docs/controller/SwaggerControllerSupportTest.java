package com.sdm.docs.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;

import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;

/*
참조 사이트
https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/#getting-started-documentation-snippets-invoking-the-service
https://velog.io/@hwsa1004/Spring-Spring-RestDocs-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
얘가 최고다
https://velog.io/@nefertiri/Spring-REST-Docs-%EC%99%80-Swagger-%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-API-%EB%AA%85%EC%84%B8%EC%84%9C-%EC%9E%91%EC%84%B1-%EC%9E%90%EB%8F%99%ED%99%94%ED%95%98%EA%B8%B0

https://github.com/jjunhwan-kim/apidoc/blob/main/src/test/java/com/example/apidoc/docs/post/PostControllerDocsTest.java
 */

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
class SwaggerControllerSupportTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void getSwagers() throws Exception {
    mockMvc.perform(
        RestDocumentationRequestBuilders.get("/swaggers")
          .accept(MediaType.APPLICATION_JSON)
          .param("page", "1")
          .param("size", "10")
          .param("sort", "clazz,desc")
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcRestDocumentationWrapper.document("getSwaggers",
          Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
          Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
          queryParameters(
            parameterWithName("page").description("page number"),
            parameterWithName("size").description("page size"),
            parameterWithName("sort").description("sort")
          ),
          responseFields(
            fieldWithPath("[].id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("[].name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("[].clazz").description("clazz").type(JsonFieldType.STRING)
          )
        )
      );
  }

  @Test
  void getSwagger() throws Exception {
    mockMvc.perform(
        RestDocumentationRequestBuilders.get("/swaggers/{id}", 1L)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcRestDocumentationWrapper.document("getSwagger",
          Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
          Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
          responseFields(
            fieldWithPath("id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("clazz").description("clazz").type(JsonFieldType.STRING)
          )
        )
      );
  }

  @Test
  void createSwagger() throws Exception {
    Map<String, Object> params = Map.of(
      "id", 1L,
      "name", "Swagger1",
      "clazz", "com.nshc.tiket.tiket0"
    );
    // Map To Json
    String json = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(params);

    mockMvc.perform(
        RestDocumentationRequestBuilders.post("/swaggers")
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .content(json)
//        .header("Authorization", "Bearer {ACCESS_TOKEN}")
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcRestDocumentationWrapper.document("createSwagger",
          Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
          Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
          requestHeaders(
//            headerWithName("Authorization").description("Authorization Access Token")
          ),
          requestFields(
            fieldWithPath("id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("clazz").description("clazz").type(JsonFieldType.STRING)
          ),
          responseFields(
            fieldWithPath("id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("clazz").description("clazz").type(JsonFieldType.STRING)
          )
        )
      );
  }

  @Test
  void updateSwagger() throws Exception {
    Map<String, Object> params = Map.of(
      "id", 1L,
      "name", "Swagger1",
      "clazz", "com.nshc.tiket.tiket0"
    );
    // Map To Json
    String json = Jackson2ObjectMapperBuilder.json().build().writeValueAsString(params);

    mockMvc.perform(
        RestDocumentationRequestBuilders.put("/swaggers/{id}", 1L)
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
          .content(json)
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcRestDocumentationWrapper.document("updateSwagger",
          Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
          Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
          requestFields(
            fieldWithPath("id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("clazz").description("clazz").type(JsonFieldType.STRING)
          ),
          responseFields(
            fieldWithPath("id").description("id").type(JsonFieldType.NUMBER),
            fieldWithPath("name").description("name").type(JsonFieldType.STRING),
            fieldWithPath("clazz").description("clazz").type(JsonFieldType.STRING)
          )
        )
      );
  }

  @Test
  void deleteSwagger() throws Exception {
    mockMvc.perform(
        RestDocumentationRequestBuilders.delete("/swaggers/{id}", 1L)
          .accept(MediaType.APPLICATION_JSON)
      )
      .andDo(MockMvcResultHandlers.print())
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andDo(MockMvcRestDocumentationWrapper.document("deleteSwagger",
          Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
          Preprocessors.preprocessResponse(Preprocessors.prettyPrint())
        )
      );
  }
}