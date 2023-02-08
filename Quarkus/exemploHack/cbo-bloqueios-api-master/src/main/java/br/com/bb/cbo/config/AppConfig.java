package br.com.bb.cbo.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        servers = @Server(
                description = "Descrição do seu servidor",
                url = "{host}",
                variables = {
                        @ServerVariable(name = "host",
                                description = "Host da aplicacao",
                                defaultValue = "http://localhost:8080",
                                enumeration = {"http://localhost:8080", "dev.teste.com.br", "hom.test.com.br"})}),
        info = @Info(
                title="Descriçao da minha aplicacao",
                version = "1.0-SNAPSHOT",
                contact = @Contact(
                        name = "Example API Support",
                        url = "http://exampleurl.com/contact",
                        email = "techsupport@example.com"))
)
public class AppConfig extends Application {}
