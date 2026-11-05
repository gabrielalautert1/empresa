# Empresa

Modelagem do domínio **Empresa** em Java, com testes unitários.
Projeto desenvolvido como avaliação da primeira etapa do programa de formação da Contmatic Phoenix.

## O que o projeto faz

- Modela as classes de negócio de uma empresa (dados cadastrais, endereço e auditoria)
- Valida CPF e CNPJ por meio de classes utilitárias
- Centraliza regras e mensagens de validação em classes de constantes
- Cobre as regras com testes unitários

## Tecnologias

- Java 17
- Maven
- JUnit 4

## Estrutura

```text
src/main/java/br/com/contmatic/model
├── constantes   → valores fixos e regras de validação (CPF, CNPJ, endereço, empresa, auditoria)
├── util         → validadores de CPF, CNPJ e regras gerais
└── Empresa.java, Auditoria.java...   → classes do domínio
```

## Como executar os testes

```bash
mvn test
```
