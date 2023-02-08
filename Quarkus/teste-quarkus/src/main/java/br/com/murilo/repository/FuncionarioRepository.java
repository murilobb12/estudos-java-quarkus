package br.com.murilo.repository;

import br.com.murilo.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {

    public List<Funcionario> listarFuncionario(){
        //Usar quando queremos abstrair a implementação da query
//        TypedQuery<Funcionario> funcionarios = getEntityManager().createQuery("SELECT f From Funcionario f", Funcionario.class);//

        //Usar isso quando queremos definir uma query nativa do sql
        Query funcionarios = getEntityManager().createNativeQuery("SELECT id, name, idade, dep_id From Funcionario where id = 1", Funcionario.class);

        return funcionarios.getResultList();


    }
}
