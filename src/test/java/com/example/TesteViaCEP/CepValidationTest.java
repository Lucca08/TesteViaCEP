package com.example.TesteViaCEP;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.apache.http.HttpStatus;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TesteViaCEP.Stubs.CepStub;
import com.example.TesteViaCEP.dto.CepDto;


@SpringBootTest
public class CepValidationTest {

    @Value("${viacep.endpoint}")
    private String viaCepEndpoint;

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
    @DisplayName("Teste CEP valido")
    public void deveRetornar200QuandoCepForValido() {
        String cepValido = "01001000";
        CepDto cepEsperado = CepStub.CepStub();

        given()
            .when()
                .get(viaCepEndpoint + cepValido + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body("cep", equalTo(cepEsperado.getCep()))
                .body("logradouro", equalTo(cepEsperado.getLogradouro()))
                .body("complemento", equalTo(cepEsperado.getComplemento()))
                .body("bairro", equalTo(cepEsperado.getBairro()))
                .body("localidade", equalTo(cepEsperado.getLocalidade()))
                .body("uf", equalTo(cepEsperado.getUf()))
                .body("ibge", equalTo(cepEsperado.getIbge()))
                .body("gia", equalTo(cepEsperado.getGia()))
                .body("ddd", equalTo(cepEsperado.getDdd()))
                .body("siafi", equalTo(cepEsperado.getSiafi()));
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP inválido")
    @ValueSource(strings = {"000000000", "adfasdfasdf", "0000000", "0000000000", "!@#$%^&*"})
    public void deveRetornar400QuandoCepForInvalido(String cepInvalido) {

        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP de Limite Mínimo de Caracteres")
    @ValueSource(strings = {"0000000", "000000000", "0000000000, 00000000000"})
    public void deveriaRetornar400ParaCepsQuePassamDoLimite(String cepLimiteMinimoEMaximo) {
       

        given()
            .when()
                .get(viaCepEndpoint + cepLimiteMinimoEMaximo + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    @ParameterizedTest
    @DisplayName("Teste de CEPs válidos com formatação")
    @ValueSource(strings = {"01001-000", "12345-678"})  
    public void deveRetornar200QuandoCepValidoEmFormatoCerto(String cepValidoFormatado) {
        given()
            .when()
                .get(viaCepEndpoint + cepValidoFormatado + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK);
    }


}

