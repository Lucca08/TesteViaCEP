package com.example.TesteViaCEP;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


@SpringBootTest
public class CepValidationTest {

    @Value("${viacep.endpoint}")
    private String viaCepEndpoint;

    @Test 
    public void testCepVazio() {
        String cepVazio = "";

        given()
            .when()
                .get(viaCepEndpoint + cepVazio + "/json")
            .then()
                .statusCode(400);
    }

    @Test
    public void testContratoCepValido(){
        String cepValido = "01001000"; 
        
        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json") 
            .then()
                .statusCode(200)
                .body("$", hasKey("cep"))
                .body("$", hasKey("logradouro"))
                .body("$", hasKey("complemento"))
                .body("$", hasKey("bairro"))
                .body("$", hasKey("localidade"))
                .body("$", hasKey("uf"))
                .body("$", hasKey("ibge"))
                .body("$", hasKey("gia"))
                .body("$", hasKey("ddd"))
                .body("$", hasKey("siafi"));       
    }

    

    @Test
    public void testContratoCepInvalido(){
        String cepInvalido = "00000000";

        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(200)
                .body("$", hasKey("erro"));
    }
    

    @Test
    public void testCepValido() {
        String cepValido = "01001000";

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
        String cepInvalido = "00000000";

        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(200)
                .body("erro", equalTo(true));
    }

    @Test
    public void exemploContratoValidar() {
        String cepValido = "01001000"; 

        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json") 
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas\\exemplo-schema.json"));
    }
    
}

