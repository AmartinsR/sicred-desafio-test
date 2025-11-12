package getBuscar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.notNullValue;

public class GetBuscarTodosProdutos {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void validarTodosProdutos() {
        Response response = given()
                .when()
                .get("/products?limit=30")
                .then()
                .statusCode(200)
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract().response();

        List<Map<String, Object>> produtos = response.jsonPath().getList("products");

        //Verificação de assert do Java verificando se os campos não são nulos
        produtos.forEach(p -> {
            assert p.get("id") != null;
            assert p.get("title") != null;
            assert p.get("category") != null;
            assert p.get("price") != null;
            assert p.get("stock") != null;
        });
    }

    @Test
    public void validarProdutosCategoria() {
        given()
                .queryParam("limit", 30)
                .when()
                .get("/products")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .statusCode(200)
                .body("products.category", everyItem(notNullValue()));
    }
}