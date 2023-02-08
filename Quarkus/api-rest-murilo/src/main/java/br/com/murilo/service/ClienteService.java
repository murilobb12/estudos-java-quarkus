package br.com.murilo.service;

import br.com.murilo.dto.ClienteDto;
import br.com.murilo.model.Cliente;
import br.com.murilo.repository.ClienteRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    public List<Cliente> listCliente(){
        return clienteRepository.listAll();
    }

    public Cliente clienteById(Long id){

        Optional<Cliente> byIdOptional = Cliente.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado não existe na nossa base!");
        }

        return clienteRepository.findById(id);

    }


    @Transactional
    public Cliente saveCliente(ClienteDto dto) {

        Cliente cliente = new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());

        cliente.persist();

        return cliente;

    }


    @Transactional
    public void updateCliente(Long id, ClienteDto dto){

        Cliente cliente;

        Optional<Cliente> byIdOptional = Cliente.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado nao existe na nossa base.");
        }

        cliente = byIdOptional.get();

        cliente.setCpf(dto.getCpf());
        cliente.setNome(dto.getNome());

        cliente.persist();

    }

    @Transactional
    public void deleteCliente(Long id) {

        Cliente cliente;

        Optional<Cliente> byIdOptional = Cliente.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado não existe na nossa base.");
        }

        cliente = byIdOptional.get();

        cliente.delete();

    }
}
