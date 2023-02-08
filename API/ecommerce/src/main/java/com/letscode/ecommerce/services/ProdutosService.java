package com.letscode.ecommerce.services;

import com.letscode.ecommerce.models.Produtos;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProdutosService {

    List<Produtos> listarProdutos();

    boolean novoProduto(Produtos produtos);

    boolean atualizarProduto(Produtos produtos);

    boolean removerProduto(long id);

    Produtos listarProdutoNamed(long id);
    
    Produtos listarProdutoJPQL(long id);
    
    Produtos listarProdutoJPA(long id);







}
