package br.com.crud.repository;

import br.com.crud.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ProductRepository implements PanacheRepository<Product> {
}
