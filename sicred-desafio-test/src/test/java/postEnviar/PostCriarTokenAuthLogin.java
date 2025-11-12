package postEnviar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class PostCriarTokenAuthLogin {

    private static String accessToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void autenticarUsuarioERetornarToken() {
        String requestBody = """
            {
                "username": "emilys",
                "password": "emilyspass"
            }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/auth/login")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        Map<String, Object> json = response.jsonPath().getMap("");
        assertEquals("emilys", json.get("username"));
        assertTrue(json.containsKey("accessToken"));
        assertTrue(json.containsKey("refreshToken"));

        accessToken = (String) json.get("accessToken");
        assertNotNull(accessToken);//retorno não pode vir vazio
    }
}