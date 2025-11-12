package getBuscar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetBuscarProdutoAuthTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void buscarProdutosComAutenticacao() {
        String loginBody = """
            { "username": "emilys", "password": "emilyspass" }
        """;

        Response loginResponse = given()
                .header("Content-Type", "application/json")
                .body(loginBody)
                .when()
                .post("/auth/login")
                .then()
                .extract()
                .response();

        String token = loginResponse.jsonPath().getString("accessToken");
        assertNotNull(token);

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/auth/products")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
    }

    @Test
    public void buscarProdutosComTokenInvalido() {
        String tokenInvalido = "token-falso-123";

        Response response = given()
                .header("Authorization", "Bearer " + tokenInvalido)
                .when()
                .get("/auth/products")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 401 || statusCode == 403);

        String message = response.jsonPath().getString("message");
        if (message == null) {
            message = response.jsonPath().getString("name");
        }
        assertNotNull(message);
    }
}