package com.example.TesteViaCEP.contrato;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThan;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.example.TesteViaCEP.util.BaseTest;

import io.qameta.allure.Description;

public class TesteContratoCep extends BaseTest{

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
    @ValueSource(strings = {"000000000", "1234", "95010A10","99999999"})
    public void deveRetornar400QuandoCepForInvalido(String cepInvalido) {
        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("ViaCEP 400")); 
    }

    @ParameterizedTest
    @Description("Testa contrato para CEP válidos de diferentes regiões")
    @ValueSource(strings = {"30140071", "20040020", "30110-012", "40010000"})
    public void deveRetornar200ParaCepValidoDeDiferentesRegioes(String cepValido) {
        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/exemplo-schema.json"));
    }

    @Test
    @Description("Teste de tempo de resposta para CEP válido")
    public void deveResponderRapidamenteParaCepValido() {
        String cepValido = "01001000";

        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(2000L)); 
    }
}
