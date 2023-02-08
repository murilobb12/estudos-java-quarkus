package br.com.murilo.repository;

import br.com.murilo.model.Bitcoin;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BitcoinRepository implements PanacheRepository<Bitcoin> {

}
