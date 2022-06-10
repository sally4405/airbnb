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
class BookIntegrationTest {

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
    void 예약_저장() {
        Map<String, Object> content = Map.of(
            "accommodationId", 1L,
            "checkInDate", "2022-03-05",
            "checkOutDate", "2022-03-11",
            "guestCount", 3
        );

        given(documentationSpec)
            .body(content)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("save-books", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .post("/books")

            .then()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void 예약_목록_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-books-list", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/books")

            .then()
            .statusCode(HttpStatus.OK.value())
            .body("", hasSize(5));
    }

    @Test
    void 예약_상세_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-books-detail", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/books/1")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("bookId", equalTo(1))
            .body("accommodationImageUrls", hasSize(1))
            .body("checkIn", equalTo("2022-03-05T16:00:00"))
            .body("checkOut", equalTo("2022-03-11T12:00:00"))
            .body("accommodationCountry", equalTo("서초구, 서울, 한국"))
            .body("accommodationName", equalTo("Spacious and Comfortable cozy house #1"))
            .body("guestCount", equalTo(2))
            .body("roomType", equalTo("PENSION"))
            .body("hostName", equalTo("Jong"))
            .body("finalPrice", equalTo(393793));
    }

    @Test
    void 예약_취소() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("cancel-books", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .delete("/books/1")

            .then()
            .statusCode(HttpStatus.OK.value());
    }
}
