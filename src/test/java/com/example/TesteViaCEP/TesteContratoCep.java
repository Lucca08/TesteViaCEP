package com.example.TesteViaCEP;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasKey;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import io.qameta.allure.Description;

public class TesteContratoCep {
    
    private static final String viaCepEndpoint = "https://viacep.com.br/ws/";

    @Test
    @Description("Testa contrato para CEP válido")
    public void deveRetornar200QuandoCepForValido(){
        String cepValido = "01001000"; 
        
        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json") 
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas\\exemplo-schema.json"))
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

    @ParameterizedTest
    @Description("Testa contrato para CEP inválido")
    @ValueSource(strings = {"000000000", "99999999", "1234", "abcde123"})
    public void deveRetornar400QuandoCepForInvalido(String cepInvalido) {
        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
