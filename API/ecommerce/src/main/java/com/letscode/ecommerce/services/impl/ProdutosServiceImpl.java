package com.letscode.ecommerce.services.impl;

import com.letscode.ecommerce.dao.ProdutosDao;
import com.letscode.ecommerce.models.Produtos;
import com.letscode.ecommerce.services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    ProdutosDao produtosDao;


    @Override
    public List<Produtos> listarProdutos() {
        return produtosDao.findAll();
    }

    @Override
    public boolean novoProduto(Produtos _produtos) {
        try{
            Produtos produtos = new Produtos(_produtos.getNome(), _produtos.getPreco(), _produtos.getDescricao());
            produtosDao.save(produtos);
            return true;
        }catch (Exception e){
            return false;

        }
    }

    @Override
    public boolean atualizarProduto(Produtos produtos) {
        try{
            produtosDao.save(produtos);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean removerProduto(long id) {
        produtosDao.deleteById(id);
        return true;
    }

    @Override
    public Produtos listarProdutoNamed(long id) {
        return produtosDao.findAllById(id);
    }

    @Override
    public Produtos listarProdutoJPQL(long id) {
        return produtosDao.findProductByID(id);
    }

    @Override
    public Produtos listarProdutoJPA(long id) {
        return produtosDao.findByProductId(id);
    }
}
