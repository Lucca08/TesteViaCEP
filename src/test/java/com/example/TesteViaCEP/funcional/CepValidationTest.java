package com.example.TesteViaCEP.funcional;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.TesteViaCEP.Stubs.CepStub;
import com.example.TesteViaCEP.dto.CepDto;
import com.example.TesteViaCEP.util.BaseTest;

import net.datafaker.Faker;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

public class CepValidationTest extends BaseTest{


    @Test
    @DisplayName("Teste de CEP vazio")
    public void deveRetornar400QuandoCepNaoForInformado() {
        String cepVazio = "";

        given()
            .when()
                .get(viaCepEndpoint + cepVazio + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("ViaCEP 400")); 
    }

    @Test
    @DisplayName("Teste CEP válido")
    public void deveRetornar200QuandoCepForValidoComFaker() {
        Faker faker = new Faker(new Locale("pt-BR"));
        String cep = faker.address().zipCode();
       
        given()
            .when()
                .get(viaCepEndpoint + cep + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK);



    }

    @Test
    @DisplayName("Teste de CEP valido com Stub")
    public void deveRetornar200QuandoCepForValidoComStub() {
        String cep = "01001-000";
        
        CepDto response = given()
            .when()
                .get(viaCepEndpoint + cep + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(CepDto.class);

                
        assertEquals(response.getCep(), CepStub.CepStub().getCep());
        assertEquals(response.getLogradouro(), CepStub.CepStub().getLogradouro());
        assertEquals(response.getComplemento(), CepStub.CepStub().getComplemento());
        assertEquals(response.getBairro(), CepStub.CepStub().getBairro());
        assertEquals(response.getLocalidade(), CepStub.CepStub().getLocalidade());
        assertEquals(response.getUf(), CepStub.CepStub().getUf());
        assertEquals(response.getIbge(), CepStub.CepStub().getIbge());
        assertEquals(response.getGia(), CepStub.CepStub().getGia());
        assertEquals(response.getDdd(), CepStub.CepStub().getDdd());
        assertEquals(response.getSiafi(), CepStub.CepStub().getSiafi());
    }


    @ParameterizedTest
    @DisplayName("Teste de CEP inválido")
    @ValueSource(strings = {"000000000", "adfasdfasdf", "0000000", "0000000000", "!@#$%^&*, 95 01010"})
    public void deveRetornar400QuandoCepInvalido(String cepInvalido) {
        given()
            .when()
                .get(viaCepEndpoint + cepInvalido + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("ViaCEP 400")); 

    }

    @ParameterizedTest
    @DisplayName("Teste de CEP de Limite Mínimo e Máximo de Caracteres")
    @ValueSource(strings = {"0000000", "000000000", "0000000000", "00000000000"})
    public void deveRetornar400ParaCepsQuePassamDoLimite(String cepLimiteMinimoEMaximo) {
        given()
            .when()
                .get(viaCepEndpoint + cepLimiteMinimoEMaximo + "/json")
            .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body(containsString("ViaCEP 400")); 
    }

    @ParameterizedTest
    @DisplayName("Teste de CEP válidos com formatação")
    @ValueSource(strings = {"01001-000"})  
    public void deveRetornar200QuandoCepValidoEmFormatoCerto(String cepValidoFormatado) {
        given()
            .when()
                .get(viaCepEndpoint + cepValidoFormatado + "/json")
            .then()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("cep"))
                .body(containsString("logradouro"))
                .body(containsString("complemento"))
                .body(containsString("bairro"))
                .body(containsString("localidade"))
                .body(containsString("uf"))
                .body(containsString("ibge"))
                .body(containsString("gia"))
                .body(containsString("ddd"))
                .body(containsString("siafi"));
    }
}
