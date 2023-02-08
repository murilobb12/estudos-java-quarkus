package br.com.murilo.service;

import br.com.murilo.dto.ContaDto;
import br.com.murilo.model.Conta;
import br.com.murilo.repository.ContaRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ContaService {


    @Inject
    ContaRepository contaRepository;

    @Inject
    SaldoService saldoService;
    public List<Conta> listContas() {

        return contaRepository.listAll();

    }

    @Transactional
    public Conta saveContas(ContaDto dto) {

        Conta conta = new Conta();

        conta.setNumeroConta(dto.getNumeroConta());
        conta.setSaldo(dto.getSaldo());
        conta.setClienteId(dto.getClienteId());

        conta.persist();

        return conta;

    }

    public Conta listContasById(Long id) {

        Optional<PanacheEntityBase> byIdOptional = Conta.findByIdOptional(id);


        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado não existe na nossa base.");
        }

        return contaRepository.findById(id);

    }

    @Transactional
    public void updateConta(Long id, ContaDto dto) {

        Conta conta;

        Optional<Conta> byIdOptional = contaRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado não exista na nossa base.");
        }

        conta = byIdOptional.get();

        saldoService.sacar(conta, dto.getSaldo());

        conta.setSaldo(conta.getSaldo().subtract(dto.getSaldo()));


        conta.persist();

    }


    @Transactional
    public void deleteConta(Long id){


        Conta conta;

        Optional<Conta> byIdOptional = contaRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado nao existe na nossa base.");
        }

        conta = byIdOptional.get();

        conta.delete();

    }

    @Transactional
    public void sacarSaldo(Long id, BigDecimal valor){

        Conta conta;

        Optional<Conta> byIdOptional = contaRepository.findByIdOptional(id);

        if(byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado nao existe na nossa base.");
        }

        conta = byIdOptional.get();

        saldoService.sacar(conta, valor);

        conta.persist();


    }

    @Transactional
    public void depositarSaldo(Long id, BigDecimal valor){

        Conta conta;

        Optional<Conta> byIdOptional = contaRepository.findByIdOptional(id);

        if (byIdOptional.isEmpty()){
            throw new NullPointerException("ID informado nao existe na nossa base");
        }

        conta = byIdOptional.get();

        saldoService.depositar(conta, valor);

        conta.persist();

    }


}
