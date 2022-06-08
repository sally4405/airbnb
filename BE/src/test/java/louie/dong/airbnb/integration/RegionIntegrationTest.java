package louie.dong.airbnb.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith({RestDocumentationExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class RegionIntegrationTest {

    @LocalServerPort
    int port;

    RequestSpecification documentationSpec;

    @BeforeEach
    void setup(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        documentationSpec = new RequestSpecBuilder()
            .addFilter(documentationConfiguration(restDocumentation))
            .build();
    }

    @Test
    void 지역_검색() {
        given(documentationSpec)
            .urlEncodingEnabled(false)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-search-country", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/regions/search?country=양재동")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("", hasSize(4))
            .body("[0].country", equalTo("양재동, 서초구, 서울특별시"))
            .body("[0].imageUrl", equalTo("https://user-images.githubusercontent.com/92966772/171333901-d1cd469c-0a4d-404f-a277-6ba9e083034a.png"))
            .body("[1].country", equalTo("양재역 사거리, 양재1동"))
            .body("[1].imageUrl", equalTo("https://user-images.githubusercontent.com/92966772/171333901-d1cd469c-0a4d-404f-a277-6ba9e083034a.png"));
    }
}
