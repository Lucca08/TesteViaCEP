# TesteViaCep

## Descrição

Este projeto realiza testes automatizados utilizando RestAssured para validação de CEPs via o serviço ViaCEP.

## 1-Pré-requisitos 

- java 17

- Gradle 

- Git 


## 2-Clonar o Repositório

1. Clone o repositório:
    ```bash
    git clone https://github.com/Lucca08/TesteViaCEP.git
    cd TesteViaCEP
    ```

## 3-Dependências

- testImplementation 'io.rest-assured:rest-assured:5.3.2'
- implementation 'io.rest-assured:json-schema-validator:5.4.0'
- testImplementation 'org.junit.jupiter:junit-jupiter-api:5.11.0-M2'
- testImplementation 'org.junit.jupiter:junit-jupiter-params:5.8.1'
- testImplementation 'org.slf4j:slf4j-simple:1.7.36' 
- testImplementation 'org.junit.jupiter:junit-jupiter-engine:5.11.0-M2' 
- implementation 'net.datafaker:datafaker:2.2.2'

## 4-Cenários de teste

## Testes de Contrato 

1. CEP Válido
2. CEP Inválido 

## Testes 

3. CEP Vazio
4. CEP Válido Com Faker
5. CEP Válido com o Stub
6. CEP Inválido
7. CEP Limite Mínimo e Maximo
8. CEP Válido com Formatação

## Detalhamento 

1. Teste de Contrato: CEP Válido

Descrição: Verificar se a API retorna um status
todos os campos necessarios para um retorno 200 (OK).

2. Teste de Contrato: CEP Inválido

Descrição: Verificar se a API retorna um status 400
(Bad Request) CEP inválido é fornecido e CEP não cadastrado.

3. CEP Vazio

Descrição: Verificar se a API retorna um status 400
(Bad Request) quando o CEP está vazio.

4. CEP Válido com faker

Descrição: Verificar se a API retorna um status 200(OK) 
quando um gerador de dados(Faker) passa um CEP.

5. CEP Válido com o stub

Descrição: Verifica se a API retorna um status 200(OK) 
quando é passado um valor de CEP igual ao stub.

6. CEP Inválido

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP inválido é fornecido.

7. Teste de CEP com Limite Mínimo e Máximo de Caracteres

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP com menos ou mais caracteres 
do que o permitido é fornecido.

8. Teste de CEP Válido com Formatação

Descrição: Verifica se a API retorna um status 200 (OK) quando 
um CEP válido é fornecido com formatação (máscara).

## Bugs encontrados


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

## 5-Rode os testes 
1. Comando para rodar testes
    ```bash
    gradle test
    ```


## 6-Gerar Relatorio de Teste

1. Gere o relatório Allure:
    ```bash
    allure serve allure-results
    ```

## Relatório gerado pelo Allure:

![Imagem do Relatório](src/main/resources/img/Relatorio_Allure.png)