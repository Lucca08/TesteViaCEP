
## Cenarios de Teste

## Testes de Contrato 

- CT001 -Deve Retornar 200 Quando Cep For Válido

- CT002 -Deve Retornar 400 Quando Cep For Inválido

- CT003 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

- CT004 -Deve Retornar 200 Quando Cep Não for Registrado(Caso De Bug)

## Testes Funcionais 

- CT005 -Deve Retornar 400 Quando Cep Nao For Informado

- CT006 -Deve Retornar 200 Quando Cep For Valido Com Faker

- CT007 -Deve Retornar 200 Quando Cep For Valido Com Stub

- CT008 -Deve Retornar 400 Quando Cep Invalido

- CT009 -Deve Retornar 400 Para Ceps Que Passam Do Limite

- CT0010 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

- CT0011 -Deve Retornar 200 Para Cep Pesquisado

- CT0012 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Logrador Inexistente

- CT0013 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Cidade Inexistente

- CT0014 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Uf Inexistente

## Teste  de Performance 

- CT0015 -Deve Responder Rapidamente Para Cep Valido

## Detalhamento 

- CT001 -Deve Retornar 200 Quando Cep For Válido

Descrição: Verificar se a API retorna um status
todos os campos necessarios para um retorno 200 (OK).

- CT002 -Deve Retornar 400 Quando Cep For Inválido

Descrição: Verificar se a API retorna um status 400
(Bad Request) CEP inválido é fornecido e CEP não cadastrado.

- CT003 - Deve Retornar 200 Quando Cep Valido Em Formato Certo

Descrição: Verificar se a API retorna um status 200 (OK) quando 
um CEP válido é fornecido no formato correto (com máscara).

- CT004 -Deve Retornar 200 Quando Cep Não for Registrado(Caso De Bug)

Descrição: Verificando como é a estrutura do bug

- CT005 -Deve Retornar 400 Quando Cep Nao For Informado

Descrição: Verificar se a API retorna um status 400
(Bad Request) quando o CEP está vazio.

- CT006 -Deve Retornar 200 Quando Cep For Valido Com Faker

Descrição: Verificar se a API retorna um status 200(OK) 
quando um gerador de dados(Faker) passa um CEP.

- CT007 -Deve Retornar 200 Quando Cep For Valido Com Stub

Descrição: Verifica se a API retorna um status 200(OK) 
quando é passado um valor de CEP igual ao stub.

- CT008 -Deve Retornar 400 Quando Cep Invalido

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP inválido é fornecido.

- CT009 -Deve Retornar 400 Para Ceps Que Passam Do Limite

Descrição: Verifica se a API retorna um status 400
(Bad Request) quando um CEP com menos ou mais caracteres 
do que o permitido é fornecido.

- CT0010 -Deve Retornar 200 Quando Cep Valido Em Formato Certo

Descrição: Verifica se a API retorna um status 200 (OK) quando 
um CEP válido é fornecido com formatação (máscara).

- CT0011 -Deve Retornar 200 Para Cep Pesquisado

Descrição: Verifica se a API retorna um status 200 (OK) e os 
dados corretos quando um CEP é pesquisado por endereço.

- CT0012 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Logrador Inexistente

Descrição: Verifica se a API retorna um status 400 (Bad Request) 
quando um endereço com logradouro inexistente é fornecido.

- CT0013 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Cidade Inexistente

Descrição: Verifica se a API retorna um status 400 (Bad Request)
quando um endereço com cidade inexistente é fornecido.

- CT0014 -Deve Retornar 400 Para Cep Pesquisado Por Endereco Com Uf Inexistente

Descrição: Verifica se a API retorna um status 400 (Bad Request) 
quando um endereço com UF inexistente é fornecido.

- CT0015 -Deve Responder Rapidamente Para Cep Valido
 
Descrição: Verificar se a API responde rapidamente (dentro de um tempo aceitável) para um CEP válido.