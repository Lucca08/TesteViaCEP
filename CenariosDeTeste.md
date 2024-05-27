## Testes de Contrato 

- CT001 -Deve Retornar 200 Quando Cep For Válido

- CT002 -Deve Retornar 400 Quando Cep For Inválido

- CT003 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

## Testes Funcionais 

- CT004 -Deve Retornar 400 Quando Cep Nao For Informado

- CT005 -Deve Retornar 200 Quando Cep For Valido Com Faker

- CT006 -Deve Retornar 200 Quando Cep For Valido Com Stub

- CT007 -Deve Retornar 400 Quando Cep Invalido

- CT008 -Deve Retornar 400 Para Ceps Que Passam Do Limite

- CT009 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

## Teste  de Performance 

- CT010 -Deve Responder Rapidamente Para Cep Valido

## Detalhamento 

- CT001 -Deve Retornar 200 Quando Cep For Válido

Descrição: Verificar se a API retorna um status
todos os campos necessarios para um retorno 200 (OK).

- CT002 -Deve Retornar 400 Quando Cep For Inválido

Descrição: Verificar se a API retorna um status 400
(Bad Request) CEP inválido é fornecido e CEP não cadastrado.

- CT003 - Deve Retornar 200 Quando Cep Valido Em Formato Certo

Descrição: Verificar se a API retorna um status 200 (OK) quando um CEP válido é fornecido no formato correto (com máscara).

- CT004 -Deve Retornar 400 Quando Cep Nao For Informado

Descrição: Verificar se a API retorna um status 400
(Bad Request) quando o CEP está vazio.

- CT005 -Deve Retornar 200 Quando Cep For Valido Com Faker

Descrição: Verificar se a API retorna um status 200(OK) 
quando um gerador de dados(Faker) passa um CEP.

- CT006 -Deve Retornar 200 Quando Cep For Valido Com Stub

Descrição: Verifica se a API retorna um status 200(OK) 
quando é passado um valor de CEP igual ao stub.

- CT007 -Deve Retornar 400 Quando Cep Invalido

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP inválido é fornecido.

- CT008 -Deve Retornar 400 Para Ceps Que Passam Do Limite

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP com menos ou mais caracteres 
do que o permitido é fornecido.

- CT009 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

Descrição: Verifica se a API retorna um status 200 (OK) quando 
um CEP válido é fornecido com formatação (máscara).

- CT010 -Deve Responder Rapidamente Para Cep Valido
 
Descrição: Verificar se a API responde rapidamente (dentro de um tempo aceitável) para um CEP válido.