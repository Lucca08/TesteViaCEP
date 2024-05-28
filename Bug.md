## Bug

No cenário de teste de contrato para CEP inválido, 
foi identificado um bug. Ao utilizar o CEP
99999999, que está documentado no projeto como um 
exemplo de CEP inválido, a API retornou um status 
200 (OK). O comportamento esperado seria um status
400 (Bad Request), indicando que o CEP fornecido 
não é válido. Portanto, a resposta 200 é 
considerada um bug,pois a API não está tratando 
corretamente os CEPs inválidos conforme a 
documentação.

Mesmo sendo um bug em minha visão está documentado 
no projeto, mas não no github, e pela minha visao 
ainda considero ele como um bug.

## Passos para Reproduzir

1. Enviar uma requisição GET para `https://viacep.com.br/ws/99999999/json`
2. Observar que a resposta é 200 (OK) em vez de 400 (Bad Request)

## Comportamento Esperado

A API deve retornar um status 400 (Bad Request) para o CEP "99999999".

## Comportamento Atual

A API retorna um status 200 (OK) para o CEP "99999999".

## Captura de Tela / Log

![Log Do Bug](src/main/resources/img/Caso%20de%20Bug.png)