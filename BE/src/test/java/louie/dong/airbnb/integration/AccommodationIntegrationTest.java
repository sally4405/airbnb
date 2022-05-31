package louie.dong.airbnb.integration;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
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
class AccommodationIntegrationTest {

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
    void 특정_지역의_숙소_요금_정보_조회() {
        given(documentationSpec)
            .urlEncodingEnabled(false)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-search-prices", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .get("/accommodations/prices?country=양재동")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("prices", hasSize(4))
            .body("average", equalTo(165556))
            .body("prices[0]", equalTo(12345))
            .body("prices[1]", equalTo(56780))
            .body("prices[2]", equalTo(12455))
            .body("prices[3]", equalTo(12350));
    }

    @Test
    void 입력값에_따른_모든_숙소_조회() {
        given(documentationSpec)
            .urlEncodingEnabled(false)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-accommodation-search", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .get("/accommodations?country=양재동&checkIn=2022-05-17&checkOut=2022-06-12&minPrice=10000&maxPrice=100000&guestCount=3")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("count", equalTo(300))
            .body("accommodations[0].id", equalTo(1))
            .body("accommodations[0].name", equalTo("숙소 이름"))
            .body("accommodations[0].imageUrl", equalTo("https://~~~"))
            .body("accommodations[0].rating", equalTo(4.8F))
            .body("accommodations[0].reviewCount", equalTo(127))
            .body("accommodations[0].price", equalTo(82953))
            .body("accommodations[0].totalPrice", equalTo(1493159))
            .body("accommodations[0].wishlist", equalTo(false))
            .body("accommodations[0].latitude", equalTo(32.12332561F))
            .body("accommodations[0].longitude", equalTo(107.21512421F))

            .body("accommodations[1].id", equalTo(2))
            .body("accommodations[1].name", equalTo("숙소 이름 2"))
            .body("accommodations[1].imageUrl", equalTo("https://~~~~~~~"))
            .body("accommodations[1].rating", equalTo(4.35F))
            .body("accommodations[1].reviewCount", equalTo(105))
            .body("accommodations[1].price", equalTo(333))
            .body("accommodations[1].totalPrice", equalTo(1333332))
            .body("accommodations[1].wishlist", equalTo(true))
            .body("accommodations[1].latitude", equalTo(32.33256112F))
            .body("accommodations[1].longitude", equalTo(107.33333321F));
    }

    @Test
    void 숙소_상세_정보_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-accommodation-detail", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .get("/accommodations/1")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("id", equalTo(1))
            .body("imageUrl", equalTo("https://~~~"))
            .body("rating", equalTo(4.80f))
            .body("reviewCount", equalTo(127))
            .body("country", equalTo("서초구, 서울, 한국"))
            .body("hostName", equalTo("Jong"))
            .body("hostImageUrl", equalTo("http://~~~~~~~~~"))
            .body("roomInformation.maxGuestCount", equalTo(3))
            .body("roomInformation.roomType", equalTo("APARTMENT"))
            .body("roomInformation.bedroomCount", equalTo(1))
            .body("roomInformation.bathroomCont", equalTo(1));
    }

    @Test
    void 숙소_예약요금_상세_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-accommodation-detail-price", preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())))

            .when()
            .get("/accommodations/1/detail-price?checkIn=2022-10-23&checkOut=2022-10-30&guestCount=3")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("price", equalTo(71466))
            .body("totalPrice", equalTo(1322396))
            .body("discountRate", equalTo(4))
            .body("discountPrice", equalTo(55948))
            .body("cleaningFee", equalTo(25996))
            .body("serviceFee", equalTo(8188))
            .body("accommodationFee", equalTo(819))
            .body("finalPrice", equalTo(1488195));
    }
}
