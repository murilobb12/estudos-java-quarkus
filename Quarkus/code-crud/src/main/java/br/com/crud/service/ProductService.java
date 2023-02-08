package br.com.crud.service;

import br.com.crud.dto.ProductDto;
import br.com.crud.model.Product;
import br.com.crud.repository.ProductRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;



    public List<Product> listProduct(){
        System.out.println("Produtos listados com sucesso!");
        return productRepository.listAll();
    }

    @Transactional
    public Product insertProduct(ProductDto dto){

        Product product = new Product();

        product.setNome(dto.getNome());

        product.setPrice(dto.getPrice());

        product.setStock(dto.getStock());

        product.persist();
        System.out.println("Produto inserido com sucesso!");

        return product;

    }

    @Transactional
    public void updateProduct(Long id, ProductDto dto){

        Product product;

        Optional<Product> byIdOptional = Product.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new NullPointerException("O ID informado não existe na nossa base.");
        }

        product = byIdOptional.get();

        product.setNome(dto.getNome());

        product.setPrice(dto.getPrice());

        product.setStock(dto.getStock());

        product.persist();
        System.out.println("Produto atualizado com sucesso!");

    }

    @Transactional
    public void deleteProduct(Long id){

        Optional<Product> byIdOptional = Product.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new NullPointerException("O ID informado não existe na nossa base.");
        }

        Product product = byIdOptional.get();

        product.delete();
        System.out.println("Produto deletado com sucesso!");

    }



}
