package com.example.TesteViaCEP;

import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

@SpringBootTest
public class CepValidationTest {

    @Value("${viacep.endpoint}")
    private String viaCepEndpoint;

    private String cepValido = "01001000"; 
    private String cepInvalido = "00000000";

    @Test
    public void testCepValido() {

        Response response = given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json") 
            .then()
                .statusCode(200)
                .extract()
                .response();

        response.then().assertThat()
            .body("cep", equalTo("01001-000"))
            .body("logradouro", equalTo("Praça da Sé"))
            .body("complemento", equalTo("lado ímpar"))
            .body("bairro", equalTo("Sé"))
            .body("localidade", equalTo("São Paulo"))
            .body("uf", equalTo("SP"))
            .body("ibge", equalTo("3550308"))
            .body("gia", equalTo("1004"))
            .body("ddd", equalTo("11"))
            .body("siafi", equalTo("7107"));
    }

    @Test
    public void testCepInvalido() {

        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(200)
                .body("erro", equalTo(true));
    }
}

