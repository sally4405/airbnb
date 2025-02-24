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
            .body("prices", hasSize(3))
            .body("average", equalTo(33333))
            .body("prices[0]", equalTo(50000))
            .body("prices[1]", equalTo(30000))
            .body("prices[2]", equalTo(20000));
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
            .body("count", equalTo(5))
            .body("accommodations[0].id", equalTo(1))
            .body("accommodations[0].name", equalTo("Spacious and Comfortable cozy house #1"))
            .body("accommodations[0].imageUrl", equalTo("https://s3-alpha-sig.figma.com/img/2b24/5101/2265996f97a70986d95f1d6fdd40009f?Expires=1655078400&Signature=D2aMRMQGvcfCORWvl9jrqgr6Rubf2044ovniztRQgTreOhAr8yzz~xQ7m0FHd1NC9DD0Z1C69F8XAODD44kfW9NDc~EHKhjdNzgBdgWZnYZRnB41avlSb2auh6ynKlaKD8yPs-St-sgrNy9QANbQguoDmDLixQXMRI1osY8zYNc4QPtUonohHD5Td71wqsLzMAWqSWsqWq5YquBmVG8iZ3nc9KjiujFSWhotD7bt0SpwoUl59w0WeuO~G~sSU7ns1FexmtPW8KYL~UIyiVXGGux23EqwOIIOZJRBWXkoSasdpczl0l4gwaXHDkpbFWSDE7lmc23B7waBAsF2I9SraA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"))
            .body("accommodations[0].rating", equalTo(4.8F))
            .body("accommodations[0].reviewCount", equalTo(127))
            .body("accommodations[0].price", equalTo(71466))
            .body("accommodations[0].totalPrice", equalTo(1858116))
            .body("accommodations[0].wishlist", equalTo(true))
            .body("accommodations[0].latitude", equalTo(37.49784F))
            .body("accommodations[0].longitude", equalTo(127.03029F))

            .body("accommodations[1].id", equalTo(2))
            .body("accommodations[1].name", equalTo("숙소 이름1"))
            .body("accommodations[1].imageUrl", equalTo("https://s3-alpha-sig.figma.com/img/6922/44b3/5c34d655bb2bab8f252f8742ee10b2c0?Expires=1655078400&Signature=fhXsW20hxN0uoDN899YKaWakMnhf6R0wzpatwbUNXYTo02mrK518vP7kNRE5P8rxN6-3Tawww7NQTFfB1cTU~cZRsR2Gfeuj~6oyEQIYEqERdjIKd6raeQaDR19bml-qUo3jDBGH2EubyzWtDeM0wMfPnaHOrG-XZOh1UGvWM921EuNI9b8AZ4CJ6KEE71SS7iq08HyyQscmkmrM2-c89HNqBH7keKXwfozKU6m2nMYiBe427qPxqP2IeKr0yU6K81fMHHuVM2gai5NKJ5vGpis74Omt4JcgQ1o5GuFfa9NbcBlAiI0rpeR2-aq1swK4gLuQp6IEdTJzmglpFSQy8Q__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"))
            .body("accommodations[1].rating", equalTo(4.80F))
            .body("accommodations[1].reviewCount", equalTo(40))
            .body("accommodations[1].price", equalTo(50000))
            .body("accommodations[1].totalPrice", equalTo(1300000))
            .body("accommodations[1].wishlist", equalTo(true))
            .body("accommodations[1].latitude", equalTo(37.49784F))
            .body("accommodations[1].longitude", equalTo(127.03029F))

            .body("accommodations[2].wishlist", equalTo(false));
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
            .body("imageUrl", equalTo("https://s3-alpha-sig.figma.com/img/2b24/5101/2265996f97a70986d95f1d6fdd40009f?Expires=1655078400&Signature=D2aMRMQGvcfCORWvl9jrqgr6Rubf2044ovniztRQgTreOhAr8yzz~xQ7m0FHd1NC9DD0Z1C69F8XAODD44kfW9NDc~EHKhjdNzgBdgWZnYZRnB41avlSb2auh6ynKlaKD8yPs-St-sgrNy9QANbQguoDmDLixQXMRI1osY8zYNc4QPtUonohHD5Td71wqsLzMAWqSWsqWq5YquBmVG8iZ3nc9KjiujFSWhotD7bt0SpwoUl59w0WeuO~G~sSU7ns1FexmtPW8KYL~UIyiVXGGux23EqwOIIOZJRBWXkoSasdpczl0l4gwaXHDkpbFWSDE7lmc23B7waBAsF2I9SraA__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"))
            .body("rating", equalTo(4.80f))
            .body("reviewCount", equalTo(127))
            .body("country", equalTo("서초구, 서울, 한국"))
            .body("hostName", equalTo("Jong"))
            .body("hostImageUrl", equalTo("https://user-images.githubusercontent.com/92966772/171337583-5428b133-eea4-4f02-9f27-7a53a414842f.png"))
            .body("roomInformation.maxGuestCount", equalTo(3))
            .body("roomInformation.roomType", equalTo("PENSION"))
            .body("roomInformation.bedroomCount", equalTo(1))
            .body("roomInformation.bathroomCount", equalTo(1));
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
            .body("totalPrice", equalTo(500262))
            .body("discountRate", equalTo(4))
            .body("discountPrice", equalTo(20010))
            .body("cleaningFee", equalTo(25996))
            .body("serviceFee", equalTo(8188))
            .body("accommodationFee", equalTo(819))
            .body("finalPrice", equalTo(515255));
    }
}
