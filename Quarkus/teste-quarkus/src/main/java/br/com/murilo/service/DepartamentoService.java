package br.com.murilo.service;

import br.com.murilo.dto.DepartamentoDto;
import br.com.murilo.dto.FuncionarioPorDepartamentoResponse;
import br.com.murilo.model.Departamento;
import br.com.murilo.model.Funcionario;
import br.com.murilo.repository.DepartamentoRepository;
import br.com.murilo.repository.FuncionarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartamentoService {


    @Inject
    DepartamentoRepository departamentoRepository;

    @Inject
    FuncionarioRepository funcionarioRepository;


    public List<Departamento> listarDep() {

        return departamentoRepository.listAll();

    }
    public List<Departamento> salvarDepList(List<DepartamentoDto> departamentoDto) {

        List<Departamento> departamentoList = new ArrayList<>();

        for (DepartamentoDto dto : departamentoDto) {

            departamentoList.add(new Departamento(dto.getNome()));

        }

        departamentoRepository.persist(departamentoList);

        return departamentoList;

    }



    public Optional<Departamento> listDepById(Long id) {

        Optional<Departamento> depByIdOptional = departamentoRepository.findByIdOptional(id);

        if (depByIdOptional.isPresent()) {
            return depByIdOptional;
        }
        throw new InvalidParameterException("ID não encontrado na nossa base");
    }

    public Departamento atualizarDep(Long id, DepartamentoDto departamentoDto) {

        Departamento depById = departamentoRepository.findById(id);

        depById.setNome(departamentoDto.getNome());

        departamentoRepository.persist(depById);

        return depById;

    }


    public void deletarDep(Long id) {

        Optional<Departamento> depByIdOptional = departamentoRepository.findByIdOptional(id);
        Departamento departamento = depByIdOptional.orElseThrow(NotFoundException::new);

        departamentoRepository.delete(departamento);

    }

    public DepartamentoDto salvarDep(DepartamentoDto departamentoDto) {
        Departamento departamento = new Departamento();

        departamento.setNome(departamentoDto.getNome());

        departamentoRepository.persist(departamento);

        return departamentoDto;

    }

    public FuncionarioPorDepartamentoResponse funcionariosPorDepartamento(Long id){

        Optional<Departamento> byIdOptional = departamentoRepository.findByIdOptional(id);
        Departamento departamento = byIdOptional.orElseThrow(NotFoundException::new);

        PanacheQuery<Funcionario> dep_id = funcionarioRepository.find("dep_id", departamento.getId());

        int quantidadeFuncionarios = dep_id.list().size();

        FuncionarioPorDepartamentoResponse funcionarioPorDepartamentoResponse = new FuncionarioPorDepartamentoResponse();

        funcionarioPorDepartamentoResponse.setNomeDepartamento(departamento.getNome());
        funcionarioPorDepartamentoResponse.setCount(quantidadeFuncionarios);

        return funcionarioPorDepartamentoResponse;

    }


}
