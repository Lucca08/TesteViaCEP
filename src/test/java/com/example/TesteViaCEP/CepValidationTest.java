package com.example.TesteViaCEP;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.checkerframework.checker.units.qual.s;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;


@SpringBootTest
public class CepValidationTest {

    @Value("${viacep.endpoint}")
    private String viaCepEndpoint;

    @Test
    @Description("Teste de CEP vazio")
    @Step("Teste de CEP vazio") 
    public void testCepVazio() {
        String cepVazio = "";

        given()
            .when()
                .get(viaCepEndpoint + cepVazio + "/json")
            .then()
                .statusCode(400);
    }

    @Test
    @Description("Teste de contrato CEP valido")
    @Step("Teste de contrato CEP valido")
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
    @Description("Teste de contrato CEP inválido")
    @Step("Teste de contrato CEP inválido")
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
    @Description("Teste CEP valido")
    @Step("Teste CEP valido")
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
    @Description("Teste de CEP inválido")
    @Step("Teste de CEP inválido")
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
    @Description("Teste de Contrato Validar")
    @Step("Teste de Contrato Validar")
    public void exemploContratoValidar() {
        String cepValido = "01001000"; 

        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json") 
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas\\exemplo-schema.json"));
    }

    @Test
    @Description("Teste de Contrato Invalidar")
    @Step("Teste de Contrato Invalidar")
    public void exemploContratoInvalidar() {
        String cepInvalido = "00000000";

        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas\\exemplo-schema.json"));
    }

    @Test
    @Description("Teste de CEP de Limite Mínimo de Caracteres")
    public void testDeLimiteMinimoDeCaracteres() {
        String cepLimiteMinimo = "0000000";

        given()
            .when()
                .get(viaCepEndpoint + cepLimiteMinimo + "/json")
            .then()
                .statusCode(400);
    }

    @Test
    @Description("Teste de CEP de Limite Máximo de Caracteres")
    @Step("Teste de CEP de Limite Máximo de Caracteres")
    public void testDeLimiteMaximoDeCaracteres() {
        String cepLimiteMaximo = "000000000";

        given()
            .when()
                .get(viaCepEndpoint + cepLimiteMaximo + "/json")
            .then()
                .statusCode(400);
    }

    @Test
    @Description("Teste de CEP de Caracteres Especiais")
    @Step("Teste de CEP de Caracteres Especiais")
    public void testDeCaracteresJaFormatados() {
        String cepCaracteresEspeciais = "00000-000";

        given()
            .when()
                .get(viaCepEndpoint + cepCaracteresEspeciais + "/json")
            .then()
                .statusCode(200);
    }

    @Test
    @Description("Teste de CEP Inexistente")
    @Step("Teste de CEP Inexistente")
    public void testDeCepInexistente() {
        String cepInexistente = "12345678";

        given()
            .when()
                .get(viaCepEndpoint + cepInexistente + "/json")
            .then()
                .statusCode(200)
                .body("erro", equalTo(true));
    }
    
}

