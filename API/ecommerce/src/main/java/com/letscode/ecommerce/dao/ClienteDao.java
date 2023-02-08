package com.letscode.ecommerce.dao;

import com.letscode.ecommerce.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long> {


    Cliente findAllById(long id); //SELECT * FROM CLIENTE WHERE id = {id} or email = {email}
}
