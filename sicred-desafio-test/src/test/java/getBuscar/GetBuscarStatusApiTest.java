package getBuscar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetBuscarStatusApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void buscarEvalidarStatusAplicacao() {
        Response response = given()
                .when()
                .get("/test")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());
        assertEquals("ok", response.jsonPath().getString("status"));
        assertEquals("GET", response.jsonPath().getString("method"));
    }
}
