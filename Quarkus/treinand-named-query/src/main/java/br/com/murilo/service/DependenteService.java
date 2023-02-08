package br.com.murilo.service;

import br.com.murilo.model.Dependentes;
import br.com.murilo.repository.DependenteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class DependenteService {

    @Inject
    DependenteRepository dependenteRepository;

    public List<Dependentes> listarDependentes(){
        return dependenteRepository.listarDependentes();
    }

    public List<Dependentes> listarDependentesById(Long id) {
        return dependenteRepository.listarDependentesById(id);
    }
}
