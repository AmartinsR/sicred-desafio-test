package postEnviar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostCriarProdutoTest {

    private static String accessToken;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";

        String loginBody = """
            { "username": "emilys", "password": "emilyspass" }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .body(loginBody)
                .when()
                .post("/auth/login")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        accessToken = response.jsonPath().getString("accessToken");
    }

    @Test
    public void criarProdutoAutenticacao() {
        String requestBody = """
        {
            "title": "Perfume Oil",
            "description": "Mega Discount, Impression of A...",
            "price": 13,
            "discountPercentage": 8.4,
            "rating": 4.26,
            "stock": 65,
            "brand": "Impression of Acqua Di Gio",
            "category": "fragrances",
            "thumbnail": "https://i.dummyjson.com/data/products/11/thumnail.jpg"
        }
        """;

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .post("/products/add")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        assertEquals(201, response.getStatusCode());
        assertEquals("Perfume Oil", response.jsonPath().getString("title"));
    }
}
