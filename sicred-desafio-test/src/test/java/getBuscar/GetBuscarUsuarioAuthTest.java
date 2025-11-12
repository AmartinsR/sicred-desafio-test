package getBuscar;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetBuscarUsuarioAuthTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://dummyjson.com";
    }

    @Test
    public void buscarUsuariosRetornoSucesso() {
        Response response = given()
                .when()
                .get("/users")
                .then()
                .log().all()//Utilizando para visualizar as chamadas e respostas
                .extract()
                .response();

        assertEquals(200, response.getStatusCode());

        List<Map<String, Object>> users = response.jsonPath().getList("users");
        assertThat("A lista de usuários não deve retornar vazia", users, is(not(empty())));

        for (Map<String, Object> user : users) {
            assertThat(user.get("username").toString(), not(isEmptyString()));
            assertThat(user.get("password").toString(), not(isEmptyString()));
        }
    }
}
