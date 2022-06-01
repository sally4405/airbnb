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
class TravelIntegrationTest {

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
    void 인기_여행지_조회() {
        given(documentationSpec)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .filter(document("get-popular-destination", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))

            .when()
            .get("/travels/popular")

            .then()
            .statusCode(HttpStatus.OK.value())
            .assertThat()
            .body("", hasSize(8))
            .body("[0].name", equalTo("서울"))
            .body("[0].imageUrl", equalTo("https://s3-alpha-sig.figma.com/img/4ee4/e169/870b792e3a4ae88671095fad825a8ef0?Expires=1655078400&Signature=EIrSNxPaFvj-98axTpoBNQce68DaGCdUpbcYfeX5SOATzix31nECjaroPUKlfrgPxsPVPNpWpxFd21d9C7p6MrWLucqKM3A-DMFcBzDeawce2ZL3h7Pvh4YP1Y8D6XE98pVgnp23t6a4~ZR8efMDUs2h3JHbIJ4Y7QTY2Tf4eYhWLypBOrNcxkIUwMyg5IzZEQ0IGGCV10z336zie-c3bybqHB~BvL2BHJMh1jR6qNH-2IhJgZW5FWmkC3YUy8PqmDwpc4aKXw8~2bwps6-k0WOAbAvdg~zKsuElJhnr~bl8vrHmLCg1LMloqxnccVIPNvskhfDMMrEhPBWFcg2aFQ__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"))
            .body("[0].distance", equalTo("차로 30분 거리"))
            .body("[1].name", equalTo("광주"))
            .body("[1].imageUrl", equalTo("https://s3-alpha-sig.figma.com/img/d11b/fafa/041959faaab5d3d5bd0c98fc56ab6feb?Expires=1655078400&Signature=UVu97EeonTnwgVld1jInrE8CpEzktSbkkKBJuEyO47xUWSUKDkAZcuIRi1RGUIYuE4qt~Q9L2oUpJS03sqSNRPAZqbBG8qIuQt5Zdx705opWB2uw3yFuH8fRSsocbT6MmAOsRip3pNaGiC55Tf3VouO5hE-YYhC-7KtRBYhNuqM2-PCYbI~UlcqBJztv0Nbzp-~quisREFC37yS~u-rusJvqp675eyRWR8vOTWz0lZgpphWwjUCupbTzm7TvsinqKMQp90nx~evjleNmcEJxKwIT8Bb81LxYWXuJWVEoXatvZkmoOFP3kDaHdHC-SVrwxZRXdU3U0M-bfZ~WKPoyYg__&Key-Pair-Id=APKAINTVSUGEWH5XD5UA"))
            .body("[1].distance", equalTo("차로 4시간 거리"));
    }
}
