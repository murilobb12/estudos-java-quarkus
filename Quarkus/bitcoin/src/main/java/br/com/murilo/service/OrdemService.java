package br.com.murilo.service;

import br.com.murilo.model.Ordem;
import br.com.murilo.model.Usuario;
import br.com.murilo.repository.OrdemRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;
import java.security.Security;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrdemService {

    @Inject
    OrdemRepository ordemRepository;

    public void inserirOrdem(SecurityContext securityContext, Ordem ordem){
        Optional<Usuario> usuarioOptional = Usuario.findByIdOptional(ordem.getUserId());
        Usuario usuario = usuarioOptional.orElseThrow();

        if (!usuario.getUsername().equals(securityContext.getUserPrincipal().getName())){
            throw new RuntimeException("Usuario logado, diferente do userID");
        }
        ordem.setDatetime(LocalDateTime.now());
        ordem.setStatus("ENVIADA");
        ordemRepository.persist(ordem);


    }


    public List<Ordem> listAll() {

        return ordemRepository.listAll();

    }
}
