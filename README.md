# Spring Boot 3 - 2023

O curso ["Spring Boot 3 | Curso Completo 2023"](https://www.youtube.com/watch?v=wlYvA2b1BWI&t=4918s&ab_channel=MichelliBrito) tem o objetivo de construir uma API RESTful completa utilizando as versões mais recentes do Spring Boot 3, Spring Framework 6 e Java 17, seguindo os princípios do Modelo de Maturidade de Richardson. 

Durante o curso, a construção da API abrangerá todos os passos necessários. O projeto "Products API RESTful" será iniciado para explorar as funcionalidades e atualizações oferecidas pelo Spring Boot 3. A configuração inicial do projeto será realizada no Initializr, permitindo um início eficiente. 

Os tópicos abordados incluem:
- Conexão com o banco de dados Postgres
- Mapeamento da entidade "Product" usando o Spring Data JPA com o auxílio do JpaRepository
- Desenvolvimento do controlador responsável pela manipulação das requisições HTTP. 

Os principais métodos HTTP, como POST, GET, PUT e DELETE, serão explicados e implementados de acordo com a necessidade do projeto. O mapeamento de dados de entrada será explorado usando DTOs em conjunto com Records. Recursos de _HATEOAS_ serão introduzidos para enriquecer as respostas da API, permitindo a criação de hipermídias. 

Ao final do curso, são fornecidas sugestões para estudos adicionais e continuidade do aprendizado. O projeto desenvolvido durante o curso estará disponível no [GitHub](https://github.com/MichelliBrito/spring-boot-3-curso-completo-2023). 

## Requisitos

- Docker: 24.0.2v
- Docker Compose: 2.18.1v

## Instalação

1. Clone o repositório para o seu ambiente local:

```shell
git clone https://github.com/rafaelportomoura/course-spring-boot-3-2023.git
```

2. Acesse o diretório do projeto:

```shell
cd course-spring-boot-3-2023
```

3. Crie um arquivo `db.env` no diretório raiz do projeto com as seguintes variáveis de ambiente, substituindo o que tiver entre `{}`

```.env
POSTGRES_DB={nome_do_banco}
POSTGRES_USER={usuário}
POSTGRES_PASSWORD={senha}
```

4. Crie um arquivo `back.env` no diretório raiz do projeto com as seguintes variáveis de ambiente, substituindo o que tiver entre `{}`

```.env
DB_HOST=db
DB_PORT=5432
```

## Rodando o Projeto

1. Inicie o contêiner do PostgreSQL e o servidor Django executando o seguinte comando:

```shell
docker-compose up
```

Isso irá construir a imagem do Docker e iniciar o contêiner do PostgreSQL juntamente com o servidor Spring e o administrador do banco de dados.

2. O base url da api é [http://localhost:3000](http://localhost:3000).


## Referências

1. [Spring](https://spring.io/)
2. [Spring Boot 3 | Curso Completo 2023](https://www.youtube.com/watch?v=wlYvA2b1BWI&t=4918s&ab_channel=MichelliBrito)
3. [🐋 Maven](https://hub.docker.com/_/maven)
4. [🐋 Postgres](https://hub.docker.com/_/postgres)
5. [🐋 Adminer](https://hub.docker.com/_/adminer)
