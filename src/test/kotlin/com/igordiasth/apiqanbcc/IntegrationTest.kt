package com.igordiasth.apiqanbcc

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)

@SpringBootTest(
    classes = [com.igordiasth.apiqanbcc.ApiqanbccApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)

class IntegrationTest {

    @LocalServerPort
    private val port = 0
    var restTemplate = TestRestTemplate()
    var headers: HttpHeaders = HttpHeaders()

    fun whenCreateHotPeppers_ThenItMustBeCreatedSuccessfully(){

        val requestBody = "{\n" +
                "  \"brandName\": \"Karma Sauce\",\n" +
                "  \"sauceName\": \"Burn After Eating\",\n" +
                "  \"description\": \"Karma Sauce Burn After Eating Hot Sauce is imbued with a unique flavour thanks to green mango,\n" +
                "                    ajwain and hing powder. Forged with a top-secret blend of super hots that may or may not include\n" +
                "                    Bhut Jolokia (Ghost), Scorpion, Carolina Reaper, 7-Pot Brown and 7-Pot Primo. This isn’t a sauce you\n" +
                "                    eat, it’s one you survive.\"\n" +
                "  \"url\": \"https://www.saucemania.com.au/karma-sauce-burn-after-eating-hot-sauce-148ml/\",\n" +
                "  \"heat\": \"669_000\",\n" +
                "}"

        val expectedRequest = "{\n" +
                "  \"brandName\": \"Karma Sauce\",\n" +
                "  \"sauceName\": \"Burn After Eating\",\n" +
                "  \"description\": \"Karma Sauce Burn After Eating Hot Sauce is imbued with a unique flavour thanks to green mango,\n" +
                "                    ajwain and hing powder. Forged with a top-secret blend of super hots that may or may not include\n" +
                "                    Bhut Jolokia (Ghost), Scorpion, Carolina Reaper, 7-Pot Brown and 7-Pot Primo. This isn’t a sauce you\n" +
                "                    eat, it’s one you survive.\"\n" +
                "  \"url\": \"https://www.saucemania.com.au/karma-sauce-burn-after-eating-hot-sauce-148ml/\",\n" +
                "  \"heat\": \"669_000\",\n" +
                "}"

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .`when`().post(createUrlWithPort("/api/hotpeppers"))
            .then()
            .statusCode(201)
            .assertThat().body(Matchers.containsString(expectedRequest))
    }

    private fun createUrlWithPort(uri: String): String {
        return "http://localhost:$port$uri"
    }
}