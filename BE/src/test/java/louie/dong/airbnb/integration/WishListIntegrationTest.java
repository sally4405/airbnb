package louie.dong.airbnb.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
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
class WishListIntegrationTest {

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
    void 위시리스트_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-wishlist-list", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/wishlists")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("", hasSize(2));
    }

    @Test
    void 위시리스트_저장() {
        Map<String, Long> content = Map.of(
            "memberId", 1L,
            "accommodationId", 1L);

        given(documentationSpec)
            .body(content)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("save-wishlist", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .patch("/wishlists")

            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void 위시리스트_삭제() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("delete-wishlist", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .delete("/wishlists/1")

            .then()
            .statusCode(HttpStatus.OK.value());
    }
}
