# Planet API
Este projeto tem por finalidade a construção de uma API para CRUD de planetas.

Requisitos
    Para executar o projeto é necessário as seguintes ferramentas:
- JDK 8+: Necessário para executar o projeto Java
- Maven: Necessário para realizar o build do projeto Java
- IDE (Eclipse/STS/VsCode) : Para desenvolvimento do projeto
- GIT

## Desenvolvimento
Para iniciar o desenvolvimento é necessário clonar o projeto do GitHub para uma pasta de sua preferência:

- cd "diretorio de sua preferencia"
- git clone https://github.com/JoseCleyton/planetApi.git

## Construção
Para construir o projeto com o Maven, executar os comando abaixo:
- mvn clean install

O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

## Features
- Criar Planetas
- Listar todos Planetas
- Buscar Planeta pelo ID
- Atualizar Planeta
- Deletar Planeta pelo ID

O projeto pode ser evoluido e utilizado como base para algum aprendizado.

## Configuração
Para executar o projeto, é necessário utilizar uma IDE de desenvolvimento, de preferência uma preparada para projetos Java como o Eclipse, NetBeans, STS, dentre outros, para que a mesma identifique as dependências necessárias para a execução no repositório .m2 do Maven. Uma vez importado o projeto, a IDE irá reconhecer a classe principal e está pronto para executar o projeto.

## Documentação
Para gerar a documentação da API basta executar o projeto e acessar a url:
 -  http://localhost:8080/swagger-ui.html

Nela terão todos os endpoints, parêmetros e retornos que a API possui.
## Testes
Para rodar os testes, utilize o comando abaixo:
- mvn test

## Contribuições

Contribuições são sempre bem-vindas! Para contribuir lembre-se sempre de adicionar testes unitários para as novas classes com a devida documentação.
ready, simply use the Dockerfile to
build the image.

## License
Não se aplica
