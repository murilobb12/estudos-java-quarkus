package br.com.murilo.service;

import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public List<Cliente> clienteListBasic(){
        return clienteRepository.clienteListBasic( );
    }

    public List<Cliente> clienteListNamed(){
       return clienteRepository.clienteListNamed();
    }

    public int atualizarCliente(Cliente cliente, Long id){
        return clienteRepository.atualizarCliente(cliente,id);
    }

    public int inserirCliente(List<Cliente> cliente){
        return clienteRepository.inserirCliente(cliente);
    }


    public int deletarCliente(Long id) {
        return clienteRepository.clienteDeleteById(id);
    }
}
