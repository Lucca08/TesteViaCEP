package com.example.TesteViaCEP.Performance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import com.example.TesteViaCEP.util.BaseTest;

import io.qameta.allure.Description;

public class TestePerformance extends BaseTest{
    
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
