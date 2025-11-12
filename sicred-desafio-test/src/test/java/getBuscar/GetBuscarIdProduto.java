package getBuscar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBuscarIdProduto {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void validarProdutoExistente() {
        Response response = given()
                .pathParam("id", 1)
                .when()
                .get("/products/{id}")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .statusCode(200)
                .extract().response();

        Map<String, Object> produto = response.jsonPath().getMap("");
        assert produto.get("id") != null;
        assert produto.get("title") != null;
        assert produto.get("category") != null;

        List<String> images = (List<String>) produto.get("images");
        assert images != null && !images.isEmpty();
    }

    @Test
    public void validarProdutoInexistente() {
        given()
                .pathParam("id", 0)
                .when()
                .get("/products/{id}")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .statusCode(404)
                .body("message", equalTo("Product with id '0' not found"));
    }
}

