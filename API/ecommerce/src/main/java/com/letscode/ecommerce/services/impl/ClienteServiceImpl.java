package com.letscode.ecommerce.services.impl;

import com.letscode.ecommerce.dao.ClienteDao;
import com.letscode.ecommerce.dto.ClienteDto;
import com.letscode.ecommerce.models.Cliente;
import com.letscode.ecommerce.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    public List<Cliente> listarTodosClientes(){
        return clienteDao.findAll();
    }

    //Usando o DTO pq nosso caso (de mentirinha), precisamos de algum trabalho nele antes de chegar a camada de persistencia
    public boolean novoCliente(ClienteDto clienteDto) {
        try {
            Cliente cliente = new Cliente(clienteDto.getNome(), clienteDto.getSobrenome(), clienteDto.getEmail(), clienteDto.getSexo(), clienteDto.getCpf());
            clienteDao.save(cliente);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean atualizarCliente(Cliente cliente) {
        try {
            clienteDao.save(cliente);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean removerCliente(long id) {
        try {
            clienteDao.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public Cliente listarCliente(long id) {
        return clienteDao.findAllById(id);

    }
}
