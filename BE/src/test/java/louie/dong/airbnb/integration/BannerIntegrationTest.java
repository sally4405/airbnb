package louie.dong.airbnb.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
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
class BannerIntegrationTest {

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
    void 메인_히어로_이미지_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-hero-image", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/banners/main")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("imageUrl", equalTo(
                "https://user-images.githubusercontent.com/73376468/171333357-01d5146b-bdf2-45a0-a49d-234ee9f133b7.jpeg"));
    }
}
