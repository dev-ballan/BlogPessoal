package com.generation.blgpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration 
public class SwaggerConfig {

	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto Blog Pessoal")
					.description("Projeto Blog Pessoal - Generation Brasil")
					.version("v0.0.1")
				.license(new License()
					.name("Generation Brasil")
					.url("https://brazil.generation.org/"))
				.contact(new Contact()
					.name("Allan Luís Baad")
					.url("https://www.linkedin.com/in/baadallan/")
					.email("munisallan@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github")
					.url("https://github.com/dev-ballan"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
	
}

//Linha 15: AAnotação (Annotation) @Configuration indica que a Classe é do tipo
//configuração, ou seja, define uma classe como fonte de definições de beans e é uma das
//anotações essenciais se você estiver usando a configuração baseada em Java.
//
//Linha 18: AAnotação @Bean utilizada em métodos de uma classe, geralmente marcada
//com @Configuration, indica ao Spring que ele deve invocar aquele método e gerenciar o
//objeto retornado por ele, ou seja, agora este objeto pode ser injetado em qualquer
//ponto da sua aplicação.
//
//Linha 20: Cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger
//utilizando a especificação OpenAPI.
//
//Linhas 21 a 24: Insere as informações sobre a API (Nome do projeto (Title), Descrição e
//Versão)
//
//Linhas 25 a 27: Insere as informações referentes a licença da API (Nome e Link)
//
//Linhas 28 a 31: Insere as informações de contato da pessoa Desenvolvedora (Nome, Site
//e e E-mail)
//
//Linhas 32 a 24: Insere as informações referentes a Documentações Externas (Github,
//Gitpage e etc), onde são informados o Nome e o Link.
//
//A Classe OpenApiCustomiser permite personalizar o Swagger, baseado na
//Especificação OpenAPI. O Método acima, personaliza todas as mensagens HTTP
//Responses (Respostas das requisições) do Swagger.
//
//Linha 40: Cria um Objeto da Classe OpenAPI, que gera a documentação no Swagger
//utilizando a especificação OpenAPI
//
//Linha 41: Cria um primeiro looping que fará a leitura de todos os recursos (Paths)
//através do Método getPaths(), que retorna o caminho de cada endpoint. Na sequência,
//cria um segundo looping que Identificará qual Método HTTP (Operations), está sendo
//executado em cada endpoint através do Método readOperations(). Para cada Método,
//todas as mensagens serão lidas e substituídas pelas novas mensagens.
//
//Linha 43: Cria um Objeto da Classe ApiResponses, que receberá as Respostas HTTP de
//cada endpoint (Paths) através do método getResponses().
//
//Linhas 44 a 51: Adiciona as novas Respostas no endpoint, substituindo as atuais e
//acrescentando as demais, através do Método addApiResponse(), identificadas pelo
//HTTP Status Code (200, 201 e etc).
//
//Linhas 57 a 61: O Método createApiResponse() adiciona uma descrição (Mensagem),
//em cada Resposta HTTP.
