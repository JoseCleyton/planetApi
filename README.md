# Planet API
Este projeto tem por finalidade a construção de uma API para CRUD de planetas.

Requisitos
    Para executar o projeto é necessário as seguintes ferramentas:
- JDK 8+: Necessário para executar o projeto Java
- Maven: Necessário para realizar o build do projeto Java
- IDE (Eclipse/STS/Netbeans/VsCode) : Para desenvolvimento do projeto
- GIT

## Desenvolvimento
Para iniciar o desenvolvimento é necessário clonar o projeto do GitHub para uma pasta de sua preferência:

- cd "diretorio de sua preferencia"
- git clone https://github.com/JoseCleyton/planetApi.git

Crie um banco de dados [MongoDb](https://www.mongodb.com/pt-br/cloud/atlas/register), após isso copie a string de conexão e crie uma variável de ambiente chamada 
" MONGO_PLANETS_REPO ".
Agora o Spring estará apto a se conectar com o banco que dados. Se estiver com a IDE aberta, feche e abra novamente para que a IDE reconheça a variável.

## Construção
Para construir o projeto com o Maven, executar os comando abaixo:
- mvn clean install

O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

Para executar o jar gerado utilize o comando:
java -jar planetApi.jar

Executar utilizando a IDE:
Para executar o projeto, é necessário utilizar uma IDE de desenvolvimento, de preferência uma preparada para projetos Java como o Eclipse, NetBeans, STS, dentre outros, para que a mesma identifique as dependências necessárias para a execução no repositório .m2 do Maven. Uma vez importado o projeto, a IDE irá reconhecer a classe principal e está pronto para executar o projeto. Execute a classe principal e o Spring irá subir o servidor.

A API irá rodar na porta 8080.

## Features
- Criar Planetas
- Listar todos Planetas
- Buscar Planeta pelo ID
- Buscar Planeta pelo Nome
- Atualizar Planeta
- Deletar Planeta pelo ID

O projeto pode ser evoluido e utilizado como base para algum aprendizado.

## Documentação
Para gerar a documentação da API basta executar o projeto e acessar a url:
 -  http://localhost:8080/swagger-ui.html

Nela terão todos os endpoints, parêmetros e retornos que a API possui.

## Testes
Para rodar os testes, utilize o comando abaixo:
- mvn test

## Contribuições
Contribuições são sempre bem-vindas! Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação.

## License
Não se aplica
