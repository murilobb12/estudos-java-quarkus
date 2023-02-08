package br.com.murilo.service;

import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;
import io.vertx.codegen.type.ClassKind;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository;

    public int inserirCliente(Cliente cliente){
        return clienteRepository.inserirCliente(cliente);
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.listarClientes();
    }

    public Cliente listarClienteId(Long id){
        return clienteRepository.listarClienteId(id);
    }

    public int deletarClienteId(Long id){
        return clienteRepository.deletarClienteId(id);
    }

}
