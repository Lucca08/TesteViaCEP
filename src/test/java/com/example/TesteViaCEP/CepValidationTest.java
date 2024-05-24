package com.example.TesteViaCEP;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.TesteViaCEP.Stubs.CepStub;
import com.example.TesteViaCEP.dto.CepDto;
import com.github.javafaker.Faker;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CepValidationTest {

    private static final String viaCepEndpoint = "https://viacep.com.br/ws/";

    @Test
    @DisplayName("Teste de CEP vazio")
    public void deveRetornar400QuandoCepNaoForInformado() {
        String cepVazio = "";

        given()
            .when()
                .get(viaCepEndpoint + cepVazio + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Teste CEP válido")
    public void deveRetornar200QuandoCepForValido() {
        Faker faker = new Faker();
        String cepValido = faker.address().zipCode().replace("-", "");
        CepDto cepEsperado = CepStub.CepStub(cepValido);

        CepDto cepRetornado = given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CepDto.class);

        assertEquals(cepEsperado, cepRetornado);
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP inválido")
    @ValueSource(strings = {"000000000", "adfasdfasdf", "0000000", "0000000000", "!@#$%^&*, 95 01010"})
    public void deveRetornar400QuandoCepInvalido(String cepInvalido) {
        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP de Limite Mínimo e Máximo de Caracteres")
    @ValueSource(strings = {"0000000", "000000000", "0000000000", "00000000000"})
    public void deveRetornar400ParaCepsQuePassamDoLimite(String cepLimiteMinimoEMaximo) {
        given()
            .when()
                .get(viaCepEndpoint + cepLimiteMinimoEMaximo + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP válidos com formatação")
    @ValueSource(strings = {"01001-000", "12345-678"})  
    public void deveRetornar200QuandoCepValidoEmFormatoCerto(String cepValidoFormatado) {
        given()
            .when()
                .get(viaCepEndpoint + cepValidoFormatado + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
