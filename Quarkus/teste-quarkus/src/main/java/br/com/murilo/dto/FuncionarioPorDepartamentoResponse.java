package br.com.murilo.dto;

import br.com.murilo.model.Funcionario;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@Data
public class FuncionarioPorDepartamentoResponse {

    private String nomeDepartamento;

    private Integer count;

}
