# Objetivo
API crud com Spring Boot.

## Iniciando

- `git clone https://github.com/sergio-aliceral/crud.git`
- `cd crud`

## Pré-requisitos
- `mvn --version`<br>

Você deverá ver a indicação da versão do Maven instalada e a versão do JDK. Observe que o JDK é obrigatório, assim como a definição das variáveis de ambiente **JAVA_HOME** e **M2_HOME**.

## Limpar, compilar e empacotar
- `mvn clean install`<br>

Gera arquivo _crud-1.0.jar_ no diretório _target_.

## Executando a aplicação
- `java -jar target/crud-1.0.jar`<br>

Executa o aplicativo por meio do arquivo jar criado pelo comando `mvn clean install`, conforme comentado anteriormente.

### Endpoints

- Lista todos as pessoas: `GET` http://localhost:8080/pessoa
- Lista uma pessoa por id: `GET` http://localhost:8080/pessoa/{id} 
- Cadastra uma nova pessoa: `POST`http://localhost:8080/pessoa

```
  {
     "nome":"Sérgio Aliceral"
   }
```
 
- Atualiza os dados de uma pessoa: `PUT` http://localhost:8080/pessoa/{id}

```
{
  "nome":"Sérgio Aliceral"
}
``` 
 - Remove uma pessoa por id: `DELETE` http://localhost:8080/pessoa/{id} 
