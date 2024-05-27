package com.example.TesteViaCEP.util;

import org.junit.jupiter.api.BeforeEach;

import io.restassured.RestAssured;

public class BaseTest {

    protected String viaCepEndpoint;

    
    @BeforeEach
    public void setup() {
         RestAssured.baseURI = "https://viacep.com.br/ws/";
         viaCepEndpoint = RestAssured.baseURI;
    }
}
