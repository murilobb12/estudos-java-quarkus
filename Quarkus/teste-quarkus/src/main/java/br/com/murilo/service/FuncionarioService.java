package br.com.murilo.service;

import br.com.murilo.dto.FuncionarioDto;
import br.com.murilo.dto.FuncionarioIdadeNomeResponse;
import br.com.murilo.dto.FuncionarioPorDepartamentoResponse;
import br.com.murilo.model.Departamento;
import br.com.murilo.model.Funcionario;
import br.com.murilo.repository.DepartamentoRepository;
import br.com.murilo.repository.FuncionarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FuncionarioService {

    @Inject
    DepartamentoRepository departamentoRepository;

    @Inject
    FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionariosNamed(){


        return funcionarioRepository.listarFuncionario();

    }

    public List<FuncionarioIdadeNomeResponse> tratarListaFuncionarios(List<Funcionario> funcionarioList){

        List<FuncionarioIdadeNomeResponse> listFuncionariosTratada = new ArrayList<>();

        System.out.println(funcionarioList.size());


        for (int i = 0; i < funcionarioList.size(); i++) {
            listFuncionariosTratada.add(new FuncionarioIdadeNomeResponse());
            listFuncionariosTratada.get(i).setIdade(funcionarioList.get(i).getIdade());
            listFuncionariosTratada.get(i).setNome(funcionarioList.get(i).getNome());

        }
        return listFuncionariosTratada;

    }

//    public List<FuncionarioIdadeNomeResponse> listarFuncionariosTratados(){
//
//
//    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.listAll();
    }

    @Transactional
    public List<Funcionario> salvarListFuncionario(List<FuncionarioDto> funcionarioDto) {

        List<Funcionario> funcionarioList = new ArrayList<>();

        for (int i = 0; i < funcionarioDto.size(); i++) {
            Departamento getDepById = departamentoRepository.findById(funcionarioDto.get(i).getDepartamentoId());
            funcionarioList.add(new Funcionario());
            funcionarioList.get(i).setNome(funcionarioDto.get(i).getNome());
            funcionarioList.get(i).setIdade(funcionarioDto.get(i).getIdade());
            funcionarioList.get(i).setDepartamentoId(getDepById);
        }

        funcionarioRepository.persist(funcionarioList);

        return funcionarioList;
    }

    @Transactional
    public Funcionario salvarFuncionario(FuncionarioDto funcionarioDto) {

        Funcionario funcionario = new Funcionario();

        Departamento departamento = funcionarioDepartamentoId(funcionarioDto);

        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setIdade(funcionarioDto.getIdade());
        funcionario.setDepartamentoId(departamento);


        funcionarioRepository.persist(funcionario);

        return funcionario;
    }

    public Funcionario listarFuncionariosById(Long id) {

        Optional<Funcionario> fundionarioByIdOptional = funcionarioRepository.findByIdOptional(id);

        return fundionarioByIdOptional.orElseThrow(NotFoundException::new);

    }


    @Transactional
    public Funcionario atualizarFuncionario(Long id, FuncionarioDto funcionarioDto) {

        Optional<Funcionario> funcionarioByIdOptional = funcionarioRepository.findByIdOptional(id);
        Funcionario funcionario = funcionarioByIdOptional.orElseThrow(NotFoundException::new);

        Departamento departamento = funcionarioDepartamentoId(funcionarioDto);

        funcionario.setNome(funcionarioDto.getNome());
        funcionario.setIdade(funcionarioDto.getIdade());
        funcionario.setDepartamentoId(departamento);

        funcionarioRepository.persist(funcionario);

        return funcionario;
    }

    @Transactional
    public Funcionario deletarFuncionarioByID(Long id) {
        Optional<Funcionario> funcionarioByIdOptional = funcionarioRepository.findByIdOptional(id);
        Funcionario funcionario = funcionarioByIdOptional.orElseThrow(NotFoundException::new);

        funcionarioRepository.delete(funcionario);

        return funcionario;
    }

    public List<Funcionario> listarFuncionariosDepartamentoById(Long id) {

        Optional<Departamento> departamentoOptional = departamentoRepository.findByIdOptional(id);
        Departamento departamento = departamentoOptional.orElseThrow(NotFoundException::new);


        PanacheQuery<Funcionario> funcionarioPanacheQuery = funcionarioRepository.find("dep_id", departamento.getId());


        return funcionarioPanacheQuery.list();


    }


    private Departamento funcionarioDepartamentoId(FuncionarioDto funcionarioDto) {
        return departamentoRepository.findById(funcionarioDto.getDepartamentoId());
    }

}
