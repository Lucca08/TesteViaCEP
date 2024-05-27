package com.example.TesteViaCEP.contrato;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.TesteViaCEP.util.BaseTest;

import io.qameta.allure.Description;

public class TesteContratoCep extends BaseTest{

    @Test
    @Description("Testa contrato para CEP válido")
    @DisplayName("Teste de CEP válido")
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

    
    @Test
    @DisplayName("Teste de CEP válidos com formatação")
    public void deveRetornar200QuandoCepValidoEmFormatoCerto() {
        String cepValidoFormatado = "01001-000";
    
        given()
            .when()
                .get(viaCepEndpoint + cepValidoFormatado + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/exemplo-schema.json"))
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
    @ValueSource(strings = {"000000000", "1234", "95010A10","99999999"})
    public void deveRetornar400QuandoCepForInvalido(String cepInvalido) {
        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("ViaCEP 400")); 
    }

   

}
