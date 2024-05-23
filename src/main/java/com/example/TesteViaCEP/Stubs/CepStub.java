package com.example.TesteViaCEP.Stubs;

import com.example.TesteViaCEP.dto.CepDto;

public class CepStub {
    public static CepDto CepStub() {
        CepDto cepDto = new CepDto();
        cepDto.setCep("01001-000");
        cepDto.setLogradouro("Praça da Sé");
        cepDto.setComplemento("lado ímpar");
        cepDto.setBairro("Sé");
        cepDto.setLocalidade("São Paulo");
        cepDto.setUf("SP");
        cepDto.setIbge("3550308");
        cepDto.setGia("1004");
        cepDto.setDdd("11");
        cepDto.setSiafi("7107");
        return cepDto;
    }
}
