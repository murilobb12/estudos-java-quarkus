package br.com.murilo.model;


import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import io.quarkus.runtime.util.HashUtil;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@UserDefinition
@Entity //Representar que essa classe é uma tabela
public class Usuario extends PanacheEntityBase {


    @Id //Esse atributo é um ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Esse atributo é gerado automaticamente, de maneira identity
    private Long id;

    @NotEmpty //Não deixa que esse campo quando seja inserido ou alterado seja nulo ou vazio.
    private String nome;

    @NotNull //Não deixa que esse campo quando seja inserido ou alterado seja nulo ou vazio.
    private String cpf;


    @Username
    @NotNull //Não deixa que esse campo quando seja inserido ou alterado seja nulo ou vazio.
    private String username;

    @Password
    @NotNull //Não deixa que esse campo quando seja inserido ou alterado seja nulo ou vazio.
     private String password;

    @Roles
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getUsername() {
        return username;
    }

    @JsonbTransient
    public String getPassword() {
        return password;
    }

    //BLOCOS ABAIXO MOVIDOS PARA USUARIOSERVICE
//        public static void adicionar(Usuario usuario){
//        usuario.password = BcryptUtil.bcryptHash(usuario.password);
//        usuario.role = validaUsername(usuario.username);
//        usuario.persist();
//    }

//    private static String validaUsername(String username) {
//        if (username.equals("murilo1212")){
//            return "admin";
//        }return "user";
//    }


}
